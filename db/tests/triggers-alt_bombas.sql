BEGIN;

SELECT PLAN(2);

SELECT trigger_is('fonsagua', 'alt_bombeos', 'alt_bombeos_compute_field_trigger', 'fonsagua', 'alt_bombeos_compute_field_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton) VALUES ('TEST-ALT-BOMBEOS','mun', 'dep', 'canton');

INSERT INTO fonsagua.preferencias_bombas(id_bomba, potencia) VALUES ('TEST-ALT-BOMBEOS-bomba0', 3.45);

INSERT INTO fonsagua.alt_bombeos(cod_alternativa, bomba_comercial) VALUES ('TEST-ALT-BOMBEOS', 'TEST-ALT-BOMBEOS-bomba0');

SELECT is(potencia, 3.45)
       FROM fonsagua.alt_bombeos
       WHERE gid = currval('fonsagua.alt_bombeos_gid_seq');