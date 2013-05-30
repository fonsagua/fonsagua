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
