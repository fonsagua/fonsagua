BEGIN;
SELECT PLAN(2);

-- SELECT has_function('fonsagua', 'pobl_futura_function', ARRAY['character varying', 'integer']);

INSERT INTO fonsagua.alternativas (cod_alternativa, cod_departamento, cod_municipio, cod_canton, tipo_distribucion) VALUES ('TEST-HELPER-FUNCTIONS','mun', 'dep', 'canton', 'Llave pública');
UPDATE fonsagua.preferencias_disenho SET tasa_crecimiento = 3, ano_horiz_sistema = 20 WHERE cod_alternativa = 'TEST-HELPER-FUNCTIONS';

SELECT is(fonsagua.pobl_futura_function('TEST-HELPER-FUNCTIONS', 10), 16);



-- SELECT has_function('fonsagua', 'demanda_poblacion_futura', ARRAY['character varying', 'character varying', 'integer']);
INSERT INTO fonsagua.alternativas (cod_alternativa, cod_departamento, cod_municipio, cod_canton, tipo_distribucion) VALUES ('TEST-DEMANDA-POBLACION-FUNCTION','mun', 'dep', 'canton', 'Llave pública');
UPDATE fonsagua.preferencias_disenho SET f_var_horaria=2, f_var_estacional=3, dot_domiciliar=15, dot_cantareras=20 where cod_alternativa = 'TEST-DEMANDA-POBLACION-FUNCTION';

SELECT is(
       fonsagua.demanda_poblacion_function('TEST-DEMANDA-POBLACION-FUNCTION', 'Llave pública', 160)::numeric(6,2),
       0.03);

SELECT * from finish();
ROLLBACK;
