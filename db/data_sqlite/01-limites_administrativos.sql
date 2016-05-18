CREATE TABLE departamentos (
       gid INTEGER PRIMARY KEY,
       cdpto VARCHAR(2),
       dpto VARCHAR
);

SELECT addgeometrycolumn('departamentos', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('departamentos', 'geom');


CREATE TABLE municipios (
       gid INTEGER PRIMARY KEY,
       cod_munic VARCHAR(4),
       munic VARCHAR
);

SELECT addgeometrycolumn('municipios', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('municipios', 'geom');


CREATE TABLE cantones (
       gid INTEGER PRIMARY KEY,
       cod_canton VARCHAR(6),
       canton VARCHAR
);

SELECT addgeometrycolumn('cantones', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('cantones', 'geom');


