
#!/bin/bash

error() {
    echo $1
    exit -1
}

CARTOGRAFIA_BASE=/tmp

output=/tmp/`date +%Y%m%d`-fonsagua-bbdd.sql

[ -d "$CARTOGRAFIA_BASE" ] || error "Falta el directorio de la cartografía base"

echo "" > $output
# echo "ALTER SCHEMA public OWNER TO fonsagua" >> $output
# echo "ALTER TABLE public.geometry_columns OWNER TO fonsagua" >> $output
# echo "ALTER TABLE public.spatial_ref_sys OWNER TO fonsagua" >> $output
cat $CARTOGRAFIA_BASE/elle.sql >> $output
cat $CARTOGRAFIA_BASE/c_base.sql >> $output
cat ./data/01-create-data-schemas.sql >> $output
cat ./data/02-domains.sql >> $output
cat ./data/03-comunidades.sql >> $output
cat ./data/04-comunidades_croquis.sql >> $output
cat ./data/05-relations.sql >> $output
cat ./data/06-triggers.sql >> $output
cat ./data/07-domains_alternatives.sql >> $output
cat ./data/08-data_alternatives.sql >> $output
# cat ./data/09-elle_alternatives.sql
cat ./data/10-helper-functions.sql >> $output
cat ./data/11-triggers-alt-fuentes.sql >> $output
cat ./data/12-triggers-alt-conexiones.sql >> $output
cat ./data/13-triggers-alt-tuberias.sql >> $output
cat ./data/14-triggers-alt-depositos.sql >> $output
cat ./data/15-triggers-alternativas.sql >> $output
cat ./data/16-triggers-alt-bombas.sql >> $output
cat ./data/17-preferencias_bombas.sql >> $output
cat ./data/18-preferencias_tuberias.sql >> $output




echo "RESET search_path;" >> $output
echo "select populate_geometry_columns();" >> $output

echo "VACUUM ANALYZE;" >> $output