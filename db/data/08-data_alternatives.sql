RESET search_path;

CREATE TABLE fonsagua.alternativas (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       UNIQUE
	       NOT NULL,
       tipo_alternativa VARCHAR
	       REFERENCES dominios.tipo_alternativa(item),
       tipo_distribucion VARCHAR
	       REFERENCES dominios.tipo_distribucion(item),
       departamento VARCHAR
	       NOT NULL,
       municipio VARCHAR
	       NOT NULL,
       canton VARCHAR
	       NOT NULL,
       pobl_actual INTEGER,
       pobl_futura INTEGER,
       n_cent_educativos INTEGER,
       dot_cent_educativos INTEGER,
       n_cent_salud INTEGER,
       dot_cent_salud INTEGER,
       n_cent_otros INTEGER,
       dot_cent_otros INTEGER,
       dot_sec_primario INTEGER,
       dot_sec_secundario INTEGER,
       dot_sec_terciario INTEGER,
       dem_poblacion INTEGER,
       dem_centros INTEGER,
       dem_econ INTEGER,
       demanda FLOAT,
       caudal_fuentes FLOAT

);

SELECT addgeometrycolumn('fonsagua', 'alternativas', 'geom', 32616, 'MULTIPOLYGON', 2);
CREATE INDEX ON fonsagua.alternativas USING GIST(geom);

ALTER TABLE fonsagua.alternativas OWNER TO fonsagua;

CREATE TABLE fonsagua.comunidades_implicadas (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
       comunidad VARCHAR,
       n_habitantes INTEGER,
       n_hab_alternativa INTEGER

);


ALTER TABLE fonsagua.comunidades_implicadas OWNER TO fonsagua;

CREATE TABLE fonsagua.fuentes_implicadas (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
       fuente VARCHAR,
       tipo_fuente VARCHAR
	       NOT NULL
	       REFERENCES dominios.tipo_fuente(item),
       aforo NUMERIC(5,2),
       q_ecol NUMERIC(5,2),
       q_usar NUMERIC(5,2)

);


ALTER TABLE fonsagua.fuentes_implicadas OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_fuentes (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL,
       fuente VARCHAR,
       cod_fuente VARCHAR,
       tipo_fuente VARCHAR
	       REFERENCES dominios.tipo_fuente(item),
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       aforo NUMERIC(5,2),
       q_ecologico NUMERIC(5,2),
       q_usar NUMERIC(5,2),
       q_calculo NUMERIC(5,2),
       altura NUMERIC(5,2),
       presion NUMERIC(5,2),
       altura_total NUMERIC(5,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_fuentes', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_fuentes USING GIST(geom);

ALTER TABLE fonsagua.alt_fuentes OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_embalses (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL,
       embalse VARCHAR,
       cod_embalse VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       aforo NUMERIC(5,2),
       q_usar NUMERIC(5,2),
       altura NUMERIC(5,2),
       caudal NUMERIC(5,2),
       altura_total NUMERIC(5,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_embalses', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_embalses USING GIST(geom);

ALTER TABLE fonsagua.alt_embalses OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_conexiones (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL,
       cod_conexion VARCHAR,
       denominacion VARCHAR,
       cota NUMERIC(5,2),
       hab_conectados INTEGER,
       demanda NUMERIC(5,2),
       presion NUMERIC(5,2),
       altura_total NUMERIC(5,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_conexiones', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_conexiones USING GIST(geom);

ALTER TABLE fonsagua.alt_conexiones OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_tuberias (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL,
       cod_tuberia VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       tipologia_tuberia VARCHAR
	       REFERENCES dominios.tipologia_tuberia(item),
       sistema VARCHAR
	       REFERENCES dominios.sistema(item),
       tuberia_comercial VARCHAR
	       REFERENCES dominios.tuberia_comercial(item),
       material VARCHAR,
       rugosidad FLOAT,
       diametro INTEGER,
       long_tuberia NUMERIC(5,2),
       caudal NUMERIC(5,2),
       velocidad NUMERIC(5,2),
       perdida_carga NUMERIC(5,2),
       factor_friccion NUMERIC(5,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_tuberias', 'geom', 32616, 'MULTILINESTRING', 2);
CREATE INDEX ON fonsagua.alt_tuberias USING GIST(geom);

ALTER TABLE fonsagua.alt_tuberias OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_valvulas (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL,
       cod_valvula VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       cota NUMERIC(5,2),
       diametro INTEGER,
       consigna NUMERIC(5,2),
       caudal NUMERIC(5,2),
       velocidad NUMERIC(5,2),
       perdidas NUMERIC(5,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_valvulas', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_valvulas USING GIST(geom);

ALTER TABLE fonsagua.alt_valvulas OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_bombeos (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL,
       cod_bomba VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       tipologia_bomba VARCHAR
	       REFERENCES dominios.tipologia_bomba(item),
       energia VARCHAR
	       REFERENCES dominios.energia(item),
       tiempo_bombeo NUMERIC(5,2),
       altura_bombeo INTEGER,
       potencia NUMERIC(5,2),
       consumo NUMERIC(5,2),
       cuota_futura NUMERIC(5,2),
       cota INTEGER,
       bomba_comercial VARCHAR
	       REFERENCES dominios.bomba_comercial(item),
       caudal NUMERIC(5,2),
       altura NUMERIC(5,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_bombeos', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_bombeos USING GIST(geom);

ALTER TABLE fonsagua.alt_bombeos OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_depositos (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL,
       cod_deposito VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       tipo_deposito VARCHAR
	       REFERENCES dominios.tipo_deposito(item),
       ubicacion VARCHAR
	       REFERENCES dominios.ubicacion(item),
       tipo_construccion VARCHAR
	       REFERENCES dominios.tipo_construccion(item),
       vol_calculado NUMERIC(5,2),
       volumen NUMERIC(5,2),
       cota NUMERIC(5,2),
       nivel_maximo NUMERIC(5,2),
       nivel_minimo NUMERIC(5,2),
       nivel_inicial NUMERIC(5,2),
       diametro NUMERIC(5,2),
       q_neto_entrante NUMERIC(5,2),
       presion NUMERIC(5,2),
       altura_total NUMERIC(5,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_depositos', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_depositos USING GIST(geom);

ALTER TABLE fonsagua.alt_depositos OWNER TO fonsagua;
