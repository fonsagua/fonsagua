#!/usr/bin/env bash

cd $(dirname ${0}) # TO IMPROVE

# Hace un dump en formato .sql de los datos de las tablas de fonsagua para cada
# base de datos del array declarativo de `municipios` y lo deja en una carpeta
# del tipo /tmp/sqlite_output/xxx/bar.sql
# Además coge la primera base de datos del array la copia a
# /tmp/sqlite_output/empty.sqlite y vacía los datos de fonsagua. Hace una copia
# de los triggers de fonsagua en /tmp/sqlite_output/fonsagua_app_tables_triggers.sql
# elimina los triggers de esa base de datos. Restaura los distintos dumps sobre esa
# base de datos y luego restaura los triggers

# TODO: Gestionar tabla de version

BASE_PATH="${1}"

if [ ! -d "${BASE_PATH}" ]; then
    echo "El directorio no existe"
    exit 1
fi

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

rm -f /tmp/sqlite_output/todas.sql
for mun in "${!municipios[@]}"; do
    bd="${BASE_PATH}/${mun}/fonsagua.sqlite"
    if [ ! -f ${bd} ]; then
        echo "No existe $bd"
        # continue
        exit
    fi
    bash dump_sqlite_db.sh "${bd}" false false "/tmp/sqlite_output/${mun}"

    # Deberíamos gestionar los BEGIN-COMMIT
    cat /tmp/sqlite_output/${mun}/bar.sql >> /tmp/sqlite_output/todas.sql
done

# ¿Como acceder al primero únicamente?
for mun in "${!municipios[@]}"; do
    bd="${BASE_PATH}/${mun}/fonsagua.sqlite"
    cp "${bd}" /tmp/sqlite_output/empty.sqlite
    break
done

spatialite -silent -bail /tmp/sqlite_output/empty.sqlite < empty_database.sql

SQLITE_FILE=/tmp/sqlite_output/empty.sqlite
CREATE_TRIGGER_SQL=/tmp/sqlite_output/fonsagua_app_tables_triggers.sql
DROP_TRIGGER_SQL=/tmp/sqlite_output/drop_fonsagua_app_tables_triggers.sql

echo -e "BEGIN;\n\n" > "${CREATE_TRIGGER_SQL}"
sqlite3 -bail "${SQLITE_FILE}" "SELECT sql || ';' FROM sqlite_master WHERE type = 'trigger' AND (name NOT LIKE 'gid_%' AND name NOT LIKE 'ggi_%' AND name NOT LIKE 'ggu_%' AND name NOT LIKE 'gii_%' AND name NOT LIKE 'giu_%' AND name NOT LIKE 'vwgcau_%' AND name NOT LIKE 'vtgcau_%' AND name NOT LIKE 'gcau_%' AND name NOT LIKE 'geometry_columns_%' AND name NOT LIKE 'gcfi_%' AND name NOT LIKE 'gctm_%' AND name NOT LIKE 'vtgcfi_%' AND name NOT LIKE 'vwgcfi_%' AND name NOT LIKE 'vtgcs_%' AND name NOT LIKE 'vwgc_%' AND name NOT LIKE 'vtgc_%' AND name NOT LIKE 'gcs_%');" >> "${CREATE_TRIGGER_SQL}"
echo -e "\n\nCOMMIT;" >> "${CREATE_TRIGGER_SQL}"

echo -e "BEGIN;\n\n" > "${DROP_TRIGGER_SQL}"
sqlite3 -bail "${SQLITE_FILE}" "SELECT 'DROP TRIGGER ' || name || ';' FROM sqlite_master WHERE type = 'trigger' AND (name NOT LIKE 'gid_%' AND name NOT LIKE 'ggi_%' AND name NOT LIKE 'ggu_%' AND name NOT LIKE 'gii_%' AND name NOT LIKE 'giu_%' AND name NOT LIKE 'vwgcau_%' AND name NOT LIKE 'vtgcau_%' AND name NOT LIKE 'gcau_%' AND name NOT LIKE 'geometry_columns_%' AND name NOT LIKE 'gcfi_%' AND name NOT LIKE 'gctm_%' AND name NOT LIKE 'vtgcfi_%' AND name NOT LIKE 'vwgcfi_%' AND name NOT LIKE 'vtgcs_%' AND name NOT LIKE 'vwgc_%' AND name NOT LIKE 'vtgc_%' AND name NOT LIKE 'gcs_%');" >> "${DROP_TRIGGER_SQL}"
echo -e "\n\nCOMMIT;" >> "${DROP_TRIGGER_SQL}"

sqlite3 -bail /"${SQLITE_FILE}" < "${DROP_TRIGGER_SQL}"

echo "Restaurando todas"
spatialite -silent -bail /"${SQLITE_FILE}" < /tmp/sqlite_output/todas.sql

sqlite3 -bail /"${SQLITE_FILE}" < "${CREATE_TRIGGER_SQL}"
