


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

CREATE TABLE entrevistados_x (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
               NOT NULL
               REFERENCES comunidades(cod_comunidad)
               ON DELETE CASCADE
               ON UPDATE CASCADE,
       nombre VARCHAR,
       cargo VARCHAR,
       telefono VARCHAR,
       fecha VARCHAR

);

INSERT INTO entrevistados_x (cod_comunidad, nombre, cargo, telefono, fecha) select cod_comunidad, nombre, cargo, telefono, fecha from entrevistados;

drop table entrevistados;

ALTER TABLE entrevistados_x RENAME TO entrevistados;

UPDATE version SET version = '20151027';

COMMIT;
