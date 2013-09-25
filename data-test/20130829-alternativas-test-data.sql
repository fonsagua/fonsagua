--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = fonsagua, pg_catalog;

SELECT pg_catalog.setval('alt_bombeos_gid_seq', 1, true);
SELECT pg_catalog.setval('alt_conexiones_gid_seq', 4, true);
SELECT pg_catalog.setval('alt_depositos_gid_seq', 2, true);
SELECT pg_catalog.setval('alt_embalses_gid_seq', 1, true);
SELECT pg_catalog.setval('alt_fuentes_gid_seq', 4, true);
SELECT pg_catalog.setval('alt_tuberias_gid_seq', 6, true);
SELECT pg_catalog.setval('alt_valvulas_gid_seq', 1, false);
SELECT pg_catalog.setval('alternativas_gid_seq', 7, true);
SELECT pg_catalog.setval('comunidades_implicadas_gid_seq', 9, true);

SET SESSION AUTHORIZATION DEFAULT;

ALTER TABLE alternativas DISABLE TRIGGER ALL;

INSERT INTO alternativas (gid, cod_alternativa, tipo_alternativa, tipo_distribucion, departamento, municipio, canton, pobl_actual, pobl_futura, n_cent_educativos, dot_cent_educativos, n_cent_salud, dot_cent_salud, n_cent_otros, dot_cent_otros, dot_sec_primario, dot_sec_secundario, dot_sec_terciario, dem_poblacion, dem_centros, dem_econ, demanda, caudal_fuentes, tipo_sistema, n_acomedidas, coment_alternativa, tipo_saneamiento, trat_trampa, trat_biofiltros, trat_otros, coment_otros, let_hoyo, let_septica, let_hidraulica, let_abonera, coment_saneamiento, geom) VALUES (7, '010206A01', 'Alternativa', 'Cantareras', '06', '0615', '061501', 250, 350, 1, 500, 1, 500, 1, 500, 500, 5000, 50, 0.19, 0.02, 0.06, 0.27, 1.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0106000020687F00000100000001030000000100000014000000D1FC69DCF430114119DFAAB8060837414CD005BB81311141519C94060D0837416DDB5E836C321141674E258C04083741628296401E321141B5BD9F5F9E073741628296408E301141ED7A89AD90073741A59848D1A72F114194B24697C1073741F307C3A49B2F114189597E54F0073741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741D1FC69DCF430114119DFAAB806083741');


ALTER TABLE alternativas ENABLE TRIGGER ALL;

INSERT INTO preferencias_disenho(cod_alternativa) VALUES ('010206A01');

--
-- Data for Name: alt_conexiones; Type: TABLE DATA; Schema: fonsagua; Owner: fonsagua
--

ALTER TABLE alt_conexiones DISABLE TRIGGER ALL;

INSERT INTO alt_conexiones (gid, cod_alternativa, cod_conexion, denominacion, cota, hab_conectados, demanda, presion, altura_total, geom) VALUES (2, '010206A01', NULL, 'comunidad1', 0.00, 150, 0.26, 18.88, 18.88, '0101000020687F0000B6A5DDDD1B31114142F9D451FB073741');
INSERT INTO alt_conexiones (gid, cod_alternativa, cod_conexion, denominacion, cota, hab_conectados, demanda, presion, altura_total, geom) VALUES (3, '010206A01', NULL, 'Comunidad2', 5.00, 100, 0.18, 3.47, 8.47, '0101000020687F00003B5CF5BCCD311141C6079751F6073741');
INSERT INTO alt_conexiones (gid, cod_alternativa, cod_conexion, denominacion, cota, hab_conectados, demanda, presion, altura_total, geom) VALUES (4, '010206A01', NULL, 'nudo', 10.00, NULL, 0.00, 10.20, 20.20, '0101000020687F000041860EC91A3111415233DD50E7073741');


ALTER TABLE alt_conexiones ENABLE TRIGGER ALL;

--
-- Data for Name: alt_depositos; Type: TABLE DATA; Schema: fonsagua; Owner: fonsagua
--

ALTER TABLE alt_depositos DISABLE TRIGGER ALL;

INSERT INTO alt_depositos (gid, cod_alternativa, cod_deposito, denominacion, existencia_elemento, tipo_deposito, ubicacion, tipo_construccion, vol_calculado, volumen, cota, nivel_maximo, nivel_minimo, nivel_inicial, diametro, q_neto_entrante, presion, altura_total, geom) VALUES (2, '010206A01', '1', 'de', 'Proyectada', 'Distribución', 'Soterrado', 'Fibrocemento', 30.00, 21.21, 20.00, 3.00, 0.00, 1.00, 3.00, 59.41, 1.00, 21.00, '0101000020687F00007007B4D3D83011414934FB72DD073741');


ALTER TABLE alt_depositos ENABLE TRIGGER ALL;

--
-- Data for Name: alt_fuentes; Type: TABLE DATA; Schema: fonsagua; Owner: fonsagua
--

ALTER TABLE alt_fuentes DISABLE TRIGGER ALL;

INSERT INTO alt_fuentes (gid, cod_alternativa, fuente, cod_fuente, tipo_fuente_alternativa, existencia_elemento, aforo, q_ecologico, q_usar, q_calculo, altura, presion, altura_total, geom) VALUES (4, '010206A01', NULL, NULL, 'Pozo', 'Proyectada', 1.00, 0.40, 0.40, -0.40, 50.00, 26.94, 76.94, '0101000020687F00008F4D333D6D3011411D660410B2073741');


ALTER TABLE alt_fuentes ENABLE TRIGGER ALL;

--
-- Data for Name: alt_tuberias; Type: TABLE DATA; Schema: fonsagua; Owner: fonsagua
--

ALTER TABLE alt_tuberias DISABLE TRIGGER ALL;

INSERT INTO alt_tuberias (gid, cod_alternativa, cod_tuberia, denominacion, existencia_elemento, tipologia_tuberia, sistema, tuberia_comercial, material, rugosidad, diametro, long_tuberia, caudal, velocidad, perdida_carga, factor_friccion, geom) VALUES (2, '010206A01', NULL, '1', 'Proyectada', 'Aducción', 'Gravedad', 'HO 4" ', 'Hormigón', 0.0250000003725290298, 101.60, 45.41, 59.45, 7.33, 418.37, 0.02, '0105000020687F000001000000010200000002000000D262C72B233011415B506150DD0737417007B4D3D83011414934FB72DD073741');
INSERT INTO alt_tuberias (gid, cod_alternativa, cod_tuberia, denominacion, existencia_elemento, tipologia_tuberia, sistema, tuberia_comercial, material, rugosidad, diametro, long_tuberia, caudal, velocidad, perdida_carga, factor_friccion, geom) VALUES (3, '010206A01', '2', '2', 'Proyectada', 'Aducción', 'Gravedad', 'HO 0,5"', 'Hormigón', 0.0250000003725290298, 12.70, 51.05, 0.40, 3.16, 1100.00, 0.03, '0105000020687F0000010000000102000000020000008F4D333D6D3011411D660410B20737417007B4D3D83011414934FB72DD073741');
INSERT INTO alt_tuberias (gid, cod_alternativa, cod_tuberia, denominacion, existencia_elemento, tipologia_tuberia, sistema, tuberia_comercial, material, rugosidad, diametro, long_tuberia, caudal, velocidad, perdida_carga, factor_friccion, geom) VALUES (4, '010206A01', NULL, '3', 'Proyectada', 'Distribución', 'Bombeo', 'HO 1" ', 'Hormigón', 0.0250000003725290298, 25.40, 19.22, 0.44, 0.87, 41.86, 0.03, '0105000020687F0000010000000102000000020000007007B4D3D83011414934FB72DD07374141860EC91A3111415233DD50E7073741');
INSERT INTO alt_tuberias (gid, cod_alternativa, cod_tuberia, denominacion, existencia_elemento, tipologia_tuberia, sistema, tuberia_comercial, material, rugosidad, diametro, long_tuberia, caudal, velocidad, perdida_carga, factor_friccion, geom) VALUES (5, '010206A01', NULL, '4', 'Proyectada', 'Distribución', 'Gravedad', 'HO 0,75" ', 'Hormigón', 0.0250000003725290298, 19.05, 20.01, 0.26, 0.91, 65.99, 0.03, '0105000020687F00000100000001020000000200000041860EC91A3111415233DD50E7073741B6A5DDDD1B31114142F9D451FB073741');
INSERT INTO alt_tuberias (gid, cod_alternativa, cod_tuberia, denominacion, existencia_elemento, tipologia_tuberia, sistema, tuberia_comercial, material, rugosidad, diametro, long_tuberia, caudal, velocidad, perdida_carga, factor_friccion, geom) VALUES (6, '010206A01', NULL, '5', 'Proyectada', 'Distribución', 'Gravedad', 'HO 0,5"', 'Hormigón', 0.0250000003725290298, 12.70, 47.19, 0.18, 1.42, 248.41, 0.03, '0105000020687F00000100000001020000000200000041860EC91A3111415233DD50E70737413B5CF5BCCD311141C6079751F6073741');


ALTER TABLE alt_tuberias ENABLE TRIGGER ALL;

--
-- Data for Name: comunidades_implicadas; Type: TABLE DATA; Schema: fonsagua; Owner: fonsagua
--

ALTER TABLE comunidades_implicadas DISABLE TRIGGER ALL;

INSERT INTO comunidades_implicadas (gid, cod_alternativa, comunidad, n_habitantes, n_hab_alternativa, cod_comunidad) VALUES (9, '010206A01', 'Prueba', 300, 2, '01010102');


ALTER TABLE comunidades_implicadas ENABLE TRIGGER ALL;

--
-- PostgreSQL database dump complete
--

