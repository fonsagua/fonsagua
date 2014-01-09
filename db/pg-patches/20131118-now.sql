insert into dominios.tipo values ('Casa mas baja');

alter table fonsagua.otros_servicios drop constraint otros_servicios_tipo_servicio_fkey, add constraint otros_servicios_tipo_servicio_fkey foreign key (tipo_servicio) references dominios.tipo_servicio(item) on update cascade;
update dominios.tipo_servicio set item='Instalación deportiva' where item='Intalación deportiva';


CREATE TABLE dominios.genero (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.genero (item) VALUES
       (' '),
       ('Femenino'),
       ('Masculino');

ALTER TABLE dominios.genero OWNER TO fonsagua;



CREATE TABLE fonsagua.personal_tecnico (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       nombre VARCHAR,
       genero VARCHAR
	       REFERENCES dominios.genero(item),
       origen VARCHAR,
       cargo VARCHAR

);


ALTER TABLE fonsagua.personal_tecnico OWNER TO fonsagua;



CREATE TABLE fonsagua.niveles_freaticos (
       gid SERIAL PRIMARY KEY,
       cod_fuente VARCHAR
              NOT NULL
              REFERENCES fonsagua.fuentes(cod_fuente)
              ON DELETE CASCADE
              ON UPDATE CASCADE,
       nivel FLOAT,
       fecha Date,
       hora VARCHAR

);


ALTER TABLE fonsagua.niveles_freaticos OWNER TO fonsagua;

ALTER TABLE fonsagua.fuentes ADD COLUMN uso_bebida boolean;

ALTER TABLE fonsagua.fuentes ADD COLUMN alternativa_viable boolean;


CREATE TABLE dominios.funcionamiento_verano (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.funcionamiento_verano (item) VALUES
       (' '),
       ('No varía caudal'),
       ('Disminuye caudal'),
       ('Se seca');

ALTER TABLE dominios.funcionamiento_verano OWNER TO fonsagua;

ALTER TABLE fonsagua.fuentes ADD COLUMN funcionamiento_verano VARCHAR REFERENCES dominios.funcionamiento_verano(item);



insert into dominios.tipo_organizacion values ('Caja rural');


ALTER TABLE fonsagua.abastecimientos ADD COLUMN tarifa_variable BOOLEAN;
ALTER TABLE fonsagua.abastecimientos ADD COLUMN cuota_variable NUMERIC(12,2);