#!/bin/sh

DATABASE="$1"
BASE_TABLES='carreteras curvas_nivel_10m rios caserios_comunidades oceano paises_limitrofes'
LIMITES_TABLES='departamentos municipios cantones'
OUTFILE=/tmp/backup_base.sql
BASE_DATA=/tmp/98-base-data-nasmar.sql
LIMITES_DATA=/tmp/97-limites-administrativos-data-nasmar.sql

process_data() {
    OUTFILE=$LIMITES_DATA  # edit here
    TABLES=$LIMITES_TABLES # edit here
    echo 'BEGIN TRANSACTION;' > $OUTFILE
    echo '' >> $OUTFILE

    for t in $TABLES; do
        COLS=$(sqlite3 $DATABASE "pragma table_info(${t})" | grep -v '|gid|' | grep -vi '|pk_uid|' | grep -vi '|shape_leng|' | cut -d'|' -f2)
        SELECT_COLS=$(echo $COLS | sed -e 's/ /,/g')
        INSERT_COLS=$(echo $SELECT_COLS | sed -e 's/GEOMETRY/geom/') # solo una vez

        ## solo una vez
        if [[ ${t} == 'departamentos' ]]; then
            SELECT_COLS="cod, depto, geometry"
            INSERT_COLS="cdpto, dpto, geom"
        fi
        if [[ ${t} == 'municipios' ]]; then
            SELECT_COLS="cod_muni, nombre, geometry"
            INSERT_COLS="cod_munic, munic, geom"
        fi
        if [[ ${t} == 'cantones' ]]; then
            SELECT_COLS="cod_aldea, nombre, geometry"
            INSERT_COLS="cod_canton, canton, geom"
        fi
        echo $COLS
        echo $SELECT_COLS
        echo $INSERT_COLS
        echo -e ".mode insert ${t}\nselect ${SELECT_COLS} from ${t};\n" | sqlite3 $DATABASE | sed "s/^INSERT INTO ${t}/INSERT INTO ${t} (${INSERT_COLS})/" >> $OUTFILE
    done
    echo '' >> $OUTFILE
    echo 'COMMIT;' >> $OUTFILE
}

process_data
