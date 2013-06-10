BEGIN;

SELECT plan(15);

SELECT has_index('fonsagua', 'comunidades', 'comunidades_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'puntos_viviendas', 'puntos_viviendas_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'areas_potenciales_riego', 'areas_potenciales_riego_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'centros_educativos', 'centros_educativos_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'centros_salud', 'centros_salud_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'otros_servicios', 'otros_servicios_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'amenazas', 'amenazas_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'abastecimientos', 'abastecimientos_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'fuentes_contaminacion', 'fuentes_contaminacion_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'bombeos', 'bombeos_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'captaciones', 'captaciones_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'dep_intermedios', 'dep_intermedios_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'dep_distribucion', 'dep_distribucion_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'tuberias', 'tuberias_geom_idx', 'the_geom');
SELECT has_index('fonsagua', 'fuentes', 'fuentes_geom_idx', 'the_geom');

SELECT * from finish();

ROLLBACK;
