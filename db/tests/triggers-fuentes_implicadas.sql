BEGIN;

SELECT PLAN(5);

SELECT trigger_is('fonsagua', 'fuentes_implicadas', 'fuentes_implicadas_compute_field_trigger', 'fonsagua', 'fuentes_implicadas_compute_field_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton) VALUES ('TEST-FUENTES-IMPLICADAS','mun', 'dep', 'canton');
UPDATE fonsagua.preferencias_disenho SET coef_q_ecologico=0.2 WHERE cod_alternativa = 'TEST-FUENTES-IMPLICADAS';

INSERT INTO fonsagua.fuentes_implicadas VALUES (default, 'TEST-FUENTES-IMPLICADAS', 'fuente-manantial', 'Manantial', 10, 10, 10);

SELECT is(q_ecol, 10::numeric)
       FROM fonsagua.fuentes_implicadas
       WHERE gid = currval('fonsagua.fuentes_implicadas_gid_seq');

SELECT is(q_usar, 10::numeric)
       FROM fonsagua.fuentes_implicadas
       WHERE gid = currval('fonsagua.fuentes_implicadas_gid_seq');


UPDATE fonsagua.fuentes_implicadas SET cod_alternativa = 'TEST-FUENTES-IMPLICADAS' WHERE cod_alternativa = 'TEST-FUENTES-IMPLICADAS';

SELECT is(q_ecol, 2::numeric)
       FROM fonsagua.fuentes_implicadas
       WHERE gid = currval('fonsagua.fuentes_implicadas_gid_seq');

SELECT is(q_usar, 0::numeric)
       FROM fonsagua.fuentes_implicadas
       WHERE gid = currval('fonsagua.fuentes_implicadas_gid_seq');
