BEGIN;
SELECT plan(8);


SELECT trigger_is('fonsagua',
                  'abastecimientos',
                  'abastecimientos_compute_fields_trigger',
                  'fonsagua',
                  'abastecimientos_compute_fields_trigger');


INSERT INTO fonsagua.abastecimientos
       (cod_abastecimiento, tot_acometidas, n_a_domiciliar, n_a_cantarera, n_a_comercial, n_a_otras)
       VALUES ('test0', 10000, 1000, 100, 10, 1);
SELECT is(tot_acometidas, 1111)
       FROM fonsagua.abastecimientos
       WHERE gid = currval('fonsagua.abastecimientos_gid_seq');
SELECT is(n_a_domiciliar, 1000)
       FROM fonsagua.abastecimientos
       WHERE gid = currval('fonsagua.abastecimientos_gid_seq');;
SELECT is(n_a_cantarera, 100)
       FROM fonsagua.abastecimientos
       WHERE gid = currval('fonsagua.abastecimientos_gid_seq');;
SELECT is(n_a_comercial, 10)
       FROM fonsagua.abastecimientos
       WHERE gid = currval('fonsagua.abastecimientos_gid_seq');;
SELECT is(n_a_otras, 1)
       FROM fonsagua.abastecimientos
       WHERE gid = currval('fonsagua.abastecimientos_gid_seq');;
UPDATE fonsagua.abastecimientos	SET tot_acometidas = 20000;
SELECT is(tot_acometidas, 1111)
       FROM fonsagua.abastecimientos
       WHERE gid = currval('fonsagua.abastecimientos_gid_seq');;
UPDATE fonsagua.abastecimientos SET n_a_domiciliar = 3000;
SELECT is(tot_acometidas, 3111)
       FROM fonsagua.abastecimientos
       WHERE gid = currval('fonsagua.abastecimientos_gid_seq');;

SELECT * from finish();
ROLLBACK;
