SERVER=$1
DATABASE=$2

[ -z $SERVER ] && exit
[ -z $DATABASE ] && exit

createuser -h $SERVER -U postgres -SPDRl fonsagua
dropdb -h $SERVER -U postgres $DATABASE
createdb -h $SERVER -U postgres --owner fonsagua -T template_postgis $DATABASE
psql -h $SERVER -U postgres -d $DATABASE -c "ALTER SCHEMA public OWNER TO fonsagua"
psql -h $SERVER -U postgres -d $DATABASE -c "ALTER TABLE public.geometry_columns OWNER TO fonsagua"
psql -h $SERVER -U postgres -d $DATABASE -c "ALTER TABLE public.spatial_ref_sys OWNER TO fonsagua"