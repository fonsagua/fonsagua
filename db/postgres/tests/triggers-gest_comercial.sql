BEGIN;
SELECT plan(9);


SELECT trigger_is('fonsagua',
                  'gest_comercial',
                  'gest_comercial_compute_fields_trigger',
                  'fonsagua',
                  'gest_comercial_compute_fields_trigger');


INSERT INTO fonsagua.abastecimientos (cod_abastecimiento) VALUES ('test0');
INSERT INTO fonsagua.gest_comercial
       (cod_abastecimiento, produccion, facturacion, a_no_contabilizada, pct_a_no_contabilizada)
       VALUES ('test0', 10, 1, 100, 500);
SELECT is(produccion, 10.0)
       FROM fonsagua.gest_comercial
       WHERE gid = currval('fonsagua.gest_comercial_gid_seq');
SELECT is(facturacion, 1.0)
       FROM fonsagua.gest_comercial
       WHERE gid = currval('fonsagua.gest_comercial_gid_seq');
SELECT is(a_no_contabilizada, 9.0)
       FROM fonsagua.gest_comercial
       WHERE gid = currval('fonsagua.gest_comercial_gid_seq');
SELECT is(pct_a_no_contabilizada, 90.0)
       FROM fonsagua.gest_comercial
       WHERE gid = currval('fonsagua.gest_comercial_gid_seq');
UPDATE fonsagua.gest_comercial	SET a_no_contabilizada = 555;
SELECT is(a_no_contabilizada, 9.0)
       FROM fonsagua.gest_comercial
       WHERE gid = currval('fonsagua.gest_comercial_gid_seq');
UPDATE fonsagua.gest_comercial SET produccion = 20;
SELECT is(produccion, 20.0)
       FROM fonsagua.gest_comercial
       WHERE gid = currval('fonsagua.gest_comercial_gid_seq');
SELECT is(a_no_contabilizada, 19.0)
       FROM fonsagua.gest_comercial
       WHERE gid = currval('fonsagua.gest_comercial_gid_seq');
SELECT is(pct_a_no_contabilizada, 95.0)
       FROM fonsagua.gest_comercial
       WHERE gid = currval('fonsagua.gest_comercial_gid_seq');

SELECT * from finish();
ROLLBACK;
