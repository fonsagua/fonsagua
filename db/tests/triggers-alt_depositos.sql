BEGIN;
SELECT PLAN(3);

SELECT trigger_is('fonsagua', 'alt_depositos', 'alt_depositos_compute_field_trigger', 'fonsagua', 'alt_depositos_compute_field_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, cod_departamento, cod_municipio, cod_canton) VALUES ('TEST-ALT-DEPOSITOS','mun', 'dep', 'canton');

INSERT INTO fonsagua.alt_depositos(cod_alternativa, diametro, nivel_maximo, nivel_minimo) VALUES ('TEST-ALT-DEPOSITOS', 10, 5, 1);
SELECT is(volumen, 314.16)
       FROM fonsagua.alt_depositos
       WHERE gid = currval('fonsagua.alt_depositos_gid_seq');

UPDATE fonsagua.alt_depositos SET diametro = NULL WHERE gid = currval('fonsagua.alt_depositos_gid_seq');

SELECT is(volumen, null)
       FROM fonsagua.alt_depositos
       WHERE gid = currval('fonsagua.alt_depositos_gid_seq');

SELECT * from finish();
ROLLBACK;
