BEGIN TRANSACTION;
CREATE TABLE datos_consumo_backup (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR NOT NULL,
       cod_abastecimiento VARCHAR NOT NULL,
       tipo_distribucion VARCHAR
	       REFERENCES tipo_distribucion(item)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       usos_agua VARCHAR,
       consumo FLOAT,
       n_miembros INTEGER,
       FOREIGN KEY (cod_comunidad, cod_abastecimiento)
		REFERENCES r_abastecimientos_comunidades (cod_comunidad, cod_abastecimiento)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO datos_consumo_backup SELECT gid, cod_comunidad, cod_abastecimiento, tipo_distribucion, usos_agua, consumo, n_miembros FROM datos_consumo;
DROP TABLE datos_consumo;
CREATE TABLE datos_consumo (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR NOT NULL,
       cod_abastecimiento VARCHAR NOT NULL,
       tipo_distribucion VARCHAR
	       REFERENCES tipo_distribucion(item)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       usos_agua VARCHAR,
       consumo FLOAT,
       n_miembros INTEGER,
       FOREIGN KEY (cod_comunidad, cod_abastecimiento)
		REFERENCES r_abastecimientos_comunidades (cod_comunidad, cod_abastecimiento)
		ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO datos_consumo SELECT gid, cod_comunidad, cod_abastecimiento, tipo_distribucion, usos_agua, consumo, n_miembros FROM datos_consumo_backup;
DROP TABLE datos_consumo_backup;
COMMIT;
