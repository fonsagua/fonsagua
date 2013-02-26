
CREATE TABLE comunidades (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	fecha DATE, 
	communic VARCHAR, 
	nomdep VARCHAR, 
	coddep INTEGER, 
	nommunic VARCHAR, 
	codmunic INTEGER, 
	nomcanton VARCHAR, 
	codcanton INTEGER, 
	nom_com VARCHAR, 
	cod_cas INTEGER, 
	x INTEGER, 
	y INTEGER, 
	alturac FLOAT, 
	descrpt VARCHAR, 
	cuenca VARCHAR, 
	loccomen VARCHAR, 
	tiponuc VARCHAR, 
	nacicom DATE, 
	tipoporige VARCHAR, 
	lugar_desp VARCHAR, 
	antigdesp VARCHAR, 
	numfam INTEGER, 
	numviv INTEGER, 
	numhab INTEGER, 
	numninhas INTEGER, 
	numninhos INTEGER, 
	totalnin INTEGER, 
	nummujeres INTEGER, 
	numhombres INTEGER, 
	nummujpt INTEGER, 
	numhompt INTEGER, 
	totaladult INTEGER, 
	numanciana INTEGER, 
	numanciano INTEGER, 
	totalancia INTEGER, 
	resdatpob VARCHAR, 
	comdatpob VARCHAR, 
	necesidad1 VARCHAR, 
	necesidad2 VARCHAR, 
	necesidad3 VARCHAR, 
	necesidad4 VARCHAR, 
	hay_adesco VARCHAR, 
	n_adescos INTEGER, 
	perscpub VARCHAR, 
	expongs VARCHAR, 
	obsasporg VARCHAR, 
	hayevacagr VARCHAR, 
	alc_toda_c VARCHAR, 
	tipoevacag VARCHAR, 
	agua_gris_calle VARCHAR, 
	agua_gris_quebrada VARCHAR, 
	haytratagr VARCHAR, 
	trat_trampa INTEGER, 
	trat_biofiltro INTEGER, 
	trat_otros INTEGER, 
	trat_nada INTEGER, 
	trataggris VARCHAR, 
	haylavc VARCHAR, 
	nlavad INTEGER, 
	tipoalma VARCHAR, 
	npilas INTEGER, 
	nbarr INTEGER, 
	usolet VARCHAR, 
	fam_let_vivienda INTEGER, 
	fam_let_comunal INTEGER, 
	fam_let_monte INTEGER, 
	let_hoyo INTEGER, 
	let_abonera INTEGER, 
	let_septica INTEGER, 
	let_hidra INTEGER, 
	usoletabo VARCHAR, 
	pqnouso VARCHAR, 
	trataboner VARCHAR, 
	dispfinab VARCHAR, 
	dletrcap VARCHAR, 
	dispbasur VARCHAR, 
	comentsan VARCHAR, 
	principal VARCHAR, 
	otrosfr VARCHAR, 
	fuentdatos VARCHAR, 
	ultimof VARCHAR, 
	comiter VARCHAR, 
	comite_riesgo_act VARCHAR, 
	comite_riesgo_nom_cont VARCHAR, 
	capacit VARCHAR, 
	comentfr VARCHAR, 
	hayabast VARCHAR, 
	parcviv INTEGER, 
	origaguas VARCHAR, 
	resabagua VARCHAR, 
	fam_manant INTEGER, 
	fam_quebra INTEGER, 
	fam_rio INTEGER, 
	fam_broquel INTEGER, 
	fam_broquel_comunal INTEGER, 
	fam_compran INTEGER, 
	fam_lluvia INTEGER, 
	fam_vecino INTEGER, 
	totnocom INTEGER, 
	llavepub INTEGER, 
	abastdom INTEGER, 
	totcom INTEGER, 
	fuentedat VARCHAR, 
	cominfraag VARCHAR, 
	constotal FLOAT, 
	consdom FLOAT, 
	cons_agr_gan FLOAT, 
	cons_coment VARCHAR, 
	consumhab FLOAT, 
	tmediofu FLOAT, 
	vdeposito FLOAT, 
	usos VARCHAR, 
	seca_tespera FLOAT, 
	seca_suficiencia VARCHAR, 
	lluvia_tespera FLOAT, 
	lluvia_suficiencia VARCHAR, 
	cosaguaext FLOAT, 
	comhabitan VARCHAR, 
	reshabitos VARCHAR, 
	hay_migracion VARCHAR, 
	econ_clas VARCHAR, 
	famagropec FLOAT, 
	famcuenpro INTEGER, 
	granosbasi INTEGER, 
	granbassub VARCHAR, 
	ganad INTEGER, 
	ganadsubs VARCHAR, 
	fam_propia_frut INTEGER, 
	cpotrsubs VARCHAR, 
	fam_propia_otros INTEGER, 
	fam_propia_otros_subs VARCHAR, 
	nfamcuenaj INTEGER, 
	cortacafe INTEGER, 
	cortacanha INTEGER, 
	fam_ajena_frut INTEGER, 
	fam_ajena_otros INTEGER, 
	hayindustr VARCHAR, 
	famindust FLOAT, 
	numfamindu INTEGER, 
	fam_ingresos_industria_formal INTEGER, 
	fam_ingresos_industria_informal INTEGER, 
	numfconstr INTEGER, 
	fam_ingresos_construccion_formal INTEGER, 
	fam_ingresos_construccion_informal INTEGER, 
	numfammaq INTEGER, 
	numfamotro INTEGER, 
	haycomerc VARCHAR, 
	famserv FLOAT, 
	fam_ingresos_comercio INTEGER, 
	fam_ingresos_comercio_formal INTEGER, 
	fam_ingresos_comercio_informal INTEGER, 
	numfamcotr INTEGER, 
	resaeco VARCHAR, 
	comaeco VARCHAR, 
	nfampropie INTEGER, 
	nprophomb INTEGER, 
	npropmuj INTEGER, 
	legalreg INTEGER, 
	legaltram INTEGER, 
	legalnoreg INTEGER, 
	acultivfam FLOAT, 
	nfamarrend INTEGER, 
	famamedh INTEGER, 
	famamedm INTEGER, 
	aculfarren FLOAT, 
	famamedias INTEGER, 
	narrcabh INTEGER, 
	narrcabm INTEGER, 
	aculfcolon FLOAT, 
	ncomppt INTEGER, 
	cmtipocult VARCHAR, 
	sacosfam FLOAT, 
	sacfconspr FLOAT, 
	lugarprox VARCHAR, 
	supregtot FLOAT, 
	tiporegad VARCHAR, 
	areapotrie VARCHAR, 
	agroquim VARCHAR, 
	tipoagroq VARCHAR, 
	consrvsuel VARCHAR, 
	tipocsrvs VARCHAR, 
	formacagro VARCHAR, 
	nfamforagr VARCHAR, 
	formamb VARCHAR, 
	nfamformam VARCHAR, 
	vacas INTEGER, 
	gallinas INTEGER, 
	cerdos INTEGER, 
	burros INTEGER, 
	caballos INTEGER, 
	hay_coop_prod VARCHAR, 
	hayelectr VARCHAR, 
	numvivelec INTEGER, 
	cuota FLOAT, 
	hay_alum_pub VARCHAR, 
	haytelfijo VARCHAR, 
	hay_tlf_movil VARCHAR, 
	hayesc VARCHAR, 
	n_ccedu INTEGER, 
	prog_salud_hay VARCHAR, 
	prog_salud VARCHAR, 
	prog_salud_frec VARCHAR, 
	tipo_as_unidad_salud BOOLEAN, 
	tipo_as_clinica_comunal BOOLEAN, 
	tipo_as_promotor_salud BOOLEAN, 
	tipo_as_otros BOOLEAN, 
	tipo_sanitaria_otro VARCHAR, 
	ccsalud_hay VARCHAR, 
	ccsalud_n INTEGER, 
	tcensalud INTEGER, 
	tcesamo INTEGER, 
	frqcesamo INTEGER, 
	vispromsal VARCHAR, 
	frqvisprom INTEGER, 
	enf_n_tipos VARCHAR, 
	enf_n_muertes INTEGER, 
	enf_a_tipos VARCHAR, 
	enf_a_muertes INTEGER, 
	fnteinfosa VARCHAR, 
	iglesias_n INTEGER, 
	tipo_acceso VARCHAR, 
	tipo_superficie VARCHAR, 
	carrtinv VARCHAR, 
	carrtveran VARCHAR, 
	transpub VARCHAR, 
	frqtrpub INTEGER, 
	distaparad FLOAT, 
	tcabmunvh FLOAT, 
	otro_tipo_transporte VARCHAR, 
	fpropviv INTEGER, 
	mujprop INTEGER, 
	homprop INTEGER, 
	fnoprptviv INTEGER, 
	famvivbarq INTEGER, 
	famvivadob INTEGER, 
	famvivladr INTEGER, 
	famvivotro INTEGER, 
	estufa INTEGER, 
	fogon INTEGER, 
	obsserv VARCHAR, 
	sillano FLOAT, 
	simedia FLOAT, 
	sielevada FLOAT, 
	veg_residencial FLOAT, 
	veg_industrial FLOAT, 
	veg_pastizal FLOAT, 
	veg_cultivos FLOAT, 
	veg_frondoso FLOAT, 
	veg_raleado FLOAT, 
	veg_matorral FLOAT, 
	veg_mangle FLOAT, 
	deforest VARCHAR, 
	avanfragr VARCHAR, 
	riesgeros VARCHAR, 
	freqincend VARCHAR, 
	hayhidrog VARCHAR, 
	hidrgaut VARCHAR, 
	nivpozos VARCHAR, 
	hayconsvma VARCHAR, 
	descconsma VARCHAR, 
	PRIMARY KEY (gid), 
	UNIQUE (id_comunidad), 
	FOREIGN KEY(tiponuc) REFERENCES tiponuc (item), 
	FOREIGN KEY(tipoporige) REFERENCES tipoporige (item), 
	FOREIGN KEY(antigdesp) REFERENCES antigdesp (item), 
	FOREIGN KEY(tipoevacag) REFERENCES tipoevacag (item), 
	FOREIGN KEY(agua_gris_calle) REFERENCES agua_gris_calle (item), 
	FOREIGN KEY(trataboner) REFERENCES trataboner (item), 
	FOREIGN KEY(dletrcap) REFERENCES dletrcap (item), 
	FOREIGN KEY(dispbasur) REFERENCES dispbasur (item), 
	FOREIGN KEY(principal) REFERENCES principal (item), 
	FOREIGN KEY(hayabast) REFERENCES hayabast (item), 
	FOREIGN KEY(origaguas) REFERENCES origaguas (item), 
	FOREIGN KEY(usos) REFERENCES usos (item), 
	FOREIGN KEY(hay_migracion) REFERENCES hay_migracion (item), 
	FOREIGN KEY(tiporegad) REFERENCES tiporegad (item), 
	FOREIGN KEY(tipo_acceso) REFERENCES tipo_acceso (item), 
	FOREIGN KEY(tipo_superficie) REFERENCES tipo_superficie (item), 
	FOREIGN KEY(carrtinv) REFERENCES carrtinv (item), 
	FOREIGN KEY(carrtveran) REFERENCES carrtveran (item), 
	FOREIGN KEY(deforest) REFERENCES deforest (item), 
	FOREIGN KEY(avanfragr) REFERENCES avanfragr (item), 
	FOREIGN KEY(riesgeros) REFERENCES riesgeros (item)
);
SELECT addgeometrycolumn('public', 'comunidades', 'geom', 32616, 'POINT', 2);

CREATE TABLE puntos_viviendas (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	id_vivienda VARCHAR, 
	tipo VARCHAR, 
	x INTEGER, 
	y INTEGER, 
	z FLOAT, 
	descripc VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(tipo) REFERENCES tipo (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);
SELECT addgeometrycolumn('public', 'puntos_viviendas', 'geom', 32616, 'POINT', 2);

CREATE TABLE interlocutores (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	cargoentrv VARCHAR, 
	entrev VARCHAR, 
	telefono INTEGER, 
	fecha VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE entrevistados (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	cargo VARCHAR, 
	entrevist VARCHAR, 
	instit VARCHAR, 
	telefono INTEGER, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE subcuencas (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	subcuenca VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE adescos (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	nombre VARCHAR, 
	ano_const DATE, 
	legalizada VARCHAR, 
	nsocios INTEGER, 
	antiguedad INTEGER, 
	nom_presi VARCHAR, 
	nhombres INTEGER, 
	nmujeres INTEGER, 
	ntotal INTEGER, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE cargos_publicos (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	cargo VARCHAR, 
	nombre VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE ongs (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	ong VARCHAR, 
	fechas VARCHAR, 
	tipoproy VARCHAR, 
	capacit VARCHAR, 
	tipocapac VARCHAR, 
	valoracion VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(valoracion) REFERENCES valoracion (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE otras_organizaciones (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	tipo_org VARCHAR, 
	nombre VARCHAR, 
	fecha_cre VARCHAR, 
	actividad VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(tipo_org) REFERENCES tipo_org (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE fuentes_contaminacion (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	codigofc INTEGER, 
	tipofc VARCHAR, 
	tipoactivi INTEGER, 
	x INTEGER, 
	y INTEGER, 
	altura INTEGER, 
	descripc VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(tipofc) REFERENCES tipofc (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE capacitaciones_gestion_riesgos (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	instit VARCHAR, 
	fecha VARCHAR, 
	temas VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE amenazas (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	codigofr INTEGER, 
	tipofr VARCHAR, 
	familiasaf INTEGER, 
	x INTEGER, 
	y INTEGER, 
	altura INTEGER, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(tipofr) REFERENCES tipofr (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);
SELECT addgeometrycolumn('public', 'amenazas', 'geom', 32616, 'POINT', 2);

CREATE TABLE r_comunidades_abastecimientos (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	id_abastecimiento VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE valoracion_sistema (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	dinero_invertido VARCHAR, 
	tiempo_invertido VARCHAR, 
	descripcion_sist_cobros VARCHAR, 
	eval_servicio_prestado VARCHAR, 
	cantidad_suficiente VARCHAR, 
	servicio_continuo VARCHAR, 
	comodidad_acceso_toma VARCHAR, 
	comentarios_usuarios VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(dinero_invertido) REFERENCES dinero_invertido (item), 
	FOREIGN KEY(tiempo_invertido) REFERENCES tiempo_invertido (item), 
	FOREIGN KEY(descripcion_sist_cobros) REFERENCES descripcion_sist_cobros (item), 
	FOREIGN KEY(eval_servicio_prestado) REFERENCES eval_servicio_prestado (item), 
	FOREIGN KEY(comodidad_acceso_toma) REFERENCES comodidad_acceso_toma (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE datos_consumo (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	codigoab INTEGER, 
	id INTEGER, 
	tipo_abast VARCHAR, 
	usosagua VARCHAR, 
	nveces FLOAT, 
	nmiembros INTEGER, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(tipo_abast) REFERENCES tipo_abast (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE habitos_consumo (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	id INTEGER, 
	con_fam_dia FLOAT, 
	quien VARCHAR, 
	nmiembros INTEGER, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(quien) REFERENCES quien (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE centros_actividad_economica (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	sector_act VARCHAR, 
	tipo_act VARCHAR, 
	numero VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(sector_act) REFERENCES sector_act (item), 
	FOREIGN KEY(tipo_act) REFERENCES tipo_act (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE tipos_cultivos (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	tipo VARCHAR, 
	numfam INTEGER, 
	nfamprop INTEGER, 
	nfamarrend INTEGER, 
	areafam FLOAT, 
	rendim FLOAT, 
	rubro VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE produccion_consumo_comparado (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	id_familia INTEGER, 
	nmiembros INTEGER, 
	produccion FLOAT, 
	consumo FLOAT, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE areas_potenciales_riego (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	id_area_pot VARCHAR, 
	descripcion VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);
SELECT addgeometrycolumn('public', 'areas_potenciales_riego', 'geom', 32616, 'POLYGON', 2);

CREATE TABLE ganaderia (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	tipo VARCHAR, 
	numfam INTEGER, 
	nfamprop INTEGER, 
	nfamarrend INTEGER, 
	areafam FLOAT, 
	rendim FLOAT, 
	rubro VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE cooperativas (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	tipoorg VARCHAR, 
	nasoc INTEGER, 
	recursos VARCHAR, 
	rubros VARCHAR, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);

CREATE TABLE centros_educativos (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	id_cedu INTEGER, 
	nombre VARCHAR, 
	gradosexis VARCHAR, 
	alumnostot INTEGER, 
	alumninhas INTEGER, 
	alumninhos INTEGER, 
	profesotot INTEGER, 
	profescom INTEGER, 
	escsalud VARCHAR, 
	escsaludcu VARCHAR, 
	escsaludfre VARCHAR, 
	comesc VARCHAR, 
	x INTEGER, 
	y INTEGER, 
	z INTEGER, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(gradosexis) REFERENCES gradosexis (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);
SELECT addgeometrycolumn('public', 'centros_educativos', 'geom', 32616, 'POINT', 2);

CREATE TABLE centros_salud (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	codcsal INTEGER, 
	nombre VARCHAR, 
	x INTEGER, 
	y INTEGER, 
	z INTEGER, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);
SELECT addgeometrycolumn('public', 'centros_salud', 'geom', 32616, 'POINT', 2);

CREATE TABLE otros_servicios (
	gid SERIAL NOT NULL, 
	id_comunidad INTEGER, 
	id_servicio INTEGER, 
	nombre VARCHAR, 
	tipo_servicio VARCHAR, 
	x INTEGER, 
	y INTEGER, 
	z INTEGER, 
	PRIMARY KEY (gid), 
	FOREIGN KEY(tipo_servicio) REFERENCES tipo_servicio (item), 
	FOREIGN KEY(id_comunidad) REFERENCES comunidades (id_comunidad)
);
SELECT addgeometrycolumn('public', 'otros_servicios', 'geom', 32616, 'POINT', 2);
