create-db() {
    $DROPDB -h $server -p $port -U $superuser $dbname;
    $DROPUSER -h $server -p $port -U $superuser $user
    $CREATEUSER -h $server -p $port -U $superuser -SPDRl $user
    $CREATEDB -h $server -p $port -U $superuser -T $template --owner $user $dbname;

    $PSQL -h $server -p $port -U $superuser $dbname -c \
    	"ALTER SCHEMA public OWNER TO $user; \
         ALTER TABLE public.geometry_columns OWNER TO $user; \
         ALTER TABLE public.spatial_ref_sys OWNER TO $user; \
         ALTER TABLE public.geography_columns OWNER TO $user;"
}