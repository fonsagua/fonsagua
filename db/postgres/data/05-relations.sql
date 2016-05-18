CREATE TABLE fonsagua.r_abastecimientos_comunidades (
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento) ON UPDATE CASCADE ON DELETE CASCADE,
       cod_comunidad VARCHAR
	       NOT NULL
       	       REFERENCES fonsagua.comunidades(cod_comunidad) ON UPDATE CASCADE ON DELETE CASCADE,
       PRIMARY KEY (cod_abastecimiento, cod_comunidad)
);

ALTER TABLE fonsagua.r_abastecimientos_comunidades OWNER TO fonsagua;


CREATE TABLE fonsagua.r_abastecimientos_fuentes (
       cod_abastecimiento VARCHAR
	       NOT NULL
       	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento) ON UPDATE CASCADE ON DELETE CASCADE,
       cod_fuente VARCHAR
	       NOT NULL
       	       REFERENCES fonsagua.fuentes(cod_fuente) ON UPDATE CASCADE ON DELETE CASCADE,
       PRIMARY KEY (cod_abastecimiento, cod_fuente)
);

ALTER TABLE fonsagua.r_abastecimientos_fuentes OWNER TO fonsagua;

ALTER TABLE fonsagua.datos_consumo ADD CONSTRAINT datos_consumo_cod_comunidad_cod_abastecimiento_fkey FOREIGN KEY (cod_comunidad, cod_abastecimiento)
      REFERENCES fonsagua.r_abastecimientos_comunidades (cod_comunidad, cod_abastecimiento) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;
ALTER TABLE fonsagua.valoracion_sistema ADD CONSTRAINT valoracion_sistema_cod_comunidad_cod_abastecimiento_fkey FOREIGN KEY (cod_comunidad, cod_abastecimiento)
      REFERENCES fonsagua.r_abastecimientos_comunidades (cod_comunidad, cod_abastecimiento) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;
