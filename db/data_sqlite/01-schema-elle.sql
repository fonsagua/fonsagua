CREATE TABLE _map (
    mapa VARCHAR(255) NOT NULL,
    nombre_capa VARCHAR(255) NOT NULL,
    nombre_tabla VARCHAR(255),
    posicion integer DEFAULT 0 NOT NULL,
    visible VARCHAR(5) DEFAULT 'false',
    max_escala VARCHAR(50),
    min_escala VARCHAR(50),
    grupo VARCHAR,
    schema VARCHAR,
    localizador VARCHAR(5) DEFAULT 'false',
    CONSTRAINT _map_pkey PRIMARY KEY (mapa, nombre_capa)
);


CREATE TABLE _map_overview (
    mapa VARCHAR NOT NULL,
    nombre_capa VARCHAR NOT NULL,
    schema VARCHAR,
    posicion integer,
    nombre_tabla VARCHAR,
    CONSTRAINT _map_overview_pkey PRIMARY KEY (mapa, nombre_capa)
);


CREATE TABLE _map_overview_style (
    nombre_capa VARCHAR NOT NULL,
    nombre_estilo VARCHAR NOT NULL,
    tipo VARCHAR(3),
    definicion xml,
    CONSTRAINT _map_overview_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo)
);


CREATE TABLE _map_style (
    nombre_capa VARCHAR NOT NULL,
    nombre_estilo VARCHAR NOT NULL,
    type VARCHAR(3),
    definicion xml,
    CONSTRAINT _map_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo)
);

--
-- Data for Name: _map; Type: TABLE DATA; Schema: elle; Owner: fonsagua
--
INSERT INTO _map VALUES ('base', 'departamentos', 'departamentos', 1, 'true', NULL, NULL, 'limites_administrativos', 'limites_administrativos', 'false');
INSERT INTO _map VALUES ('base', 'municipios', 'municipios', 2, 'true', NULL, NULL, 'limites_administrativos', 'limites_administrativos', 'false');
INSERT INTO _map VALUES ('base', 'cantones', 'cantones', 3, 'true', NULL, NULL, 'limites_administrativos', 'limites_administrativos', 'false');

INSERT INTO _map VALUES ('general', 'abastecimientos', 'abastecimientos', 4, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'fuentes', 'fuentes', 5, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'bombeos', 'bombeos', 6, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'dep_distribucion', 'dep_distribucion', 7, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'dep_intermedios', 'dep_intermedios', 8, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'captaciones', 'captaciones', 9, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'otros_servicios', 'otros_servicios', 10, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'centros_salud', 'centros_salud', 11, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'centros_educativos', 'centros_educativos', 12, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'amenazas', 'amenazas', 13, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'fuentes_contaminacion', 'fuentes_contaminacion', 14, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'puntos_viviendas', 'puntos_viviendas', 15, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'comunidades', 'comunidades', 16, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'tuberias', 'tuberias', 17, 'true', NULL, NULL, '', 'fonsagua', 'false');
INSERT INTO _map VALUES ('general', 'areas_potenciales_riego', 'areas_potenciales_riego', 18, 'true', NULL, NULL, '', 'fonsagua', 'false');


INSERT INTO _map VALUES ('alternativas', 'alternativas', 'alternativas', 19, 'true', NULL, NULL, 'Alternativa', 'fonsagua', 'false');
INSERT INTO _map VALUES ('alternativas', 'alt_bombeos', 'alt_bombeos', 20, 'true', NULL, NULL, 'Alternativa', 'fonsagua', 'false');
INSERT INTO _map VALUES ('alternativas', 'alt_conexiones', 'alt_conexiones', 21, 'true', NULL, NULL, 'Alternativa', 'fonsagua', 'false');
INSERT INTO _map VALUES ('alternativas', 'alt_depositos', 'alt_depositos', 22, 'true', NULL, NULL, 'Alternativa', 'fonsagua', 'false');
INSERT INTO _map VALUES ('alternativas', 'alt_fuentes', 'alt_fuentes', 23, 'true', NULL, NULL, 'Alternativa', 'fonsagua', 'false');
INSERT INTO _map VALUES ('alternativas', 'alt_tuberias', 'alt_tuberias', 24, 'true', NULL, NULL, 'Alternativa', 'fonsagua', 'false');
INSERT INTO _map VALUES ('alternativas', 'alt_valvulas', 'alt_valvulas', 25, 'true', NULL, NULL, 'Alternativa', 'fonsagua', 'false');
INSERT INTO _map VALUES ('alternativas', 'alt_embalses', 'alt_embalses', 26, 'true', NULL, NULL, 'Alternativa', 'fonsagua', 'false');
