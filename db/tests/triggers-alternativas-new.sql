BEGIN;
SELECT PLAN(3);

SELECT trigger_is('fonsagua', 'alternativas', 'alternativas_new_trigger', 'fonsagua', 'alternativas_new_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, cod_departamento, cod_municipio, cod_canton, tipo_distribucion) VALUES ('TEST-ALTERNATIVAS-NEW-TRIGGER','mun', 'dep', 'canton', 'Llave pública');

SELECT is((SELECT count(*) FROM fonsagua.preferencias_disenho WHERE cod_alternativa = 'TEST-ALTERNATIVAS-NEW-TRIGGER')::integer, 1);
SELECT is((SELECT count(*) FROM fonsagua.presupuesto WHERE cod_alternativa = 'TEST-ALTERNATIVAS-NEW-TRIGGER')::integer, 1);

SELECT * from finish();
ROLLBACK;
