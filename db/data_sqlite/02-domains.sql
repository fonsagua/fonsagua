CREATE TABLE tip_nucleo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_nucleo (item) SELECT
       (' ') UNION SELECT
       ('Concentrado') UNION SELECT
       ('Disperso');


CREATE TABLE tip_origen (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_origen (item) SELECT
       (' ') UNION SELECT
       ('Oriunda') UNION SELECT
       ('Desplazada') UNION SELECT
       ('Mixta');


CREATE TABLE genero (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO genero (item) SELECT
       (' ') UNION SELECT
       ('Femenino') UNION SELECT
       ('Masculino');


CREATE TABLE antiguedad (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO antiguedad (item) SELECT
       (' ') UNION SELECT
       ('Más de 5 años') UNION SELECT
       ('Menos de 5 años');


CREATE TABLE emigracion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO emigracion (item) SELECT
       (' ') UNION SELECT
       ('Mucha') UNION SELECT
       ('Media') UNION SELECT
       ('Poca');


CREATE TABLE tip_regadio (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_regadio (item) SELECT
       (' ') UNION SELECT
       ('Inundación') UNION SELECT
       ('Aspersión') UNION SELECT
       ('Goteo') UNION SELECT
       ('Ninguno');


CREATE TABLE tip_acceso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_acceso (item) SELECT
       (' ') UNION SELECT
       ('Vereda') UNION SELECT
       ('Calle') UNION SELECT
       ('Carretera');


CREATE TABLE tip_sup_acceso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tip_sup_acceso (item) SELECT
       (' ') UNION SELECT
       ('Tierra') UNION SELECT
       ('Balastro') UNION SELECT
       ('Empedrado') UNION SELECT
       ('Pavimentado');


CREATE TABLE acceso_ver (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO acceso_ver (item) SELECT
       (' ') UNION SELECT
       ('Transitable') UNION SELECT
       ('Intransitable');


CREATE TABLE acceso_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO acceso_inv (item) SELECT
       (' ') UNION SELECT
       ('Transitable') UNION SELECT
       ('Intransitable');


CREATE TABLE deforestacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO deforestacion (item) SELECT
       (' ') UNION SELECT
       ('Alta') UNION SELECT
       ('Media') UNION SELECT
       ('Baja');


CREATE TABLE avance_fagricola (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO avance_fagricola (item) SELECT
       (' ') UNION SELECT
       ('Alta') UNION SELECT
       ('Media') UNION SELECT
       ('Baja');


CREATE TABLE riesgo_erosion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO riesgo_erosion (item) SELECT
       (' ') UNION SELECT
       ('Alto') UNION SELECT
       ('Medio') UNION SELECT
       ('Bajo');


CREATE TABLE sist_abastecimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO sist_abastecimiento (item) SELECT
       (' ') UNION SELECT
       ('Si') UNION SELECT
       ('No') UNION SELECT
       ('Parcial');


CREATE TABLE origen_aguas (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO origen_aguas (item) SELECT
       (' ') UNION SELECT
       ('Manantial') UNION SELECT
       ('Río') UNION SELECT
       ('Pozo');


CREATE TABLE ag_gris_calle (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO ag_gris_calle (item) SELECT
       (' ') UNION SELECT
       ('Mucho') UNION SELECT
       ('Poco') UNION SELECT
       ('Nada');


CREATE TABLE trat_aboneras (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO trat_aboneras (item) SELECT
       (' ') UNION SELECT
       ('Ceniza') UNION SELECT
       ('Cal') UNION SELECT
       ('Aserrín') UNION SELECT
       ('Otros');


CREATE TABLE dist_let_agua (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dist_let_agua (item) SELECT
       (' ') UNION SELECT
       ('Más de 15 m') UNION SELECT
       ('Menos de 15 m');


CREATE TABLE disp_basuras (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO disp_basuras (item) SELECT
       (' ') UNION SELECT
       ('No se hace nada') UNION SELECT
       ('Se traslada a otro lugar') UNION SELECT
       ('Se entierra') UNION SELECT
       ('Se quema') UNION SELECT
       ('Pasa el tren de aseo ( camión)') UNION SELECT
       ('Otros');


CREATE TABLE tipo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo (item) SELECT
       (' ') UNION SELECT
       ('Punto de referencia') UNION SELECT
       ('Casa extremo norte') UNION SELECT
       ('Casa extremo sur') UNION SELECT
       ('Casa extremo oriente') UNION SELECT
       ('Casa extremo poniente') UNION SELECT
       ('Casa mas alta') UNION SELECT
       ('Casa mas baja') UNION SELECT
       ('Primera casa con electricidad') UNION SELECT
       ('Última casa con electricidad') UNION SELECT
       ('Otras casas');


CREATE TABLE valoracion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO valoracion (item) SELECT
       (' ') UNION SELECT
       ('Bien') UNION SELECT
       ('Regular') UNION SELECT
       ('Mal');


CREATE TABLE tipo_organizacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_organizacion (item) SELECT
       (' ') UNION SELECT
       ('Asociación de padres y madres de familia') UNION SELECT
       ('Cooperativa de producción') UNION SELECT
       ('Comité de iglesia') UNION SELECT
       ('Comité de jóvenes') UNION SELECT
       ('Caja rural') UNION SELECT
       ('Otros');


CREATE TABLE niveles (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO niveles (item) SELECT
       (' ') UNION SELECT
       ('Pre-escolar') UNION SELECT
       ('Primaria') UNION SELECT
       ('Básica') UNION SELECT
       ('Media') UNION SELECT
       ('Superior');


CREATE TABLE tipo_servicio (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_servicio (item) SELECT
       (' ') UNION SELECT
       ('Iglesia') UNION SELECT
       ('Instalación deportiva') UNION SELECT
       ('Centro comunitario') UNION SELECT
       ('Puesto policial') UNION SELECT
       ('Otros');


CREATE TABLE tipo_amenaza (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_amenaza (item) SELECT
       (' ') UNION SELECT
       ('Deslizamientos') UNION SELECT
       ('Desbordamiento de río') UNION SELECT
       ('Inundaciones') UNION SELECT
       ('Puntos de asalto') UNION SELECT
       ('Otros');


CREATE TABLE dinero_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO dinero_inv (item) SELECT
       (' ') UNION SELECT
       ('Alto') UNION SELECT
       ('Medio') UNION SELECT
       ('Bajo');


CREATE TABLE tiempo_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tiempo_inv (item) SELECT
       (' ') UNION SELECT
       ('Alto') UNION SELECT
       ('Medio') UNION SELECT
       ('Bajo');


CREATE TABLE sist_cobros (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO sist_cobros (item) SELECT
       (' ') UNION SELECT
       ('Bueno') UNION SELECT
       ('Regular') UNION SELECT
       ('Malo');


CREATE TABLE nivel_serv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO nivel_serv (item) SELECT
       (' ') UNION SELECT
       ('Bueno') UNION SELECT
       ('Regular') UNION SELECT
       ('Malo');


CREATE TABLE acceso_tomas (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO acceso_tomas (item) SELECT
       (' ') UNION SELECT
       ('Fácil') UNION SELECT
       ('Difícil');


CREATE TABLE tipo_distribucion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_distribucion (item) SELECT
       (' ') UNION SELECT
       ('Domiciliar') UNION SELECT
       ('Llave pública');


CREATE TABLE tipo_contaminacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_contaminacion (item) SELECT
       (' ') UNION SELECT
       ('Puntos de vertido a río') UNION SELECT
       ('Basurero') UNION SELECT
       ('Puntos de lavado y baño') UNION SELECT
       ('Abrevaderos') UNION SELECT
       ('Otras');


CREATE TABLE gestion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO gestion (item) SELECT
       (' ') UNION SELECT
       ('Municipal') UNION SELECT
       ('Comunidad') UNION SELECT
       ('ONG');


CREATE TABLE tipo_sistema (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_sistema (item) SELECT
       (' ') UNION SELECT
       ('Gravedad') UNION SELECT
       ('Bombeo') UNION SELECT
       ('Mixto');


CREATE TABLE tipo_mantenimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_mantenimiento (item) SELECT
       (' ') UNION SELECT
       ('Preventivo') UNION SELECT
       ('Correctivo');


CREATE TABLE proced_tecnicos (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO proced_tecnicos (item) SELECT
       (' ') UNION SELECT
       ('De la comunidad') UNION SELECT
       ('De otra comunidad') UNION SELECT
       ('Otros');


CREATE TABLE tipo_fuente (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_fuente (item) SELECT
       (' ') UNION SELECT
       ('Manantial') UNION SELECT
       ('Pozo') UNION SELECT
       ('Punto rio') UNION SELECT
       ('Embalse');


CREATE TABLE sistema (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO sistema (item) SELECT
       (' ') UNION SELECT
       ('Gravedad') UNION SELECT
       ('Bombeo');


CREATE TABLE tipo_construccion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_construccion (item) SELECT
       (' ') UNION SELECT
       ('Ladrillo') UNION SELECT
       ('Ferrocemento') UNION SELECT
       ('Concreto');


CREATE TABLE estado (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO estado (item) SELECT
       (' ') UNION SELECT
       ('Bueno') UNION SELECT
       ('Deteriorado') UNION SELECT
       ('Malo');


CREATE TABLE ubicacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO ubicacion (item) SELECT
       (' ') UNION SELECT
       ('Elevado') UNION SELECT
       ('Superficie') UNION SELECT
       ('Soterrado') UNION SELECT
       ('Otros');


CREATE TABLE tipologia_tuberia (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipologia_tuberia (item) SELECT
       (' ') UNION SELECT
       ('Aducción') UNION SELECT
       ('Impulsión') UNION SELECT
       ('Distribución');


CREATE TABLE material (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO material (item) SELECT
       (' ') UNION SELECT
       ('PVC') UNION SELECT
       ('HG');


CREATE TABLE tipologia_bomba (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipologia_bomba (item) SELECT
       (' ') UNION SELECT
       ('Sumergible') UNION SELECT
       ('Externa') UNION SELECT
       ('Otros');


CREATE TABLE energia (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO energia (item) SELECT
       (' ') UNION SELECT
       ('Red Eléctrica') UNION SELECT
       ('Motor Diésel') UNION SELECT
       ('Solar') UNION SELECT
       ('Otros');


CREATE TABLE calidad_agua (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO calidad_agua (item) SELECT
       (' ') UNION SELECT
       ('Buena') UNION SELECT
       ('Regular') UNION SELECT
       ('Mala');


CREATE TABLE tipo_vegetacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_vegetacion (item) SELECT
       (' ') UNION SELECT
       ('Bosque') UNION SELECT
       ('Arbustiva') UNION SELECT
       ('Pastizales') UNION SELECT
       ('Otros');


CREATE TABLE propietario (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO propietario (item) SELECT
       (' ') UNION SELECT
       ('Privado') UNION SELECT
       ('Comunitario');


CREATE TABLE tipo_pozo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO tipo_pozo (item) SELECT
       (' ') UNION SELECT
       ('Excavado') UNION SELECT
       ('Perforado');


CREATE TABLE funcionamiento_verano (
       item VARCHAR
	       PRIMARY KEY
);

INSERT INTO funcionamiento_verano (item) SELECT
       (' ') UNION SELECT
       ('No varía caudal') UNION SELECT
       ('Disminuye caudal') UNION SELECT
       ('Se seca');


CREATE TABLE cond_muestra (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO cond_muestra (item) SELECT
       (' ') UNION SELECT
       ('Buenas') UNION SELECT
       ('Regulares') UNION SELECT
       ('Malas');
