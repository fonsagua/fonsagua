BEGIN;

SELECT PLAN(11);

SELECT trigger_is('fonsagua', 'alternativas', 'alternativas_compute_field_trigger', 'fonsagua', 'alternativas_compute_field_trigger');

INSERT INTO fonsagua.comunidades
       (cod_comunidad, cod_departamento, cod_municipio, cod_canton, cod_caserio, caserio, comunidad, n_habitantes)
       VALUES ('TEST-ALTERNATIVAS-COMUNIDAD', 'test0', 'test0', 'test0', 'test0', 'nombre_caserio', 'nombre_comunidad', 200);

INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton, tipo_distribucion) VALUES ('TEST-ALTERNATIVAS','mun', 'dep', 'canton', 'Domiciliar');

INSERT INTO fonsagua.preferencias_disenho (cod_alternativa, f_var_estacional, tasa_crecimiento, ano_horiz_sistema, dot_domiciliar, dot_cantareras) VALUES ('TEST-ALTERNATIVAS', 3, 4, 10, 15, 100);

INSERT INTO fonsagua.comunidades_implicadas
       (cod_alternativa, comunidad, n_habitantes, n_hab_alternativa)
       VALUES ('TEST-ALTERNATIVAS', 'nombre_comunidad', 200, 100);

UPDATE fonsagua.alternativas SET pobl_actual=1000 WHERE cod_alternativa = 'TEST-ALTERNATIVAS';

SELECT is(pobl_futura, 140)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_poblacion, 0.07)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_centros, 0::numeric)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_econ, 0::numeric)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(demanda, 0.07)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

UPDATE fonsagua.alternativas SET tipo_distribucion='Cantareras' WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(demanda, 0.49)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

UPDATE fonsagua.alternativas SET 
       n_cent_educativos= 2,
       dot_cent_educativos=10000,
       n_cent_salud=-3,
       dot_cent_salud=10000,
       n_cent_otros=10,
       dot_cent_otros=1000
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_centros, 1.04)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(demanda, 1.53)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

UPDATE fonsagua.alternativas SET 
       dot_sec_primario = 10000,
       dot_sec_secundario = -20000,
       dot_sec_terciario = NULL
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_econ, 0.12)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(demanda, 1.65)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');