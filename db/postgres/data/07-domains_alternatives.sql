

CREATE TABLE dominios.tipo_alternativa (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_alternativa (item) VALUES
       (' '),
       ('Alternativa'),
       ('Sustitución'),
       ('Ampliación');

ALTER TABLE dominios.tipo_alternativa OWNER TO fonsagua;


CREATE TABLE dominios.tipo_saneamiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_saneamiento (item) VALUES
       (' '),
       ('Sistema de alcantarillado'),
       ('Letrinas'),
       ('Mixto');

ALTER TABLE dominios.tipo_saneamiento OWNER TO fonsagua;


CREATE TABLE dominios.tipo_fuente_alternativa (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_fuente_alternativa (item) VALUES
       (' '),
       ('Pozo'),
       ('Punto rio'),
       ('Manantial');

ALTER TABLE dominios.tipo_fuente_alternativa OWNER TO fonsagua;


CREATE TABLE dominios.existencia_elemento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.existencia_elemento (item) VALUES
       (' '),
       ('Existente'),
       ('Proyectada');

ALTER TABLE dominios.existencia_elemento OWNER TO fonsagua;


CREATE TABLE dominios.tipo_deposito (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_deposito (item) VALUES
       (' '),
       ('Distribución'),
       ('Bombeo');

ALTER TABLE dominios.tipo_deposito OWNER TO fonsagua;


CREATE TABLE dominios.prioridad_alt (
       item INTEGER
	       PRIMARY KEY

);

INSERT INTO dominios.prioridad_alt (item) VALUES
       (1),
       (2),
       (3),
       (4),
       (5),
       (6),
       (7),
       (8),
       (9),
       (10),
       (11),
       (12),
       (13),
       (14),
       (15),
       (16),
       (17),
       (18),
       (19);

ALTER TABLE dominios.prioridad_alt OWNER TO fonsagua;
