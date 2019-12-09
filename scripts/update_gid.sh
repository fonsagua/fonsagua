#!/usr/bin/env bash

# Este script es temporal y se mantiene sólo como ejemplo. Se usó para actulizar
# los gid de las tablas en función del rango que le toca por municipio

cd $(dirname ${0}) # TO IMPROVE

update_gid_for_all_tables_in_database() {
    local DATABASE="${1}"
    local GID_INCREMENT="${2}"

    echo -e 'BEGIN;\n' > /tmp/foo.sql
    for table in "${TABLES_FILE[@]}"; do
        if echo "${TABLAS_SIN_GID}" | grep -vw "${table}" > /dev/null; then
            echo "UPDATE ${table} SET gid = ${GID_INCREMENT} + gid;" >> /tmp/foo.sql
        fi
    done
    echo -e 'COMMIT;' >> /tmp/foo.sql

    echo "Actualizando ${DATABASE} con ${GID_INCREMENT}"
    spatialite -silent -bail "${DATABASE}" < /tmp/foo.sql
}

update_gid_for_all_databases() {
    local BASE_PATH="${1}"
    local -n MUNICIPIOS="${2}"

    for mun in "${!MUNICIPIOS[@]}"; do
        bd="${BASE_PATH}/${mun}/fonsagua.sqlite"
        if [ ! -f ${bd} ]; then
            echo "No existe $bd"
            continue
        fi
        update_gid_for_all_tables_in_database "${bd}" ${municipios[$mun]}
        # sqlite3 "${bd}" "SELECT * FROM fuentes_implicadas"
    done
}

add_field_ano_pgirh() {
    local BASE_PATH="${1}"
    local -n MUNICIPIOS="${2}"

    for mun in "${!MUNICIPIOS[@]}"; do
        bd="${BASE_PATH}/${mun}/fonsagua.sqlite"
        if [ ! -f ${bd} ]; then
            echo "No existe $bd"
            continue
        fi
        echo "Añadiendo 'ano_pgirh' a ${bd}"
        sqlite3 "${bd}" "ALTER TABLE comunidades ADD COLUMN ano_pgirh INTEGER; DELETE FROM version; INSERT INTO version VALUES ('200320');"
    done
}

other_patches() {
    local BASE_PATH="${1}"
    # parches y workarounds específicos que podrán eliminarse en algún momento
}

source read_file.sh
read_file_into_array table_list/fonsagua_app_tables.txt 'TABLES_FILE'
TABLAS_SIN_GID="r_abastecimientos_fuentes r_abastecimientos_comunidades comunidades_croquis"

declare -A TABLES_CODES
for table in "${TABLES_FILE[@]}"; do
    k=$(echo $table | cut -d " " -f1)
    v=$(echo $table | cut -d " " -f2)
    TABLES_CODES[$k]=$v
done

# for mun in "${!TABLES_CODES[@]}"; do
#     echo $mun
#     echo ${TABLES_CODES[$mun]}
# done

BASE_PATH="${1}"

# Contiene los paths relativos a los directorios donde habrá un fichero
# 'fonsagua.sqlite' a procesar, y el inicio del rango de 'gid' con el que
# trabaja ese municipio
declare -A municipios
municipios[1912_amapala]=54000
municipios[1912_goascoran]=63000
municipios[1910_marcovia]=18000
municipios[1910_Nacaome]=48000
municipios[1912_namasigue]=24000
municipios[1912_sanLorenzo]=72000
municipios[200312_eltriunfo]=15000

update_gid_for_all_databases "${BASE_PATH}" 'municipios'
add_field_ano_pgirh "${BASE_PATH}" 'municipios'
other_patches "${BASE_PATH}"
