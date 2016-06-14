CREATE TABLE carreteras(
gid INTEGER PRIMARY KEY,
codigo FLOAT,
descripc VARCHAR,
tipo VARCHAR
);
SELECT addgeometrycolumn('carreteras', 'geom', 32616, 'MULTILINESTRING', 2);
SELECT CreateSpatialIndex('carreteras', 'geom');


CREATE TABLE curvas_nivel_10m(
gid INTEGER PRIMARY KEY,
id INTEGER,
elevacion INTEGER,
tipo VARCHAR
);
SELECT addgeometrycolumn('curvas_nivel_10m', 'geom', 32616, 'MULTILINESTRING', 2);
SELECT CreateSpatialIndex('curvas_nivel_10m', 'geom');


CREATE TABLE rios(
gid INTEGER PRIMARY KEY,
codigo INTEGER,
descripc VARCHAR
);
SELECT addgeometrycolumn('rios', 'geom', 32616, 'MULTILINESTRING', 2);
SELECT CreateSpatialIndex('rios', 'geom');


CREATE TABLE caserios_comunidades(
gid INTEGER PRIMARY KEY,
cod_caseri VARCHAR,
caserio VARCHAR,
sex_h FLOAT,
sex_m FLOAT,
ed_0a10 FLOAT,
ed_11a20 FLOAT,
ed_21a30 FLOAT,
ed_31a40 FLOAT,
ed_41a50 FLOAT,
ed_51a60 FLOAT,
ed_61a70 FLOAT,
ed_71a80 FLOAT,
ed_81a90 FLOAT,
ed_91a100 FLOAT,
ed__101 FLOAT,
dd_codigo FLOAT,
dm_codigo FLOAT,
da_codigo FLOAT,
dc_codigo FLOAT,
cod_muni VARCHAR,
cod_aldea VARCHAR,
pob_total FLOAT,
categoria VARCHAR,
municipio VARCHAR,
departamen VARCHAR,
aldea VARCHAR,
cod_dpto VARCHAR
);
SELECT addgeometrycolumn('caserios_comunidades', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('caserios_comunidades', 'geom');


CREATE TABLE oceano(
gid INTEGER PRIMARY KEY
);
SELECT addgeometrycolumn('oceano', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('oceano', 'geom');


CREATE TABLE paises_limitrofes(
gid INTEGER PRIMARY KEY,
pais VARCHAR,
codpais VARCHAR
);
SELECT addgeometrycolumn('paises_limitrofes', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('paises_limitrofes', 'geom');

