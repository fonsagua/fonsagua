BEGIN;

SELECT plan(3);


SELECT trigger_is('fonsagua',
                  'comunidades',
                  'comunidades_compute_fields_trigger',
                  'fonsagua',
                  'comunidades_compute_fields_trigger');


INSERT INTO fonsagua.comunidades
       (cod_comunidad, cod_departamento, cod_municipio, cod_canton, cod_caserio, caserio, comunidad)
       VALUES ('test0', 'test0', 'test0', 'test0', 'test0', 'nombre_caserio', 'nombre_comunidad');
SELECT is(caserio, comunidad)
       FROM fonsagua.comunidades
       WHERE gid = currval('fonsagua.comunidades_gid_seq');
SELECT is(comunidad, 'nombre_comunidad')
       FROM fonsagua.comunidades
       WHERE gid = currval('fonsagua.comunidades_gid_seq');

SELECT * from finish();

ROLLBACK;
