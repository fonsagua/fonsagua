


BEGIN;

UPDATE tipo_construccion SET item = 'Ferrocemento' WHERE item = 'Fibrocemento';
UPDATE alt_depositos SET tipo_construccion = 'Ferrocemento' WHERE tipo_construccion = 'Fibrocemento';
UPDATE dep_intermedios SET tipo_construccion = 'Ferrocemento' WHERE tipo_construccion = 'Fibrocemento';
UPDATE dep_distribucion SET tipo_construccion = 'Ferrocemento' WHERE tipo_construccion = 'Fibrocemento';
UPDATE captaciones SET tipo_construccion = 'Ferrocemento' WHERE tipo_construccion = 'Fibrocemento';

DELETE FROM origen_aguas WHERE item= 'Anda';


ALTER TABLE alt_fuentes ADD COLUMN cota NUMERIC(12,2);
ALTER TABLE alt_fuentes ADD COLUMN profundidad_pozo NUMERIC(12,2);
ALTER TABLE alt_fuentes ADD COLUMN margen_fondo NUMERIC(12,2);
ALTER TABLE alt_fuentes ADD COLUMN nivel_dinamico NUMERIC(12,2);

UPDATE version SET version = '20151027';

COMMIT;
