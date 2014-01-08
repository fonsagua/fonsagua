insert into dominios.tipo values ('Casa mas baja');

alter table fonsagua.otros_servicios drop constraint otros_servicios_tipo_servicio_fkey, add constraint otros_servicios_tipo_servicio_fkey foreign key (tipo_servicio) references dominios.tipo_servicio(item) on update cascade;
update dominios.tipo_servicio set item='Instalación deportiva' where item='Intalación deportiva';



CREATE TABLE fonsagua.personal_tecnico (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
              NOT NULL
              REFERENCES fonsagua.abastecimientos(cod_abastecimiento)
              ON DELETE CASCADE
              ON UPDATE CASCADE,
       nombre VARCHAR,
       genero VARCHAR,
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