#!/bin/bash

error() {
    echo $1
    exit -1
}

CARTOGRAFIA_BASE=/home/fpuga/Escritorio/cartolab/Fonsagua/BDD_Fonsagua/
output=/tmp/`date +%Y%m%d`-fonsagua-bbdd.sql

[ -d "$CARTOGRAFIA_BASE" ] || error "Falta el directorio de la cartografía base"

echo "ALTER SCHEMA public OWNER TO fonsagua" > $output
echo "ALTER TABLE public.geometry_columns OWNER TO fonsagua" >> $output
echo "ALTER TABLE public.spatial_ref_sys OWNER TO fonsagua" >> $output
echo "ALTER TABLE public.geography_columns OWNER TO fonsagua" >> $output
cat ./data/01-create-data-schemas.sql  > $output
cat ./data/02-domains.sql >> $output
cat ./data/03-comunidades.sql >> $output
cat ./data/04-comunidades_croquis.sql >> $output
cat ./data/05-relations.sql >> $output
cat ./data/06-triggers.sql >> $output

cat $CARTOGRAFIA_BASE/elle.sql >> $output
cat $CARTOGRAFIA_BASE/c_base.sql >> $output

echo "RESET search_path;" >> $output
echo "select populate_geometry_columns();" >> $output

echo "VACUUM ANALYZE;" >> $output

