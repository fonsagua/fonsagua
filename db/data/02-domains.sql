

CREATE TABLE dominios.tip_nucleo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tip_nucleo (item) VALUES
       (' '),
       ('Concentrado'),
       ('Disperso');

ALTER TABLE dominios.tip_nucleo OWNER TO fonsagua;


CREATE TABLE dominios.tip_origen (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tip_origen (item) VALUES
       (' '),
       ('Oriunda'),
       ('Desplazada'),
       ('Mixta');

ALTER TABLE dominios.tip_origen OWNER TO fonsagua;


CREATE TABLE dominios.antiguedad (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.antiguedad (item) VALUES
       (' '),
       ('Más de 5 años'),
       ('Menos de 5 años');

ALTER TABLE dominios.antiguedad OWNER TO fonsagua;


CREATE TABLE dominios.emigracion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.emigracion (item) VALUES
       (' '),
       ('Mucha'),
       ('Media'),
       ('Poca');

ALTER TABLE dominios.emigracion OWNER TO fonsagua;


CREATE TABLE dominios.tip_regadio (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tip_regadio (item) VALUES
       (' '),
       ('Inundación'),
       ('Aspersión'),
       ('Goteo'),
       ('Ninguno');

ALTER TABLE dominios.tip_regadio OWNER TO fonsagua;


CREATE TABLE dominios.tip_acceso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tip_acceso (item) VALUES
       (' '),
       ('Vereda'),
       ('Calle'),
       ('Carretera');

ALTER TABLE dominios.tip_acceso OWNER TO fonsagua;


CREATE TABLE dominios.tip_sup_acceso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tip_sup_acceso (item) VALUES
       (' '),
       ('Tierra'),
       ('Balastro'),
       ('Empedrado'),
       ('Pavimentado');

ALTER TABLE dominios.tip_sup_acceso OWNER TO fonsagua;


CREATE TABLE dominios.acceso_ver (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.acceso_ver (item) VALUES
       (' '),
       ('Transitable'),
       ('Intransitable');

ALTER TABLE dominios.acceso_ver OWNER TO fonsagua;


CREATE TABLE dominios.acceso_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.acceso_inv (item) VALUES
       (' '),
       ('Transitable'),
       ('Intransitable');

ALTER TABLE dominios.acceso_inv OWNER TO fonsagua;


CREATE TABLE dominios.veg_agr_tip (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.veg_agr_tip (item) VALUES
       (' '),
       ('Pastizal'),
       ('Cultivos');

ALTER TABLE dominios.veg_agr_tip OWNER TO fonsagua;


CREATE TABLE dominios.veg_for_tip (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.veg_for_tip (item) VALUES
       (' '),
       ('Raleado'),
       ('Frondoso'),
       ('Matorral'),
       ('Mangle');

ALTER TABLE dominios.veg_for_tip OWNER TO fonsagua;


CREATE TABLE dominios.deforestacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.deforestacion (item) VALUES
       (' '),
       ('Alta'),
       ('Media'),
       ('Baja');

ALTER TABLE dominios.deforestacion OWNER TO fonsagua;


CREATE TABLE dominios.avance_fagricola (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.avance_fagricola (item) VALUES
       (' '),
       ('Alta'),
       ('Media'),
       ('Baja');

ALTER TABLE dominios.avance_fagricola OWNER TO fonsagua;


CREATE TABLE dominios.riesgo_erosion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.riesgo_erosion (item) VALUES
       (' '),
       ('Alto'),
       ('Medio'),
       ('Bajo');

ALTER TABLE dominios.riesgo_erosion OWNER TO fonsagua;


CREATE TABLE dominios.sist_abastecimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.sist_abastecimiento (item) VALUES
       (' '),
       ('Si'),
       ('No'),
       ('Parcial');

ALTER TABLE dominios.sist_abastecimiento OWNER TO fonsagua;


CREATE TABLE dominios.origen_aguas (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.origen_aguas (item) VALUES
       (' '),
       ('Manantial'),
       ('Río'),
       ('Pozo'),
       ('Anda');

ALTER TABLE dominios.origen_aguas OWNER TO fonsagua;


CREATE TABLE dominios.ag_gris_calle (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.ag_gris_calle (item) VALUES
       (' '),
       ('Mucho'),
       ('Poco'),
       ('Nada');

ALTER TABLE dominios.ag_gris_calle OWNER TO fonsagua;


CREATE TABLE dominios.trat_aboneras (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.trat_aboneras (item) VALUES
       (' '),
       ('Ceniza'),
       ('Cal'),
       ('Aserrín'),
       ('Otros');

ALTER TABLE dominios.trat_aboneras OWNER TO fonsagua;


CREATE TABLE dominios.dist_let_agua (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.dist_let_agua (item) VALUES
       (' '),
       ('Más de 30 m'),
       ('Menos de 30 m');

ALTER TABLE dominios.dist_let_agua OWNER TO fonsagua;


CREATE TABLE dominios.disp_basuras (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.disp_basuras (item) VALUES
       (' '),
       ('No se hace nada'),
       ('Se traslada a otro lugar'),
       ('Se entierra'),
       ('Se quema'),
       ('Pasa el tren de aseo ( camión)'),
       ('Otros');

ALTER TABLE dominios.disp_basuras OWNER TO fonsagua;


CREATE TABLE dominios.tipo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo (item) VALUES
       (' '),
       ('Punto de referencia'),
       ('Casa extremo norte'),
       ('Casa extremo sur'),
       ('Casa extremo oriente'),
       ('Casa extremo poniente'),
       ('Casa mas alta'),
       ('Primera casa con electricidad'),
       ('Última casa con electricidad'),
       ('Otras casas');

ALTER TABLE dominios.tipo OWNER TO fonsagua;


CREATE TABLE dominios.valoracion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.valoracion (item) VALUES
       (' '),
       ('Bien'),
       ('Regular'),
       ('Mal');

ALTER TABLE dominios.valoracion OWNER TO fonsagua;


CREATE TABLE dominios.tipo_organizacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_organizacion (item) VALUES
       (' '),
       ('Asociación de padres y madres de familia'),
       ('Cooperativa de producción'),
       ('Comité de iglesia'),
       ('Comité de jóvenes'),
       ('Otros');

ALTER TABLE dominios.tipo_organizacion OWNER TO fonsagua;


CREATE TABLE dominios.niveles (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.niveles (item) VALUES
       (' '),
       ('Parvularia'),
       ('Primaria'),
       ('Básica'),
       ('Media');

ALTER TABLE dominios.niveles OWNER TO fonsagua;


CREATE TABLE dominios.tipo_servicio (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_servicio (item) VALUES
       (' '),
       ('Iglesia'),
       ('Intalación deportiva'),
       ('Centro comunitario'),
       ('Puesto policial'),
       ('Otros');

ALTER TABLE dominios.tipo_servicio OWNER TO fonsagua;


CREATE TABLE dominios.tipo_amenaza (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_amenaza (item) VALUES
       (' '),
       ('Deslizamientos'),
       ('Desbordamiento de río'),
       ('Inundaciones'),
       ('Puntos de asalto'),
       ('Otros');

ALTER TABLE dominios.tipo_amenaza OWNER TO fonsagua;


CREATE TABLE dominios.dinero_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.dinero_inv (item) VALUES
       (' '),
       ('Alto'),
       ('Medio'),
       ('Bajo');

ALTER TABLE dominios.dinero_inv OWNER TO fonsagua;


CREATE TABLE dominios.tiempo_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tiempo_inv (item) VALUES
       (' '),
       ('Alto'),
       ('Medio'),
       ('Bajo');

ALTER TABLE dominios.tiempo_inv OWNER TO fonsagua;


CREATE TABLE dominios.sist_cobros (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.sist_cobros (item) VALUES
       (' '),
       ('Bueno'),
       ('Regular'),
       ('Malo');

ALTER TABLE dominios.sist_cobros OWNER TO fonsagua;


CREATE TABLE dominios.nivel_serv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.nivel_serv (item) VALUES
       (' '),
       ('Bueno'),
       ('Regular'),
       ('Malo');

ALTER TABLE dominios.nivel_serv OWNER TO fonsagua;


CREATE TABLE dominios.acceso_tomas (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.acceso_tomas (item) VALUES
       (' '),
       ('Fácil'),
       ('Difícil');

ALTER TABLE dominios.acceso_tomas OWNER TO fonsagua;


CREATE TABLE dominios.tipo_abastecimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_abastecimiento (item) VALUES
       (' '),
       ('Cantareras'),
       ('Domiciliar');

ALTER TABLE dominios.tipo_abastecimiento OWNER TO fonsagua;


CREATE TABLE dominios.tipo_contaminacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_contaminacion (item) VALUES
       (' '),
       ('Puntos de vertido a río'),
       ('Basurero'),
       ('Puntos de lavado y baño'),
       ('Abrevaderos'),
       ('Otras');

ALTER TABLE dominios.tipo_contaminacion OWNER TO fonsagua;


CREATE TABLE dominios.gestion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.gestion (item) VALUES
       (' '),
       ('Municipal'),
       ('Comunidad'),
       ('ONG'),
       ('ANDA');

ALTER TABLE dominios.gestion OWNER TO fonsagua;


CREATE TABLE dominios.tipo_sistema (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_sistema (item) VALUES
       (' '),
       ('Gravedad'),
       ('Bombeo'),
       ('Mixto');

ALTER TABLE dominios.tipo_sistema OWNER TO fonsagua;


CREATE TABLE dominios.tipo_mantenimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_mantenimiento (item) VALUES
       (' '),
       ('Preventivo'),
       ('Correctivo');

ALTER TABLE dominios.tipo_mantenimiento OWNER TO fonsagua;


CREATE TABLE dominios.proced_tecnicos (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.proced_tecnicos (item) VALUES
       (' '),
       ('De la comunidad'),
       ('De otra comunidad'),
       ('Otros');

ALTER TABLE dominios.proced_tecnicos OWNER TO fonsagua;


CREATE TABLE dominios.tipo_fuente (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_fuente (item) VALUES
       (' '),
       ('Manantial'),
       ('Pozo'),
       ('Punto rio'),
       ('Embalse'),
       ('Infraestructura ANDA');

ALTER TABLE dominios.tipo_fuente OWNER TO fonsagua;


CREATE TABLE dominios.sistema (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.sistema (item) VALUES
       (' '),
       ('Gravedad'),
       ('Bombeo');

ALTER TABLE dominios.sistema OWNER TO fonsagua;


CREATE TABLE dominios.tipo_construccion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_construccion (item) VALUES
       (' '),
       ('Ladrillo'),
       ('Fibrocemento'),
       ('Concreto');

ALTER TABLE dominios.tipo_construccion OWNER TO fonsagua;


CREATE TABLE dominios.estado (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.estado (item) VALUES
       (' '),
       ('Bueno'),
       ('Deteriorado'),
       ('Malo');

ALTER TABLE dominios.estado OWNER TO fonsagua;


CREATE TABLE dominios.ubicacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.ubicacion (item) VALUES
       (' '),
       ('Elevado'),
       ('Superficie'),
       ('Soterrado'),
       ('Otros');

ALTER TABLE dominios.ubicacion OWNER TO fonsagua;


CREATE TABLE dominios.tipologia_tuberia (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipologia_tuberia (item) VALUES
       (' '),
       ('Aducción'),
       ('Impulsión'),
       ('Distribución');

ALTER TABLE dominios.tipologia_tuberia OWNER TO fonsagua;


CREATE TABLE dominios.material (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.material (item) VALUES
       (' '),
       ('PVC'),
       ('HG'),
       ('HF');

ALTER TABLE dominios.material OWNER TO fonsagua;


CREATE TABLE dominios.tipologia_bomba (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipologia_bomba (item) VALUES
       (' '),
       ('Sumergible'),
       ('Externa'),
       ('Otros');

ALTER TABLE dominios.tipologia_bomba OWNER TO fonsagua;


CREATE TABLE dominios.energia (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.energia (item) VALUES
       (' '),
       ('Red Eléctrica'),
       ('Motor Diésel'),
       ('Solar'),
       ('Otros');

ALTER TABLE dominios.energia OWNER TO fonsagua;


CREATE TABLE dominios.calidad_agua (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.calidad_agua (item) VALUES
       (' '),
       ('Buena'),
       ('Regular'),
       ('Mala');

ALTER TABLE dominios.calidad_agua OWNER TO fonsagua;


CREATE TABLE dominios.tipo_vegetacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_vegetacion (item) VALUES
       (' '),
       ('Bosque'),
       ('Arbustiva'),
       ('Pastizales'),
       ('Otros');

ALTER TABLE dominios.tipo_vegetacion OWNER TO fonsagua;


CREATE TABLE dominios.propietario (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.propietario (item) VALUES
       (' '),
       ('Privado'),
       ('Comunitario');

ALTER TABLE dominios.propietario OWNER TO fonsagua;


CREATE TABLE dominios.tipo_pozo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.tipo_pozo (item) VALUES
       (' '),
       ('Excavado'),
       ('Perforado');

ALTER TABLE dominios.tipo_pozo OWNER TO fonsagua;


CREATE TABLE dominios.cond_muestra (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dominios.cond_muestra (item) VALUES
       (' '),
       ('Buenas'),
       ('Regulares'),
       ('Malas');

ALTER TABLE dominios.cond_muestra OWNER TO fonsagua;
