CREATE TABLE tipo_alternativa (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_alternativa (item) SELECT
       (' ') UNION SELECT
       ('Alternativa') UNION SELECT
       ('Sustitución') UNION SELECT
       ('Ampliación');


CREATE TABLE tipo_saneamiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_saneamiento (item) SELECT
       (' ') UNION SELECT
       ('Sistema de alcantarillado') UNION SELECT
       ('Letrinas') UNION SELECT
       ('Mixto');


CREATE TABLE tipo_fuente_alternativa (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_fuente_alternativa (item) SELECT
       (' ') UNION SELECT
       ('Pozo') UNION SELECT
       ('Punto rio') UNION SELECT
       ('Manantial');


CREATE TABLE existencia_elemento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO existencia_elemento (item) SELECT
       (' ') UNION SELECT
       ('Existente') UNION SELECT
       ('Proyectada');


CREATE TABLE tipo_deposito (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_deposito (item) SELECT
       (' ') UNION SELECT
       ('Distribución') UNION SELECT
       ('Bombeo');


CREATE TABLE prioridad_alt (
       item INTEGER
	       PRIMARY KEY

);

INSERT INTO prioridad_alt (item) SELECT
       (1) UNION SELECT
       (2) UNION SELECT
       (3) UNION SELECT
       (4) UNION SELECT
       (5) UNION SELECT
       (6) UNION SELECT
       (7) UNION SELECT
       (8) UNION SELECT
       (9) UNION SELECT
       (10) UNION SELECT
       (11) UNION SELECT
       (12) UNION SELECT
       (13) UNION SELECT
       (14) UNION SELECT
       (15) UNION SELECT
       (16) UNION SELECT
       (17) UNION SELECT
       (18) UNION SELECT
       (19);
