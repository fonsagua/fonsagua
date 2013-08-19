

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


CREATE TABLE dominios.tipo_distribucion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_distribucion (item) VALUES
       (' '),
       ('Domiciliar'),
       ('Cantareras');

ALTER TABLE dominios.tipo_distribucion OWNER TO fonsagua;


CREATE TABLE dominios.existencia_elemento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.existencia_elemento (item) VALUES
       (' '),
       ('Existente'),
       ('Proyectada');

ALTER TABLE dominios.existencia_elemento OWNER TO fonsagua;


CREATE TABLE dominios.tuberia_comercial (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tuberia_comercial (item) VALUES
       (' '),
       ('foo'),
       ('bar');

ALTER TABLE dominios.tuberia_comercial OWNER TO fonsagua;


CREATE TABLE dominios.bomba_comercial (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.bomba_comercial (item) VALUES
       (' '),
       ('foo'),
       ('bar');

ALTER TABLE dominios.bomba_comercial OWNER TO fonsagua;


CREATE TABLE dominios.tipo_deposito (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_deposito (item) VALUES
       (' '),
       ('Distribución'),
       ('Bombeo');

ALTER TABLE dominios.tipo_deposito OWNER TO fonsagua;
