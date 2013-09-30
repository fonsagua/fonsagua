CREATE SCHEMA limites_administrativos;
ALTER SCHEMA limites_administrativos OWNER TO fonsagua;

CREATE TABLE limites_administrativos.departamentos (
       gid SERIAL PRIMARY KEY,
       cdpto VARCHAR(2),
       dpto VARCHAR
);

SELECT addgeometrycolumn('limites_administrativos', 'departamentos', 'geom', 32616, 'MULTIPOLYGON', 2);
CREATE INDEX departamentos_geom_idx ON limites_administrativos.departamentos USING GIST(geom);
ALTER TABLE limites_administrativos.departamentos OWNER TO fonsagua;


CREATE TABLE limites_administrativos.municipios (
       gid SERIAL PRIMARY KEY,
       cod_munic VARCHAR(4),
       munic VARCHAR
);

SELECT addgeometrycolumn('limites_administrativos', 'municipios', 'geom', 32616, 'MULTIPOLYGON', 2);
CREATE INDEX municipios_geom_idx ON limites_administrativos.municipios USING GIST(geom);
ALTER TABLE limites_administrativos.municipios OWNER TO fonsagua;


CREATE TABLE limites_administrativos.cantones (
       gid SERIAL PRIMARY KEY,
       cod_canton VARCHAR(6),
       canton VARCHAR
);

SELECT addgeometrycolumn('limites_administrativos', 'cantones', 'geom', 32616, 'MULTIPOLYGON', 2);
CREATE INDEX cantones_geom_idx ON limites_administrativos.cantones USING GIST(geom);
ALTER TABLE limites_administrativos.cantones OWNER TO fonsagua;



