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
       dem_poblacion NUMERIC(6,2),
       dem_centros NUMERIC(6,2),
       dem_econ NUMERIC(6,2),
       demanda NUMERIC(6,2),
       caudal_fuentes NUMERIC(6,2),
       tipo_sistema VARCHAR
	       REFERENCES dominios.tipo_sistema(item),
       n_acomedidas INTEGER,
       coment_alternativa VARCHAR,
       tipo_saneamiento VARCHAR
	       REFERENCES dominios.tipo_saneamiento(item),
       trat_trampa INTEGER,
       trat_biofiltros INTEGER,
       trat_otros INTEGER,
       coment_otros VARCHAR,
       let_hoyo INTEGER,
       let_septica INTEGER,
       let_hidraulica INTEGER,
       let_abonera INTEGER,
       coment_saneamiento VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'alternativas', 'geom', 32616, 'MULTIPOLYGON', 2);
CREATE INDEX ON fonsagua.alternativas USING GIST(geom);

ALTER TABLE fonsagua.alternativas OWNER TO fonsagua;

CREATE TABLE fonsagua.preferencias_disenho (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
       tasa_crecimiento NUMERIC(6,2) DEFAULT 2,
       ano_horiz_sistema INTEGER DEFAULT 20,
       ano_horiz_bomba INTEGER DEFAULT 10,
       dot_domiciliar INTEGER DEFAULT 90,
       dot_cantareras INTEGER DEFAULT 40,
       f_var_estacional NUMERIC(6,2) DEFAULT 1.2,
       f_var_horaria NUMERIC(6,2) DEFAULT 2.25,
       coef_q_ecologico NUMERIC(6,2) DEFAULT 0.4,
       n_integrantes_familia INTEGER DEFAULT 6,
       rendimiento_bomba NUMERIC(6,2) DEFAULT 0.6,
       pvp_kwh NUMERIC(6,2) DEFAULT 4,
       perdidas_puntutal NUMERIC(6,2) DEFAULT 1,
       v_min NUMERIC(6,2) DEFAULT 0.5,
       v_max NUMERIC(6,2) DEFAULT 2,
       presion_min INTEGER DEFAULT 10,
       presion_max INTEGER DEFAULT 50

);


ALTER TABLE fonsagua.preferencias_disenho OWNER TO fonsagua;

CREATE TABLE fonsagua.preferencias_bombas (
       gid SERIAL PRIMARY KEY,
       id_bomba VARCHAR
       		UNIQUE
		NOT NULL,
       bomba VARCHAR,
       potencia NUMERIC(6,2),
       precio_lmp FLOAT

);


ALTER TABLE fonsagua.preferencias_bombas OWNER TO fonsagua;

CREATE TABLE fonsagua.preferencias_tuberias (
       gid SERIAL PRIMARY KEY,
       id_tub VARCHAR
       	      UNIQUE
	      NOT NULL,
       denominacion VARCHAR,
       material VARCHAR,
       diametro NUMERIC(6,2),
       presion NUMERIC(6,2),
       rugosidad NUMERIC(7,4),
       precio_lmp NUMERIC(6,2)

);


ALTER TABLE fonsagua.preferencias_tuberias OWNER TO fonsagua;


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
       aforo NUMERIC(6,2),
       q_ecol NUMERIC(6,2),
       q_usar NUMERIC(6,2)

);


ALTER TABLE fonsagua.fuentes_implicadas OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_embalses (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
       embalse VARCHAR,
       cod_embalse VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       aforo NUMERIC(6,2),
       q_usar NUMERIC(6,2),
       altura NUMERIC(6,2),
       caudal NUMERIC(6,2),
       altura_total NUMERIC(6,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_embalses', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_embalses USING GIST(geom);

ALTER TABLE fonsagua.alt_embalses OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_fuentes (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
       fuente VARCHAR,
       cod_fuente VARCHAR,
       tipo_fuente_alternativa VARCHAR
	       REFERENCES dominios.tipo_fuente_alternativa(item),
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       aforo NUMERIC(6,2),
       q_ecologico NUMERIC(6,2),
       q_usar NUMERIC(6,2),
       q_calculo NUMERIC(6,2),
       altura NUMERIC(6,2),
       presion NUMERIC(6,2),
       altura_total NUMERIC(6,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_fuentes', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_fuentes USING GIST(geom);

ALTER TABLE fonsagua.alt_fuentes OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_depositos (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
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
       vol_calculado NUMERIC(6,2),
       volumen NUMERIC(6,2),
       cota NUMERIC(6,2),
       nivel_maximo NUMERIC(6,2),
       nivel_minimo NUMERIC(6,2),
       nivel_inicial NUMERIC(6,2),
       diametro NUMERIC(6,2),
       q_neto_entrante NUMERIC(6,2),
       presion NUMERIC(6,2),
       altura_total NUMERIC(6,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_depositos', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_depositos USING GIST(geom);

ALTER TABLE fonsagua.alt_depositos OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_tuberias (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
       cod_tuberia VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       tipologia_tuberia VARCHAR
	       REFERENCES dominios.tipologia_tuberia(item),
       sistema VARCHAR
	       REFERENCES dominios.sistema(item),
       tuberia_comercial VARCHAR
	       REFERENCES fonsagua.preferencias_tuberias(id_tub),
       material VARCHAR,
       rugosidad NUMERIC(7,4),
       diametro NUMERIC(6,2),
       long_tuberia NUMERIC(6,2),
       caudal NUMERIC(6,2),
       velocidad NUMERIC(6,2),
       perdida_carga NUMERIC(6,2),
       factor_friccion NUMERIC(6,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_tuberias', 'geom', 32616, 'MULTILINESTRING', 2);
CREATE INDEX ON fonsagua.alt_tuberias USING GIST(geom);

ALTER TABLE fonsagua.alt_tuberias OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_bombeos (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
       cod_bomba VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       tipologia_bomba VARCHAR
	       REFERENCES dominios.tipologia_bomba(item),
       energia VARCHAR
	       REFERENCES dominios.energia(item),
       altura_bombeo INTEGER,
       tiempo_bombeo NUMERIC(6,2),
       caudal_bombeo NUMERIC(6,2),
       potencia_disenho NUMERIC(6,2),
       cota INTEGER,
       bomba_comercial VARCHAR
	       REFERENCES fonsagua.preferencias_bombas(id_bomba),
       potencia NUMERIC(6,2),
       caudal NUMERIC(6,2),
       altura NUMERIC(6,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_bombeos', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_bombeos USING GIST(geom);

ALTER TABLE fonsagua.alt_bombeos OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_conexiones (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.alternativas(cod_alternativa),
       cod_conexion VARCHAR,
       denominacion VARCHAR,
       cota NUMERIC(6,2),
       hab_conectados INTEGER,
       demanda NUMERIC(6,2),
       presion NUMERIC(6,2),
       altura_total NUMERIC(6,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_conexiones', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_conexiones USING GIST(geom);

ALTER TABLE fonsagua.alt_conexiones OWNER TO fonsagua;

CREATE TABLE fonsagua.alt_valvulas (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL,
       cod_valvula VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES dominios.existencia_elemento(item),
       cota NUMERIC(6,2),
       diametro INTEGER,
       consigna NUMERIC(6,2),
       caudal NUMERIC(6,2),
       velocidad NUMERIC(6,2),
       perdidas NUMERIC(6,2)

);

SELECT addgeometrycolumn('fonsagua', 'alt_valvulas', 'geom', 32616, 'POINT', 2);
CREATE INDEX ON fonsagua.alt_valvulas USING GIST(geom);

ALTER TABLE fonsagua.alt_valvulas OWNER TO fonsagua;