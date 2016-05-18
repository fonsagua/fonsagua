
CREATE SCHEMA elle;
ALTER SCHEMA elle OWNER TO fonsagua;

SET search_path = elle, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: _map; Type: TABLE; Schema: elle; Owner: fonsagua; Tablespace: 
--

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


ALTER TABLE elle._map OWNER TO fonsagua;

--
-- Name: _map_overview; Type: TABLE; Schema: elle; Owner: fonsagua; Tablespace: 
--

CREATE TABLE _map_overview (
    mapa character varying NOT NULL,
    nombre_capa character varying NOT NULL,
    schema character varying,
    posicion integer,
    nombre_tabla character varying
);


ALTER TABLE elle._map_overview OWNER TO fonsagua;

--
-- Name: _map_overview_style; Type: TABLE; Schema: elle; Owner: fonsagua; Tablespace: 
--

CREATE TABLE _map_overview_style (
    nombre_capa character varying NOT NULL,
    nombre_estilo character varying NOT NULL,
    tipo character varying(3),
    definicion xml
);


ALTER TABLE elle._map_overview_style OWNER TO fonsagua;

--
-- Name: _map_style; Type: TABLE; Schema: elle; Owner: fonsagua; Tablespace: 
--

CREATE TABLE _map_style (
    nombre_capa character varying NOT NULL,
    nombre_estilo character varying NOT NULL,
    type character varying(3),
    definicion xml
);


ALTER TABLE elle._map_style OWNER TO fonsagua;

--
-- Data for Name: _map; Type: TABLE DATA; Schema: elle; Owner: fonsagua
--
INSERT INTO _map VALUES ('base', 'departamentos', 'departamentos', 1, true, NULL, NULL, 'limites_administrativos', 'limites_administrativos', NULL);
INSERT INTO _map VALUES ('base', 'municipios', 'municipios', 2, true, NULL, NULL, 'limites_administrativos', 'limites_administrativos', NULL);
INSERT INTO _map VALUES ('base', 'cantones', 'cantones', 3, true, NULL, NULL, 'limites_administrativos', 'limites_administrativos', NULL);

INSERT INTO _map VALUES ('general', 'abastecimientos', 'abastecimientos', 4, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'fuentes', 'fuentes', 5, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'bombeos', 'bombeos', 6, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'dep_distribucion', 'dep_distribucion', 7, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'dep_intermedios', 'dep_intermedios', 8, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'captaciones', 'captaciones', 9, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'otros_servicios', 'otros_servicios', 10, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'centros_salud', 'centros_salud', 11, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'centros_educativos', 'centros_educativos', 12, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'amenazas', 'amenazas', 13, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'fuentes_contaminacion', 'fuentes_contaminacion', 14, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'puntos_viviendas', 'puntos_viviendas', 15, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'comunidades', 'comunidades', 16, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'tuberias', 'tuberias', 17, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('general', 'areas_potenciales_riego', 'areas_potenciales_riego', 18, true, NULL, NULL, '', 'fonsagua', NULL);


INSERT INTO elle._map VALUES ('alternativas', 'alternativas', 'alternativas', 19, true, NULL, NULL, 'Alternativa', 'fonsagua', NULL);
INSERT INTO elle._map VALUES ('alternativas', 'alt_bombeos', 'alt_bombeos', 20, true, NULL, NULL, 'Alternativa', 'fonsagua', NULL);
INSERT INTO elle._map VALUES ('alternativas', 'alt_conexiones', 'alt_conexiones', 21, true, NULL, NULL, 'Alternativa', 'fonsagua', NULL);
INSERT INTO elle._map VALUES ('alternativas', 'alt_depositos', 'alt_depositos', 22, true, NULL, NULL, 'Alternativa', 'fonsagua', NULL);
INSERT INTO elle._map VALUES ('alternativas', 'alt_fuentes', 'alt_fuentes', 23, true, NULL, NULL, 'Alternativa', 'fonsagua', NULL);
INSERT INTO elle._map VALUES ('alternativas', 'alt_tuberias', 'alt_tuberias', 24, true, NULL, NULL, 'Alternativa', 'fonsagua', NULL);
INSERT INTO elle._map VALUES ('alternativas', 'alt_valvulas', 'alt_valvulas', 25, true, NULL, NULL, 'Alternativa', 'fonsagua', NULL);
INSERT INTO elle._map VALUES ('alternativas', 'alt_embalses', 'alt_embalses', 26, true, NULL, NULL, 'Alternativa', 'fonsagua', NULL);




ALTER TABLE ONLY _map_overview
    ADD CONSTRAINT _map_overview_pkey PRIMARY KEY (mapa, nombre_capa);

ALTER TABLE ONLY _map_overview_style
    ADD CONSTRAINT _map_overview_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo);

ALTER TABLE ONLY _map
    ADD CONSTRAINT _map_pkey PRIMARY KEY (mapa, nombre_capa);


ALTER TABLE ONLY _map_style
    ADD CONSTRAINT _map_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo);
