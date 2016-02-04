

BEGIN;

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

ALTER TABLE fuentes_implicadas ADD COLUMN cod_fuente VARCHAR;

UPDATE version SET version = '20151111';

COMMIT;
