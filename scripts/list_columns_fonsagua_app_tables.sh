#!/usr/bin/env bash

source read_file.sh
read_file_into_array table_list/fonsagua_app_tables.txt 'TABLES'

DATABASE="${1}"

TABLE_LIST=""
for table in "${TABLES[@]}"; do
    TABLE_LIST="${TABLE_LIST}'${table}', "
done
TABLE_LIST="${TABLE_LIST%, }"

spatialite -bail -silent "${DATABASE}" "SELECT
  m.name AS table_name, 
  p.cid AS col_id,
  p.name AS col_name,
  p.type AS col_type,
  p.pk AS col_is_pk,
  p.dflt_value AS col_default_val,
  p.[notnull] AS col_is_not_null
FROM sqlite_master m
LEFT OUTER JOIN pragma_table_info((m.name)) p
  ON m.name <> p.name
WHERE 
    m.type = 'table'
    -- do not list spatialite internal tables
    AND m.name NOT LIKE 'virts_geometry_columns%' AND m.name NOT LIKE 'views_geometry_columns%' AND m.name NOT LIKE 'idx_%' AND m.name NOT LIKE 'geometry_columns%' AND m.name NOT IN ('SpatialIndex', 'spatialite_history', 'spatial_ref_sys')
    -- do not list sqlite internal tables
    AND m.name NOT LIKE 'sqlite_%' AND m.name NOT IN ('sql_statements_log')
    AND m.name IN (${TABLE_LIST})
ORDER BY table_name, col_id
;"

# echo $TABLE_LIST
