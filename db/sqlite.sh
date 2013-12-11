
DB_PATH=/home/fpuga/.gvSIG_conf/fonsagua_devel/fonsagua.sqlite

rm $DB_PATH

spatialite -bail $DB_PATH "SELECT InitSpatialMetaData();"

for file in `ls ./data_sqlite//*.sql` ; do
    echo $file
    spatialite -bail $DB_PATH < $file
done

exit 1
#ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nlt MULTIPOLYGON -nln departamentos -dsco SPATIALITE=YES -sql "select OGC_FID as gid, GEOMETRY as geom, cod as cdpto, depto as dpto from m1102vA001970_HN" $DB_PATH ./data_sqlite/datos/departamentos/m1102vA001970_HN.shp

# departamentos
ogr2ogr -append -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nlt MULTIPOLYGON $DB_PATH ./data_sqlite/datos/departamentos/m1102vA001970_HN.shp

spatialite $DB_PATH "INSERT INTO departamentos SELECT ogc_fid, cod, depto, geometry from m1102vA001970_HN; DROP TABLE m1102vA001970_HN;"

# municipios
ogr2ogr -append -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nlt MULTIPOLYGON $DB_PATH ./data_sqlite/datos/municipios/m1103vA002001_HN.shp

spatialite $DB_PATH "INSERT INTO municipios SELECT ogc_fid, cod_muni, nombre, geometry from m1103vA002001_HN;DROP TABLE m1103vA002001_HN;"

# aldeas
ogr2ogr -append -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nlt MULTIPOLYGON $DB_PATH ./data_sqlite/datos/aldeas/m1104vA002001_HN.shp

spatialite $DB_PATH "INSERT INTO cantones SELECT ogc_fid, cod_aldea, nombre, geometry from m1104vA002001_HN; DROP TABLE m1104vA002001_HN;"

# ELLE
sqlite3 $DB_PATH "DELETE FROM _map; DELETE FROM _map_overview; DELETE FROM _map_style; DELETE FROM _map_overview_style"
pg_dump -t elle._map --data-only --inserts --no-tablespaces --no-privileges --no-owner -U fonsagua fonsagua_testing | tail -n +16 | head -n -4 | sed  "s/true/'true'/g" | sed "s/false/'false'/g" > /tmp/_map.sql
pg_dump -t elle._map_overview --data-only --inserts --no-tablespaces --no-privileges --no-owner -U fonsagua fonsagua_testing | tail -n +16 | head -n -4 > /tmp/_map_overview.sql
pg_dump -t elle._map_style --data-only --inserts --no-tablespaces --no-privileges --no-owner -U fonsagua fonsagua_testing | tail -n +16 | head -n -4 > /tmp/_map_style.sql
pg_dump -t elle._map_overview_style --data-only --inserts --no-tablespaces --no-privileges --no-owner -U fonsagua fonsagua_testing | tail -n +16 | head -n -4 > /tmp/_map_overview_style.sql
sqlite3 $DB_PATH < /tmp/_map.sql
sqlite3 $DB_PATH < /tmp/_map_overview.sql
sqlite3 $DB_PATH < /tmp/_map_style.sql
sqlite3 $DB_PATH < /tmp/_map_overview_style.sql

SHP=/var/tmp/110207_data_Marcovia/_DB/SHP/
# comunidades
ogr2ogr -append -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite $DB_PATH ${SHP}comunidades.shp