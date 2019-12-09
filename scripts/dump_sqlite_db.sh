#!/usr/bin/env bash

# set -e

cd $(dirname ${0}) # TO IMPROVE

source read_file.sh
# http://stackoverflow.com/questions/75675/how-do-i-dump-the-data-of-some-sqlite3-tables/37296788
# http://stackoverflow.com/questions/4199850/sqlite-export-with-column-names

# Usage:
# bash dump_sqlite_db.sh PATH_TO.sqlite true|false true|false
# El primer true|false es si queremos excluir `true` el gid del dump
# El segundo true|false es si estamos haciendo un dump de una única bd `true`

EXCLUDE_GID=$2
ONLY_ONE_DUMP=$3

# Directorio donde se guardaran los fichero de salida. El contenido de este
# directorio será eliminado
OUTPUT_FOLDER=${4:-/tmp/sqlite_ouput}

# Esto truco es útil para cuando se quieren eliminar columnas de una tabla, y se
# hace un dump desde una base de datos que todavía tiene esas columnas.
declare -A IGNORE_COLUMNS_MAP
IGNORE_COLUMNS_MAP[comunidades]="veg_res_sup veg_ind_sup veg_agr_tip veg_agr_sup veg_for_tip"

# Los INSERT hay que generarlos "siempre" con las columnas. Si se confía en el orden
# por defecto puede pasar que estemos insertando en una base de datos que por
# ejemplo tenga las columnas en distinto orden e inserte en columnas incorrectas
insert_with_fields() {
    local DATABASE="${1}"
    for table in "${TABLES[@]}"; do
        if $EXCLUDE_GID; then
            COLS=$(sqlite3 "${DATABASE}" "pragma table_info(${table})" | grep -v '|gid|')
        else
            COLS=$(sqlite3 "${DATABASE}" "pragma table_info(${table})")
        fi

        IGNORE_COLUMNS=${IGNORE_COLUMNS_MAP[$table]}
        if [[ -n ${IGNORE_COLUMNS} ]]; then
            for ignore_column in ${IGNORE_COLUMNS}; do
                COLS=$(echo "${COLS}" | grep -v "|$ignore_column|")
            done
        fi

        COLS=$(echo "${COLS}" | cut -d'|' -f2)

        SELECT_COLS=$(echo $COLS | sed -e 's/ /, /g')
        INSERT_COLS=$SELECT_COLS

        # No se porqué está esto. En las pruebas realizadas no hubo problema
        # if [[ ${table} == 'fuentes_implicadas' ]] ; then
        #     continue
        # fi

        echo -e ".mode insert ${table}\nSELECT ${SELECT_COLS} FROM ${table};\n" | sqlite3 "${DATABASE}" | sed "s/^INSERT INTO ${table} VALUES/INSERT INTO ${table} (${INSERT_COLS}) VALUES /" > "${OUTPUT_FOLDER}"/${table}.sql
    done
}

insert_without_fields() {
    echo '' > "${OUTPUT_FOLDER}"/bar_commands.txt

    for table in "${TABLES[@]}"; do
        echo -e ".output '"${OUTPUT_FOLDER}"/$table.sql'\n.mode insert ${table}\nSELECT * FROM ${table};\n\n" >> "${OUTPUT_FOLDER}"/bar_commands.txt
    done

    # En este comando no se puede usar spatialite, porqué no genera bien los blobs
    # de las geometrías.
    sqlite3 $1 < /tmp/bar_commands.txt
}

dump_to_shp_dbf() {
    # Dump all tables in TABLES array to shp or dbf (if not geometry)
    # Usando spatialite_tool salen muchos errores
    local DATABASE="${1}"
    local OUTPUT_FOLDER="${2}"
    local GEOMETRY_COLUMN

    # uncomment only if you know what you are doing
    # rm -rf "${OUTPUT_FOLDER}"
    mkdir -p "${OUTPUT_FOLDER}"

    for table in "${TABLES[@]}"; do
        GEOMETRY_COLUMN=$(spatialite -silent -bail "${DATABASE}" "SELECT f_geometry_column FROM geometry_columns WHERE f_table_name = '$table';")
        if [[ -z "${GEOMETRY_COLUMN}" ]]; then
            spatialite -bail -silent "${DATABASE}" ".dumpdbf ${table} ${OUTPUT_FOLDER}/${table}.dbf UTF-8"
        else
            spatialite -bail -silent "${DATABASE}" ".dumpshp ${table} ${GEOMETRY_COLUMN} ${OUTPUT_FOLDER}/${table} UTF-8"
        fi
    done
}

layer_is_empty() {
    local FILE_PATH="${1}"
    local n_features
    n_features=$(ogrinfo -rl -so "${FILE_PATH}" | grep -o -E 'Feature Count: ([0-9]+)' | grep -o -E '[0-9]+')
    if [[ -z "${n_features}" ]]; then
        true
    else
        false
    fi
}

delete_shp() {
    # Given a file path like mi/path/mi_file[.shp] that can end in .shp or not
    # removes mi/path/mi_file.*
    local FILE_PATH="${1}"

    ACTUAL_EXT=${FILE_PATH:(-4)}
    EXPECTED_EXT=.shp
    [ "${ACTUAL_EXT,,}" = "${EXPECTED_EXT,,}" ] && FILE_PATH=${FILE_PATH%$ACTUAL_EXT}
    echo "When uncommnet the line these will be deleted: ${FILE_PATH}.*"
    rm "${FILE_PATH}.shp"
    rm "${FILE_PATH}.prj"
    rm "${FILE_PATH}.dbf"
    rm "${FILE_PATH}.shx"
    rm "${FILE_PATH}.cpg"
}

drop_emtpy_shp_dbf() {
    # Recursively finds all .shp files and delete them if they are empty
    # then searchs for all .dbf files and delete then if they are empty
    # It could be improved to avoid check shp-dbf files twice but it works
    local BASE_PATH="${1}"

    for f in $(find "${BASE_PATH}" -iname '*.shp'); do
        layer_is_empty "${f}" && echo "${f} is empty. Deleting." && delete_shp "${f}"
    done

    for f in $(find "${BASE_PATH}" -iname '*.dbf'); do
        layer_is_empty "${f}" && echo "${f} is empty. Deleting." && rm "${f}"
    done
}

rm -rf "${OUTPUT_FOLDER}"
mkdir -p "${OUTPUT_FOLDER}"

read_file_into_array table_list/fonsagua_app_tables.txt 'TABLES'
# declare -p TABLES  # test the array

insert_with_fields $1
# insert_without_fields $1

# Esta operación en dos fases es para gestionar las FK
# Podríamos lanzar al principio un `PRAGMA foreign_keys = OFF;` pero aumenta
# el riesgo de que haya incoherencias
EXCLUDE="comunidades abastecimientos fuentes r_abastecimientos_comunidades alternativas"
echo -e 'PRAGMA foreign_keys = ON;\n\nBEGIN;\n' > "${OUTPUT_FOLDER}"/bar.sql
for table in ${EXCLUDE}; do
    cat "${OUTPUT_FOLDER}"/$table.sql >> "${OUTPUT_FOLDER}"/bar.sql
    echo '' >> "${OUTPUT_FOLDER}"/bar.sql
done

for table in "${TABLES[@]}"; do
    if echo "${EXCLUDE}" | grep -w "${table}" > /dev/null; then
        continue
    fi

    # Un trigger al insertar en 'alternativas', inserta también en 'preferencias_disenho'
    # y 'presupuesto'. Cuando se hace un dump de una única base de datos esta solución
    # funciona. Pero si se quieren dumpear varias y restaurar en una sóla esto no funciona
    # habría que reordenar los 'insert' para dejar de últimos los insert en esas tablas
    # de todas las bd, y hacer primero los 'delete'. Como alternativa que es la que se
    # está usando en 'dump_fonsagua_tables_for_all_dbs.sh' es tirar los triggers
    # y restaurarlos luego
    if $ONLY_ONE_DUMP; then
        if [[ ${table} == 'preferencias_disenho' ]]; then
            echo '' >> "${OUTPUT_FOLDER}"/bar.sql
            echo 'DELETE FROM preferencias_disenho;' >> "${OUTPUT_FOLDER}"/bar.sql
            echo '' >> "${OUTPUT_FOLDER}"/bar.sql
        fi
        if [[ ${table} == 'presupuesto' ]]; then
            echo '' >> "${OUTPUT_FOLDER}"/bar.sql
            echo 'DELETE FROM presupuesto;' >> "${OUTPUT_FOLDER}"/bar.sql
            echo '' >> "${OUTPUT_FOLDER}"/bar.sql
        fi
    fi

    cat "${OUTPUT_FOLDER}"/${table}.sql >> "${OUTPUT_FOLDER}"/bar.sql
    echo '' >> "${OUTPUT_FOLDER}"/bar.sql
done
echo -e '\nCOMMIT;' >> "${OUTPUT_FOLDER}"/bar.sql

# dump_to_shp_dbf "${1}" "${OUTPUT_FOLDER}/shp_dbf/"
# drop_emtpy_shp_dbf "${OUTPUT_FOLDER}/shp_dbf/"
