BEGIN;

SELECT PLAN(4);

SELECT trigger_is('fonsagua', 'alt_conexiones', 'alt_conexiones_compute_field_trigger', 'fonsagua', 'alt_conexiones_compute_field_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton, tipo_distribucion) VALUES ('TEST-ALT-CONEXIONES','mun', 'dep', 'canton', 'Domiciliar');
INSERT INTO fonsagua.preferencias_disenho (cod_alternativa, f_var_horaria, f_var_estacional, tasa_crecimiento, ano_horiz_sistema, dot_domiciliar, dot_cantareras) VALUES ('TEST-ALT-CONEXIONES', 2, 3, 4, 10, 15, 20);

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