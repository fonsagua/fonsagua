BEGIN;
SELECT PLAN(4);

SELECT trigger_is('fonsagua', 'alt_conexiones', 'alt_conexiones_compute_field_trigger', 'fonsagua', 'alt_conexiones_compute_field_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, cod_departamento, cod_municipio, cod_canton, tipo_distribucion) VALUES ('TEST-ALT-CONEXIONES','mun', 'dep', 'canton', 'Llave pública');

UPDATE fonsagua.preferencias_disenho SET f_var_horaria = 2, f_var_estacional=3, tasa_crecimiento=4, ano_horiz_sistema=10, dot_domiciliar=15, dot_cantareras=20 WHERE cod_alternativa = 'TEST-ALT-CONEXIONES';

INSERT INTO fonsagua.alt_conexiones(cod_alternativa, hab_conectados) VALUES ('TEST-ALT-CONEXIONES', 100);
SELECT is(demanda, 0.15)
       FROM fonsagua.alt_conexiones
       WHERE gid = currval('fonsagua.alt_conexiones_gid_seq');

SELECT is(hab_conectados, 100)
       FROM fonsagua.alt_conexiones
       WHERE gid = currval('fonsagua.alt_conexiones_gid_seq');

UPDATE fonsagua.alt_conexiones SET q_extra=8640.5 WHERE gid = currval('fonsagua.alt_conexiones_gid_seq');

SELECT is(demanda, 0.25)
       FROM fonsagua.alt_conexiones
       WHERE gid = currval('fonsagua.alt_conexiones_gid_seq');

SELECT * from finish();
ROLLBACK;
