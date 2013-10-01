BEGIN;

SELECT PLAN(13);

SELECT trigger_is('fonsagua', 'alt_fuentes', 'alt_fuentes_compute_field_trigger', 'fonsagua', 'alt_fuentes_compute_field_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton) VALUES ('TEST-ALT-FUENTES','mun', 'dep', 'canton');
UPDATE fonsagua.preferencias_disenho SET coef_q_ecologico=0.5 WHERE cod_alternativa='TEST-ALT-FUENTES';

INSERT INTO fonsagua.alt_fuentes(cod_alternativa, tipo_fuente_alternativa, aforo, q_usar) VALUES ('TEST-ALT-FUENTES', 'Manantial', 2, 0);
SELECT is(q_ecologico, 1::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(q_usar, 0::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(q_calculo, 0::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

UPDATE fonsagua.alt_fuentes SET aforo=-5 WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(aforo, 0::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(q_ecologico, 0::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(q_usar, 0::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

INSERT INTO fonsagua.alt_fuentes(cod_alternativa, aforo, q_usar, tipo_fuente_alternativa) VALUES ('TEST-ALT-FUENTES', 0.5, NULL, 'Manantial');
SELECT is(q_ecologico, 0.25)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(q_usar, 0.25::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(q_calculo, -0.25)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

UPDATE fonsagua.alt_fuentes SET tipo_fuente_alternativa='Pozo', q_usar=10 WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');
SELECT is(q_ecologico, NULL)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(q_usar, 10::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');

SELECT is(q_calculo, -10::numeric)
       FROM fonsagua.alt_fuentes
       WHERE gid = currval('fonsagua.alt_fuentes_gid_seq');