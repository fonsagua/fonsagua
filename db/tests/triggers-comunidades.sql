BEGIN;

SELECT plan(5);


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

-- check area_cultivada is 0 with null input values
SELECT is(area_cultivada,0.0::double precision)
       FROM fonsagua.comunidades
       WHERE gid = currval('fonsagua.comunidades_gid_seq');

-- check area_cultivada has correct value
INSERT INTO fonsagua.comunidades
       (cod_comunidad, cod_departamento, cod_municipio, cod_canton, cod_caserio, f_propietarias, prop_area_cultivada, f_arrendatarias, arre_area_cultivada, f_medias, med_area_cultivada)
       VALUES ('test1', 'test1', 'test1', 'test1', 'test1', 2, 3, 4, 5, 6, 7);
SELECT is(area_cultivada, 68.0::double precision)
       FROM fonsagua.comunidades
       WHERE gid = currval('fonsagua.comunidades_gid_seq');

SELECT * from finish();

ROLLBACK;
