RESET search_path;

CREATE TABLE fonsagua.comunidades (
       gid SERIAL PRIMARY KEY,
       comunidad VARCHAR,
       cod_comunidad VARCHAR
	       UNIQUE
	       NOT NULL,
       fecha Date,
       coment_gen VARCHAR,
       departamento VARCHAR,
       cod_departamento VARCHAR
	       NOT NULL,
       municipio VARCHAR,
       cod_municipio VARCHAR
	       NOT NULL,
       canton VARCHAR,
       cod_canton VARCHAR
	       NOT NULL,
       caserio VARCHAR,
       cod_caserio VARCHAR
	       NOT NULL,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       punto_descripcion VARCHAR,
       cuenca VARCHAR,
       coment_loc VARCHAR,
       tip_nucleo VARCHAR
	       REFERENCES dominios.tip_nucleo(item),
       anho_establecimiento INTEGER,
       n_familias INTEGER,
       n_viviendas INTEGER,
       n_habitantes INTEGER,
       tip_origen VARCHAR
	       REFERENCES dominios.tip_origen(item),
       antiguedad VARCHAR
	       REFERENCES dominios.antiguedad(item),
       lug_origen VARCHAR,
       n_ninhas INTEGER,
       n_ninhos INTEGER,
       tot_ninhos INTEGER,
       n_muj_jovenes INTEGER,
       n_hom_jovenes INTEGER,
       n_mujeres INTEGER,
       n_hombres INTEGER,
       tot_adultos INTEGER,
       n_ancianas INTEGER,
       n_ancianos INTEGER,
       tot_ancianos INTEGER,
       resum_censo VARCHAR,
       coment_pob VARCHAR,
       h_adescos BOOLEAN,
       n_adescos INTEGER,
       h_cargos_publicos BOOLEAN,
       exp_ongs BOOLEAN,
       coment_org VARCHAR,
       emigracion VARCHAR
	       REFERENCES dominios.emigracion(item),
       f_primario FLOAT,
       f_secundario FLOAT,
       f_terciario FLOAT,
       resum_econ VARCHAR,
       cp_granos INTEGER,
       sub_granos BOOLEAN,
       cp_ganaderia INTEGER,
       sub_ganaderia BOOLEAN,
       cp_frutales INTEGER,
       sub_frutales BOOLEAN,
       cp_otros INTEGER,
       sub_otros BOOLEAN,
       f_c_propia INTEGER,
       ca_cafe INTEGER,
       ca_canha INTEGER,
       ca_frutales INTEGER,
       ca_otros INTEGER,
       f_c_ajena INTEGER,
       ind_tall_maq BOOLEAN,
       f_industria INTEGER,
       f_indus_for INTEGER,
       f_indus_inf INTEGER,
       f_construccion INTEGER,
       f_const_for INTEGER,
       f_const_inf INTEGER,
       f_maquila INTEGER,
       f_otros_sec INTEGER,
       comer_serv BOOLEAN,
       f_comercio INTEGER,
       f_comer_for INTEGER,
       f_comer_inf INTEGER,
       f_otros_ter INTEGER,
       n_agropecuario INTEGER,
       n_industria INTEGER,
       n_construccion INTEGER,
       n_maquila INTEGER,
       n_comercio INTEGER,
       n_otros INTEGER,
       coment_eco VARCHAR,
       f_propietarias INTEGER,
       prop_hombres INTEGER,
       prop_mujeres INTEGER,
       prop_registrada INTEGER,
       prop_tramites INTEGER,
       prop_noregistrada INTEGER,
       prop_area_cultivada FLOAT,
       f_arrendatarias INTEGER,
       arre_hombres INTEGER,
       arre_mujeres INTEGER,
       arre_area_cultivada FLOAT,
       f_medias INTEGER,
       med_hombres INTEGER,
       med_mujeres INTEGER,
       med_area_cultivada FLOAT,
       area_cultivada FLOAT,
       tip_cultivos VARCHAR,
       produccion FLOAT,
       consumo FLOAT,
       lug_agua_cercano VARCHAR,
       tip_regadio VARCHAR
	       REFERENCES dominios.tip_regadio(item),
       sup_total_riego FLOAT,
       areas_pot_riego BOOLEAN,
       agroquimicos BOOLEAN,
       tip_agroquimicos VARCHAR,
       tec_conservacion BOOLEAN,
       tip_tec_conservacion VARCHAR,
       form_agricola BOOLEAN,
       f_form_agricola VARCHAR,
       form_ambiental BOOLEAN,
       f_form_ambiental VARCHAR,
       n_vacas INTEGER,
       n_gallinas INTEGER,
       n_cerdos INTEGER,
       n_burros INTEGER,
       n_caballos INTEGER,
       h_cooperativas BOOLEAN,
       f_viv_prop INTEGER,
       viv_prop_hombres INTEGER,
       viv_prop_mujeres INTEGER,
       f_viv_noprop INTEGER,
       viv_bahareque INTEGER,
       viv_adobe INTEGER,
       viv_mixto INTEGER,
       viv_otros INTEGER,
       coc_lenha INTEGER,
       coc_lenha_mej INTEGER,
       electricidad BOOLEAN,
       viv_electricidad INTEGER,
       tarifa_electricidad FLOAT,
       alumbrado BOOLEAN,
       telf_fijo BOOLEAN,
       telf_movil BOOLEAN,
       cent_educativos BOOLEAN,
       n_cent_educativos INTEGER,
       h_prog_salud VARCHAR,
       prog_salud VARCHAR,
       frec_prog_salud VARCHAR,
       as_unidad_salud BOOLEAN,
       as_clinica_comunal BOOLEAN,
       as_promotor BOOLEAN,
       as_otros BOOLEAN,
       as_otros_detalle VARCHAR,
       n_cent_salud INTEGER,
       t_unidad_salud INTEGER,
       t_clinica_comunal INTEGER,
       frec_med_clinica INTEGER,
       frec_promotor INTEGER,
       enf_tip_ninhos VARCHAR,
       muertes_ninhos INTEGER,
       muertes_ninhos_enf_agua VARCHAR,
       enf_tip_adultos VARCHAR,
       muertes_adultos INTEGER,
       f_dat_sanidad VARCHAR,
       n_iglesias INTEGER,
       tip_acceso VARCHAR
	       REFERENCES dominios.tip_acceso(item),
       tip_sup_acceso VARCHAR
	       REFERENCES dominios.tip_sup_acceso(item),
       acceso_ver VARCHAR
	       REFERENCES dominios.acceso_ver(item),
       acceso_inv VARCHAR
	       REFERENCES dominios.acceso_inv(item),
       trans_publico BOOLEAN,
       frec_tpublico INTEGER,
       t_parada INTEGER,
       t_cabmunicipal INTEGER,
       otros_transportes VARCHAR,
       coment_ser VARCHAR,
       llano FLOAT,
       pend_media FLOAT,
       pend_elevada FLOAT,
       veg_residencial BOOLEAN,
       veg_res_sup FLOAT,
       veg_industrial BOOLEAN,
       veg_ind_sup FLOAT,
       veg_agricola BOOLEAN,
       veg_agr_tip VARCHAR
	       REFERENCES dominios.veg_agr_tip(item),
       veg_agr_sup FLOAT,
       veg_forestal BOOLEAN,
       veg_for_tip VARCHAR
	       REFERENCES dominios.veg_for_tip(item),
       veg_for_sup FLOAT,
       deforestacion VARCHAR
	       REFERENCES dominios.deforestacion(item),
       avance_fagricola VARCHAR
	       REFERENCES dominios.avance_fagricola(item),
       riesgo_erosion VARCHAR
	       REFERENCES dominios.riesgo_erosion(item),
       frec_incendios VARCHAR,
       est_hidrogeologicos BOOLEAN,
       autor_hidrogeo VARCHAR,
       pozos_extraccion BOOLEAN,
       pozos_nivel BOOLEAN,
       conserv_ma BOOLEAN,
       desc_conserv_ma VARCHAR,
       fr_deslizam BOOLEAN,
       fr_desbord BOOLEAN,
       fr_inundac BOOLEAN,
       fr_asaltos BOOLEAN,
       fr_ninguno BOOLEAN,
       fr_otros BOOLEAN,
       fr_otros_detalle VARCHAR,
       ult_fenomeno VARCHAR,
       f_dat_frg VARCHAR,
       comite_riesgos BOOLEAN,
       comite_activo BOOLEAN,
       comite VARCHAR,
       capac_riesgos BOOLEAN,
       coment_frg VARCHAR,
       sist_abastecimiento VARCHAR
	       REFERENCES dominios.sist_abastecimiento(item),
       n_viv_abast INTEGER,
       origen_aguas VARCHAR
	       REFERENCES dominios.origen_aguas(item),
       f_dat_abast VARCHAR,
       resum_abast VARCHAR,
       aba_s_cantareras INTEGER,
       aba_ll_cantareras INTEGER,
       aba_s_domiciliar INTEGER,
       aba_ll_domiciliar INTEGER,
       tot_s_abast INTEGER,
       tot_ll_abast INTEGER,
       aba_s_nacimiento INTEGER,
       aba_ll_nacimiento INTEGER,
       aba_s_rio INTEGER,
       aba_ll_rio INTEGER,
       aba_s_quebrada INTEGER,
       aba_ll_quebrada INTEGER,
       aba_s_lluvia INTEGER,
       aba_ll_lluvia INTEGER,
       aba_s_broquel INTEGER,
       aba_ll_broquel INTEGER,
       aba_s_broquel_com INTEGER,
       aba_ll_broquel_com INTEGER,
       aba_s_compra INTEGER,
       aba_ll_compra INTEGER,
       aba_s_vecino INTEGER,
       aba_ll_vecino INTEGER,
       tot_s_sin_abast INTEGER,
       tot_ll_sin_abast INTEGER,
       cons_dom FLOAT,
       cons_ag_gan FLOAT,
       tot_consumo FLOAT,
       comen_cons VARCHAR,
       cons_hab FLOAT,
       f_rio BOOLEAN,
       f_manantial BOOLEAN,
       f_pozo BOOLEAN,
       f_quebrada BOOLEAN,
       f_compra BOOLEAN,
       coste_agua FLOAT,
       t_medio_fuente INTEGER,
       seca_suficiencia BOOLEAN,
       seca_t_espera INTEGER,
       lluvia_suficiencia BOOLEAN,
       lluvia_t_espera INTEGER,
       res_hab_agua VARCHAR,
       coment_agua VARCHAR,
       coment_hab VARCHAR,
       sist_alcantarillado BOOLEAN,
       alc_completo BOOLEAN,
       evac_calle BOOLEAN,
       evac_familiar BOOLEAN,
       evac_comunal BOOLEAN,
       evac_otros BOOLEAN,
       ag_gris_calle VARCHAR
	       REFERENCES dominios.ag_gris_calle(item),
       a_gris_quebrada BOOLEAN,
       trat_ag_residuales BOOLEAN,
       trat_trampa INTEGER,
       trat_biofiltro INTEGER,
       trat_otros INTEGER,
       sin_tratamiento INTEGER,
       trat_comunal VARCHAR,
       let_vivienda INTEGER,
       let_comunal INTEGER,
       let_monte INTEGER,
       let_hoyo INTEGER,
       let_septica INTEGER,
       let_hidra INTEGER,
       h_let_abonera BOOLEAN,
       let_abonera INTEGER,
       pq_no_aboneras VARCHAR,
       trat_aboneras VARCHAR
	       REFERENCES dominios.trat_aboneras(item),
       disp_abono VARCHAR,
       uso_letrinas BOOLEAN,
       dist_let_agua VARCHAR
	       REFERENCES dominios.dist_let_agua(item),
       h_lavad_comun BOOLEAN,
       n_lavaderos INTEGER,
       tip_almacenamiento VARCHAR,
       n_pilas INTEGER,
       n_barriles INTEGER,
       disp_basuras VARCHAR
	       REFERENCES dominios.disp_basuras(item),
       coment_san VARCHAR,
       necesidad1 VARCHAR,
       necesidad2 VARCHAR,
       necesidad3 VARCHAR,
       necesidad4 VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'comunidades', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.comunidades OWNER TO fonsagua;

CREATE TABLE fonsagua.puntos_viviendas (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       cod_vivienda VARCHAR
	       UNIQUE
	       NOT NULL,
       tipo VARCHAR
	       REFERENCES dominios.tipo(item),
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'puntos_viviendas', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.puntos_viviendas OWNER TO fonsagua;

CREATE TABLE fonsagua.entrevistadores (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       nombre VARCHAR,
       cargo VARCHAR,
       instit VARCHAR,
       telefono VARCHAR

);


ALTER TABLE fonsagua.entrevistadores OWNER TO fonsagua;

CREATE TABLE fonsagua.entrevistados (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       nombre VARCHAR,
       cargo VARCHAR,
       telefono VARCHAR,
       fecha Date

);


ALTER TABLE fonsagua.entrevistados OWNER TO fonsagua;

CREATE TABLE fonsagua.subcuencas (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       subcuenca VARCHAR

);


ALTER TABLE fonsagua.subcuencas OWNER TO fonsagua;

CREATE TABLE fonsagua.adescos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       nombre VARCHAR,
       anho_const INTEGER,
       legalizada BOOLEAN,
       n_socios INTEGER,
       antiguedad INTEGER,
       presidencia VARCHAR,
       n_hombres INTEGER,
       n_mujeres INTEGER,
       tot_miembros INTEGER

);


ALTER TABLE fonsagua.adescos OWNER TO fonsagua;

CREATE TABLE fonsagua.cargos_publicos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       nombre VARCHAR,
       cargo VARCHAR

);


ALTER TABLE fonsagua.cargos_publicos OWNER TO fonsagua;

CREATE TABLE fonsagua.ongs (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       ong VARCHAR,
       fechas VARCHAR,
       tipo_proy VARCHAR,
       capacitacion BOOLEAN,
       tipo_capac VARCHAR,
       valoracion VARCHAR
	       REFERENCES dominios.valoracion(item)

);


ALTER TABLE fonsagua.ongs OWNER TO fonsagua;

CREATE TABLE fonsagua.otras_organizaciones (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       tipo_organizacion VARCHAR
	       REFERENCES dominios.tipo_organizacion(item),
       nombre VARCHAR,
       f_creacion Date,
       actividad VARCHAR

);


ALTER TABLE fonsagua.otras_organizaciones OWNER TO fonsagua;

CREATE TABLE fonsagua.tipos_cultivos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       tipo VARCHAR,
       n_familias INTEGER,
       f_propietarias INTEGER,
       f_arrendatarias INTEGER,
       superficie FLOAT,
       rendimiento FLOAT,
       rubro VARCHAR

);


ALTER TABLE fonsagua.tipos_cultivos OWNER TO fonsagua;

CREATE TABLE fonsagua.produccion_consumo (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       n_miembros INTEGER,
       produccion FLOAT,
       consumo FLOAT

);


ALTER TABLE fonsagua.produccion_consumo OWNER TO fonsagua;

CREATE TABLE fonsagua.areas_potenciales_riego (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       descripcion VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'areas_potenciales_riego', 'geom', 32616, 'MULTIPOLYGON', 2);

ALTER TABLE fonsagua.areas_potenciales_riego OWNER TO fonsagua;

CREATE TABLE fonsagua.ganaderia (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       tipo VARCHAR,
       n_familias INTEGER,
       f_propietarias INTEGER,
       f_arrendatarias INTEGER,
       areafam INTEGER,
       rendimiento FLOAT,
       rubro VARCHAR

);


ALTER TABLE fonsagua.ganaderia OWNER TO fonsagua;

CREATE TABLE fonsagua.cooperativas (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       tipo VARCHAR,
       n_asociados INTEGER,
       recursos VARCHAR,
       rubros VARCHAR

);


ALTER TABLE fonsagua.cooperativas OWNER TO fonsagua;

CREATE TABLE fonsagua.centros_educativos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       cod_c_educativo VARCHAR
	       UNIQUE
	       NOT NULL,
       nombre VARCHAR,
       niveles VARCHAR
	       REFERENCES dominios.niveles(item),
       tot_alumnos INTEGER,
       n_ninhas INTEGER,
       n_ninhos INTEGER,
       n_profesores INTEGER,
       n_prof_com INTEGER,
       prog_esc_salud BOOLEAN,
       programa VARCHAR,
       frec_prog VARCHAR,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       comentarios VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'centros_educativos', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.centros_educativos OWNER TO fonsagua;

CREATE TABLE fonsagua.centros_salud (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       cod_c_salud VARCHAR
	       UNIQUE
	       NOT NULL,
       nombre VARCHAR,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT

);

SELECT addgeometrycolumn('fonsagua', 'centros_salud', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.centros_salud OWNER TO fonsagua;

CREATE TABLE fonsagua.otros_servicios (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       cod_servicio VARCHAR
	       UNIQUE
	       NOT NULL,
       nombre VARCHAR,
       tipo_servicio VARCHAR
	       REFERENCES dominios.tipo_servicio(item),
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT

);

SELECT addgeometrycolumn('fonsagua', 'otros_servicios', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.otros_servicios OWNER TO fonsagua;

CREATE TABLE fonsagua.capacitaciones_riesgos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       institucion VARCHAR,
       fecha Date,
       temas VARCHAR

);


ALTER TABLE fonsagua.capacitaciones_riesgos OWNER TO fonsagua;

CREATE TABLE fonsagua.amenazas (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       cod_amenaza VARCHAR
	       UNIQUE
	       NOT NULL,
       tipo_amenaza VARCHAR
	       REFERENCES dominios.tipo_amenaza(item),
       n_fam_afectadas INTEGER,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT

);

SELECT addgeometrycolumn('fonsagua', 'amenazas', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.amenazas OWNER TO fonsagua;

CREATE TABLE fonsagua.implicacion_comunidad (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       dinero_inv VARCHAR
	       REFERENCES dominios.dinero_inv(item),
       tiempo_inv VARCHAR
	       REFERENCES dominios.tiempo_inv(item)

);


ALTER TABLE fonsagua.implicacion_comunidad OWNER TO fonsagua;


CREATE TABLE fonsagua.abastecimientos (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       UNIQUE
	       NOT NULL,
       abastecimiento VARCHAR,
       fecha Date,
       gestion VARCHAR
	       REFERENCES dominios.gestion(item),
       ong VARCHAR,
       h_juntas_agua BOOLEAN,
       h_adescos BOOLEAN,
       tarifa_agua BOOLEAN,
       cuota_domiciliar NUMERIC(5,2),
       cuota_comercial NUMERIC(5,2),
       cuota_cantarera NUMERIC(5,2),
       cuota_otros NUMERIC(5,2),
       frec_pago VARCHAR,
       gastos_cubiertos VARCHAR,
       mora_porcent NUMERIC(5,2),
       coment_tarifa VARCHAR,
       coment_gestion VARCHAR,
       cons_domestico NUMERIC(5,2),
       cons_ag_gan NUMERIC(5,2),
       tot_consumo NUMERIC(5,2),
       tipo_sistema VARCHAR
	       REFERENCES dominios.tipo_sistema(item),
       ent_constructora VARCHAR,
       anho_construccion INTEGER,
       coste_energia NUMERIC(5,2),
       a_domiciliar BOOLEAN,
       n_a_domiciliar INTEGER,
       a_cantarera BOOLEAN,
       n_a_cantarera INTEGER,
       a_comercial BOOLEAN,
       n_a_comercial INTEGER,
       a_otras BOOLEAN,
       n_a_otras INTEGER,
       tot_acometidas INTEGER,
       a_medidor INTEGER,
       a_sin_medidor INTEGER,
       coment_sis VARCHAR,
       des_agua BOOLEAN,
       des_cloracion BOOLEAN,
       des_quimicos BOOLEAN,
       des_otros BOOLEAN,
       des_otros_detalle VARCHAR,
       des_tanque BOOLEAN,
       des_impelencia BOOLEAN,
       des_otra BOOLEAN,
       des_otra_detalle VARCHAR,
       metodo VARCHAR,
       frec_desinfeccion VARCHAR,
       coste_desinfeccion NUMERIC(5,2),
       coment_desinfeccion VARCHAR,
       tipo_mantenimiento VARCHAR
	       REFERENCES dominios.tipo_mantenimiento(item),
       zona_mantenimiento VARCHAR,
       coste_mantenimiento NUMERIC(5,2),
       con_calidad BOOLEAN,
       frec_con_calidad INTEGER,
       con_funcionamiento BOOLEAN,
       fre_con_funcionamiento INTEGER,
       mant_tecnicos BOOLEAN,
       proced_tecnicos VARCHAR
	       REFERENCES dominios.proced_tecnicos(item),
       proced_tec_detalle VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'abastecimientos', 'geom', 32616, 'MULTIPOLYGON', 2);

ALTER TABLE fonsagua.abastecimientos OWNER TO fonsagua;


CREATE TABLE fonsagua.valoracion_sistema (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       cod_abastecimiento VARCHAR
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       sist_cobros VARCHAR
	       REFERENCES dominios.sist_cobros(item),
       nivel_serv VARCHAR
	       REFERENCES dominios.nivel_serv(item),
       agua_suf BOOLEAN,
       serv_continuo BOOLEAN,
       acceso_tomas VARCHAR
	       REFERENCES dominios.acceso_tomas(item),
       comentarios_usuarios VARCHAR

);


ALTER TABLE fonsagua.valoracion_sistema OWNER TO fonsagua;

CREATE TABLE fonsagua.datos_consumo (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       cod_abastecimiento VARCHAR
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       tipo_abastecimiento VARCHAR
	       REFERENCES dominios.tipo_abastecimiento(item),
       mujeres BOOLEAN,
       hombres BOOLEAN,
       ninhas BOOLEAN,
       ninhos BOOLEAN,
       tiempo INTEGER,
       usos_agua VARCHAR,
       consumo FLOAT,
       n_miembros INTEGER

);


ALTER TABLE fonsagua.datos_consumo OWNER TO fonsagua;

CREATE TABLE fonsagua.habitos_consumo (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       mujeres BOOLEAN,
       hombres BOOLEAN,
       ninhas BOOLEAN,
       ninhos BOOLEAN,
       tiempo INTEGER,
       consumo FLOAT,
       n_miembros INTEGER

);


ALTER TABLE fonsagua.habitos_consumo OWNER TO fonsagua;

CREATE TABLE fonsagua.fuentes_contaminacion (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.comunidades(cod_comunidad),
       tipo_contaminacion VARCHAR
	       REFERENCES dominios.tipo_contaminacion(item),
       n_fam_vierten INTEGER,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'fuentes_contaminacion', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.fuentes_contaminacion OWNER TO fonsagua;



CREATE TABLE fonsagua.juntas_agua (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       nombre VARCHAR,
       ubicacion VARCHAR,
       contacto VARCHAR,
       telefono VARCHAR,
       n_miembros INTEGER,
       n_mujeres INTEGER,
       antiguedad INTEGER,
       per_juridica BOOLEAN,
       nit VARCHAR,
       anho_per_juridica INTEGER,
       reglamento BOOLEAN,
       red_juntas BOOLEAN,
       nom_red_juntas VARCHAR

);


ALTER TABLE fonsagua.juntas_agua OWNER TO fonsagua;


CREATE TABLE fonsagua.bombeos (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       cod_bombeo VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       tipologia_bomba VARCHAR
	       REFERENCES dominios.tipologia_bomba(item),
       energia VARCHAR
	       REFERENCES dominios.energia(item),
       potencia NUMERIC(5,2),
       caudal NUMERIC(5,2),
       prof_succion NUMERIC(5,2),
       tiempo NUMERIC(5,2),
       altura INTEGER,
       n_bombas INTEGER,
       anho_construccion INTEGER,
       estado VARCHAR
	       REFERENCES dominios.estado(item),
       utm_x NUMERIC(5,3),
       utm_y NUMERIC(5,3),
       utm_z NUMERIC(5,3),
       coment_bom VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'bombeos', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.bombeos OWNER TO fonsagua;

CREATE TABLE fonsagua.cobertura (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       fecha Date,
       acometidas INTEGER,
       viviendas INTEGER,
       cobertura NUMERIC(5,2)

);


CREATE TABLE fonsagua.captaciones (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       cod_captacion VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       tipo_fuente VARCHAR
	       REFERENCES dominios.tipo_fuente(item),
       sistema VARCHAR
	       REFERENCES dominios.sistema(item),
       cod_bombeo VARCHAR
	       REFERENCES fonsagua.bombeos(cod_bombeo),
       tipo_construccion VARCHAR
	       REFERENCES dominios.tipo_construccion(item),
       anho_construccion INTEGER,
       volumen NUMERIC(5,2),
       estado VARCHAR
	       REFERENCES dominios.estado(item),
       utm_x NUMERIC(5,3),
       utm_y NUMERIC(5,3),
       utm_z NUMERIC(5,3),
       coment_cap VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'captaciones', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.captaciones OWNER TO fonsagua;

CREATE TABLE fonsagua.dep_intermedios (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       cod_dep_intermedio VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       ubicacion VARCHAR
	       REFERENCES dominios.ubicacion(item),
       altura NUMERIC(5,2),
       sistema VARCHAR
	       REFERENCES dominios.sistema(item),
       cod_bombeo VARCHAR
	       REFERENCES fonsagua.bombeos(cod_bombeo),
       tipo_construccion VARCHAR
	       REFERENCES dominios.tipo_construccion(item),
       anho_construccion INTEGER,
       volumen NUMERIC(5,2),
       estado VARCHAR
	       REFERENCES dominios.estado(item),
       utm_x NUMERIC(5,3),
       utm_y NUMERIC(5,3),
       utm_z NUMERIC(5,3),
       descripcion VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'dep_intermedios', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.dep_intermedios OWNER TO fonsagua;

CREATE TABLE fonsagua.dep_distribucion (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       cod_dep_distribucion VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       ubicacion VARCHAR
	       REFERENCES dominios.ubicacion(item),
       altura NUMERIC(5,2),
       tipo_construccion VARCHAR
	       REFERENCES dominios.tipo_construccion(item),
       anho_construccion INTEGER,
       volumen NUMERIC(5,2),
       t_llenado NUMERIC(5,2),
       estado VARCHAR
	       REFERENCES dominios.estado(item),
       utm_x NUMERIC(5,3),
       utm_y NUMERIC(5,3),
       utm_z NUMERIC(5,3),
       descripcion VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'dep_distribucion', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.dep_distribucion OWNER TO fonsagua;

CREATE TABLE fonsagua.tuberias (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       cod_tuberia VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       tipologia_tuberia VARCHAR
	       REFERENCES dominios.tipologia_tuberia(item),
       sistema VARCHAR
	       REFERENCES dominios.sistema(item),
       material VARCHAR
	       REFERENCES dominios.material(item),
       diametro NUMERIC(5,2),
       anho_construccion INTEGER,
       estado VARCHAR
	       REFERENCES dominios.estado(item),
       fugas BOOLEAN,
       loc_fugas VARCHAR,
       coment_tub VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'tuberias', 'geom', 32616, 'MULTILINESTRING', 2);

ALTER TABLE fonsagua.tuberias OWNER TO fonsagua;



ALTER TABLE fonsagua.cobertura OWNER TO fonsagua;

CREATE TABLE fonsagua.gest_comercial (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       fecha Date,
       produccion NUMERIC(5,2),
       facturacion NUMERIC(5,2),
       a_no_contabilizada NUMERIC(5,2),
       pct_a_no_contabilizada NUMERIC(5,2),
       acometidas INTEGER,
       con_medidor INTEGER,
       micromedicion NUMERIC(5,2)

);


ALTER TABLE fonsagua.gest_comercial OWNER TO fonsagua;

CREATE TABLE fonsagua.gest_financiera (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       fecha Date,
       cost_energetico NUMERIC(5,2),
       cost_quimico NUMERIC(5,2),
       cost_personal NUMERIC(5,2),
       cost_diversos NUMERIC(5,2),
       cost_totales NUMERIC(5,2),
       ingr_totales NUMERIC(5,2),
       facturacion NUMERIC(5,2),
       produccion NUMERIC(5,2),
       cost_produccion NUMERIC(5,2),
       ingr_produccion NUMERIC(5,2),
       fact_produc NUMERIC(5,2),
       margen_utilidad NUMERIC(5,2),
       activos_corrientes NUMERIC(5,2),
       pasivos_corrientes NUMERIC(5,2),
       razon_liquidez NUMERIC(5,2)

);


ALTER TABLE fonsagua.gest_financiera OWNER TO fonsagua;

CREATE TABLE fonsagua.evaluacion (
       gid SERIAL PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.abastecimientos(cod_abastecimiento),
       fecha Date,
       cobertura NUMERIC(5,2),
       agua_no_contabilizada NUMERIC(5,2),
       micromedicion NUMERIC(5,2),
       calidad_agua VARCHAR
	       REFERENCES dominios.calidad_agua(item),
       cob_saneamiento NUMERIC(5,2),
       margen_utilidad NUMERIC(5,2),
       razon_liquidez NUMERIC(5,2),
       evaluacion NUMERIC(5,2)

);


ALTER TABLE fonsagua.evaluacion OWNER TO fonsagua;

CREATE TABLE fonsagua.fuentes (
       gid SERIAL PRIMARY KEY,
       cod_fuente VARCHAR
	       UNIQUE
	       NOT NULL,
       fuente VARCHAR,
       tipo_fuente VARCHAR
	       NOT NULL
	       REFERENCES dominios.tipo_fuente(item),
       comunidad VARCHAR,
       fecha Date,
       dist_comunidad FLOAT,
       dist_linea_electrica FLOAT,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       uso BOOLEAN,
       dom_numero INTEGER,
       dom_cantaradas INTEGER,
       agr_numero INTEGER,
       agr_cantaradas INTEGER,
       gan_numero INTEGER,
       gan_cantaradas INTEGER,
       cob_vegetal BOOLEAN,
       tipo_vegetacion VARCHAR
	       REFERENCES dominios.tipo_vegetacion(item),
       especies INTEGER,
       deforestacion VARCHAR
	       REFERENCES dominios.deforestacion(item),
       naci_terremoto BOOLEAN,
       cambios_terremoto BOOLEAN,
       propietario VARCHAR
	       REFERENCES dominios.propietario(item),
       nom_propietario VARCHAR,
       escritura BOOLEAN,
       tipo_pozo VARCHAR
	       REFERENCES dominios.tipo_pozo(item),
       prof_pozo FLOAT,
       diametro_interior FLOAT,
       altura_brocal FLOAT,
       prof_bomba FLOAT,
       reperforado BOOLEAN,
       rep_distancia FLOAT,
       limpiezas BOOLEAN,
       rep_metodo VARCHAR,
       n_limpiezas INTEGER,
       comentarios VARCHAR

);

SELECT addgeometrycolumn('fonsagua', 'fuentes', 'geom', 32616, 'POINT', 2);

ALTER TABLE fonsagua.fuentes OWNER TO fonsagua;

CREATE TABLE fonsagua.aforos (
       gid SERIAL PRIMARY KEY,
       cod_fuente VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.fuentes(cod_fuente),
       aforo FLOAT,
       fecha Date,
       hora VARCHAR

);


ALTER TABLE fonsagua.aforos OWNER TO fonsagua;

CREATE TABLE fonsagua.analiticas (
       gid SERIAL PRIMARY KEY,
       cod_fuente VARCHAR
	       NOT NULL
	       REFERENCES fonsagua.fuentes(cod_fuente),
       fuente VARCHAR,
       fecha_muestra Date,
       hora_muestra VARCHAR,
       c_temperatura FLOAT,
       c_conductividad FLOAT,
       c_color INTEGER,
       c_olor VARCHAR,
       c_ph FLOAT,
       para_rango BOOLEAN,
       cond_muestra VARCHAR
	       REFERENCES dominios.cond_muestra(item),
       coment_muestra VARCHAR,
       laboratorio VARCHAR,
       fecha_analisis Date,
       temperatura FLOAT,
       color FLOAT,
       turbidez FLOAT,
       conductividad FLOAT,
       ph FLOAT,
       alcalinidad FLOAT,
       dureza FLOAT,
       oxigeno_disuelto FLOAT,
       dbo FLOAT,
       dqo FLOAT,
       mo FLOAT,
       sol_suspension FLOAT,
       sol_disueltos FLOAT,
       sol_totales FLOAT,
       nitratos FLOAT,
       nitritos FLOAT,
       coli_fecales FLOAT,
       coli_totales FLOAT,
       ecoli FLOAT,
       bac_het_tot FLOAT,
       pes_cloro FLOAT,
       pes_fosforo FLOAT,
       sulfatos FLOAT,
       cl_residual FLOAT,
       cloruros FLOAT,
       fosfatos FLOAT,
       ca FLOAT,
       mg FLOAT,
       amonio FLOAT,
       arsenico FLOAT,
       k FLOAT,
       na FLOAT,
       si FLOAT,
       fe FLOAT,
       mn FLOAT,
       al FLOAT,
       b FLOAT,
       cd FLOAT,
       co FLOAT,
       cr3 FLOAT,
       cr6 FLOAT,
       cu FLOAT,
       hg FLOAT,
       ni FLOAT,
       pb FLOAT,
       zn FLOAT,
       coment_laboratorio VARCHAR

);


ALTER TABLE fonsagua.analiticas OWNER TO fonsagua;
