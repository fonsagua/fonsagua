BEGIN;

SELECT PLAN(11);

SELECT trigger_is('fonsagua', 'alternativas', 'alternativas_compute_field_trigger', 'fonsagua', 'alternativas_compute_field_trigger');

INSERT INTO fonsagua.preferencias (cod_alternativa, f_var_est, tasa_crec, ano_horiz_sist, dot_sist_domiciliar, dot_sist_cantareras) VALUES ('TEST-ALTERNATIVAS', 3, 4, 10, 15, 100);

INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton, tipo_distribucion, pobl_actual) VALUES ('TEST-ALTERNATIVAS','mun', 'dep', 'canton', 'Domiciliar', 100);

SELECT is(pobl_futura, 140)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_poblacion::real, 0.0729167::real)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_centros, 0::real)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_econ, 0::real)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(demanda::real, 0.0729167::real)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

UPDATE fonsagua.alternativas SET tipo_distribucion='Cantareras' WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(demanda::real, 0.486111::real)
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

SELECT is(dem_centros::real, 1.04167::real)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(demanda::real, 1.52778::real)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

UPDATE fonsagua.alternativas SET 
       dot_sec_primario = 10000,
       dot_sec_secundario = -20000,
       dot_sec_terciario = NULL
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(dem_econ::real, 0.115741::real)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');

SELECT is(demanda, 1.64352)
       FROM fonsagua.alternativas
       WHERE gid = currval('fonsagua.alternativas_gid_seq');