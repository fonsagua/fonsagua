
DB_PATH=./fonsagua.sqlite

if [ -f "$DB_PATH" ] ; then
    rm $DB_PATH
fi

# Al usar el comando spatialite en lugar de sqlite no es necesario inicializar a mano el metadata
# spatialite -bail $DB_PATH "SELECT InitSpatialMetaData();"

echo "BEGIN TRANSACTION;" > /tmp/foo.sql
for file in `ls ./data_sqlite/*.sql` ; do
    echo $file
    cat $file >> /tmp/foo.sql
done
echo "COMMIT;" >> /tmp/foo.sql
spatialite -bail $DB_PATH < /tmp/foo.sql

cartografia_base() {
# departamentos
ogr2ogr -append -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nlt MULTIPOLYGON -dsco SPATIALITE=YES $DB_PATH ./data_sqlite/datos/departamento/m1102vA001970_HN.shp -gt 65536 --config OGR_SQLITE_CACHE 512
spatialite $DB_PATH "INSERT INTO departamentos SELECT ogc_fid, cod, depto, geometry from m1102vA001970_HN;"
spatialite $DB_PATH "SELECT DiscardGeometryColumn('m1102vA001970_HN', 'GEOMETRY'); DROP TABLE m1102vA001970_HN;"

# municipios
ogr2ogr -append -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nlt MULTIPOLYGON -dsco SPATIALITE=YES $DB_PATH ./data_sqlite/datos/municipio/m1103vA002001_HN.shp -gt 65536 --config OGR_SQLITE_CACHE 512
spatialite $DB_PATH "INSERT INTO municipios SELECT ogc_fid, cod_muni, nombre, geometry from m1103vA002001_HN; SELECT DiscardGeometryColumn('m1103vA002001_HN', 'GEOMETRY'); DROP TABLE m1103vA002001_HN;"

# aldeas
ogr2ogr -append -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nlt MULTIPOLYGON -dsco SPATIALITE=YES $DB_PATH ./data_sqlite/datos/aldea/m1104vA002001_HN.shp -gt 65536 --config OGR_SQLITE_CACHE 512
spatialite $DB_PATH "INSERT INTO cantones SELECT ogc_fid, cod_aldea, nombre, geometry from m1104vA002001_HN WHERE cod_depto IN ('06', '17'); SELECT DiscardGeometryColumn('m1104vA002001_HN', 'GEOMETRY'); DROP TABLE m1104vA002001_HN;"

# paises_limitrofes
# ogr2ogr -append -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln paises_limitrofes -nlt MULTIPOLYGON -dsco SPATIALITE=YES $DB_PATH ./data_sqlite/datos/paises_limitrofes/paises_vecinos.shp -gt 65536 --config OGR_SQLITE_CACHE 512

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


sqlite3 $DB_PATH "DELETE FROM _map WHERE nombre_capa IN ('cabecera_municipal', 'casas_cultura', 'cb_centros_educativos', 'cb_centros_salud', 'ciudades', 'cuencas', 'cb_fuentes', 'pozos', 'paises_limitrofes', 'resto_paises_mesoamerica', 'el_salvador', 'batimetria', 'areas_protegidas', 'bosques_pais');"
sqlite3 $DB_PATH "DELETE FROM _map_style WHERE nombre_capa IN ('cabecera_municipal', 'casas_cultura', 'cb_centros_educativos', 'cb_centros_salud', 'ciudades', 'cuencas', 'cb_fuentes', 'pozos', 'paises_limitrofes', 'resto_paises_mesoamerica', 'el_salvador', 'batimetria', 'areas_protegidas', 'bosques_pais');"



layer=carreteras
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt MULTILINESTRING $DB_PATH ./data_sqlite/datos/recortadas/${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=curvas_nivel_10m
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt MULTILINESTRING $DB_PATH ./data_sqlite/datos/recortadas/${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

layer=rios
ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt MULTILINESTRING $DB_PATH ./data_sqlite/datos/recortadas/${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512    
}


sqlite3 $DB_PATH "VACUUM;"


# function clip_and_2_spatialite {
# layer=carreteras
# ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt MULTILINESTRING -clipsrc ./data_sqlite/datos/choluteca_valle.shp $DB_PATH ./data_sqlite/datos/recortadas/${layer}.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

# layer=curvas_nivel_10m
# ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt MULTILINESTRING -clipsrc ./data_sqlite/datos/choluteca_valle.shp $DB_PATH ./data_sqlite/datos/curvas_nivel/r1201vL001970_HN.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

# layer=rios
# ogr2ogr -append -progress -s_srs EPSG:32616 -t_srs EPSG:32616 -f SQLite -dialect sqlite -nln $layer -nlt MULTILINESTRING -clipsrc ./data_sqlite/datos/choluteca_valle.shp $DB_PATH ./data_sqlite/datos/rios/m2301vL001970_HN.shp -dsco SPATIALITE=yes -gt 65536 --config OGR_SQLITE_CACHE 512

# sqlite3 $DB_PATH "VACUUM;"
# }
