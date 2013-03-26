CREATE TABLE public.comunidades (
       gid SERIAL PRIMARY KEY,
       comunidad VARCHAR
	       ,
       cod_comunidad VARCHAR
	       UNIQUE
	       NOT NULL
	       ,
       fecha Date
	       ,
       coment_gen VARCHAR
	       ,
       departamento VARCHAR
	       ,
       cod_departamento VARCHAR
	       NOT NULL
	       ,
       municipio VARCHAR
	       ,
       cod_municipio VARCHAR
	       NOT NULL
	       ,
       canton VARCHAR
	       ,
       cod_canton VARCHAR
	       NOT NULL
	       ,
       caserio VARCHAR
	       ,
       cod_caserio VARCHAR
	       NOT NULL
	       ,
       utm_x FLOAT
	       ,
       utm_y FLOAT
	       ,
       utm_z FLOAT
	       ,
       punto_descripcion VARCHAR
	       ,
       cuenca VARCHAR
	       ,
       coment_loc VARCHAR
	       ,
       tip_nucleo VARCHAR
	       REFERENCES tip_nucleo
	       ,
       anho_establecimiento INTEGER
	       ,
       n_familias INTEGER
	       ,
       n_viviendas INTEGER
	       ,
       n_habitantes INTEGER
	       ,
       tip_origen VARCHAR
	       REFERENCES tip_origen
	       ,
       antiguedad VARCHAR
	       REFERENCES antiguedad
	       ,
       lug_origen VARCHAR
	       ,
       n_ninhas INTEGER
	       ,
       n_ninhos INTEGER
	       ,
       tot_ninhos INTEGER
	       ,
       n_muj_jovenes INTEGER
	       ,
       n_hom_jovenes INTEGER
	       ,
       n_mujeres INTEGER
	       ,
       n_hombres INTEGER
	       ,
       tot_adultos INTEGER
	       ,
       n_ancianas INTEGER
	       ,
       n_ancianos INTEGER
	       ,
       tot_ancianos INTEGER
	       ,
       resum_censo VARCHAR
	       ,
       coment_pob VARCHAR
	       ,
       h_adescos BOOLEAN
	       ,
       n_adescos INTEGER
	       ,
       h_cargos_publicos BOOLEAN
	       ,
       exp_ongs BOOLEAN
	       ,
       coment_org VARCHAR
	       ,
       emigracion VARCHAR
	       REFERENCES emigracion
	       ,
       f_primario FLOAT
	       ,
       f_secundario FLOAT
	       ,
       f_terciario FLOAT
	       ,
       resum_econ VARCHAR
	       ,
       cp_granos INTEGER
	       ,
       sub_granos BOOLEAN
	       ,
       cp_ganaderia INTEGER
	       ,
       sub_ganaderia BOOLEAN
	       ,
       cp_frutales INTEGER
	       ,
       sub_frutales BOOLEAN
	       ,
       cp_otros INTEGER
	       ,
       sub_otros BOOLEAN
	       ,
       f_c_propia INTEGER
	       ,
       ca_cafe INTEGER
	       ,
       ca_canha INTEGER
	       ,
       ca_frutales INTEGER
	       ,
       ca_otros INTEGER
	       ,
       f_c_ajena INTEGER
	       ,
       ind_tall_maq BOOLEAN
	       ,
       f_industria INTEGER
	       ,
       f_indus_for INTEGER
	       ,
       f_indus_inf INTEGER
	       ,
       f_construccion INTEGER
	       ,
       f_const_for INTEGER
	       ,
       f_const_inf INTEGER
	       ,
       f_maquila INTEGER
	       ,
       f_otros_sec INTEGER
	       ,
       comer_serv BOOLEAN
	       ,
       f_comercio INTEGER
	       ,
       f_comer_for INTEGER
	       ,
       f_comer_inf INTEGER
	       ,
       f_otros_ter INTEGER
	       ,
       n_agropecuario INTEGER
	       ,
       n_industria INTEGER
	       ,
       n_construccion INTEGER
	       ,
       n_maquila INTEGER
	       ,
       n_comercio INTEGER
	       ,
       n_otros INTEGER
	       ,
       coment_eco VARCHAR
	       ,
       f_propietarias INTEGER
	       ,
       prop_hombres INTEGER
	       ,
       prop_mujeres INTEGER
	       ,
       prop_registrada INTEGER
	       ,
       prop_tramites INTEGER
	       ,
       prop_noregistrada INTEGER
	       ,
       prop_area_cultivada FLOAT
	       ,
       f_arrendatarias INTEGER
	       ,
       arre_hombres INTEGER
	       ,
       arre_mujeres INTEGER
	       ,
       arre_area_cultivada FLOAT
	       ,
       f_medias INTEGER
	       ,
       med_hombres INTEGER
	       ,
       med_mujeres INTEGER
	       ,
       med_area_cultivada FLOAT
	       ,
       area_cultivada FLOAT
	       ,
       tip_cultivos VARCHAR
	       ,
       produccion FLOAT
	       ,
       consumo FLOAT
	       ,
       lug_agua_cercano VARCHAR
	       ,
       tip_regadio VARCHAR
	       REFERENCES tip_regadio
	       ,
       sup_total_riego FLOAT
	       ,
       areas_pot_riego BOOLEAN
	       ,
       agroquimicos BOOLEAN
	       ,
       tip_agroquimicos VARCHAR
	       ,
       tec_conservacion BOOLEAN
	       ,
       tip_tec_conservacion VARCHAR
	       ,
       form_agricola BOOLEAN
	       ,
       f_form_agricola VARCHAR
	       ,
       form_ambiental BOOLEAN
	       ,
       f_form_ambiental VARCHAR
	       ,
       n_vacas INTEGER
	       ,
       n_gallinas INTEGER
	       ,
       n_cerdos INTEGER
	       ,
       n_burros INTEGER
	       ,
       n_caballos INTEGER
	       ,
       h_cooperativas BOOLEAN
	       ,
       f_viv_prop INTEGER
	       ,
       viv_prop_hombres INTEGER
	       ,
       viv_prop_mujeres INTEGER
	       ,
       f_viv_noprop INTEGER
	       ,
       viv_bahareque INTEGER
	       ,
       viv_adobe INTEGER
	       ,
       viv_mixto INTEGER
	       ,
       viv_otros INTEGER
	       ,
       coc_lenha INTEGER
	       ,
       coc_lenha_mej INTEGER
	       ,
       electricidad BOOLEAN
	       ,
       viv_electricidad INTEGER
	       ,
       tarifa_electricidad FLOAT
	       ,
       alumbrado BOOLEAN
	       ,
       telf_fijo BOOLEAN
	       ,
       telf_movil BOOLEAN
	       ,
       cent_educativos BOOLEAN
	       ,
       n_cent_educativos INTEGER
	       ,
       h_prog_salud VARCHAR
	       ,
       prog_salud VARCHAR
	       ,
       frec_prog_salud VARCHAR
	       ,
       as_unidad_salud BOOLEAN
	       ,
       as_clinica_comunal BOOLEAN
	       ,
       as_promotor BOOLEAN
	       ,
       as_otros BOOLEAN
	       ,
       as_otros_detalle VARCHAR
	       ,
       n_cent_salud INTEGER
	       ,
       t_unidad_salud INTEGER
	       ,
       t_clinica_comunal INTEGER
	       ,
       frec_med_clinica INTEGER
	       ,
       frec_promotor INTEGER
	       ,
       enf_tip_ninhos VARCHAR
	       ,
       muertes_ninhos INTEGER
	       ,
       enf_tip_adultos VARCHAR
	       ,
       muertes_adultos INTEGER
	       ,
       f_dat_sanidad VARCHAR
	       ,
       n_iglesias INTEGER
	       ,
       tip_acceso VARCHAR
	       REFERENCES tip_acceso
	       ,
       tip_sup_acceso VARCHAR
	       REFERENCES tip_sup_acceso
	       ,
       acceso_ver VARCHAR
	       REFERENCES acceso_ver
	       ,
       acceso_inv VARCHAR
	       REFERENCES acceso_inv
	       ,
       trans_publico BOOLEAN
	       ,
       frec_tpublico INTEGER
	       ,
       t_parada INTEGER
	       ,
       t_cabmunicipal INTEGER
	       ,
       otros_transportes VARCHAR
	       ,
       coment_ser VARCHAR
	       ,
       llano FLOAT
	       ,
       pend_media FLOAT
	       ,
       pend_elevada FLOAT
	       ,
       veg_residencial BOOLEAN
	       ,
       veg_res_sup FLOAT
	       ,
       veg_industrial BOOLEAN
	       ,
       veg_ind_sup FLOAT
	       ,
       veg_agricola BOOLEAN
	       ,
       veg_agr_tip VARCHAR
	       REFERENCES veg_agr_tip
	       ,
       veg_agr_sup FLOAT
	       ,
       veg_forestal BOOLEAN
	       ,
       veg_for_tip VARCHAR
	       REFERENCES veg_for_tip
	       ,
       veg_for_sup FLOAT
	       ,
       deforestacion VARCHAR
	       REFERENCES deforestacion
	       ,
       avance_fagricola VARCHAR
	       REFERENCES avance_fagricola
	       ,
       riesgo_erosion VARCHAR
	       REFERENCES riesgo_erosion
	       ,
       frec_incendios VARCHAR
	       ,
       est_hidrogeologicos BOOLEAN
	       ,
       autor_hidrogeo VARCHAR
	       ,
       pozos_extraccion BOOLEAN
	       ,
       pozos_nivel BOOLEAN
	       ,
       conserv_ma BOOLEAN
	       ,
       desc_conserv_ma VARCHAR
	       ,
       fr_deslizam BOOLEAN
	       ,
       fr_desbord BOOLEAN
	       ,
       fr_inundac BOOLEAN
	       ,
       fr_asaltos BOOLEAN
	       ,
       fr_ninguno BOOLEAN
	       ,
       fr_otros BOOLEAN
	       ,
       fr_otros_detalle VARCHAR
	       ,
       ult_fenomeno VARCHAR
	       ,
       f_dat_frg VARCHAR
	       ,
       comite_riesgos BOOLEAN
	       ,
       comite_activo BOOLEAN
	       ,
       comite VARCHAR
	       ,
       capac_riesgos BOOLEAN
	       ,
       coment_frg VARCHAR
	       ,
       sist_abastecimiento VARCHAR
	       REFERENCES sist_abastecimiento
	       ,
       n_viv_abast INTEGER
	       ,
       origen_aguas VARCHAR
	       REFERENCES origen_aguas
	       ,
       f_dat_abast VARCHAR
	       ,
       resum_abast VARCHAR
	       ,
       aba_cantareras INTEGER
	       ,
       aba_domiciliar INTEGER
	       ,
       tot_abast INTEGER
	       ,
       aba_nacimiento INTEGER
	       ,
       aba_quebrada INTEGER
	       ,
       aba_rio INTEGER
	       ,
       aba_broquel INTEGER
	       ,
       aba_broquel_com INTEGER
	       ,
       aba_compra INTEGER
	       ,
       aba_lluvia INTEGER
	       ,
       aba_vecino INTEGER
	       ,
       tot_sin_abast INTEGER
	       ,
       cons_dom FLOAT
	       ,
       cons_ag_gan FLOAT
	       ,
       tot_consumo FLOAT
	       ,
       comen_cons VARCHAR
	       ,
       cons_hab FLOAT
	       ,
       f_rio BOOLEAN
	       ,
       f_manantial BOOLEAN
	       ,
       f_pozo BOOLEAN
	       ,
       f_quebrada BOOLEAN
	       ,
       f_compra BOOLEAN
	       ,
       coste_agua FLOAT
	       ,
       t_medio_fuente INTEGER
	       ,
       seca_suficiencia BOOLEAN
	       ,
       seca_t_espera INTEGER
	       ,
       lluvia_suficiencia BOOLEAN
	       ,
       lluvia_t_espera INTEGER
	       ,
       res_hab_agua VARCHAR
	       ,
       coment_agua VARCHAR
	       ,
       coment_hab VARCHAR
	       ,
       sist_alcantarillado BOOLEAN
	       ,
       alc_completo BOOLEAN
	       ,
       evac_calle BOOLEAN
	       ,
       evac_familiar BOOLEAN
	       ,
       evac_comunal BOOLEAN
	       ,
       evac_otros BOOLEAN
	       ,
       ag_gris_calle VARCHAR
	       REFERENCES ag_gris_calle
	       ,
       a_gris_quebrada BOOLEAN
	       ,
       trat_ag_residuales BOOLEAN
	       ,
       trat_trampa INTEGER
	       ,
       trat_biofiltro INTEGER
	       ,
       trat_otros INTEGER
	       ,
       sin_tratamiento INTEGER
	       ,
       trat_comunal VARCHAR
	       ,
       let_vivienda INTEGER
	       ,
       let_comunal INTEGER
	       ,
       let_monte INTEGER
	       ,
       let_hoyo INTEGER
	       ,
       let_septica INTEGER
	       ,
       let_hidra INTEGER
	       ,
       h_let_abonera BOOLEAN
	       ,
       let_abonera INTEGER
	       ,
       pq_no_aboneras VARCHAR
	       ,
       trat_aboneras VARCHAR
	       REFERENCES trat_aboneras
	       ,
       disp_abono VARCHAR
	       ,
       uso_letrinas BOOLEAN
	       ,
       dist_let_agua VARCHAR
	       REFERENCES dist_let_agua
	       ,
       h_lavad_comun BOOLEAN
	       ,
       n_lavaderos INTEGER
	       ,
       tip_almacenamiento VARCHAR
	       ,
       n_pilas INTEGER
	       ,
       n_barriles INTEGER
	       ,
       disp_basuras VARCHAR
	       REFERENCES disp_basuras
	       ,
       coment_san VARCHAR
	       ,
       necesidad1 VARCHAR
	       ,
       necesidad2 VARCHAR
	       ,
       necesidad3 VARCHAR
	       ,
       necesidad4 VARCHAR

);

SELECT addgeometrycolumn('public', 'comunidades', 'geom', 32616, 'POINT', 2);

ALTER TABLE public.comunidades OWNER TO fonsagua;CREATE TABLE public.puntos_viviendas (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       cod_vivienda INTEGER
	       UNIQUE
	       NOT NULL
	       ,
       tipo VARCHAR
	       REFERENCES tipo
	       ,
       utm_x FLOAT
	       ,
       utm_y FLOAT
	       ,
       utm_z FLOAT
	       ,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('public', 'puntos_viviendas', 'geom', 32616, 'POINT', 2);

ALTER TABLE public.puntos_viviendas OWNER TO fonsagua;CREATE TABLE public.entrevistadores (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       nombre VARCHAR
	       ,
       cargo VARCHAR
	       ,
       instit VARCHAR
	       ,
       telefono INTEGER

);


ALTER TABLE public.entrevistadores OWNER TO fonsagua;CREATE TABLE public.entrevistados (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       nombre VARCHAR
	       ,
       cargo VARCHAR
	       ,
       telefono INTEGER
	       ,
       fecha Date

);


ALTER TABLE public.entrevistados OWNER TO fonsagua;CREATE TABLE public.subcuencas (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       subcuenca VARCHAR

);


ALTER TABLE public.subcuencas OWNER TO fonsagua;CREATE TABLE public.adescos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       nombre VARCHAR
	       ,
       anho_const INTEGER
	       ,
       legalizada BOOLEAN
	       ,
       n_socios INTEGER
	       ,
       antiguedad INTEGER
	       ,
       presidente VARCHAR
	       ,
       n_hombres INTEGER
	       ,
       n_mujeres INTEGER
	       ,
       tot_miembros INTEGER

);


ALTER TABLE public.adescos OWNER TO fonsagua;CREATE TABLE public.cargos_publicos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       nombre VARCHAR
	       ,
       cargo VARCHAR

);


ALTER TABLE public.cargos_publicos OWNER TO fonsagua;CREATE TABLE public.ongs (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       ong VARCHAR
	       ,
       fechas VARCHAR
	       ,
       tipo_proy VARCHAR
	       ,
       capacitacion BOOLEAN
	       ,
       tipo_capac VARCHAR
	       ,
       valoracion VARCHAR
	       REFERENCES valoracion

);


ALTER TABLE public.ongs OWNER TO fonsagua;CREATE TABLE public.otras_organizaciones (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       tipo_organizacion VARCHAR
	       REFERENCES tipo_organizacion
	       ,
       nombre VARCHAR
	       ,
       f_creacion Date
	       ,
       actividad VARCHAR

);


ALTER TABLE public.otras_organizaciones OWNER TO fonsagua;CREATE TABLE public.tipos_cultivos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       tipo VARCHAR
	       ,
       n_familias INTEGER
	       ,
       f_propietarias INTEGER
	       ,
       f_arrendatarias INTEGER
	       ,
       superficie FLOAT
	       ,
       rendimiento FLOAT
	       ,
       rubro VARCHAR

);


ALTER TABLE public.tipos_cultivos OWNER TO fonsagua;CREATE TABLE public.produccion_consumo (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       n_miembros INTEGER
	       ,
       produccion FLOAT
	       ,
       consumo FLOAT

);


ALTER TABLE public.produccion_consumo OWNER TO fonsagua;CREATE TABLE public.areas_potenciales_riego (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('public', 'areas_potenciales_riego', 'geom', 32616, 'MULTIPOLYGON', 2);

ALTER TABLE public.areas_potenciales_riego OWNER TO fonsagua;CREATE TABLE public.ganaderia (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       tipo VARCHAR
	       ,
       n_familias INTEGER
	       ,
       f_propietarias INTEGER
	       ,
       f_arrendatarias INTEGER
	       ,
       areafam INTEGER
	       ,
       rendimiento FLOAT
	       ,
       rubro VARCHAR

);


ALTER TABLE public.ganaderia OWNER TO fonsagua;CREATE TABLE public.cooperativas (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       tipo VARCHAR
	       ,
       n_asociados INTEGER
	       ,
       recursos VARCHAR
	       ,
       rubros VARCHAR

);


ALTER TABLE public.cooperativas OWNER TO fonsagua;CREATE TABLE public.centros_educativos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       cod_c_educativo INTEGER
	       UNIQUE
	       NOT NULL
	       ,
       nombre VARCHAR
	       ,
       niveles VARCHAR
	       REFERENCES niveles
	       ,
       tot_alumnos INTEGER
	       ,
       n_ninhas INTEGER
	       ,
       n_ninhos INTEGER
	       ,
       n_profesores INTEGER
	       ,
       n_prof_com INTEGER
	       ,
       prog_esc_salud BOOLEAN
	       ,
       programa VARCHAR
	       ,
       frec_prog VARCHAR
	       ,
       utm_x FLOAT
	       ,
       utm_y FLOAT
	       ,
       utm_z FLOAT
	       ,
       comentarios VARCHAR

);

SELECT addgeometrycolumn('public', 'centros_educativos', 'geom', 32616, 'POINT', 2);

ALTER TABLE public.centros_educativos OWNER TO fonsagua;CREATE TABLE public.centros_salud (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       cod_c_salud INTEGER
	       UNIQUE
	       NOT NULL
	       ,
       nombre VARCHAR
	       ,
       utm_x FLOAT
	       ,
       utm_y FLOAT
	       ,
       utm_z FLOAT

);

SELECT addgeometrycolumn('public', 'centros_salud', 'geom', 32616, 'POINT', 2);

ALTER TABLE public.centros_salud OWNER TO fonsagua;CREATE TABLE public.otros_servicios (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       cod_servicio INTEGER
	       UNIQUE
	       NOT NULL
	       ,
       nombre VARCHAR
	       ,
       tipo_servicio VARCHAR
	       REFERENCES tipo_servicio
	       ,
       utm_x FLOAT
	       ,
       utm_y FLOAT
	       ,
       utm_z FLOAT

);

SELECT addgeometrycolumn('public', 'otros_servicios', 'geom', 32616, 'POINT', 2);

ALTER TABLE public.otros_servicios OWNER TO fonsagua;CREATE TABLE public.capacitaciones_riesgos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       institucion VARCHAR
	       ,
       fecha Date
	       ,
       temas VARCHAR

);


ALTER TABLE public.capacitaciones_riesgos OWNER TO fonsagua;CREATE TABLE public.amenazas (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       cod_amenaza INTEGER
	       UNIQUE
	       NOT NULL
	       ,
       tipo_amenaza VARCHAR
	       REFERENCES tipo_amenaza
	       ,
       n_fam_afectadas INTEGER
	       ,
       utm_x FLOAT
	       ,
       utm_y FLOAT
	       ,
       utm_z FLOAT

);

SELECT addgeometrycolumn('public', 'amenazas', 'geom', 32616, 'POINT', 2);

ALTER TABLE public.amenazas OWNER TO fonsagua;CREATE TABLE public.r_comunidades_abastecimientos (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       cod_abastecimiento VARCHAR
	       NOT NULL

);


ALTER TABLE public.r_comunidades_abastecimientos OWNER TO fonsagua;CREATE TABLE public.implicacion_comunidad (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       dinero_inv VARCHAR
	       REFERENCES dinero_inv
	       ,
       tiempo_inv VARCHAR
	       REFERENCES tiempo_inv

);


ALTER TABLE public.implicacion_comunidad OWNER TO fonsagua;CREATE TABLE public.valoracion_sistema (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       sist_cobros VARCHAR
	       REFERENCES sist_cobros
	       ,
       nivel_serv VARCHAR
	       REFERENCES nivel_serv
	       ,
       agua_suf BOOLEAN
	       ,
       serv_continuo BOOLEAN
	       ,
       acceso_tomas VARCHAR
	       REFERENCES acceso_tomas
	       ,
       comentarios_usuarios VARCHAR

);


ALTER TABLE public.valoracion_sistema OWNER TO fonsagua;CREATE TABLE public.datos_consumo (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       cod_abastecimiento VARCHAR
	       ,
       tipo_abastecimiento VARCHAR
	       REFERENCES tipo_abastecimiento
	       ,
       mujeres BOOLEAN
	       ,
       hombres BOOLEAN
	       ,
       ninhas BOOLEAN
	       ,
       ninhos BOOLEAN
	       ,
       tiempo INTEGER
	       ,
       usos_agua VARCHAR
	       ,
       consumo FLOAT
	       ,
       n_miembros INTEGER

);


ALTER TABLE public.datos_consumo OWNER TO fonsagua;CREATE TABLE public.habitos_consumo (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       mujeres BOOLEAN
	       ,
       hombres BOOLEAN
	       ,
       ninhas BOOLEAN
	       ,
       ninhos BOOLEAN
	       ,
       tiempo INTEGER
	       ,
       consumo FLOAT
	       ,
       n_miembros INTEGER

);


ALTER TABLE public.habitos_consumo OWNER TO fonsagua;CREATE TABLE public.fuentes_contaminacion (
       gid SERIAL PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ,
       cod_f_contaminacion INTEGER
	       UNIQUE
	       NOT NULL
	       ,
       tipo_contaminzacion VARCHAR
	       REFERENCES tipo_contaminzacion
	       ,
       n_fam_vierten INTEGER
	       ,
       utm_x FLOAT
	       ,
       utm_y FLOAT
	       ,
       utm_z FLOAT
	       ,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('public', 'fuentes_contaminacion', 'geom', 32616, 'POINT', 2);

ALTER TABLE public.fuentes_contaminacion OWNER TO fonsagua;