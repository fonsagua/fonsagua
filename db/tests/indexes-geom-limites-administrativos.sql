BEGIN;
SELECT plan(3);

\set limites_administrativos '\'limites_administrativos\''

-- a workround to create a constant in postgresql
--create function pg_temp.schemaname() returns text as 
--$$ select 'limites_administrativos'::text $$ language sql;


SELECT has_index(:limites_administrativos, 'cantones', 'cantones_geom_idx', 'geom');
SELECT has_index(:limites_administrativos, 'departamentos', 'departamentos_geom_idx', 'geom');
SELECT has_index(:limites_administrativos, 'municipios', 'municipios_geom_idx', 'geom');

SELECT * from finish();
ROLLBACK;
