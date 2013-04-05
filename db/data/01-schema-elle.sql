SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

DROP SCHEMA IF EXISTS elle CASCADE;
CREATE SCHEMA elle;


SET search_path = elle, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE _map (
    mapa character varying(255) NOT NULL,
    nombre_capa character varying(255) NOT NULL,
    nombre_tabla character varying(255),
    posicion integer DEFAULT 0 NOT NULL,
    visible boolean,
    max_escala character varying(50),
    min_escala character varying(50),
    grupo character varying,
    schema character varying,
    localizador boolean
);

-- for s in `grep TABLE 02-comunidades.sql | cut -f 3 -d ' '` ; do echo "('Vista general', '$s', '$s', 0, true, null, null, null, 'public', false)" ; done

INSERT INTO _map VALUES
       ('Vista general', 'comunidades', 'comunidades', 0, true, null, null, null, 'public', false),
	('Vista general', 'puntos_viviendas', 'puntos_viviendas', 0, true, null, null, null, 'public', false),
	--('Vista general', 'Comunidades', 'interlocutores', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'entrevistados', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'subcuencas', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'adescos', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'cargos_publicos', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'ongs', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'otras_organizaciones', 0, true, null, null, null, 'public', false)
	('Vista general', 'fuentes_contaminacion', 'fuentes_contaminacion', 0, true, null, null, null, 'public', false),
	--('Vista general', 'Comunidades', 'capacitaciones_gestion_riesgos', 0, true, null, null, null, 'public', false)
	('Vista general', 'amenazas', 'amenazas', 0, true, null, null, null, 'public', false),
	--('Vista general', 'Comunidades', 'r_comunidades_abastecimientos', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'valoracion_sistema', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'datos_consumo', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'habitos_consumo', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'centros_actividad_economica', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'tipos_cultivos', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'produccion_consumo_comparado', 0, true, null, null, null, 'public', false)
	('Vista general', 'areas_potenciales_riego', 'areas_potenciales_riego', 0, true, null, null, null, 'public', false),
	--('Vista general', 'Comunidades', 'ganaderia', 0, true, null, null, null, 'public', false)
	--('Vista general', 'Comunidades', 'cooperativas', 0, true, null, null, null, 'public', false)
	('Vista general', 'centros_educativos', 'centros_educativos', 0, true, null, null, null, 'public', false),
	('Vista general', 'centros_salud', 'centros_salud', 0, true, null, null, null, 'public', false),
	('Vista general', 'otros_servicios', 'otros_servicios', 0, true, null, null, null, 'public', false),
	('Vista general', 'abastecimientos', 'abastecimientos', 0, true, null, null, null, 'public', false),
	('Vista general', 'captaciones', 'captaciones', 0, true, null, null, null, 'public', false),
	('Vista general', 'dep_intermedios', 'dep_intermedios', 0, true, null, null, null, 'public', false),
	('Vista general', 'dep_distribucion', 'dep_distribucion', 0, true, null, null, null, 'public', false),
	('Vista general', 'tuberias', 'tuberias', 0, true, null, null, null, 'public', false),
	('Vista general', 'bombeos', 'bombeos', 0, true, null, null, null, 'public', false),
	('Vista general', 'fuentes', 'fuentes', 0, true, null, null, null, 'public', false);

CREATE TABLE _map_overview (
    mapa character varying NOT NULL,
    nombre_capa character varying NOT NULL,
    schema character varying,
    posicion integer,
    nombre_tabla character varying
);


CREATE TABLE _map_overview_style (
    nombre_capa character varying NOT NULL,
    nombre_estilo character varying NOT NULL,
    tipo character varying(3),
    definicion xml
);


CREATE TABLE _map_style (
    nombre_capa character varying NOT NULL,
    nombre_estilo character varying NOT NULL,
    type character varying(3),
    definicion xml
);


ALTER TABLE ONLY _map_overview
    ADD CONSTRAINT _map_overview_pkey PRIMARY KEY (mapa, nombre_capa);


ALTER TABLE ONLY _map_overview_style
    ADD CONSTRAINT _map_overview_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo);


ALTER TABLE ONLY _map
    ADD CONSTRAINT _map_pkey PRIMARY KEY (mapa, nombre_capa);


ALTER TABLE ONLY _map_style
    ADD CONSTRAINT _map_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo);

