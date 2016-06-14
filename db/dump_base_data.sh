#!/bin/sh

TABLES='carreteras curvas_nivel_10m rios caserios_comunidades oceano paises_limitrofes departamentos municipios cantones'
# honduras
OUTFILE=/tmp/backup_base.sql

echo 'BEGIN TRANSACTION;' > $OUTFILE
echo '' >> $OUTFILE

# Sólo una vez
spatialite $1 "update carreteras set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update curvas_nivel_10m set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update rios set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update equipamientos set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update puertos_aeropuertos set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update caserios_comunidades set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update areas_protegidas_2011 set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update usos_suelo_2003 set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update geometry_columns set srid=32616 where f_table_name='oceano';update oceano set geometry=st_geomfromtext(astext(geometry), 32616);"
# spatialite $1 "update geometry_columns set srid=32616 where f_table_name='honduras';update honduras set geometry=st_geomfromtext(astext(geometry), 32616);"
spatialite $1 "update paises_limitrofes set geometry=st_geomfromtext(astext(geometry), 32616);"


for t in $TABLES ; do
    COLS=`sqlite3 $1 "pragma table_info(${t})" | grep -v '|gid|' | cut -d'|' -f2`
    SELECT_COLS=`echo $COLS | sed -e 's/ /,/g'`
    INSERT_COLS=`echo $SELECT_COLS | sed -e 's/GEOMETRY/geom/'` # solo una vez

    ## solo una vez
    if [[ ${t} == 'departamentos' ]] ; then
	SELECT_COLS="cod, depto"
	INSERT_COLS="cdpto, dpto"
    fi
    if [[ ${t} == 'municipios' ]] ; then
	SELECT_COLS="cod_muni, nombre"
	INSERT_COLS="cod_munic, munic"
    fi
    if [[ ${t} == 'cantones' ]] ; then
	SELECT_COLS="cod_aldea, nombre"
	INSERT_COLS="cod_canton, canton"
    fi
    if [[ ${t} == 'oceano' ]] ; then
	continue
    fi

    echo -e ".mode insert ${t}\nselect ${SELECT_COLS} from ${t};\n" | sqlite3 $1 | sed "s/^INSERT INTO ${t}/INSERT INTO ${t} (${INSERT_COLS})/" >> $OUTFILE
done

echo '' >> $OUTFILE
echo 'COMMIT;' >> $OUTFILE
