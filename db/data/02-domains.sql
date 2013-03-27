CREATE TABLE public.tip_nucleo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_nucleo (item) VALUES
       (' '),
       ('Concentrado'),
       ('Disperso');


ALTER TABLE public.tip_nucleo OWNER TO fonsagua;
CREATE TABLE public.tip_origen (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_origen (item) VALUES
       (' '),
       ('Oriunda'),
       ('Desplazada'),
       ('Mixta');


ALTER TABLE public.tip_origen OWNER TO fonsagua;
CREATE TABLE public.antiguedad (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.antiguedad (item) VALUES
       (' '),
       ('Más de 5 años'),
       ('Menos de 5 años');


ALTER TABLE public.antiguedad OWNER TO fonsagua;
CREATE TABLE public.emigracion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.emigracion (item) VALUES
       (' '),
       ('Mucha'),
       ('Media'),
       ('Poca');


ALTER TABLE public.emigracion OWNER TO fonsagua;
CREATE TABLE public.tip_regadio (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_regadio (item) VALUES
       (' '),
       ('Inundación'),
       ('Aspersión'),
       ('Goteo'),
       ('Ninguno');


ALTER TABLE public.tip_regadio OWNER TO fonsagua;
CREATE TABLE public.tip_acceso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_acceso (item) VALUES
       (' '),
       ('Vereda'),
       ('Calle'),
       ('Carretera');


ALTER TABLE public.tip_acceso OWNER TO fonsagua;
CREATE TABLE public.tip_sup_acceso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_sup_acceso (item) VALUES
       (' '),
       ('Tierra'),
       ('Balastro'),
       ('Empedrado'),
       ('Pavimentado');


ALTER TABLE public.tip_sup_acceso OWNER TO fonsagua;
CREATE TABLE public.acceso_ver (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.acceso_ver (item) VALUES
       (' '),
       ('Transitable'),
       ('Intransitable');


ALTER TABLE public.acceso_ver OWNER TO fonsagua;
CREATE TABLE public.acceso_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.acceso_inv (item) VALUES
       (' '),
       ('Transitable'),
       ('Intransitable');


ALTER TABLE public.acceso_inv OWNER TO fonsagua;
CREATE TABLE public.veg_agr_tip (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.veg_agr_tip (item) VALUES
       (' '),
       ('Pastizal'),
       ('Cultivos');


ALTER TABLE public.veg_agr_tip OWNER TO fonsagua;
CREATE TABLE public.veg_for_tip (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.veg_for_tip (item) VALUES
       (' '),
       ('Raleado'),
       ('Frondoso'),
       ('Matorral'),
       ('Mangle');


ALTER TABLE public.veg_for_tip OWNER TO fonsagua;
CREATE TABLE public.deforestacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.deforestacion (item) VALUES
       (' '),
       ('Alta'),
       ('Media'),
       ('Baja');


ALTER TABLE public.deforestacion OWNER TO fonsagua;
CREATE TABLE public.avance_fagricola (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.avance_fagricola (item) VALUES
       (' '),
       ('Alta'),
       ('Media'),
       ('Baja');


ALTER TABLE public.avance_fagricola OWNER TO fonsagua;
CREATE TABLE public.riesgo_erosion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.riesgo_erosion (item) VALUES
       (' '),
       ('Alto'),
       ('Medio'),
       ('Bajo');


ALTER TABLE public.riesgo_erosion OWNER TO fonsagua;
CREATE TABLE public.sist_abastecimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.sist_abastecimiento (item) VALUES
       (' '),
       ('Si'),
       ('No'),
       ('Parcial');


ALTER TABLE public.sist_abastecimiento OWNER TO fonsagua;
CREATE TABLE public.origen_aguas (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.origen_aguas (item) VALUES
       (' '),
       ('Manantial'),
       ('Río'),
       ('Pozo'),
       ('Anda');


ALTER TABLE public.origen_aguas OWNER TO fonsagua;
CREATE TABLE public.ag_gris_calle (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.ag_gris_calle (item) VALUES
       (' '),
       ('Mucho'),
       ('Poco'),
       ('Nada');


ALTER TABLE public.ag_gris_calle OWNER TO fonsagua;
CREATE TABLE public.trat_aboneras (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.trat_aboneras (item) VALUES
       (' '),
       ('Ceniza'),
       ('Cal'),
       ('Aserrín'),
       ('Otros');


ALTER TABLE public.trat_aboneras OWNER TO fonsagua;
CREATE TABLE public.dist_let_agua (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.dist_let_agua (item) VALUES
       (' '),
       ('Más de 30 m'),
       ('Menos de 30 m');


ALTER TABLE public.dist_let_agua OWNER TO fonsagua;
CREATE TABLE public.disp_basuras (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.disp_basuras (item) VALUES
       (' '),
       ('No se hace nada'),
       ('Se traslada a otro lugar'),
       ('Se entierra'),
       ('Se quema'),
       ('Pasa el tren de aseo ( camión)'),
       ('Otros');


ALTER TABLE public.disp_basuras OWNER TO fonsagua;
CREATE TABLE public.tipo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo (item) VALUES
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


ALTER TABLE public.tipo OWNER TO fonsagua;
CREATE TABLE public.valoracion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.valoracion (item) VALUES
       (' '),
       ('Bien'),
       ('Regular'),
       ('Mal');


ALTER TABLE public.valoracion OWNER TO fonsagua;
CREATE TABLE public.tipo_organizacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_organizacion (item) VALUES
       (' '),
       ('Asociación de padres y madres de familia'),
       ('Cooperativa de producción'),
       ('Comité de iglesia'),
       ('Comité de jóvenes'),
       ('Otros');


ALTER TABLE public.tipo_organizacion OWNER TO fonsagua;
CREATE TABLE public.niveles (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.niveles (item) VALUES
       (' '),
       ('Parvularia'),
       ('Primaria'),
       ('Básica'),
       ('Media');


ALTER TABLE public.niveles OWNER TO fonsagua;
CREATE TABLE public.tipo_servicio (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_servicio (item) VALUES
       (' '),
       ('Iglesia'),
       ('Intalación deportiva'),
       ('Centro comunitario'),
       ('Puesto policial'),
       ('Otros');


ALTER TABLE public.tipo_servicio OWNER TO fonsagua;
CREATE TABLE public.tipo_amenaza (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_amenaza (item) VALUES
       (' '),
       ('Deslizamientos'),
       ('Desbordamiento de río'),
       ('Inundaciones'),
       ('Puntos de asalto'),
       ('Otros');


ALTER TABLE public.tipo_amenaza OWNER TO fonsagua;
CREATE TABLE public.dinero_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.dinero_inv (item) VALUES
       (' '),
       ('Alto'),
       ('Medio'),
       ('Bajo');


ALTER TABLE public.dinero_inv OWNER TO fonsagua;
CREATE TABLE public.tiempo_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tiempo_inv (item) VALUES
       (' '),
       ('Alto'),
       ('Medio'),
       ('Bajo');


ALTER TABLE public.tiempo_inv OWNER TO fonsagua;
CREATE TABLE public.sist_cobros (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.sist_cobros (item) VALUES
       (' '),
       ('Bueno'),
       ('Regular'),
       ('Malo');


ALTER TABLE public.sist_cobros OWNER TO fonsagua;
CREATE TABLE public.nivel_serv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.nivel_serv (item) VALUES
       (' '),
       ('Bueno'),
       ('Regular'),
       ('Malo');


ALTER TABLE public.nivel_serv OWNER TO fonsagua;
CREATE TABLE public.acceso_tomas (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.acceso_tomas (item) VALUES
       (' '),
       ('Fácil'),
       ('Difícil');


ALTER TABLE public.acceso_tomas OWNER TO fonsagua;
CREATE TABLE public.tipo_abastecimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_abastecimiento (item) VALUES
       (' '),
       ('Cantareras'),
       ('Domiciliar');


ALTER TABLE public.tipo_abastecimiento OWNER TO fonsagua;
CREATE TABLE public.tipo_contaminzacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_contaminzacion (item) VALUES
       (' '),
       ('Puntos de vertido a río'),
       ('Basurero'),
       ('Puntos de lavado y baño'),
       ('Abrevaderos'),
       ('Otras');


ALTER TABLE public.tipo_contaminzacion OWNER TO fonsagua;
