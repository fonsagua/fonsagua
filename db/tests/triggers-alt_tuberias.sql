BEGIN;

SELECT PLAN(5);

SELECT trigger_is('fonsagua', 'alt_tuberias', 'alt_tuberias_compute_field_trigger', 'fonsagua', 'alt_tuberias_compute_field_trigger');

INSERT INTO fonsagua.tuberias_comerciales(id_tub, material, rugosidad, diametro) VALUES ('tub-test0', 'PVC', 2, 3);

INSERT INTO fonsagua.alt_tuberias(cod_alternativa, tuberia_comercial, geom) VALUES ('TEST-ALT-TUBERIAS', 'tub-test0', ST_GeomFromText('MULTILINESTRING((0 1, 0 2, 0 3.5))', 32616));


SELECT is(material, 'PVC')
       FROM fonsagua.alt_tuberias
       WHERE gid = currval('fonsagua.alt_tuberias_gid_seq');

SELECT is(rugosidad, 2::double precision)
       FROM fonsagua.alt_tuberias
       WHERE gid = currval('fonsagua.alt_tuberias_gid_seq');

SELECT is(diametro, 3::integer)
       FROM fonsagua.alt_tuberias
       WHERE gid = currval('fonsagua.alt_tuberias_gid_seq');

SELECT is(long_tuberia, 2.5::numeric)
       FROM fonsagua.alt_tuberias
       WHERE gid = currval('fonsagua.alt_tuberias_gid_seq');