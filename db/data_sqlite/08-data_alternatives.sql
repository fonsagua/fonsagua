CREATE TABLE alternativas (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       UNIQUE
	       NOT NULL,
       tipo_alternativa VARCHAR
	       REFERENCES tipo_alternativa(item),
       tipo_distribucion VARCHAR
	       REFERENCES tipo_distribucion(item),
       cod_departamento VARCHAR
               NOT NULL,
       departamento VARCHAR,
       cod_municipio VARCHAR
       	       NOT NULL,
       municipio VARCHAR,
       cod_canton VARCHAR
	       NOT NULL,
       canton VARCHAR,
       pobl_actual INTEGER,
       pobl_futura INTEGER,
       n_cent_educativos INTEGER,
       dot_cent_educativos INTEGER,
       n_cent_salud INTEGER,
       dot_cent_salud INTEGER,
       n_cent_otros INTEGER,
       dot_cent_otros INTEGER,
       dot_sec_primario INTEGER,
       dot_sec_secundario INTEGER,
       dot_sec_terciario INTEGER,
       dem_poblacion NUMERIC(12,2),
       dem_centros NUMERIC(12,2),
       dem_econ NUMERIC(12,2),
       demanda NUMERIC(12,2),
       caudal_fuentes NUMERIC(12,2),
       tipo_sistema VARCHAR
	       REFERENCES tipo_sistema(item),
       n_acomedidas INTEGER,
       coment_alternativa VARCHAR,
       tipo_saneamiento VARCHAR
	       REFERENCES tipo_saneamiento(item),
       trat_trampa INTEGER,
       trat_biofiltros INTEGER,
       trat_otros INTEGER,
       coment_otros VARCHAR,
       let_hoyo INTEGER,
       let_septica INTEGER,
       let_hidraulica INTEGER,
       let_abonera INTEGER,
       coment_saneamiento VARCHAR

);

SELECT addgeometrycolumn('alternativas', 'geom', 32616, 'MULTIPOLYGON', 2);


CREATE TABLE preferencias_disenho (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       UNIQUE
	       REFERENCES alternativas(cod_alternativa)
       	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       tasa_crecimiento NUMERIC(12,2) DEFAULT 2,
       ano_horiz_sistema INTEGER DEFAULT 20,
       ano_horiz_bomba INTEGER DEFAULT 10,
       dot_domiciliar INTEGER DEFAULT 90,
       dot_cantareras INTEGER DEFAULT 40,
       f_var_estacional NUMERIC(12,2) DEFAULT 1.2,
       f_var_horaria NUMERIC(12,2) DEFAULT 2.25,
       coef_q_ecologico NUMERIC(12,2) DEFAULT 0.4,
       n_integrantes_familia INTEGER DEFAULT 6,
       rendimiento_bomba NUMERIC(12,2) DEFAULT 0.6,
       pvp_kwh NUMERIC(12,2) DEFAULT 4,
       perdidas_puntual NUMERIC(12,2) DEFAULT 1,
       v_min NUMERIC(12,2) DEFAULT 0.5,
       v_max NUMERIC(12,2) DEFAULT 2,
       presion_min INTEGER DEFAULT 10,
       presion_max INTEGER DEFAULT 50

);


CREATE TABLE preferencias_bombas (
       gid INTEGER PRIMARY KEY,
       id_bomba VARCHAR
       		UNIQUE
		NOT NULL,
       potencia NUMERIC(12,2),
       precio_m NUMERIC(12,2)

);


CREATE TABLE preferencias_tuberias (
       gid INTEGER PRIMARY KEY,
       id_tub VARCHAR
       	      UNIQUE
	      NOT NULL,
       material VARCHAR,
       diametro NUMERIC(12,2),
       presion NUMERIC(12,2),
       rugosidad NUMERIC(12,4),
       precio_tubo NUMERIC(12,2),
       precio_m NUMERIC(12,2)
);


CREATE TABLE comunidades_implicadas (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES alternativas(cod_alternativa)
       	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       comunidad VARCHAR,
       cod_comunidad VARCHAR
	       NOT NULL
	       REFERENCES comunidades(cod_comunidad)
       	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       n_habitantes INTEGER,
       n_hab_alternativa INTEGER,
       dotacion NUMERIC(12,2),
       fuentes VARCHAR,
       calidad_agua VARCHAR
	       REFERENCES calidad_agua(item),
       implicacion_comunidad VARCHAR,
       prioridad_alt INTEGER
	       REFERENCES prioridad_alt(item)
               DEFAULT '1'

);


CREATE TABLE fuentes_implicadas (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES alternativas(cod_alternativa)
       	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       fuente VARCHAR,
       tipo_fuente VARCHAR
	       NOT NULL
	       REFERENCES tipo_fuente(item),
       aforo NUMERIC(12,2),
       q_ecol NUMERIC(12,2),
       q_usar NUMERIC(12,2)

);


CREATE TABLE alt_embalses (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES alternativas(cod_alternativa)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       embalse VARCHAR,
       cod_embalse VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES existencia_elemento(item),
       aforo NUMERIC(12,2),
       q_usar NUMERIC(12,2),
       altura NUMERIC(12,2),
       caudal NUMERIC(12,2),
       altura_total NUMERIC(12,2)

);

SELECT addgeometrycolumn('alt_embalses', 'geom', 32616, 'POINT', 2);


CREATE TABLE alt_fuentes (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES alternativas(cod_alternativa)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       fuente VARCHAR,
       cod_fuente VARCHAR,
       tipo_fuente_alternativa VARCHAR
	       REFERENCES tipo_fuente_alternativa(item),
       existencia_elemento VARCHAR
	       REFERENCES existencia_elemento(item),
       aforo NUMERIC(12,2),
       q_ecologico NUMERIC(12,2),
       q_usar NUMERIC(12,2),
       q_calculo NUMERIC(12,2),
       altura NUMERIC(12,2),
       presion NUMERIC(12,2),
       altura_total NUMERIC(12,2)

);

SELECT addgeometrycolumn('alt_fuentes', 'geom', 32616, 'POINT', 2);


CREATE TABLE alt_depositos (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES alternativas(cod_alternativa)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_deposito VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES existencia_elemento(item),
       tipo_deposito VARCHAR
	       REFERENCES tipo_deposito(item),
       ubicacion VARCHAR
	       REFERENCES ubicacion(item),
       tipo_construccion VARCHAR
	       REFERENCES tipo_construccion(item),
       vol_calculado NUMERIC(12,2),
       volumen NUMERIC(12,2),
       cota NUMERIC(12,2),
       nivel_maximo NUMERIC(12,2),
       nivel_minimo NUMERIC(12,2),
       nivel_inicial NUMERIC(12,2),
       diametro NUMERIC(12,2),
       q_neto_entrante NUMERIC(12,2),
       presion NUMERIC(12,2),
       altura_total NUMERIC(12,2)

);

SELECT addgeometrycolumn('alt_depositos', 'geom', 32616, 'POINT', 2);


CREATE TABLE alt_tuberias (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES alternativas(cod_alternativa)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_tuberia VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES existencia_elemento(item),
       tipologia_tuberia VARCHAR
	       REFERENCES tipologia_tuberia(item),
       sistema VARCHAR
	       REFERENCES sistema(item),
       tuberia_comercial VARCHAR
	       REFERENCES preferencias_tuberias(id_tub)
	       ON UPDATE CASCADE,
       material VARCHAR,
       rugosidad NUMERIC(12,4),
       diametro NUMERIC(12,2),
       long_tuberia NUMERIC(12,2),
       caudal NUMERIC(12,2),
       velocidad NUMERIC(12,2),
       perdida_carga NUMERIC(12,2),
       factor_friccion NUMERIC(12,2)

);

SELECT addgeometrycolumn('alt_tuberias', 'geom', 32616, 'MULTILINESTRING', 2);


CREATE TABLE alt_bombeos (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES alternativas(cod_alternativa)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_bomba VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES existencia_elemento(item),
       tipologia_bomba VARCHAR
	       REFERENCES tipologia_bomba(item),
       energia VARCHAR
	       REFERENCES energia(item),
       altura_bombeo INTEGER,
       tiempo_bombeo NUMERIC(12,2),
       caudal_bombeo NUMERIC(12,2),
       potencia_disenho NUMERIC(12,2),
       cota INTEGER,
       bomba_comercial VARCHAR
	       REFERENCES preferencias_bombas(id_bomba),
       potencia NUMERIC(12,2),
       caudal NUMERIC(12,2),
       altura NUMERIC(12,2)

);

SELECT addgeometrycolumn('alt_bombeos', 'geom', 32616, 'POINT', 2);


CREATE TABLE alt_conexiones (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
	       REFERENCES alternativas(cod_alternativa)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_conexion VARCHAR,
       denominacion VARCHAR,
       cota NUMERIC(12,2),
       hab_conectados INTEGER,
       q_extra NUMERIC(12,2),
       demanda NUMERIC(12,2),
       presion NUMERIC(12,2),
       altura_total NUMERIC(12,2)

);

SELECT addgeometrycolumn('alt_conexiones', 'geom', 32616, 'POINT', 2);


CREATE TABLE alt_valvulas (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       NOT NULL
       	       REFERENCES alternativas(cod_alternativa)
	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       cod_valvula VARCHAR,
       denominacion VARCHAR,
       existencia_elemento VARCHAR
	       REFERENCES existencia_elemento(item),
       cota NUMERIC(12,2),
       diametro INTEGER,
       consigna NUMERIC(12,2),
       caudal NUMERIC(12,2),
       velocidad NUMERIC(12,2),
       perdidas NUMERIC(12,2)

);

SELECT addgeometrycolumn('alt_valvulas', 'geom', 32616, 'POINT', 2);


CREATE TABLE presupuesto (
       gid INTEGER PRIMARY KEY,
       cod_alternativa VARCHAR
	       UNIQUE
	       NOT NULL
       	       REFERENCES alternativas(cod_alternativa)
       	       ON DELETE CASCADE
	       ON UPDATE CASCADE,
       presupuesto VARCHAR,
       levantamiento_topo NUMERIC(12,2),
       carpeta_tecnica NUMERIC(12,2),
       analiticas NUMERIC(12,2),
       estudio_hidro NUMERIC(12,2),
       compra_terrenos NUMERIC(12,2),
       legalizacion_terrenos NUMERIC(12,2),
       personalidad_junta NUMERIC(12,2),
       total_disenho NUMERIC(12,2),
       captaciones NUMERIC(12,2),
       tuberias_conduccion NUMERIC(12,2),
       accesorios_conduccion NUMERIC(12,2),
       depositos_bombeo NUMERIC(12,2),
       estacion_bombeo NUMERIC(12,2),
       bombas NUMERIC(12,2),
       instalacion_electrica NUMERIC(12,2),
       tuberias_bombeo NUMERIC(12,2),
       accesorios_bombeo NUMERIC(12,2),
       estructuras NUMERIC(12,2),
       limpieza NUMERIC(12,2),
       transporte NUMERIC(12,2),
       total_conduccion NUMERIC(12,2),
       depositos_distribucion NUMERIC(12,2),
       sistema_potab NUMERIC(12,2),
       tuberias_distribucion NUMERIC(12,2),
       accesorios_distribucion NUMERIC(12,2),
       tanques_rompecargas NUMERIC(12,2),
       cantareras NUMERIC(12,2),
       acomedidas NUMERIC(12,2),
       herramientas NUMERIC(12,2),
       bodega NUMERIC(12,2),
       total_distribucion NUMERIC(12,2),
       total_pilas NUMERIC(12,2),
       proteccion_fuentes NUMERIC(12,2),
       reforestacion NUMERIC(12,2),
       total_proteccion NUMERIC(12,2),
       personal_no_cualificado NUMERIC(12,2),
       personal_especializado NUMERIC(12,2),
       total_personal NUMERIC(12,2),
       material_equipo NUMERIC(12,2),
       varios_juntas NUMERIC(12,2),
       cursos_comunidad NUMERIC(12,2),
       total_implementacion NUMERIC(12,2),
       total_abastecimiento NUMERIC(12,2),
       trampas NUMERIC(12,2),
       biofiltros NUMERIC(12,2),
       resumidero NUMERIC(12,2),
       let_fosa NUMERIC(12,2),
       let_cierre NUMERIC(12,2),
       let_aboneras NUMERIC(12,2),
       total_saneamiento NUMERIC(12,2),
       total_abast_san NUMERIC(12,2),
       aporte_comunidad NUMERIC(12,2),
       total NUMERIC(12,2),
       total_persona NUMERIC(12,2),
       amortizacion_bomba NUMERIC(12,2),
       consumo_bomba NUMERIC(12,2),
       mantenimiento_bomba NUMERIC(12,2),
       amortizacion_tuberias NUMERIC(12,2),
       analisis_agua NUMERIC(12,2),
       cloro NUMERIC(12,2),
       fungible NUMERIC(12,2),
       fontanero NUMERIC(12,2),
       reparaciones NUMERIC(12,2),
       asistencia NUMERIC(12,2),
       cuota NUMERIC(12,2),
       cuota_persona NUMERIC(12,2)

);


CREATE VIEW priorizacion_alternativa AS
	SELECT a.gid AS oid, a.cod_comunidad AS cod_comunidad, a.cod_alternativa AS cod_alternativa, a.dotacion AS dotacion, a.fuentes AS fuentes, a.calidad_agua AS calidad_agua, a.implicacion_comunidad AS implicacion_comunidad, a.prioridad_alt AS prioridad_alt, b.tipo_alternativa AS tipo_alternativa, b.tipo_distribucion AS tipo_distribucion, b.pobl_actual AS pobl_actual, b.caudal_fuentes AS caudal_fuentes, b.demanda AS demanda, c.cuota_persona AS cuota_persona, c.total AS coste_total, c.total_persona AS coste_habitante
	FROM comunidades_implicadas a JOIN alternativas b ON a.cod_alternativa = b.cod_alternativa JOIN presupuesto c ON c.cod_alternativa = a.cod_alternativa;



DROP TRIGGER IF EXISTS priorizacion_alternativa_insert_trigger;
CREATE TRIGGER priorizacion_alternativa_insert_trigger
INSTEAD OF INSERT ON priorizacion_alternativa
BEGIN
	SELECT 1;
END;

DROP TRIGGER IF EXISTS priorizacion_alternativa_update_trigger;
CREATE TRIGGER priorizacion_alternativa_update_trigger
INSTEAD OF UPDATE ON priorizacion_alternativa
FOR EACH ROW BEGIN
	UPDATE comunidades_implicadas SET dotacion = NEW.dotacion, fuentes = NEW.fuentes, calidad_agua = NEW.calidad_agua, implicacion_comunidad = NEW.implicacion_comunidad, prioridad_alt = NEW.prioridad_alt WHERE gid = old.oid;
END;

DROP TRIGGER IF EXISTS priorizacion_alternativa_delete_trigger;
CREATE TRIGGER priorizacion_alternativa_delete_trigger
INSTEAD OF DELETE ON priorizacion_alternativa
BEGIN
	SELECT 1;
END;
