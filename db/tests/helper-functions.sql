BEGIN;

SELECT PLAN(2);

-- SELECT has_function('fonsagua', 'pobl_futura_function', ARRAY['character varying', 'integer']);

INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton, tipo_distribucion) VALUES ('TEST-HELPER-FUNCTIONS','mun', 'dep', 'canton', 'Domiciliar');
INSERT INTO fonsagua.preferencias_disenho (cod_alternativa, tasa_crecimiento, ano_horiz_sistema) VALUES ('TEST-HELPER-FUNCTIONS', 3, 20);

SELECT is(fonsagua.pobl_futura_function('TEST-HELPER-FUNCTIONS', 10), 16);



-- SELECT has_function('fonsagua', 'demanda_poblacion_futura', ARRAY['character varying', 'character varying', 'integer']);
INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton, tipo_distribucion) VALUES ('TEST-DEMANDA-POBLACION-FUNCTION','mun', 'dep', 'canton', 'Domiciliar');
INSERT INTO fonsagua.preferencias_disenho (cod_alternativa, f_var_horaria, f_var_estacional, dot_domiciliar, dot_cantareras) VALUES ('TEST-DEMANDA-POBLACION-FUNCTION', 2, 3, 15, 20);

SELECT is(
       fonsagua.demanda_poblacion_function('TEST-DEMANDA-POBLACION-FUNCTION', 'Domiciliar', 160)::numeric(6,2),
       0.03)
