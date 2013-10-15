BEGIN;
SELECT plan(23);

SELECT has_index('fonsagua', 'puntos_viviendas', 'puntos_viviendas_geom_idx', 'geom');
SELECT has_index('fonsagua', 'areas_potenciales_riego', 'areas_potenciales_riego_geom_idx', 'geom');
SELECT has_index('fonsagua', 'centros_educativos', 'centros_educativos_geom_idx', 'geom');
SELECT has_index('fonsagua', 'centros_salud', 'centros_salud_geom_idx', 'geom');
SELECT has_index('fonsagua', 'amenazas', 'amenazas_geom_idx', 'geom');
SELECT has_index('fonsagua', 'otros_servicios', 'otros_servicios_geom_idx', 'geom');
SELECT has_index('fonsagua', 'comunidades', 'comunidades_geom_idx', 'geom');
SELECT has_index('fonsagua', 'fuentes_contaminacion', 'fuentes_contaminacion_geom_idx', 'geom');
SELECT has_index('fonsagua', 'bombeos', 'bombeos_geom_idx', 'geom');
SELECT has_index('fonsagua', 'captaciones', 'captaciones_geom_idx', 'geom');
SELECT has_index('fonsagua', 'dep_intermedios', 'dep_intermedios_geom_idx', 'geom');
SELECT has_index('fonsagua', 'dep_distribucion', 'dep_distribucion_geom_idx', 'geom');
SELECT has_index('fonsagua', 'tuberias', 'tuberias_geom_idx', 'geom');
SELECT has_index('fonsagua', 'abastecimientos', 'abastecimientos_geom_idx', 'geom');
SELECT has_index('fonsagua', 'fuentes', 'fuentes_geom_idx', 'geom');
SELECT has_index('fonsagua', 'alt_embalses', 'alt_embalses_geom_idx', 'geom');
SELECT has_index('fonsagua', 'alt_fuentes', 'alt_fuentes_geom_idx', 'geom');
SELECT has_index('fonsagua', 'alt_depositos', 'alt_depositos_geom_idx', 'geom');
SELECT has_index('fonsagua', 'alt_tuberias', 'alt_tuberias_geom_idx', 'geom');
SELECT has_index('fonsagua', 'alt_bombeos', 'alt_bombeos_geom_idx', 'geom');
SELECT has_index('fonsagua', 'alt_valvulas', 'alt_valvulas_geom_idx', 'geom');
SELECT has_index('fonsagua', 'alt_conexiones', 'alt_conexiones_geom_idx', 'geom');
SELECT has_index('fonsagua', 'alternativas', 'alternativas_geom_idx', 'geom');

SELECT * from finish();
ROLLBACK;
