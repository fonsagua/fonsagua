BEGIN;

SELECT plan(21);

SELECT has_index('c_base', 'areas_protegidas', 'areas_protegidas_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'batimetria', 'batimetria_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'bosques_pais', 'bosques_pais_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'cabecera_municipal', 'cabecera_municipal_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'cantones', 'cantones_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'ciudades', 'ciudades_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'carreteras', 'carreteras_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'casas_cultura', 'casas_cultura_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'centros_educativos', 'centros_educativos_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'curvas_nivel_100m_poligono', 'curvas_nivel_100m_poligono_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'centros_salud', 'centros_salud_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'cuencas', 'cuencas_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'curvas_nivel_10m', 'curvas_nivel_10m_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'departamentos', 'departamentos_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'el_salvador', 'el_salvador_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'fuentes', 'fuentes_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'municipios', 'municipios_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'paises_limitrofes', 'paises_limitrofes_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'pozos', 'pozos_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'resto_paises_mesoamerica', 'resto_paises_mesoamerica_the_geom_gist', 'the_geom');
SELECT has_index('c_base', 'rios', 'rios_the_geom_gist', 'the_geom');

SELECT * from finish();

ROLLBACK;

--SELECT 'SELECT has_index(''c_base'', ''' || c.relname ||''', ''' || c.relname || '_the_geom_gist'', ''the_geom'');' 
--FROM pg_catalog.pg_class c 
--LEFT JOIN pg_catalog.pg_user u ON u.usesysid = c.relowner 
--LEFT JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace 
--WHERE n.nspname='c_base' AND c.relkind IN ('r','') 
--AND n.nspname NOT IN ('pg_catalog', 'pg_toast', 'information_schema')
--;