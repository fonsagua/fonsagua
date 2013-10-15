BEGIN;
SELECT plan(16);

SELECT has_index('c_base', 'areas_protegidas', 'areas_protegidas_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'batimetria', 'batimetria_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'bosques_pais', 'bosques_pais_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'cabecera_municipal', 'cabecera_municipal_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'ciudades', 'ciudades_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'carreteras', 'carreteras_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'casas_cultura', 'casas_cultura_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'centros_educativos', 'centros_educativos_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'centros_salud', 'centros_salud_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'curvas_nivel_10m', 'curvas_nivel_10m_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'el_salvador', 'el_salvador_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'fuentes', 'fuentes_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'paises_limitrofes', 'paises_limitrofes_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'pozos', 'pozos_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'resto_paises_mesoamerica', 'resto_paises_mesoamerica_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'rios', 'rios_the_geom_gist', 'the_geom');

SELECT * from finish();
ROLLBACK;