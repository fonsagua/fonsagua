

CREATE TABLE dominios.tuberia_comercial (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tuberia_comercial (item) VALUES
       (' '),
       ('foo'),
       ('bar');

ALTER TABLE dominios.tuberia_comercial OWNER TO fonsagua;


CREATE TABLE dominios.tipo_valvula (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_valvula (item) VALUES
       (' '),
       ('Reductora'),
       ('Sostenedora'),
       ('Rotura de carga'),
       ('Limitadora de caudal'),
       ('Regulación'),
       ('Propósito general');

ALTER TABLE dominios.tipo_valvula OWNER TO fonsagua;


CREATE TABLE dominios.existencia_elemento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.existencia_elemento (item) VALUES
       (' '),
       ('Existente'),
       ('Proyectada');

ALTER TABLE dominios.existencia_elemento OWNER TO fonsagua;


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
