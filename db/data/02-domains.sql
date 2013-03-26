CREATE TABLE public.tip_nucleo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_nucleo (item) VALUES
       ('0'),
       ('1'),
       ('2');


ALTER TABLE public.tip_nucleo OWNER TO fonsagua;
CREATE TABLE public.tip_origen (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_origen (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.tip_origen OWNER TO fonsagua;
CREATE TABLE public.antiguedad (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.antiguedad (item) VALUES
       ('0'),
       ('1'),
       ('2');


ALTER TABLE public.antiguedad OWNER TO fonsagua;
CREATE TABLE public.emigracion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.emigracion (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.emigracion OWNER TO fonsagua;
CREATE TABLE public.tip_regadio (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_regadio (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4');


ALTER TABLE public.tip_regadio OWNER TO fonsagua;
CREATE TABLE public.tip_acceso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_acceso (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.tip_acceso OWNER TO fonsagua;
CREATE TABLE public.tip_sup_acceso (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tip_sup_acceso (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4');


ALTER TABLE public.tip_sup_acceso OWNER TO fonsagua;
CREATE TABLE public.acceso_ver (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.acceso_ver (item) VALUES
       ('0'),
       ('1'),
       ('2');


ALTER TABLE public.acceso_ver OWNER TO fonsagua;
CREATE TABLE public.acceso_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.acceso_inv (item) VALUES
       ('0'),
       ('1'),
       ('2');


ALTER TABLE public.acceso_inv OWNER TO fonsagua;
CREATE TABLE public.veg_agr_tip (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.veg_agr_tip (item) VALUES
       ('0'),
       ('1'),
       ('2');


ALTER TABLE public.veg_agr_tip OWNER TO fonsagua;
CREATE TABLE public.veg_for_tip (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.veg_for_tip (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4');


ALTER TABLE public.veg_for_tip OWNER TO fonsagua;
CREATE TABLE public.deforestacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.deforestacion (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.deforestacion OWNER TO fonsagua;
CREATE TABLE public.avance_fagricola (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.avance_fagricola (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.avance_fagricola OWNER TO fonsagua;
CREATE TABLE public.riesgo_erosion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.riesgo_erosion (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.riesgo_erosion OWNER TO fonsagua;
CREATE TABLE public.sist_abastecimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.sist_abastecimiento (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.sist_abastecimiento OWNER TO fonsagua;
CREATE TABLE public.origen_aguas (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.origen_aguas (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4');


ALTER TABLE public.origen_aguas OWNER TO fonsagua;
CREATE TABLE public.ag_gris_calle (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.ag_gris_calle (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.ag_gris_calle OWNER TO fonsagua;
CREATE TABLE public.trat_aboneras (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.trat_aboneras (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4');


ALTER TABLE public.trat_aboneras OWNER TO fonsagua;
CREATE TABLE public.dist_let_agua (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.dist_let_agua (item) VALUES
       ('0'),
       ('1'),
       ('2');


ALTER TABLE public.dist_let_agua OWNER TO fonsagua;
CREATE TABLE public.disp_basuras (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.disp_basuras (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4'),
       ('5'),
       ('6');


ALTER TABLE public.disp_basuras OWNER TO fonsagua;
CREATE TABLE public.tipo (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4'),
       ('5'),
       ('6'),
       ('7'),
       ('8'),
       ('9');


ALTER TABLE public.tipo OWNER TO fonsagua;
CREATE TABLE public.valoracion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.valoracion (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.valoracion OWNER TO fonsagua;
CREATE TABLE public.tipo_organizacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_organizacion (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4'),
       ('5');


ALTER TABLE public.tipo_organizacion OWNER TO fonsagua;
CREATE TABLE public.niveles (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.niveles (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4');


ALTER TABLE public.niveles OWNER TO fonsagua;
CREATE TABLE public.tipo_servicio (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_servicio (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4'),
       ('5');


ALTER TABLE public.tipo_servicio OWNER TO fonsagua;
CREATE TABLE public.tipo_amenaza (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_amenaza (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4'),
       ('5');


ALTER TABLE public.tipo_amenaza OWNER TO fonsagua;
CREATE TABLE public.dinero_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.dinero_inv (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.dinero_inv OWNER TO fonsagua;
CREATE TABLE public.tiempo_inv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tiempo_inv (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.tiempo_inv OWNER TO fonsagua;
CREATE TABLE public.sist_cobros (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.sist_cobros (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.sist_cobros OWNER TO fonsagua;
CREATE TABLE public.nivel_serv (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.nivel_serv (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3');


ALTER TABLE public.nivel_serv OWNER TO fonsagua;
CREATE TABLE public.acceso_tomas (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.acceso_tomas (item) VALUES
       ('0'),
       ('1'),
       ('2');


ALTER TABLE public.acceso_tomas OWNER TO fonsagua;
CREATE TABLE public.tipo_abastecimiento (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_abastecimiento (item) VALUES
       ('0'),
       ('1'),
       ('2');


ALTER TABLE public.tipo_abastecimiento OWNER TO fonsagua;
CREATE TABLE public.tipo_contaminzacion (
       item VARCHAR
	       PRIMARY KEY

);

INSERT INTO public.tipo_contaminzacion (item) VALUES
       ('0'),
       ('1'),
       ('2'),
       ('3'),
       ('4'),
       ('5');


ALTER TABLE public.tipo_contaminzacion OWNER TO fonsagua;
