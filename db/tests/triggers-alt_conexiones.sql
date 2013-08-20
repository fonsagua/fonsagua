BEGIN;

SELECT PLAN(2);

SELECT trigger_is('fonsagua', 'alt_conexiones', 'alt_conexiones_compute_field_trigger', 'fonsagua', 'alt_conexiones_compute_field_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton, tipo_distribucion) VALUES ('T2EST-ALT-CONEXIONES','mun', 'dep', 'canton', 'Domiciliar');
INSERT INTO fonsagua.preferencias (cod_alternativa, f_var_hor, f_var_est, tasa_crec, ano_horiz_sist, dot_sist_domiciliar, dot_sist_cantareras) VALUES ('T2EST-ALT-CONEXIONES', 2, 3, 4, 10, 15, 20);

INSERT INTO fonsagua.alt_conexiones(cod_alternativa, hab_conectados) VALUES ('T2EST-ALT-CONEXIONES', 100);
SELECT is(demanda, 1::numeric)
       FROM fonsagua.alt_conexiones
       WHERE gid = currval('fonsagua.alt_conexiones_gid_seq');