DROP TRIGGER IF EXISTS abastecimientos_compute_fields_insert_trigger;
CREATE TRIGGER abastecimientos_compute_fields_insert_trigger
AFTER INSERT ON abastecimientos
FOR EACH ROW BEGIN
	UPDATE abastecimientos SET tot_consumo = NULL, cons_domestico = NULL, tot_acometidas = NULL WHERE cod_abastecimiento = NEW.cod_abastecimiento;
	UPDATE abastecimientos SET cons_domestico = (SELECT SUM(consumo)/SUM(n_miembros) FROM datos_consumo WHERE cod_abastecimiento = abastecimientos.cod_abastecimiento GROUP BY cod_abastecimiento HAVING SUM(n_miembros) > 0), tot_consumo = (SELECT SUM(consumo) FROM datos_consumo WHERE cod_abastecimiento = abastecimientos.cod_abastecimiento GROUP BY cod_abastecimiento), tot_acometidas = IFNULL(n_a_domiciliar, 0) + IFNULL(n_a_cantarera, 0) + IFNULL(n_a_comercial, 0) + IFNULL(n_a_otras, 0) WHERE cod_abastecimiento = NEW.cod_abastecimiento;
END;

DROP TRIGGER IF EXISTS abastecimientos_compute_fields_update_trigger;
CREATE TRIGGER abastecimientos_compute_fields_update_trigger
AFTER UPDATE ON abastecimientos
FOR EACH ROW BEGIN
	UPDATE abastecimientos SET tot_consumo = NULL, cons_domestico = NULL, tot_acometidas = NULL WHERE cod_abastecimiento = NEW.cod_abastecimiento;
	UPDATE abastecimientos SET cons_domestico = (SELECT SUM(consumo)/SUM(n_miembros) FROM datos_consumo WHERE cod_abastecimiento = abastecimientos.cod_abastecimiento GROUP BY cod_abastecimiento HAVING SUM(n_miembros) > 0), tot_consumo = (SELECT SUM(consumo) FROM datos_consumo WHERE cod_abastecimiento = abastecimientos.cod_abastecimiento GROUP BY cod_abastecimiento), tot_acometidas = IFNULL(n_a_domiciliar, 0) + IFNULL(n_a_cantarera, 0) + IFNULL(n_a_comercial, 0) + IFNULL(n_a_otras, 0) WHERE cod_abastecimiento = NEW.cod_abastecimiento;
END;



DROP TRIGGER IF EXISTS amenazas_compute_fields_insert_trigger;
CREATE TRIGGER amenazas_compute_fields_insert_trigger
AFTER INSERT ON amenazas
FOR EACH ROW BEGIN
	UPDATE amenazas SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS amenazas_compute_fields_update_trigger;
CREATE TRIGGER amenazas_compute_fields_update_trigger
AFTER UPDATE ON amenazas
FOR EACH ROW BEGIN
	UPDATE amenazas SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS bombeos_compute_fields_insert_trigger;
CREATE TRIGGER bombeos_compute_fields_insert_trigger
AFTER INSERT ON bombeos
FOR EACH ROW BEGIN
	UPDATE bombeos SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS bombeos_compute_fields_update_trigger;
CREATE TRIGGER bombeos_compute_fields_update_trigger
AFTER UPDATE ON bombeos
FOR EACH ROW BEGIN
	UPDATE bombeos SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS captaciones_compute_fields_insert_trigger;
CREATE TRIGGER captaciones_compute_fields_insert_trigger
AFTER INSERT ON captaciones
FOR EACH ROW BEGIN
	UPDATE captaciones SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS captaciones_compute_fields_update_trigger;
CREATE TRIGGER captaciones_compute_fields_update_trigger
AFTER UPDATE ON captaciones
FOR EACH ROW BEGIN
	UPDATE captaciones SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS centros_educativos_compute_fields_insert_trigger;
CREATE TRIGGER centros_educativos_compute_fields_insert_trigger
AFTER INSERT ON centros_educativos
FOR EACH ROW BEGIN
	UPDATE centros_educativos SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS centros_educativos_compute_fields_update_trigger;
CREATE TRIGGER centros_educativos_compute_fields_update_trigger
AFTER UPDATE ON centros_educativos
FOR EACH ROW BEGIN
	UPDATE centros_educativos SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS centros_salud_compute_fields_insert_trigger;
CREATE TRIGGER centros_salud_compute_fields_insert_trigger
AFTER INSERT ON centros_salud
FOR EACH ROW BEGIN
	UPDATE centros_salud SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS centros_salud_compute_fields_update_trigger;
CREATE TRIGGER centros_salud_compute_fields_update_trigger
AFTER UPDATE ON centros_salud
FOR EACH ROW BEGIN
	UPDATE centros_salud SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS cobertura_compute_fields_insert_trigger;
CREATE TRIGGER cobertura_compute_fields_insert_trigger
AFTER INSERT ON cobertura
FOR EACH ROW BEGIN
	UPDATE cobertura SET cobertura = NULL WHERE gid = NEW.gid;
	UPDATE cobertura SET cobertura = acometidas * 100 / viviendas WHERE gid = NEW.gid AND acometidas IS NOT NULL AND viviendas IS NOT NULL AND viviendas > 0;
END;

DROP TRIGGER IF EXISTS cobertura_compute_fields_update_trigger;
CREATE TRIGGER cobertura_compute_fields_update_trigger
AFTER UPDATE ON cobertura
FOR EACH ROW BEGIN
	UPDATE cobertura SET cobertura = NULL WHERE gid = NEW.gid;
	UPDATE cobertura SET cobertura = acometidas * 100 / viviendas WHERE gid = NEW.gid AND acometidas IS NOT NULL AND viviendas IS NOT NULL AND viviendas > 0;
END;



DROP TRIGGER IF EXISTS comunidades_compute_fields_insert_trigger;
CREATE TRIGGER comunidades_compute_fields_insert_trigger
AFTER INSERT ON comunidades
FOR EACH ROW BEGIN
	UPDATE comunidades SET n_adescos = NULL WHERE gid = NEW.gid;
	UPDATE comunidades SET n_adescos = (SELECT COUNT(*) FROM adescos WHERE cod_comunidad = comunidades.cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad AND h_adescos = 'true';

	UPDATE comunidades SET produccion = (SELECT SUM(produccion)/COUNT(*) FROM produccion_consumo WHERE comunidades.cod_comunidad = cod_comunidad), consumo = (SELECT SUM(consumo)/COUNT(*) FROM produccion_consumo WHERE comunidades.cod_comunidad = cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET cons_dom = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET tot_consumo = (SELECT SUM(consumo) FROM datos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET cons_dom = tot_consumo / (SELECT SUM(n_miembros) FROM datos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad AND (SELECT SUM(n_miembros) FROM datos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) > 0;

	UPDATE comunidades SET cons_hab = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET cons_hab = (SELECT SUM(consumo) FROM habitos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) / (SELECT SUM(n_miembros) FROM habitos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad AND (SELECT SUM(n_miembros) FROM habitos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) > 0;

	UPDATE comunidades SET caserio = comunidad WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET utm_x = X(geom), utm_y = Y(geom) WHERE cod_comunidad = NEW.cod_comunidad AND geom IS NOT NULL;

	UPDATE comunidades SET tot_ninhos = IFNULL(n_ninhos, 0) + IFNULL(n_ninhas, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_adultos = IFNULL(n_muj_jovenes, 0) + IFNULL(n_hom_jovenes, 0) + IFNULL(n_mujeres, 0) + IFNULL(n_hombres, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_ancianos = IFNULL(n_ancianos, 0) + IFNULL(n_ancianas, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET f_c_propia = IFNULL(cp_granos, 0) + IFNULL(cp_ganaderia, 0) + IFNULL(cp_frutales, 0) + IFNULL(cp_otros, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_c_ajena = IFNULL(ca_cafe, 0) + IFNULL(ca_canha, 0) + IFNULL(ca_frutales, 0) + IFNULL(ca_otros, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_primario = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_primario = round( (IFNULL(f_c_propia, 0) + IFNULL(f_c_ajena, 0)) * 100.0 / n_familias , 1 ) WHERE cod_comunidad = NEW.cod_comunidad AND n_familias IS NOT NULL AND n_familias > 0;

	UPDATE comunidades SET f_industria = IFNULL(f_indus_for, 0) + IFNULL(f_indus_inf, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_construccion = IFNULL(f_const_for, 0) + IFNULL(f_const_inf, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_secundario = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_secundario = round ( (IFNULL(f_industria, 0) + IFNULL(f_construccion, 0) + IFNULL(f_maquila, 0) + IFNULL(f_otros_sec, 0)) * 100.0 / n_familias , 1 ) WHERE cod_comunidad = NEW.cod_comunidad AND n_familias IS NOT NULL AND n_familias > 0;

	UPDATE comunidades SET f_comercio = IFNULL(f_comer_for, 0) + IFNULL(f_comer_inf, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_terciario = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_terciario = round( (IFNULL(f_comercio, 0) + IFNULL(f_otros_ter, 0)) * 100.0 / n_familias , 1 ) WHERE cod_comunidad = NEW.cod_comunidad AND n_familias IS NOT NULL AND n_familias > 0;

	UPDATE comunidades SET area_cultivada = 0 WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET area_cultivada = area_cultivada + (f_propietarias * prop_area_cultivada) WHERE cod_comunidad = NEW.cod_comunidad AND f_propietarias IS NOT NULL AND prop_area_cultivada IS NOT NULL;
	UPDATE comunidades SET area_cultivada = area_cultivada + (f_arrendatarias * arre_area_cultivada) WHERE cod_comunidad = NEW.cod_comunidad AND f_arrendatarias IS NOT NULL AND arre_area_cultivada IS NOT NULL;
	UPDATE comunidades SET area_cultivada = area_cultivada + (f_medias * med_area_cultivada) WHERE cod_comunidad = NEW.cod_comunidad AND f_medias IS NOT NULL AND med_area_cultivada IS NOT NULL;

	UPDATE comunidades SET tot_s_abast = IFNULL(aba_s_cantareras, 0) + IFNULL(aba_s_domiciliar, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_ll_abast = IFNULL(aba_ll_cantareras, 0) + IFNULL(aba_ll_domiciliar, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_s_sin_abast = IFNULL(aba_s_nacimiento, 0) + IFNULL(aba_s_rio, 0) + IFNULL(aba_s_quebrada, 0) + IFNULL(aba_s_lluvia, 0) + IFNULL(aba_s_broquel, 0) + IFNULL(aba_s_broquel_com, 0) + IFNULL(aba_s_compra, 0) + IFNULL(aba_s_vecino, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_ll_sin_abast = IFNULL(aba_ll_nacimiento, 0) + IFNULL(aba_ll_rio, 0) + IFNULL(aba_ll_quebrada, 0) + IFNULL(aba_ll_lluvia, 0) + IFNULL(aba_ll_broquel, 0) + IFNULL(aba_ll_broquel_com, 0) + IFNULL(aba_ll_compra, 0) + IFNULL(aba_ll_vecino, 0) WHERE cod_comunidad = NEW.cod_comunidad;


END;



DROP TRIGGER IF EXISTS comunidades_compute_fields_update_trigger;
CREATE TRIGGER comunidades_compute_fields_update_trigger
AFTER UPDATE ON comunidades
FOR EACH ROW BEGIN
	UPDATE comunidades SET n_adescos = NULL WHERE gid = NEW.gid;
	UPDATE comunidades SET n_adescos = (SELECT COUNT(*) FROM adescos WHERE cod_comunidad = comunidades.cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad AND h_adescos = 'true';

	UPDATE comunidades SET produccion = (SELECT SUM(produccion)/COUNT(*) FROM produccion_consumo WHERE comunidades.cod_comunidad = cod_comunidad), consumo = (SELECT SUM(consumo)/COUNT(*) FROM produccion_consumo WHERE comunidades.cod_comunidad = cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET cons_dom = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET tot_consumo = (SELECT SUM(consumo) FROM datos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET cons_dom = tot_consumo / (SELECT SUM(n_miembros) FROM datos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad AND (SELECT SUM(n_miembros) FROM datos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) > 0;

	UPDATE comunidades SET cons_hab = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET cons_hab = (SELECT SUM(consumo) FROM habitos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) / (SELECT SUM(n_miembros) FROM habitos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) WHERE cod_comunidad = NEW.cod_comunidad AND (SELECT SUM(n_miembros) FROM habitos_consumo WHERE cod_comunidad = comunidades.cod_comunidad) > 0;

	UPDATE comunidades SET caserio = comunidad WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET utm_x = X(geom), utm_y = Y(geom) WHERE cod_comunidad = NEW.cod_comunidad AND geom IS NOT NULL;

	UPDATE comunidades SET tot_ninhos = IFNULL(n_ninhos, 0) + IFNULL(n_ninhas, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_adultos = IFNULL(n_muj_jovenes, 0) + IFNULL(n_hom_jovenes, 0) + IFNULL(n_mujeres, 0) + IFNULL(n_hombres, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_ancianos = IFNULL(n_ancianos, 0) + IFNULL(n_ancianas, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET f_c_propia = IFNULL(cp_granos, 0) + IFNULL(cp_ganaderia, 0) + IFNULL(cp_frutales, 0) + IFNULL(cp_otros, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_c_ajena = IFNULL(ca_cafe, 0) + IFNULL(ca_canha, 0) + IFNULL(ca_frutales, 0) + IFNULL(ca_otros, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_primario = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_primario = round ( (IFNULL(f_c_propia, 0) + IFNULL(f_c_ajena, 0)) * 100.0 / n_familias , 1 ) WHERE cod_comunidad = NEW.cod_comunidad AND n_familias IS NOT NULL AND n_familias > 0;

	UPDATE comunidades SET f_industria = IFNULL(f_indus_for, 0) + IFNULL(f_indus_inf, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_construccion = IFNULL(f_const_for, 0) + IFNULL(f_const_inf, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_secundario = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_secundario = round ( (IFNULL(f_industria, 0) + IFNULL(f_construccion, 0) + IFNULL(f_maquila, 0) + IFNULL(f_otros_sec, 0)) * 100.0 / n_familias , 1 ) WHERE cod_comunidad = NEW.cod_comunidad AND n_familias IS NOT NULL AND n_familias > 0;

	UPDATE comunidades SET f_comercio = IFNULL(f_comer_for, 0) + IFNULL(f_comer_inf, 0) WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_terciario = NULL WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET f_terciario = round ( (IFNULL(f_comercio, 0) + IFNULL(f_otros_ter, 0)) * 100.0 / n_familias , 1 ) WHERE cod_comunidad = NEW.cod_comunidad AND n_familias IS NOT NULL AND n_familias > 0;

	UPDATE comunidades SET area_cultivada = 0 WHERE cod_comunidad = NEW.cod_comunidad;
	UPDATE comunidades SET area_cultivada = area_cultivada + (f_propietarias * prop_area_cultivada) WHERE cod_comunidad = NEW.cod_comunidad AND f_propietarias IS NOT NULL AND prop_area_cultivada IS NOT NULL;
	UPDATE comunidades SET area_cultivada = area_cultivada + (f_arrendatarias * arre_area_cultivada) WHERE cod_comunidad = NEW.cod_comunidad AND f_arrendatarias IS NOT NULL AND arre_area_cultivada IS NOT NULL;
	UPDATE comunidades SET area_cultivada = area_cultivada + (f_medias * med_area_cultivada) WHERE cod_comunidad = NEW.cod_comunidad AND f_medias IS NOT NULL AND med_area_cultivada IS NOT NULL;

	UPDATE comunidades SET tot_s_abast = IFNULL(aba_s_cantareras, 0) + IFNULL(aba_s_domiciliar, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_ll_abast = IFNULL(aba_ll_cantareras, 0) + IFNULL(aba_ll_domiciliar, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_s_sin_abast = IFNULL(aba_s_nacimiento, 0) + IFNULL(aba_s_rio, 0) + IFNULL(aba_s_quebrada, 0) + IFNULL(aba_s_lluvia, 0) + IFNULL(aba_s_broquel, 0) + IFNULL(aba_s_broquel_com, 0) + IFNULL(aba_s_compra, 0) + IFNULL(aba_s_vecino, 0) WHERE cod_comunidad = NEW.cod_comunidad;

	UPDATE comunidades SET tot_ll_sin_abast = IFNULL(aba_ll_nacimiento, 0) + IFNULL(aba_ll_rio, 0) + IFNULL(aba_ll_quebrada, 0) + IFNULL(aba_ll_lluvia, 0) + IFNULL(aba_ll_broquel, 0) + IFNULL(aba_ll_broquel_com, 0) + IFNULL(aba_ll_compra, 0) + IFNULL(aba_ll_vecino, 0) WHERE cod_comunidad = NEW.cod_comunidad;

END;



DROP TRIGGER IF EXISTS dep_distribucion_compute_fields_insert_trigger;
CREATE TRIGGER dep_distribucion_compute_fields_insert_trigger
AFTER INSERT ON dep_distribucion
FOR EACH ROW BEGIN
	UPDATE dep_distribucion SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS dep_distribucion_compute_fields_update_trigger;
CREATE TRIGGER dep_distribucion_compute_fields_update_trigger
AFTER UPDATE ON dep_distribucion
FOR EACH ROW BEGIN
	UPDATE dep_distribucion SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS dep_intermedios_compute_fields_insert_trigger;
CREATE TRIGGER dep_intermedios_compute_fields_insert_trigger
AFTER INSERT ON dep_intermedios
FOR EACH ROW BEGIN
	UPDATE dep_intermedios SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS dep_intermedios_compute_fields_update_trigger;
CREATE TRIGGER dep_intermedios_compute_fields_update_trigger
AFTER UPDATE ON dep_intermedios
FOR EACH ROW BEGIN
	UPDATE dep_intermedios SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS fuentes_compute_fields_insert_trigger;
CREATE TRIGGER fuentes_compute_fields_insert_trigger
AFTER INSERT ON fuentes
FOR EACH ROW BEGIN
	UPDATE fuentes SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS fuentes_compute_fields_update_trigger;
CREATE TRIGGER fuentes_compute_fields_update_trigger
AFTER UPDATE ON fuentes
FOR EACH ROW BEGIN
	UPDATE fuentes SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS gest_comercial_compute_fields_insert_trigger;
CREATE TRIGGER gest_comercial_compute_fields_insert_trigger
AFTER INSERT ON gest_comercial
FOR EACH ROW BEGIN
	UPDATE gest_comercial SET a_no_contabilizada = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET a_no_contabilizada = produccion - facturacion WHERE gid = NEW.gid AND produccion IS NOT NULL AND facturacion IS NOT NULL;

	UPDATE gest_comercial SET pct_a_no_contabilizada = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET pct_a_no_contabilizada = a_no_contabilizada * 100 / produccion WHERE gid = NEW.gid AND a_no_contabilizada IS NOT NULL;

	UPDATE gest_comercial SET micromedicion = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET micromedicion = con_medidor * 100 / acometidas WHERE gid = NEW.gid AND con_medidor IS NOT NULL AND acometidas IS NOT NULL AND acometidas > 0;
END;

DROP TRIGGER IF EXISTS gest_comercial_compute_fields_update_trigger;
CREATE TRIGGER gest_comercial_compute_fields_update_trigger
AFTER UPDATE ON gest_comercial
FOR EACH ROW BEGIN
	UPDATE gest_comercial SET a_no_contabilizada = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET a_no_contabilizada = produccion - facturacion WHERE gid = NEW.gid AND produccion IS NOT NULL AND facturacion IS NOT NULL;

	UPDATE gest_comercial SET pct_a_no_contabilizada = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET pct_a_no_contabilizada = a_no_contabilizada * 100 / produccion WHERE gid = NEW.gid AND a_no_contabilizada IS NOT NULL;

	UPDATE gest_comercial SET micromedicion = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET micromedicion = con_medidor * 100 / acometidas WHERE gid = NEW.gid AND con_medidor IS NOT NULL AND acometidas IS NOT NULL AND acometidas > 0;
END;



DROP TRIGGER IF EXISTS gest_financiera_compute_fields_insert_trigger;
CREATE TRIGGER gest_financiera_compute_fields_insert_trigger
AFTER INSERT ON gest_financiera
FOR EACH ROW BEGIN
	UPDATE gest_financiera SET cost_totales = IFNULL(cost_energetico, 0) + IFNULL(cost_quimico, 0) + IFNULL(cost_personal, 0) + IFNULL(cost_diversos, 0) WHERE gid = NEW.gid;

	UPDATE gest_financiera SET cost_produccion = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET cost_produccion = cost_totales / produccion WHERE gid = NEW.gid AND produccion > 0 AND produccion IS NOT NULL;

	UPDATE gest_financiera SET ingr_produccion = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET ingr_produccion = ingr_totales / produccion WHERE gid = NEW.gid AND produccion > 0 AND produccion IS NOT NULL AND ingr_totales IS NOT NULL;

	UPDATE gest_financiera SET fact_produc = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET fact_produc = facturacion / produccion WHERE gid = NEW.gid AND produccion > 0 AND produccion IS NOT NULL AND facturacion IS NOT NULL;

	UPDATE gest_financiera SET margen_utilidad = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET margen_utilidad = (ingr_totales - cost_totales) / fact_produc WHERE gid = NEW.gid AND fact_produc > 0 AND fact_produc IS NOT NULL AND ingr_totales IS NOT NULL AND cost_totales IS NOT NULL;

	UPDATE gest_financiera SET razon_liquidez = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET razon_liquidez = activos_corrientes / pasivos_corrientes WHERE gid = NEW.gid AND pasivos_corrientes > 0 AND pasivos_corrientes IS NOT NULL AND activos_corrientes IS NOT NULL;
END;

DROP TRIGGER IF EXISTS gest_financiera_compute_fields_update_trigger;
CREATE TRIGGER gest_financiera_compute_fields_update_trigger
AFTER UPDATE ON gest_financiera
FOR EACH ROW BEGIN
	UPDATE gest_financiera SET cost_totales = IFNULL(cost_energetico, 0) + IFNULL(cost_quimico, 0) + IFNULL(cost_personal, 0) + IFNULL(cost_diversos, 0) WHERE gid = NEW.gid;

	UPDATE gest_financiera SET cost_produccion = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET cost_produccion = cost_totales / produccion WHERE gid = NEW.gid AND produccion > 0 AND produccion IS NOT NULL;

	UPDATE gest_financiera SET ingr_produccion = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET ingr_produccion = ingr_totales / produccion WHERE gid = NEW.gid AND produccion > 0 AND produccion IS NOT NULL AND ingr_totales IS NOT NULL;

	UPDATE gest_financiera SET fact_produc = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET fact_produc = facturacion / produccion WHERE gid = NEW.gid AND produccion > 0 AND produccion IS NOT NULL AND facturacion IS NOT NULL;

	UPDATE gest_financiera SET margen_utilidad = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET margen_utilidad = (ingr_totales - cost_totales) / fact_produc WHERE gid = NEW.gid AND fact_produc > 0 AND fact_produc IS NOT NULL AND ingr_totales IS NOT NULL AND cost_totales IS NOT NULL;

	UPDATE gest_financiera SET razon_liquidez = NULL WHERE gid = NEW.gid;
	UPDATE gest_financiera SET razon_liquidez = activos_corrientes / pasivos_corrientes WHERE gid = NEW.gid AND pasivos_corrientes > 0 AND pasivos_corrientes IS NOT NULL AND activos_corrientes IS NOT NULL;
END;



DROP TRIGGER IF EXISTS otros_servicios_compute_fields_insert_trigger;
CREATE TRIGGER otros_servicios_compute_fields_insert_trigger
AFTER INSERT ON otros_servicios
FOR EACH ROW BEGIN
	UPDATE otros_servicios SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS otros_servicios_compute_fields_update_trigger;
CREATE TRIGGER otros_servicios_compute_fields_update_trigger
AFTER UPDATE ON otros_servicios
FOR EACH ROW BEGIN
	UPDATE otros_servicios SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;



DROP TRIGGER IF EXISTS puntos_viviendas_compute_fields_insert_trigger;
CREATE TRIGGER puntos_viviendas_compute_fields_insert_trigger
AFTER INSERT ON puntos_viviendas
FOR EACH ROW BEGIN
	UPDATE puntos_viviendas SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;

DROP TRIGGER IF EXISTS puntos_viviendas_compute_fields_update_trigger;
CREATE TRIGGER puntos_viviendas_compute_fields_update_trigger
AFTER UPDATE ON puntos_viviendas
FOR EACH ROW BEGIN
	UPDATE puntos_viviendas SET utm_x = X(geom), utm_y = Y(geom) WHERE gid = NEW.gid AND geom IS NOT NULL;
END;


-- SECONDARY TABLES TRIGGERS


DROP TRIGGER IF EXISTS datos_consumo_compute_fields_insert_trigger;
CREATE TRIGGER datos_consumo_compute_fields_insert_trigger
AFTER INSERT ON datos_consumo
FOR EACH ROW BEGIN
	UPDATE abastecimientos SET cons_domestico = 0 WHERE cod_abastecimiento = NEW.cod_abastecimiento;
	UPDATE comunidades SET cons_dom = 0 WHERE cod_comunidad = NEW.cod_comunidad;
END;

DROP TRIGGER IF EXISTS datos_consumo_compute_fields_update_trigger;
CREATE TRIGGER datos_consumo_compute_fields_update_trigger
AFTER UPDATE ON datos_consumo
FOR EACH ROW BEGIN
	UPDATE abastecimientos SET cons_domestico = 0 WHERE cod_abastecimiento = OLD.cod_abastecimiento;
	UPDATE comunidades SET cons_dom = 0 WHERE cod_comunidad = OLD.cod_comunidad;
	UPDATE abastecimientos SET cons_domestico = 0 WHERE cod_abastecimiento = NEW.cod_abastecimiento;
	UPDATE comunidades SET cons_dom = 0 WHERE cod_comunidad = NEW.cod_comunidad;
END;

DROP TRIGGER IF EXISTS datos_consumo_compute_fields_update_trigger;
CREATE TRIGGER datos_consumo_compute_fields_update_trigger
AFTER DELETE ON datos_consumo
FOR EACH ROW BEGIN
	UPDATE abastecimientos SET cons_domestico = 0 WHERE cod_abastecimiento = OLD.cod_abastecimiento;
	UPDATE comunidades SET cons_dom = 0 WHERE cod_comunidad = OLD.cod_comunidad;
END;



DROP TRIGGER IF EXISTS adescos_compute_fields_insert_trigger;
CREATE TRIGGER adescos_compute_fields_insert_trigger
AFTER INSERT ON adescos
FOR EACH ROW BEGIN
	UPDATE adescos SET tot_miembros = IFNULL(n_hombres, 0) + IFNULL(n_mujeres, 0) WHERE gid = NEW.gid;
	UPDATE comunidades SET n_adescos = 0 WHERE cod_comunidad = NEW.cod_comunidad;
END;

DROP TRIGGER IF EXISTS adescos_compute_fields_update_trigger;
CREATE TRIGGER adescos_compute_fields_update_trigger
AFTER UPDATE ON adescos
FOR EACH ROW BEGIN
	UPDATE adescos SET tot_miembros = IFNULL(n_hombres, 0) + IFNULL(n_mujeres, 0) WHERE gid = NEW.gid;
	UPDATE comunidades SET n_adescos = 0 WHERE cod_comunidad = OLD.cod_comunidad;
	UPDATE comunidades SET n_adescos = 0 WHERE cod_comunidad = NEW.cod_comunidad;
END;



DROP TRIGGER IF EXISTS produccion_consumo_compute_fields_insert_trigger;
CREATE TRIGGER produccion_consumo_compute_fields_insert_trigger
AFTER INSERT ON produccion_consumo
FOR EACH ROW BEGIN
	UPDATE comunidades SET produccion = 0 WHERE cod_comunidad = NEW.cod_comunidad;
END;

DROP TRIGGER IF EXISTS produccion_consumo_compute_fields_update_trigger;
CREATE TRIGGER produccion_consumo_compute_fields_update_trigger
AFTER UPDATE ON produccion_consumo
FOR EACH ROW BEGIN
	UPDATE comunidades SET produccion = 0 WHERE cod_comunidad = OLD.cod_comunidad;
	UPDATE comunidades SET produccion = 0 WHERE cod_comunidad = NEW.cod_comunidad;
END;

DROP TRIGGER IF EXISTS produccion_consumo_compute_fields_update_trigger;
CREATE TRIGGER produccion_consumo_compute_fields_update_trigger
AFTER DELETE ON produccion_consumo
FOR EACH ROW BEGIN
	UPDATE comunidades SET produccion = 0 WHERE cod_comunidad = OLD.cod_comunidad;
END;



DROP TRIGGER IF EXISTS habitos_consumo_compute_fields_insert_trigger;
CREATE TRIGGER habitos_consumo_compute_fields_insert_trigger
AFTER INSERT ON habitos_consumo
FOR EACH ROW BEGIN
	UPDATE comunidades SET cons_hab = 0 WHERE cod_comunidad = NEW.cod_comunidad;
END;

DROP TRIGGER IF EXISTS habitos_consumo_compute_fields_update_trigger;
CREATE TRIGGER habitos_consumo_compute_fields_update_trigger
AFTER UPDATE ON habitos_consumo
FOR EACH ROW BEGIN
	UPDATE comunidades SET cons_hab = 0 WHERE cod_comunidad = OLD.cod_comunidad;
	UPDATE comunidades SET cons_hab = 0 WHERE cod_comunidad = NEW.cod_comunidad;
END;

DROP TRIGGER IF EXISTS habitos_consumo_compute_fields_update_trigger;
CREATE TRIGGER habitos_consumo_compute_fields_update_trigger
AFTER DELETE ON habitos_consumo
FOR EACH ROW BEGIN
	UPDATE comunidades SET cons_hab = 0 WHERE cod_comunidad = OLD.cod_comunidad;
END;
