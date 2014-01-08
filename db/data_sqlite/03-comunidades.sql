CREATE TABLE comunidades (
       gid INTEGER PRIMARY KEY,
       comunidad VARCHAR,
       cod_comunidad VARCHAR
	       UNIQUE
	       NOT NULL,
       fecha VARCHAR,
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
	       REFERENCES tip_nucleo(item),
       anho_establecimiento INTEGER,
       n_familias INTEGER,
       n_viviendas INTEGER,
       n_habitantes INTEGER,
       tip_origen VARCHAR
	       REFERENCES tip_origen(item),
       antiguedad VARCHAR
	       REFERENCES antiguedad(item),
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
       h_adescos VARCHAR(5) DEFAULT 'false',
       n_adescos INTEGER,
       h_cargos_publicos VARCHAR(5) DEFAULT 'false',
       exp_ongs VARCHAR(5) DEFAULT 'false',
       coment_org VARCHAR,
       emigracion VARCHAR
	       REFERENCES emigracion(item),
       f_primario FLOAT,
       f_secundario FLOAT,
       f_terciario FLOAT,
       resum_econ VARCHAR,
       cp_granos INTEGER,
       sub_granos VARCHAR(5) DEFAULT 'false',
       cp_ganaderia INTEGER,
       sub_ganaderia VARCHAR(5) DEFAULT 'false',
       cp_frutales INTEGER,
       sub_frutales VARCHAR(5) DEFAULT 'false',
       cp_otros INTEGER,
       sub_otros VARCHAR(5) DEFAULT 'false',
       f_c_propia INTEGER,
       ca_cafe INTEGER,
       ca_canha INTEGER,
       ca_frutales INTEGER,
       ca_otros INTEGER,
       f_c_ajena INTEGER,
       ind_tall_maq VARCHAR(5) DEFAULT 'false',
       f_industria INTEGER,
       f_indus_for INTEGER,
       f_indus_inf INTEGER,
       f_construccion INTEGER,
       f_const_for INTEGER,
       f_const_inf INTEGER,
       f_maquila INTEGER,
       f_otros_sec INTEGER,
       comer_serv VARCHAR(5) DEFAULT 'false',
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
	       REFERENCES tip_regadio(item),
       sup_total_riego FLOAT,
       areas_pot_riego VARCHAR(5) DEFAULT 'false',
       agroquimicos VARCHAR(5) DEFAULT 'false',
       tip_agroquimicos VARCHAR,
       tec_conservacion VARCHAR(5) DEFAULT 'false',
       tip_tec_conservacion VARCHAR,
       form_agricola VARCHAR(5) DEFAULT 'false',
       f_form_agricola VARCHAR,
       form_ambiental VARCHAR(5) DEFAULT 'false',
       f_form_ambiental VARCHAR,
       n_vacas INTEGER,
       n_gallinas INTEGER,
       n_cerdos INTEGER,
       n_burros INTEGER,
       n_caballos INTEGER,
       h_cooperativas VARCHAR(5) DEFAULT 'false',
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
       electricidad VARCHAR(5) DEFAULT 'false',
       viv_electricidad INTEGER,
       tarifa_electricidad FLOAT,
       alumbrado VARCHAR(5) DEFAULT 'false',
       telf_fijo VARCHAR(5) DEFAULT 'false',
       telf_movil VARCHAR(5) DEFAULT 'false',
       cent_educativos VARCHAR(5) DEFAULT 'false',
       n_cent_educativos INTEGER,
       h_prog_salud VARCHAR,
       prog_salud VARCHAR,
       frec_prog_salud VARCHAR,
       as_unidad_salud VARCHAR(5) DEFAULT 'false',
       as_clinica_comunal VARCHAR(5) DEFAULT 'false',
       as_promotor VARCHAR(5) DEFAULT 'false',
       as_otros VARCHAR(5) DEFAULT 'false',
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
	       REFERENCES tip_acceso(item),
       tip_sup_acceso VARCHAR
	       REFERENCES tip_sup_acceso(item),
       acceso_ver VARCHAR
	       REFERENCES acceso_ver(item),
       acceso_inv VARCHAR
	       REFERENCES acceso_inv(item),
       trans_publico VARCHAR(5) DEFAULT 'false',
       frec_tpublico INTEGER,
       t_parada INTEGER,
       t_cabmunicipal INTEGER,
       otros_transportes VARCHAR,
       coment_ser VARCHAR,
       llano FLOAT,
       pend_media FLOAT,
       pend_elevada FLOAT,
       veg_residencial VARCHAR(5) DEFAULT 'false',
       veg_res_sup FLOAT,
       veg_industrial VARCHAR(5) DEFAULT 'false',
       veg_ind_sup FLOAT,
       veg_agricola VARCHAR(5) DEFAULT 'false',
       veg_agr_tip VARCHAR
	       REFERENCES veg_agr_tip(item),
       veg_agr_sup FLOAT,
       veg_forestal VARCHAR(5) DEFAULT 'false',
       veg_for_tip VARCHAR
	       REFERENCES veg_for_tip(item),
       veg_for_sup FLOAT,
       deforestacion VARCHAR
	       REFERENCES deforestacion(item),
       avance_fagricola VARCHAR
	       REFERENCES avance_fagricola(item),
       riesgo_erosion VARCHAR
	       REFERENCES riesgo_erosion(item),
       frec_incendios VARCHAR,
       est_hidrogeologicos VARCHAR(5) DEFAULT 'false',
       autor_hidrogeo VARCHAR,
       pozos_extraccion VARCHAR(5) DEFAULT 'false',
       pozos_nivel VARCHAR(5) DEFAULT 'false',
       conserv_ma VARCHAR(5) DEFAULT 'false',
       desc_conserv_ma VARCHAR,
       fr_deslizam VARCHAR(5) DEFAULT 'false',
       fr_desbord VARCHAR(5) DEFAULT 'false',
       fr_inundac VARCHAR(5) DEFAULT 'false',
       fr_asaltos VARCHAR(5) DEFAULT 'false',
       fr_ninguno VARCHAR(5) DEFAULT 'false',
       fr_otros VARCHAR(5) DEFAULT 'false',
       fr_otros_detalle VARCHAR,
       ult_fenomeno VARCHAR,
       f_dat_frg VARCHAR,
       comite_riesgos VARCHAR(5) DEFAULT 'false',
       comite_activo VARCHAR(5) DEFAULT 'false',
       comite VARCHAR,
       capac_riesgos VARCHAR(5) DEFAULT 'false',
       coment_frg VARCHAR,
       sist_abastecimiento VARCHAR
	       REFERENCES sist_abastecimiento(item),
       n_viv_abast INTEGER,
       origen_aguas VARCHAR
	       REFERENCES origen_aguas(item),
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
       f_rio VARCHAR(5) DEFAULT 'false',
       f_manantial VARCHAR(5) DEFAULT 'false',
       f_pozo VARCHAR(5) DEFAULT 'false',
       f_quebrada VARCHAR(5) DEFAULT 'false',
       f_compra VARCHAR(5) DEFAULT 'false',
       coste_agua FLOAT,
       t_medio_fuente INTEGER,
       seca_suficiencia VARCHAR(5) DEFAULT 'false',
       seca_t_espera INTEGER,
       lluvia_suficiencia VARCHAR(5) DEFAULT 'false',
       lluvia_t_espera INTEGER,
       res_hab_agua VARCHAR,
       coment_agua VARCHAR,
       coment_hab VARCHAR,
       sist_alcantarillado VARCHAR(5) DEFAULT 'false',
       alc_completo VARCHAR(5) DEFAULT 'false',
       evac_calle VARCHAR(5) DEFAULT 'false',
       evac_familiar VARCHAR(5) DEFAULT 'false',
       evac_comunal VARCHAR(5) DEFAULT 'false',
       evac_otros VARCHAR(5) DEFAULT 'false',
       ag_gris_calle VARCHAR
	       REFERENCES ag_gris_calle(item),
       a_gris_quebrada VARCHAR(5) DEFAULT 'false',
       trat_ag_residuales VARCHAR(5) DEFAULT 'false',
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
       h_let_abonera VARCHAR(5) DEFAULT 'false',
       let_abonera INTEGER,
       pq_no_aboneras VARCHAR,
       trat_aboneras VARCHAR
	       REFERENCES trat_aboneras(item),
       disp_abono VARCHAR,
       uso_letrinas VARCHAR(5) DEFAULT 'false',
       dist_let_agua VARCHAR
	       REFERENCES dist_let_agua(item),
       h_lavad_comun VARCHAR(5) DEFAULT 'false',
       n_lavaderos INTEGER,
       tip_almacenamiento VARCHAR,
       n_pilas INTEGER,
       n_barriles INTEGER,
       disp_basuras VARCHAR
	       REFERENCES disp_basuras(item),
       coment_san VARCHAR,
       necesidad1 VARCHAR,
       necesidad2 VARCHAR,
       necesidad3 VARCHAR,
       necesidad4 VARCHAR

);

SELECT addgeometrycolumn('comunidades', 'geom', 32616, 'POINT', 2);


CREATE TABLE puntos_viviendas (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_vivienda VARCHAR
	       UNIQUE
	       NOT NULL,
       tipo VARCHAR
	       REFERENCES tipo(item),
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('puntos_viviendas', 'geom', 32616, 'POINT', 2);


CREATE TABLE entrevistadores (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       nombre VARCHAR,
       cargo VARCHAR,
       instit VARCHAR,
       telefono VARCHAR

);


CREATE TABLE entrevistados (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       nombre VARCHAR,
       cargo VARCHAR,
       telefono VARCHAR,
       fecha Date

);


CREATE TABLE subcuencas (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       subcuenca VARCHAR

);


CREATE TABLE adescos (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       nombre VARCHAR,
       anho_const INTEGER,
       legalizada VARCHAR(5) DEFAULT 'false',
       n_socios INTEGER,
       antiguedad INTEGER,
       presidencia VARCHAR,
       n_hombres INTEGER,
       n_mujeres INTEGER,
       tot_miembros INTEGER

);


CREATE TABLE cargos_publicos (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       nombre VARCHAR,
       cargo VARCHAR

);


CREATE TABLE ongs (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       ong VARCHAR,
       fechas VARCHAR,
       tipo_proy VARCHAR,
       capacitacion VARCHAR(5) DEFAULT 'false',
       tipo_capac VARCHAR,
       valoracion VARCHAR
	       REFERENCES valoracion(item)

);


CREATE TABLE otras_organizaciones (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       tipo_organizacion VARCHAR
	       REFERENCES tipo_organizacion(item),
       nombre VARCHAR,
       f_creacion VARCHAR,
       actividad VARCHAR

);


CREATE TABLE tipos_cultivos (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       tipo VARCHAR,
       n_familias INTEGER,
       f_propietarias INTEGER,
       f_arrendatarias INTEGER,
       superficie FLOAT,
       rendimiento FLOAT,
       rubro VARCHAR

);


CREATE TABLE produccion_consumo (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       n_miembros INTEGER,
       produccion FLOAT,
       consumo FLOAT

);


CREATE TABLE areas_potenciales_riego (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('areas_potenciales_riego', 'geom', 32616, 'MULTIPOLYGON', 2);


CREATE TABLE ganaderia (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       tipo VARCHAR,
       n_familias INTEGER,
       f_propietarias INTEGER,
       f_arrendatarias INTEGER,
       areafam INTEGER,
       rendimiento FLOAT,
       rubro VARCHAR

);


CREATE TABLE cooperativas (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       tipo VARCHAR,
       n_asociados INTEGER,
       recursos VARCHAR,
       rubros VARCHAR

);


CREATE TABLE centros_educativos (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_c_educativo VARCHAR
	       UNIQUE
	       NOT NULL,
       nombre VARCHAR,
       niveles VARCHAR
	       REFERENCES niveles(item),
       tot_alumnos INTEGER,
       n_ninhas INTEGER,
       n_ninhos INTEGER,
       n_profesores INTEGER,
       n_prof_com INTEGER,
       prog_esc_salud VARCHAR(5) DEFAULT 'false',
       programa VARCHAR,
       frec_prog VARCHAR,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       comentarios VARCHAR

);

SELECT addgeometrycolumn('centros_educativos', 'geom', 32616, 'POINT', 2);


CREATE TABLE centros_salud (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_c_salud VARCHAR
	       UNIQUE
	       NOT NULL,
       nombre VARCHAR,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT

);

SELECT addgeometrycolumn('centros_salud', 'geom', 32616, 'POINT', 2);


CREATE TABLE otros_servicios (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_servicio VARCHAR
	       UNIQUE
	       NOT NULL,
       nombre VARCHAR,
       tipo_servicio VARCHAR
	       REFERENCES tipo_servicio(item)
	       ON UPDATE CASCADE,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT

);

SELECT addgeometrycolumn('otros_servicios', 'geom', 32616, 'POINT', 2);


CREATE TABLE capacitaciones_riesgos (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       institucion VARCHAR,
       fecha VARCHAR,
       temas VARCHAR

);


CREATE TABLE amenazas (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_amenaza VARCHAR
	       UNIQUE
	       NOT NULL,
       tipo_amenaza VARCHAR
	       REFERENCES tipo_amenaza(item),
       n_fam_afectadas INTEGER,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT

);

SELECT addgeometrycolumn('amenazas', 'geom', 32616, 'POINT', 2);


CREATE TABLE implicacion_comunidad (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       dinero_inv VARCHAR
	       REFERENCES dinero_inv(item),
       tiempo_inv VARCHAR
	       REFERENCES tiempo_inv(item)

);


CREATE TABLE abastecimientos (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       UNIQUE
	       NOT NULL,
       abastecimiento VARCHAR,
       fecha VARCHAR,
       gestion VARCHAR
	       REFERENCES gestion(item),
       ong VARCHAR,
       h_juntas_agua VARCHAR(5) DEFAULT 'false',
       h_adescos VARCHAR(5) DEFAULT 'false',
       tarifa_agua VARCHAR(5) DEFAULT 'false',
       cuota_domiciliar NUMERIC(12,2),
       cuota_comercial NUMERIC(12,2),
       cuota_cantarera NUMERIC(12,2),
       cuota_otros NUMERIC(12,2),
       tarifa_variable VARCHAR(5) DEFAULT 'false',
       cuota_variable NUMERIC(12,2),
       frec_pago VARCHAR,
       gastos_cubiertos VARCHAR,
       mora_porcent NUMERIC(12,2),
       coment_tarifa VARCHAR,
       coment_gestion VARCHAR,
       cons_domestico NUMERIC(12,2),
       cons_ag_gan NUMERIC(12,2),
       tot_consumo NUMERIC(12,2),
       tipo_sistema VARCHAR
	       REFERENCES tipo_sistema(item),
       ent_constructora VARCHAR,
       anho_construccion INTEGER,
       coste_energia NUMERIC(12,2),
       a_domiciliar VARCHAR(5) DEFAULT 'false',
       n_a_domiciliar INTEGER,
       a_cantarera VARCHAR(5) DEFAULT 'false',
       n_a_cantarera INTEGER,
       a_comercial VARCHAR(5) DEFAULT 'false',
       n_a_comercial INTEGER,
       a_otras VARCHAR(5) DEFAULT 'false',
       n_a_otras INTEGER,
       tot_acometidas INTEGER,
       a_medidor INTEGER,
       a_sin_medidor INTEGER,
       coment_sis VARCHAR,
       des_agua VARCHAR(5) DEFAULT 'false',
       des_cloracion VARCHAR(5) DEFAULT 'false',
       des_quimicos VARCHAR(5) DEFAULT 'false',
       des_otros VARCHAR(5) DEFAULT 'false',
       des_otros_detalle VARCHAR,
       des_tanque VARCHAR(5) DEFAULT 'false',
       des_impelencia VARCHAR(5) DEFAULT 'false',
       des_otra VARCHAR(5) DEFAULT 'false',
       des_otra_detalle VARCHAR,
       metodo VARCHAR,
       frec_desinfeccion VARCHAR,
       coste_desinfeccion NUMERIC(12,2),
       coment_desinfeccion VARCHAR,
       tipo_mantenimiento VARCHAR
	       REFERENCES tipo_mantenimiento(item),
       zona_mantenimiento VARCHAR,
       coste_mantenimiento NUMERIC(12,2),
       con_calidad VARCHAR(5) DEFAULT 'false',
       frec_con_calidad INTEGER,
       con_funcionamiento VARCHAR(5) DEFAULT 'false',
       fre_con_funcionamiento INTEGER,
       mant_tecnicos VARCHAR(5) DEFAULT 'false',
       proced_tecnicos VARCHAR
	       REFERENCES proced_tecnicos(item),
       proced_tec_detalle VARCHAR

);

SELECT addgeometrycolumn('abastecimientos', 'geom', 32616, 'MULTIPOLYGON', 2);


CREATE TABLE r_abastecimientos_comunidades (
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento) ON UPDATE CASCADE ON DELETE CASCADE,
       cod_comunidad VARCHAR
	       NOT NULL
       	       REFERENCES comunidades(cod_comunidad) ON UPDATE CASCADE ON DELETE CASCADE,
       PRIMARY KEY (cod_abastecimiento, cod_comunidad)
);


CREATE TABLE valoracion_sistema (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR NOT NULL,
       cod_abastecimiento VARCHAR NOT NULL,
       sist_cobros VARCHAR
	       REFERENCES sist_cobros(item),
       nivel_serv VARCHAR
	       REFERENCES nivel_serv(item),
       agua_suf VARCHAR(5) DEFAULT 'false',
       serv_continuo VARCHAR(5) DEFAULT 'false',
       acceso_tomas VARCHAR
	       REFERENCES acceso_tomas(item),
       comentarios_usuarios VARCHAR,
       FOREIGN KEY (cod_comunidad, cod_abastecimiento)
		REFERENCES r_abastecimientos_comunidades (cod_comunidad, cod_abastecimiento)
		ON UPDATE CASCADE ON DELETE CASCADE

);


CREATE TABLE datos_consumo (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR NOT NULL,
       cod_abastecimiento VARCHAR NOT NULL,
       tipo_distribucion VARCHAR
	       REFERENCES tipo_distribucion(item)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       mujeres VARCHAR(5) DEFAULT 'false',
       hombres VARCHAR(5) DEFAULT 'false',
       ninhas VARCHAR(5) DEFAULT 'false',
       ninhos VARCHAR(5) DEFAULT 'false',
       tiempo INTEGER,
       usos_agua VARCHAR,
       consumo FLOAT,
       n_miembros INTEGER,
       FOREIGN KEY (cod_comunidad, cod_abastecimiento)
		REFERENCES r_abastecimientos_comunidades (cod_comunidad, cod_abastecimiento)
		ON UPDATE CASCADE ON DELETE CASCADE

);


CREATE TABLE habitos_consumo (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       mujeres VARCHAR(5) DEFAULT 'false',
       hombres VARCHAR(5) DEFAULT 'false',
       ninhas VARCHAR(5) DEFAULT 'false',
       ninhos VARCHAR(5) DEFAULT 'false',
       tiempo INTEGER,
       consumo FLOAT,
       n_miembros INTEGER

);


CREATE TABLE fuentes_contaminacion (
       gid INTEGER PRIMARY KEY,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       tipo_contaminacion VARCHAR
	       REFERENCES tipo_contaminacion(item),
       n_fam_vierten INTEGER,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('fuentes_contaminacion', 'geom', 32616, 'POINT', 2);


CREATE TABLE juntas_agua (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       nombre VARCHAR,
       ubicacion VARCHAR,
       contacto VARCHAR,
       telefono VARCHAR,
       n_miembros INTEGER,
       n_mujeres INTEGER,
       antiguedad INTEGER,
       per_juridica VARCHAR(5) DEFAULT 'false',
       nit VARCHAR,
       anho_per_juridica INTEGER,
       reglamento VARCHAR(5) DEFAULT 'false',
       red_juntas VARCHAR(5) DEFAULT 'false',
       nom_red_juntas VARCHAR

);


CREATE TABLE personal_tecnico (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       nombre VARCHAR,
       genero VARCHAR
	       REFERENCES genero(item),
       origen VARCHAR,
       cargo VARCHAR
);


CREATE TABLE bombeos (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_bombeo VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       tipologia_bomba VARCHAR
	       REFERENCES tipologia_bomba(item),
       energia VARCHAR
	       REFERENCES energia(item),
       potencia NUMERIC(12,2),
       caudal NUMERIC(12,2),
       prof_succion NUMERIC(12,2),
       tiempo NUMERIC(12,2),
       altura INTEGER,
       n_bombas INTEGER,
       anho_construccion INTEGER,
       estado VARCHAR
	       REFERENCES estado(item),
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       coment_bom VARCHAR

);

SELECT addgeometrycolumn('bombeos', 'geom', 32616, 'POINT', 2);


CREATE TABLE cobertura (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       fecha VARCHAR,
       acometidas INTEGER,
       viviendas INTEGER,
       cobertura NUMERIC(12,2)

);


CREATE TABLE captaciones (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_captacion VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       tipo_fuente VARCHAR
	       REFERENCES tipo_fuente(item),
       sistema VARCHAR
	       REFERENCES sistema(item),
       cod_bombeo VARCHAR,
       tipo_construccion VARCHAR
	       REFERENCES tipo_construccion(item),
       anho_construccion INTEGER,
       volumen NUMERIC(12,2),
       estado VARCHAR
	       REFERENCES estado(item),
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       coment_cap VARCHAR

);

SELECT addgeometrycolumn('captaciones', 'geom', 32616, 'POINT', 2);


CREATE TABLE dep_intermedios (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_dep_intermedio VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       ubicacion VARCHAR
	       REFERENCES ubicacion(item),
       altura NUMERIC(12,2),
       sistema VARCHAR
	       REFERENCES sistema(item),
       cod_bombeo VARCHAR,
       tipo_construccion VARCHAR
	       REFERENCES tipo_construccion(item),
       anho_construccion INTEGER,
       volumen NUMERIC(12,2),
       estado VARCHAR
	       REFERENCES estado(item),
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('dep_intermedios', 'geom', 32616, 'POINT', 2);


CREATE TABLE dep_distribucion (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_dep_distribucion VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       ubicacion VARCHAR
	       REFERENCES ubicacion(item),
       altura NUMERIC(12,2),
       tipo_construccion VARCHAR
	       REFERENCES tipo_construccion(item),
       anho_construccion INTEGER,
       volumen NUMERIC(12,2),
       t_llenado NUMERIC(12,2),
       estado VARCHAR
	       REFERENCES estado(item),
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       descripcion VARCHAR

);

SELECT addgeometrycolumn('dep_distribucion', 'geom', 32616, 'POINT', 2);


CREATE TABLE tuberias (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_tuberia VARCHAR
	       UNIQUE
	       NOT NULL,
       denominacion VARCHAR,
       tipologia_tuberia VARCHAR
	       REFERENCES tipologia_tuberia(item),
       sistema VARCHAR
	       REFERENCES sistema(item),
       material VARCHAR
	       REFERENCES material(item),
       diametro NUMERIC(12,2),
       anho_construccion INTEGER,
       estado VARCHAR
	       REFERENCES estado(item),
       fugas VARCHAR(5) DEFAULT 'false',
       loc_fugas VARCHAR,
       coment_tub VARCHAR

);

SELECT addgeometrycolumn('tuberias', 'geom', 32616, 'MULTILINESTRING', 2);


CREATE TABLE gest_comercial (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       fecha VARCHAR,
       produccion NUMERIC(12,2),
       facturacion NUMERIC(12,2),
       a_no_contabilizada NUMERIC(12,2),
       pct_a_no_contabilizada NUMERIC(12,2),
       acometidas INTEGER,
       con_medidor INTEGER,
       micromedicion NUMERIC(12,2)

);


CREATE TABLE gest_financiera (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       fecha VARCHAR,
       cost_energetico NUMERIC(12,2),
       cost_quimico NUMERIC(12,2),
       cost_personal NUMERIC(12,2),
       cost_diversos NUMERIC(12,2),
       cost_totales NUMERIC(12,2),
       ingr_totales NUMERIC(12,2),
       facturacion NUMERIC(12,2),
       produccion NUMERIC(12,2),
       cost_produccion NUMERIC(12,2),
       ingr_produccion NUMERIC(12,2),
       fact_produc NUMERIC(12,2),
       margen_utilidad NUMERIC(12,2),
       activos_corrientes NUMERIC(12,2),
       pasivos_corrientes NUMERIC(12,2),
       razon_liquidez NUMERIC(12,2)

);


CREATE TABLE evaluacion (
       gid INTEGER PRIMARY KEY,
       cod_abastecimiento VARCHAR
	       NOT NULL
	       REFERENCES abastecimientos(cod_abastecimiento)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       fecha VARCHAR,
       cobertura NUMERIC(12,2),
       agua_no_contabilizada NUMERIC(12,2),
       micromedicion NUMERIC(12,2),
       calidad_agua VARCHAR
	       REFERENCES calidad_agua(item),
       cob_saneamiento NUMERIC(12,2),
       margen_utilidad NUMERIC(12,2),
       razon_liquidez NUMERIC(12,2),
       evaluacion NUMERIC(12,2)

);


CREATE TABLE fuentes (
       gid INTEGER PRIMARY KEY,
       cod_fuente VARCHAR
	       UNIQUE
	       NOT NULL,
       fuente VARCHAR,
       tipo_fuente VARCHAR
	       NOT NULL
	       REFERENCES tipo_fuente(item),
       comunidad VARCHAR,
       fecha VARCHAR,
       dist_comunidad FLOAT,
       dist_linea_electrica FLOAT,
       utm_x FLOAT,
       utm_y FLOAT,
       utm_z FLOAT,
       uso VARCHAR(5) DEFAULT 'false',
       uso_bebida VARCHAR(5) DEFAULT 'false',
       dom_numero INTEGER,
       dom_cantaradas INTEGER,
       agr_numero INTEGER,
       agr_cantaradas INTEGER,
       gan_numero INTEGER,
       gan_cantaradas INTEGER,
       cob_vegetal VARCHAR(5) DEFAULT 'false',
       tipo_vegetacion VARCHAR
	       REFERENCES tipo_vegetacion(item),
       especies INTEGER,
       deforestacion VARCHAR
	       REFERENCES deforestacion(item),
       naci_terremoto VARCHAR(5) DEFAULT 'false',
       cambios_terremoto VARCHAR(5) DEFAULT 'false',
       propietario VARCHAR
	       REFERENCES propietario(item),
       nom_propietario VARCHAR,
       escritura VARCHAR(5) DEFAULT 'false',
       tipo_pozo VARCHAR
	       REFERENCES tipo_pozo(item),
       prof_pozo FLOAT,
       diametro_interior FLOAT,
       altura_brocal FLOAT,
       prof_bomba FLOAT,
       reperforado VARCHAR(5) DEFAULT 'false',
       rep_distancia FLOAT,
       limpiezas VARCHAR(5) DEFAULT 'false',
       rep_metodo VARCHAR,
       n_limpiezas INTEGER,
       alternativa_viable VARCHAR(5) DEFAULT 'false',
       funcionamiento_verano VARCHAR
	       REFERENCES funcionamiento_verano(item),
       comentarios VARCHAR

);

SELECT addgeometrycolumn('fuentes', 'geom', 32616, 'POINT', 2);


CREATE TABLE r_abastecimientos_fuentes (
       cod_abastecimiento VARCHAR
	       NOT NULL
       	       REFERENCES abastecimientos(cod_abastecimiento) ON UPDATE CASCADE ON DELETE CASCADE,
       cod_fuente VARCHAR
	       NOT NULL
       	       REFERENCES fuentes(cod_fuente) ON UPDATE CASCADE ON DELETE CASCADE,
       PRIMARY KEY (cod_abastecimiento, cod_fuente)
);


CREATE TABLE aforos (
       gid INTEGER PRIMARY KEY,
       cod_fuente VARCHAR
	       NOT NULL
	       REFERENCES fuentes(cod_fuente)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       aforo FLOAT,
       fecha VARCHAR,
       hora VARCHAR

);


CREATE TABLE niveles_freaticos (
       gid INTEGER PRIMARY KEY,
       cod_fuente VARCHAR
	       NOT NULL
	       REFERENCES fuentes(cod_fuente)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       nivel FLOAT,
       fecha VARCHAR,
       hora VARCHAR

);


CREATE TABLE analiticas (
       gid INTEGER PRIMARY KEY,
       cod_fuente VARCHAR
	       NOT NULL
	       REFERENCES fuentes(cod_fuente)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       fuente VARCHAR,
       fecha_muestra VARCHAR,
       hora_muestra VARCHAR,
       c_temperatura FLOAT,
       c_conductividad FLOAT,
       c_color INTEGER,
       c_olor VARCHAR,
       c_ph FLOAT,
       para_rango VARCHAR(5) DEFAULT 'false',
       cond_muestra VARCHAR
	       REFERENCES cond_muestra(item),
       coment_muestra VARCHAR,
       laboratorio VARCHAR,
       fecha_analisis VARCHAR,
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
