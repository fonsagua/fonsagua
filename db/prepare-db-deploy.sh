#!/bin/bash

error() {
    echo $1
    exit -1
}

dump_base_cartophy()  {
    pg_dump -n c_base -U fonsagua --no-owner -x  -f /tmp/c_base.sql $1
    pg_dump -n elle -U fonsagua --no-owner -x  -f /tmp/elle.sql $1
    pg_dump -n limites_administrativos -U fonsagua --no-owner -x  --data-only -f /tmp/limites_administrativos_data.sql $1
}

[ -z $1 ] || dump_base_cartophy $1

output=/tmp/`date +%Y%m%d`-fonsagua-bbdd.sql



CARTOGRAFIA_BASE=/tmp
[ -d "$CARTOGRAFIA_BASE" ] || error "Falta el directorio de la cartografía base"




echo "" > $output
# echo "ALTER SCHEMA public OWNER TO fonsagua" >> $output
# echo "ALTER TABLE public.geometry_columns OWNER TO fonsagua" >> $output
# echo "ALTER TABLE public.spatial_ref_sys OWNER TO fonsagua" >> $output

cat $CARTOGRAFIA_BASE/elle.sql >> $output || error
cat $CARTOGRAFIA_BASE/c_base.sql >> $output || error
cat ./data/01-create-data-schemas.sql >> $output || error
cat ./data/01-limites_administrativos.sql >> $output || error
cat ./data/02-domains.sql >> $output || error
cat ./data/03-comunidades.sql >> $output || error
cat ./data/04-comunidades_croquis.sql >> $output || error
cat ./data/05-relations.sql >> $output || error
cat ./data/06-triggers.sql >> $output || error
cat ./data/07-domains_alternatives.sql >> $output || error
cat ./data/08-data_alternatives.sql >> $output || error
cat ./data/09-helper-functions.sql >> $output || error
cat ./data/10-triggers-alt-fuentes.sql >> $output || error
cat ./data/11-triggers-alt-conexiones.sql >> $output || error
cat ./data/12-triggers-alt-tuberias.sql >> $output || error
cat ./data/13-triggers-alt-depositos.sql >> $output || error
cat ./data/14-triggers-alternativas.sql >> $output || error
cat ./data/15-triggers-alt-bombas.sql >> $output || error
cat ./data/16-preferencias_bombas.sql >> $output || error
cat ./data/17-preferencias_tuberias.sql >> $output || error
cat ./data/18-triggers-fuentes-implicadas.sql >> $output || error
cat ./data/19-triggers_preferencias.sql >> $output || error
cat $CARTOGRAFIA_BASE/limites_administrativos_data.sql >> $output || error




echo "RESET search_path;" >> $output
echo "select populate_geometry_columns();" >> $output

echo "VACUUM ANALYZE;" >> $output
