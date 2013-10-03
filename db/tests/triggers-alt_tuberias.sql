BEGIN;

SELECT PLAN(5);

SELECT trigger_is('fonsagua', 'alt_tuberias', 'alt_tuberias_compute_field_trigger', 'fonsagua', 'alt_tuberias_compute_field_trigger');

INSERT INTO fonsagua.alternativas (cod_alternativa, cod_departamento, cod_municipio, cod_canton) VALUES ('TEST-ALT-TUBERIAS','mun', 'dep', 'canton');

INSERT INTO fonsagua.preferencias_tuberias(id_tub, material, rugosidad, diametro) VALUES ('tub-test0', 'PVC', 0.025, 3);

INSERT INTO fonsagua.alt_tuberias(cod_alternativa, tuberia_comercial, geom) VALUES ('TEST-ALT-TUBERIAS', 'tub-test0', ST_GeomFromText('MULTILINESTRING((0 1, 0 2, 0 3.5))', 32616));


SELECT is(material, 'PVC')
       FROM fonsagua.alt_tuberias
       WHERE gid = currval('fonsagua.alt_tuberias_gid_seq');

SELECT is(rugosidad, 0.025)
       FROM fonsagua.alt_tuberias
       WHERE gid = currval('fonsagua.alt_tuberias_gid_seq');

SELECT is(diametro, 3.0)
       FROM fonsagua.alt_tuberias
       WHERE gid = currval('fonsagua.alt_tuberias_gid_seq');

SELECT is(long_tuberia, 2.5::numeric)
       FROM fonsagua.alt_tuberias
       WHERE gid = currval('fonsagua.alt_tuberias_gid_seq');