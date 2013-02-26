
CREATE TABLE si_no (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO si_no (item) VALUES ('Si');
INSERT INTO si_no (item) VALUES ('No');

CREATE TABLE tiponuc (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tiponuc (item) VALUES ('Concentrado');
INSERT INTO tiponuc (item) VALUES ('disperso');

CREATE TABLE tipoporige (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipoporige (item) VALUES ('Oriunda');
INSERT INTO tipoporige (item) VALUES ('desplazada');
INSERT INTO tipoporige (item) VALUES ('mixta');

CREATE TABLE antigdesp (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO antigdesp (item) VALUES ('más de 5 años');
INSERT INTO antigdesp (item) VALUES ('menos de 5 años');

CREATE TABLE tipoevacag (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipoevacag (item) VALUES ('Calle');
INSERT INTO tipoevacag (item) VALUES ('Sistema comunal');
INSERT INTO tipoevacag (item) VALUES ('Sistema familiar');
INSERT INTO tipoevacag (item) VALUES ('Otros');

CREATE TABLE agua_gris_calle (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO agua_gris_calle (item) VALUES ('Mucho');
INSERT INTO agua_gris_calle (item) VALUES ('Poco');
INSERT INTO agua_gris_calle (item) VALUES ('Nada');

CREATE TABLE trataboner (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO trataboner (item) VALUES ('ceniza');
INSERT INTO trataboner (item) VALUES ('cal');
INSERT INTO trataboner (item) VALUES ('aserrín');
INSERT INTO trataboner (item) VALUES ('otros');

CREATE TABLE dletrcap (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO dletrcap (item) VALUES ('mas de 30m');
INSERT INTO dletrcap (item) VALUES ('menos de 30 m');

CREATE TABLE dispbasur (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO dispbasur (item) VALUES ('No se hace nada');
INSERT INTO dispbasur (item) VALUES ('se traslada a otro lugar');
INSERT INTO dispbasur (item) VALUES ('se entierra');
INSERT INTO dispbasur (item) VALUES ('se quema');
INSERT INTO dispbasur (item) VALUES ('pasa el trend e aseo ( camión)');
INSERT INTO dispbasur (item) VALUES ('otros');

CREATE TABLE principal (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO principal (item) VALUES ('Deslizamientos');
INSERT INTO principal (item) VALUES ('Desbordamiento de río');
INSERT INTO principal (item) VALUES ('Inundaciones');
INSERT INTO principal (item) VALUES ('Puntos de asalto');
INSERT INTO principal (item) VALUES ('Otros');
INSERT INTO principal (item) VALUES ('Ninguno');

CREATE TABLE hayabast (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO hayabast (item) VALUES ('Si');
INSERT INTO hayabast (item) VALUES ('No');
INSERT INTO hayabast (item) VALUES ('Parcial');

CREATE TABLE origaguas (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO origaguas (item) VALUES ('manantial');
INSERT INTO origaguas (item) VALUES ('río');
INSERT INTO origaguas (item) VALUES ('Pozo');
INSERT INTO origaguas (item) VALUES ('Anda');

CREATE TABLE usos (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO usos (item) VALUES ('Manantial');
INSERT INTO usos (item) VALUES ('río');
INSERT INTO usos (item) VALUES ('pozo');
INSERT INTO usos (item) VALUES ('quebrada');
INSERT INTO usos (item) VALUES ('compra');

CREATE TABLE hay_migracion (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO hay_migracion (item) VALUES ('mucha');
INSERT INTO hay_migracion (item) VALUES ('media');
INSERT INTO hay_migracion (item) VALUES ('poca');

CREATE TABLE tiporegad (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tiporegad (item) VALUES ('Inundacion');
INSERT INTO tiporegad (item) VALUES ('Aspersion');
INSERT INTO tiporegad (item) VALUES ('Goteo');
INSERT INTO tiporegad (item) VALUES ('Ninguno');

CREATE TABLE tipo_acceso (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipo_acceso (item) VALUES ('Vereda');
INSERT INTO tipo_acceso (item) VALUES ('Calle');
INSERT INTO tipo_acceso (item) VALUES ('Carretera');

CREATE TABLE tipo_superficie (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipo_superficie (item) VALUES ('Tierra');
INSERT INTO tipo_superficie (item) VALUES ('Balastro');
INSERT INTO tipo_superficie (item) VALUES ('Empedrado');
INSERT INTO tipo_superficie (item) VALUES ('Pavimentado');

CREATE TABLE carrtinv (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO carrtinv (item) VALUES ('Transitable');
INSERT INTO carrtinv (item) VALUES ('Intransitable');

CREATE TABLE carrtveran (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO carrtveran (item) VALUES ('Transitable');
INSERT INTO carrtveran (item) VALUES ('Intransitable');

CREATE TABLE deforest (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO deforest (item) VALUES ('alta');
INSERT INTO deforest (item) VALUES ('media');
INSERT INTO deforest (item) VALUES ('baja');

CREATE TABLE avanfragr (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO avanfragr (item) VALUES ('alta');
INSERT INTO avanfragr (item) VALUES ('media');
INSERT INTO avanfragr (item) VALUES ('baja');

CREATE TABLE riesgeros (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO riesgeros (item) VALUES ('alta');
INSERT INTO riesgeros (item) VALUES ('media');
INSERT INTO riesgeros (item) VALUES ('baja');

CREATE TABLE tipo (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipo (item) VALUES ('Punto de referencia');
INSERT INTO tipo (item) VALUES ('Casa extremos norte');
INSERT INTO tipo (item) VALUES ('Casa extremo sur');
INSERT INTO tipo (item) VALUES ('Casa extremo oriente');
INSERT INTO tipo (item) VALUES ('Casa extremo poniente');
INSERT INTO tipo (item) VALUES ('Casa mas alta');
INSERT INTO tipo (item) VALUES ('Primera casa con electricidad');
INSERT INTO tipo (item) VALUES ('Última casa con electricidad');
INSERT INTO tipo (item) VALUES ('Otras casas');

CREATE TABLE valoracion (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO valoracion (item) VALUES ('bien');
INSERT INTO valoracion (item) VALUES ('regular');
INSERT INTO valoracion (item) VALUES ('mal');

CREATE TABLE tipo_org (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipo_org (item) VALUES ('Asociación de padres y madres de familia');
INSERT INTO tipo_org (item) VALUES ('cooperativa de producción');
INSERT INTO tipo_org (item) VALUES ('Comité de iglesia');
INSERT INTO tipo_org (item) VALUES ('Comité de  jóvenes');
INSERT INTO tipo_org (item) VALUES ('otros');

CREATE TABLE tipofc (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipofc (item) VALUES ('Puntos de vertido al río');
INSERT INTO tipofc (item) VALUES ('basureros');
INSERT INTO tipofc (item) VALUES ('puntos de lavado y bañado');
INSERT INTO tipofc (item) VALUES ('Abrevaderos');
INSERT INTO tipofc (item) VALUES ('otras fuentes de contaminación');

CREATE TABLE tipofr (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipofr (item) VALUES ('Deslizamientos');
INSERT INTO tipofr (item) VALUES ('Desbordamiento de río');
INSERT INTO tipofr (item) VALUES ('Inundaciones');
INSERT INTO tipofr (item) VALUES ('Puntos de asalto');
INSERT INTO tipofr (item) VALUES ('Otros');

CREATE TABLE dinero_invertido (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO dinero_invertido (item) VALUES ('alto');
INSERT INTO dinero_invertido (item) VALUES ('medio');
INSERT INTO dinero_invertido (item) VALUES ('bajo');

CREATE TABLE tiempo_invertido (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tiempo_invertido (item) VALUES ('alto');
INSERT INTO tiempo_invertido (item) VALUES ('medio');
INSERT INTO tiempo_invertido (item) VALUES ('bajo');

CREATE TABLE descripcion_sist_cobros (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO descripcion_sist_cobros (item) VALUES ('Bueno');
INSERT INTO descripcion_sist_cobros (item) VALUES ('regular');
INSERT INTO descripcion_sist_cobros (item) VALUES ('malo');

CREATE TABLE eval_servicio_prestado (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO eval_servicio_prestado (item) VALUES ('Bueno');
INSERT INTO eval_servicio_prestado (item) VALUES ('regular');
INSERT INTO eval_servicio_prestado (item) VALUES ('malo');

CREATE TABLE comodidad_acceso_toma (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO comodidad_acceso_toma (item) VALUES ('difícil');
INSERT INTO comodidad_acceso_toma (item) VALUES ('fácil');

CREATE TABLE tipo_abast (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipo_abast (item) VALUES ('Chorro');
INSERT INTO tipo_abast (item) VALUES ('cantarera');

CREATE TABLE quien (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO quien (item) VALUES ('mujeres');
INSERT INTO quien (item) VALUES ('niños');
INSERT INTO quien (item) VALUES ('niñas');
INSERT INTO quien (item) VALUES ('hombres');

CREATE TABLE sector_act (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO sector_act (item) VALUES ('primario');
INSERT INTO sector_act (item) VALUES ('secundario');
INSERT INTO sector_act (item) VALUES ('terciario');

CREATE TABLE tipo_act (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipo_act (item) VALUES ('Agropecuario');
INSERT INTO tipo_act (item) VALUES ('industria');
INSERT INTO tipo_act (item) VALUES ('construcción');
INSERT INTO tipo_act (item) VALUES ('maquila');
INSERT INTO tipo_act (item) VALUES ('comercio');
INSERT INTO tipo_act (item) VALUES ('otros');

CREATE TABLE gradosexis (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO gradosexis (item) VALUES ('Parvularia');
INSERT INTO gradosexis (item) VALUES ('Primaria');
INSERT INTO gradosexis (item) VALUES ('Báasica');
INSERT INTO gradosexis (item) VALUES ('Media');

CREATE TABLE tipo_servicio (
	item VARCHAR NOT NULL, 
	PRIMARY KEY (item)
);
INSERT INTO tipo_servicio (item) VALUES ('Iglesia');
INSERT INTO tipo_servicio (item) VALUES ('Intalación deportiva');
INSERT INTO tipo_servicio (item) VALUES ('Centro comunitario');
INSERT INTO tipo_servicio (item) VALUES ('Puesto policial');
INSERT INTO tipo_servicio (item) VALUES ('Otros');
