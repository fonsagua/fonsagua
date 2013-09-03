# Flow to use with this script
# 1) GIS technician prepares the base cartography (c_base schema) and
#    the maps (elle schema) in a local db in his computer
# 2) A backup of elle and c_base schema is done with pgadmin in custom format is done by the GIS technician.
# 3) DevOp restores the dump in his own computer, reasigns the ownership to fonsagua user and cleans the not needed maps of elle


createdb -h localhost -T template_postgis -U postgres --owner fonsagua fonsagua_c_base || exit -1

psql -h localhost -U postgres -d fonsagua_c_base -c \
    	"ALTER SCHEMA public OWNER TO fonsagua; \
         ALTER TABLE public.geometry_columns OWNER TO fonsagua; \
         ALTER TABLE public.spatial_ref_sys OWNER TO fonsagua; \
         ALTER TABLE public.geography_columns OWNER TO fonsagua;"

#pg_restore -U fonsagua -d fonsagua_c_base --no-owner /tmp/BDD_Fonsagua/fonsagua_elle.backup

# Contains c_base and elle
pg_restore -U fonsagua -d fonsagua_c_base --no-owner /tmp/BDD_Fonsagua/fonsagua_c_base.backup

psql -h localhost -U fonsagua -d fonsagua_c_base -c "DELETE FROM elle._map WHERE mapa NOT IN ('Vista alternativas', 'Vista general');"
psql -h localhost -U fonsagua -d fonsagua_c_base -c "DELETE FROM elle._map_overview WHERE mapa NOT IN ('Vista alternativas', 'Vista general');"
psql -h localhost -U fonsagua -d fonsagua_c_base -c "DELETE FROM elle._map_style WHERE nombre_estilo NOT IN ('Vista alternativas', 'Vista general');"
psql -h localhost -U fonsagua -d fonsagua_c_base -c "DELETE FROM elle._map_overview_style WHERE nombre_estilo NOT IN ('Vista alternativas', 'Vista general');"
psql -h localhost -U fonsagua -d fonsagua_c_base -c "select populate_geometry_columns();"

pg_dump -n c_base -U fonsagua --no-owner -x  -f /tmp/c_base.sql fonsagua_c_base
pg_dump -n elle -U fonsagua --no-owner -x  -f /tmp/elle.sql fonsagua_c_base