#!/bin/bash

CARTOGRAFIA_BASE=
output=/tmp/`date +%Y%m%d`-fonsagua-bbdd.sql

cat ./data/01-schemas-data.sql  > $output
cat ./data/02-domains.sql >> $output
cat ./data/03-comunidades.sql >> $output
cat ./data/04-comunidades_croquis.sql >> $output
cat ./data/05-relations.sql >> $output

cat $CARTOGRAFIA_BASE/elle.sql >> $output
cat $CARTOGRAFIA_BASE/c_base.sql >> $output

echo "RESET search_path;" >> $output
echo "select populate_geometry_columns();" >> $output

