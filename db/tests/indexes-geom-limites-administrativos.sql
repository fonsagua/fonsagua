BEGIN;

SELECT plan(3);

-- a workround to create a constant in postgresql
create function pg_temp.schemaname() returns text as 
$$ select 'limites_administrativos'::text $$ language sql;


SELECT has_index(pg_temp.schemaname(), 'cantones', 'cantones_geom_idx', 'geom');
SELECT has_index(pg_temp.schemaname(), 'departamentos', 'departamentos_geom_idx', 'geom');
SELECT has_index(pg_temp.schemaname(), 'municipios', 'municipios_geom_idx', 'geom');

SELECT * from finish();

ROLLBACK;
