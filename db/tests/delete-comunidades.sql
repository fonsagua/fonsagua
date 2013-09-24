BEGIN;

SELECT PLAN(53);

INSERT INTO fonsagua.comunidades (cod_comunidad, cod_departamento, cod_municipio, cod_canton, cod_caserio) VALUES ('DELETE-COM', 'dep', 'mun', 'can', 'cas');
INSERT INTO fonsagua.puntos_viviendas (cod_comunidad, cod_vivienda) VALUES ('DELETE-COM', 'DELETE-VIV');
INSERT INTO fonsagua.comunidades_croquis VALUES('DELETE-COM', 'a'::bytea);
INSERT INTO fonsagua.entrevistadores (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.entrevistados (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.subcuencas (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.adescos (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.cargos_publicos(cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.ongs (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.otras_organizaciones (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.tipos_cultivos (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.produccion_consumo (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.areas_potenciales_riego (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.ganaderia (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.cooperativas (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.centros_educativos (cod_comunidad, cod_c_educativo) VALUES ('DELETE-COM', 'cedu');
INSERT INTO fonsagua.centros_salud (cod_comunidad, cod_c_salud) VALUES ('DELETE-COM', 'csalud');
INSERT INTO fonsagua.otros_servicios (cod_comunidad, cod_servicio) VALUES ('DELETE-COM', 'oservicio');
INSERT INTO fonsagua.capacitaciones_riesgos (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.amenazas (cod_comunidad, cod_amenaza) VALUES ('DELETE-COM', 'camenaza');
INSERT INTO fonsagua.implicacion_comunidad (cod_comunidad) VALUES ('DELETE-COM');
INSERT INTO fonsagua.habitos_consumo (cod_comunidad) VALUES ('DELETE-COM');


--ABASTECIMIENTOS

INSERT INTO fonsagua.abastecimientos (cod_abastecimiento) VALUES ('DELETE-ABAST');
INSERT INTO fonsagua.juntas_agua (cod_abastecimiento) VALUES ('DELETE-ABAST');
INSERT INTO fonsagua.bombeos (cod_abastecimiento, cod_bombeo) VALUES ('DELETE-ABAST', 'codbombeo');
INSERT INTO fonsagua.cobertura (cod_abastecimiento) VALUES ('DELETE-ABAST');
INSERT INTO fonsagua.captaciones (cod_abastecimiento, cod_captacion) VALUES ('DELETE-ABAST', 'codcaptacion');
INSERT INTO fonsagua.dep_intermedios (cod_abastecimiento, cod_dep_intermedio) VALUES ('DELETE-ABAST', 'depintermedio');
INSERT INTO fonsagua.dep_distribucion (cod_abastecimiento, cod_dep_distribucion) VALUES ('DELETE-ABAST', 'depdistribucion');
INSERT INTO fonsagua.tuberias (cod_abastecimiento, cod_tuberia) VALUES ('DELETE-ABAST', 'tuberia');
INSERT INTO fonsagua.gest_comercial (cod_abastecimiento) VALUES ('DELETE-ABAST');
INSERT INTO fonsagua.gest_financiera (cod_abastecimiento) VALUES ('DELETE-ABAST');
INSERT INTO fonsagua.evaluacion (cod_abastecimiento) VALUES ('DELETE-ABAST');

--FUENTES;
INSERT INTO fonsagua.fuentes (cod_fuente, tipo_fuente) VALUES ('DELETE-FUENTE', 'Manantial');
INSERT INTO fonsagua.aforos (cod_fuente) VALUES ('DELETE-FUENTE');
INSERT INTO fonsagua.analiticas (cod_fuente) VALUES ('DELETE-FUENTE');


--ALTERNATIVAS;
INSERT INTO fonsagua.alternativas (cod_alternativa, departamento, municipio, canton) VALUES ('DELETE-ALT', 'dep', 'mun', 'canton');
INSERT INTO fonsagua.preferencias_disenho (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.fuentes_implicadas (cod_alternativa, tipo_fuente) VALUES ('DELETE-ALT', 'Manantial');
INSERT INTO fonsagua.comunidades_implicadas (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.alt_embalses (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.alt_fuentes (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.alt_depositos (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.alt_tuberias (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.alt_bombeos (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.alt_valvulas (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.presupuesto (cod_alternativa) VALUES ('DELETE-ALT');
INSERT INTO fonsagua.alt_conexiones (cod_alternativa) VALUES ('DELETE-ALT');


--MIXTOS;
INSERT INTO fonsagua.r_abastecimientos_comunidades (cod_abastecimiento, cod_comunidad) VALUES ('DELETE-ABAST', 'DELETE-COM');
INSERT INTO fonsagua.valoracion_sistema (cod_comunidad, cod_abastecimiento) VALUES ('DELETE-COM', 'DELETE-ABAST');
INSERT INTO fonsagua.datos_consumo (cod_comunidad, cod_abastecimiento) VALUES ('DELETE-COM', 'DELETE-ABAST');
INSERT INTO fonsagua.r_abastecimientos_fuentes (cod_abastecimiento, cod_fuente) VALUES ('DELETE-ABAST', 'DELETE-FUENTE');

DELETE FROM fonsagua.comunidades WHERE cod_comunidad = 'DELETE-COM';
DELETE FROM fonsagua.abastecimientos WHERE cod_abastecimiento = 'DELETE-ABAST';
DELETE FROM fonsagua.fuentes WHERE cod_fuente = 'DELETE-FUENTE';
DELETE FROM fonsagua.alternativas WHERE cod_alternativa = 'DELETE-ALT';


SELECT is ( (SELECT count(*) FROM fonsagua.puntos_viviendas WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.entrevistadores WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.entrevistados WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.subcuencas WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.adescos WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.cargos_publicos WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.ongs WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.otras_organizaciones WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.produccion_consumo WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.tipos_cultivos WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.areas_potenciales_riego WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.ganaderia WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.cooperativas WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.centros_educativos WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.centros_salud WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.amenazas WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.otros_servicios WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.capacitaciones_riesgos WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.implicacion_comunidad WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.comunidades WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.fuentes_contaminacion WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.habitos_consumo WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.comunidades_croquis WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);

-- ABASTECIMIENTOS
SELECT is ( (SELECT count(*) FROM fonsagua.juntas_agua WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.bombeos WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.cobertura WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.captaciones WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.dep_intermedios WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.dep_distribucion WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.tuberias WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.abastecimientos WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.gest_comercial WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.gest_financiera WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.evaluacion WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);

--FUENTES
SELECT is ( (SELECT count(*) FROM fonsagua.aforos WHERE cod_fuente = 'DELETE-FUENTE'), 0::bigint, 'AFOROS - TEST 35');
SELECT is ( (SELECT count(*) FROM fonsagua.analiticas WHERE cod_fuente = 'DELETE-FUENTE'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.fuentes WHERE cod_fuente = 'DELETE-FUENTE'), 0::bigint);


--ALTERNATIVAS
SELECT is ( (SELECT count(*) FROM fonsagua.preferencias_disenho WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.fuentes_implicadas WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.comunidades_implicadas WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.alt_embalses WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.alt_fuentes WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.alt_depositos WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.alt_tuberias WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.alt_bombeos WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.alt_valvulas WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint, 'alt_valvulas');
SELECT is ( (SELECT count(*) FROM fonsagua.presupuesto WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.alt_conexiones WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.alternativas WHERE cod_alternativa = 'DELETE-ALT'), 0::bigint);

--mixtos
SELECT is ( (SELECT count(*) FROM fonsagua.valoracion_sistema WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.datos_consumo WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.r_abastecimientos_comunidades WHERE cod_comunidad = 'DELETE-COM'), 0::bigint);
SELECT is ( (SELECT count(*) FROM fonsagua.r_abastecimientos_fuentes WHERE cod_abastecimiento = 'DELETE-ABAST'), 0::bigint);

SELECT * from finish();

ROLLBACK;
