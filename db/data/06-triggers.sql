CREATE OR REPLACE FUNCTION abastecimientos_compute_fields_trigger() RETURNS TRIGGER AS $abastecimientos_compute_fields_trigger$
    BEGIN
	NEW.tot_acometidas = 0;
	IF (NEW.n_a_domiciliar IS NOT NULL) THEN NEW.tot_acometidas = NEW.tot_acometidas + NEW.n_a_domiciliar; END IF;
	IF (NEW.n_a_cantarera IS NOT NULL) THEN NEW.tot_acometidas = NEW.tot_acometidas + NEW.n_a_cantarera; END IF;
	IF (NEW.n_a_comercial IS NOT NULL) THEN NEW.tot_acometidas = NEW.tot_acometidas + NEW.n_a_comercial; END IF;
	IF (NEW.n_a_otras IS NOT NULL) THEN NEW.tot_acometidas = NEW.tot_acometidas + NEW.n_a_otras; END IF;
        RETURN NEW;
    END;
$abastecimientos_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS abastecimientos_compute_fields_trigger ON fonsagua.abastecimientos;
CREATE TRIGGER abastecimientos_compute_fields_trigger
BEFORE INSERT OR UPDATE ON fonsagua.abastecimientos
    FOR EACH ROW EXECUTE PROCEDURE abastecimientos_compute_fields_trigger();


CREATE OR REPLACE FUNCTION gest_comercial_compute_fields_trigger() RETURNS TRIGGER AS $gest_comercial_compute_fields_trigger$
    BEGIN
	IF ((NEW.produccion IS NOT NULL) AND (NEW.facturacion IS NOT NULL)) THEN NEW.a_no_contabilizada = NEW.produccion - NEW.facturacion; ELSE NEW.a_no_contabilizada = NULL; END IF;
	IF ((NEW.a_no_contabilizada IS NOT NULL) AND (NEW.produccion > 0)) THEN NEW.pct_a_no_contabilizada = NEW.a_no_contabilizada * 100 / NEW.produccion; ELSE NEW.pct_a_no_contabilizada = NULL; END IF;
        RETURN NEW;
    END;
$gest_comercial_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS gest_comercial_compute_fields_trigger ON fonsagua.gest_comercial;
CREATE TRIGGER gest_comercial_compute_fields_trigger
BEFORE INSERT OR UPDATE ON fonsagua.gest_comercial
    FOR EACH ROW EXECUTE PROCEDURE gest_comercial_compute_fields_trigger();


CREATE OR REPLACE FUNCTION gest_financiera_compute_fields_trigger() RETURNS TRIGGER AS $gest_financiera_compute_fields_trigger$
    BEGIN
	NEW.cost_totales = 0;
	IF (NEW.cost_energetico IS NOT NULL) THEN NEW.cost_totales = NEW.cost_totales + NEW.cost_energetico; END IF;
	IF (NEW.cost_quimico IS NOT NULL) THEN NEW.cost_totales = NEW.cost_totales + NEW.cost_quimico; END IF;
	IF (NEW.cost_personal IS NOT NULL) THEN NEW.cost_totales = NEW.cost_totales + NEW.cost_personal; END IF;
	IF (NEW.cost_diversos IS NOT NULL) THEN NEW.cost_totales = NEW.cost_totales + NEW.cost_diversos; END IF;

	IF ((NEW.produccion IS NOT NULL) AND (NEW.produccion > 0)) THEN NEW.cost_produccion = cost_totales / produccion; ELSE NEW.cost_produccion = NULL; END IF;

	IF ((NEW.produccion IS NOT NULL) AND (NEW.produccion > 0) AND (NEW.ingr_totales IS NOT NULL)) THEN NEW.ingr_produccion = ingr_totales / produccion; ELSE NEW.ingr_produccion = NULL; END IF;

	IF ((NEW.produccion IS NOT NULL) AND (NEW.produccion > 0) AND (NEW.facturacion IS NOT NULL)) THEN NEW.fact_produc = facturacion / produccion; ELSE NEW.fact_produc = NULL; END IF;

	IF ((NEW.ingr_totales IS NOT NULL) AND (NEW.fact_produc IS NOT NULL) AND (NEW.fact_produc > 0)) THEN NEW.margen_utilidad = (NEW.ingr_totales - NEW.cost_totales) / NEW.fact_produc; ELSE NEW.margen_utilidad = NULL; END IF;

	IF ((NEW.activos_corrientes IS NOT NULL) AND (NEW.pasivos_corrientes IS NOT NULL)) THEN NEW.razon_liquidez = NEW.activos_corrientes / NEW.pasivos_corrientes; ELSE NEW.razon_liquidez = NULL; END IF;
        RETURN NEW;
    END;
$gest_financiera_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS gest_financiera_compute_fields_trigger ON fonsagua.gest_financiera;
CREATE TRIGGER gest_financiera_compute_fields_trigger
BEFORE INSERT OR UPDATE ON fonsagua.gest_financiera
    FOR EACH ROW EXECUTE PROCEDURE gest_financiera_compute_fields_trigger();


CREATE OR REPLACE FUNCTION comunidades_compute_fields_trigger() RETURNS TRIGGER AS $comunidades_compute_fields_trigger$
    BEGIN
	NEW.caserio = NEW.comunidad;

	NEW.tot_ninhos = 0;
	IF (NEW.n_ninhos IS NOT NULL) THEN NEW.tot_ninhos = NEW.tot_ninhos + NEW.n_ninhos; END IF;
	IF (NEW.n_ninhas IS NOT NULL) THEN NEW.tot_ninhos = NEW.tot_ninhos + NEW.n_ninhas; END IF;

	NEW.tot_adultos = 0;
	IF (NEW.n_muj_jovenes IS NOT NULL) THEN NEW.tot_adultos = NEW.tot_adultos + NEW.n_muj_jovenes; END IF;
	IF (NEW.n_hom_jovenes IS NOT NULL) THEN NEW.tot_adultos = NEW.tot_adultos + NEW.n_hom_jovenes; END IF;
	IF (NEW.n_mujeres IS NOT NULL) THEN NEW.tot_adultos = NEW.tot_adultos + NEW.n_mujeres; END IF;
	IF (NEW.n_hombres IS NOT NULL) THEN NEW.tot_adultos = NEW.tot_adultos + NEW.n_hombres; END IF;

	NEW.tot_ancianos = 0;
	IF (NEW.n_ancianos IS NOT NULL) THEN NEW.tot_ancianos = NEW.tot_ancianos + NEW.n_ancianos; END IF;
	IF (NEW.n_ancianas IS NOT NULL) THEN NEW.tot_ancianos = NEW.tot_ancianos + NEW.n_ancianas; END IF;

	NEW.f_c_propia = 0;
	IF (NEW.cp_granos IS NOT NULL) THEN NEW.f_c_propia = NEW.f_c_propia + NEW.cp_granos; END IF;
	IF (NEW.cp_ganaderia IS NOT NULL) THEN NEW.f_c_propia = NEW.f_c_propia + NEW.cp_ganaderia; END IF;
	IF (NEW.cp_frutales IS NOT NULL) THEN NEW.f_c_propia = NEW.f_c_propia + NEW.cp_frutales; END IF;
	IF (NEW.cp_otros IS NOT NULL) THEN NEW.f_c_propia = NEW.f_c_propia + NEW.cp_otros; END IF;

	NEW.f_c_ajena = 0;
	IF (NEW.ca_cafe IS NOT NULL) THEN NEW.f_c_ajena = NEW.f_c_ajena + NEW.ca_cafe; END IF;
	IF (NEW.ca_canha IS NOT NULL) THEN NEW.f_c_ajena = NEW.f_c_ajena + NEW.ca_canha; END IF;
	IF (NEW.ca_frutales IS NOT NULL) THEN NEW.f_c_ajena = NEW.f_c_ajena + NEW.ca_frutales; END IF;
	IF (NEW.ca_otros IS NOT NULL) THEN NEW.f_c_ajena = NEW.f_c_ajena + NEW.ca_otros; END IF;

	IF (NEW.n_familias > 0) THEN
		NEW.f_primario = 0;
		IF (NEW.f_c_propia IS NOT NULL) THEN NEW.f_primario = NEW.f_primario + NEW.f_c_propia; END IF;
		IF (NEW.f_c_ajena IS NOT NULL) THEN NEW.f_primario = NEW.f_primario + NEW.f_c_ajena; END IF;
		NEW.f_primario = NEW.f_primario * 100 / NEW.n_familias;
	ELSE NEW.f_primario = NULL;
	END IF;

        IF (NEW.n_familias > 0) THEN
		NEW.f_secundario = 0;
		IF (NEW.f_industria IS NOT NULL) THEN NEW.f_secundario = NEW.f_secundario + NEW.f_industria; END IF;
		IF (NEW.f_construccion IS NOT NULL) THEN NEW.f_secundario = NEW.f_secundario + NEW.f_construccion; END IF;
		IF (NEW.f_maquila IS NOT NULL) THEN NEW.f_secundario = NEW.f_secundario + NEW.f_maquila; END IF;
		IF (NEW.f_otros_sec IS NOT NULL) THEN NEW.f_secundario = NEW.f_secundario + NEW.f_otros_sec; END IF;
		NEW.f_secundario = NEW.f_secundario * 100 / NEW.n_familias;
        ELSE NEW.f_secundario = NULL; END IF;

        IF (NEW.n_familias > 0) THEN
		NEW.f_terciario = 0;
		IF (NEW.f_comercio IS NOT NULL) THEN NEW.f_terciario = NEW.f_terciario + NEW.f_comercio; END IF;
		IF (NEW.f_otros_ter IS NOT NULL) THEN NEW.f_terciario = NEW.f_terciario + NEW.f_otros_ter; END IF;
		NEW.f_terciario = NEW.f_terciario * 100 / NEW.n_familias;
	ELSE NEW.f_terciario = NULL; END IF;

	NEW.tot_s_abast = 0;
	IF (NEW.aba_s_cantareras IS NOT NULL) THEN NEW.tot_s_abast = NEW.tot_s_abast + NEW.aba_s_cantareras; END IF;
	IF (NEW.aba_s_domiciliar IS NOT NULL) THEN NEW.tot_s_abast = NEW.tot_s_abast + NEW.aba_s_domiciliar; END IF;

	NEW.tot_ll_abast = 0;
	IF (NEW.aba_ll_cantareras IS NOT NULL) THEN NEW.tot_ll_abast = NEW.tot_ll_abast + NEW.aba_ll_cantareras; END IF;
	IF (NEW.aba_ll_domiciliar IS NOT NULL) THEN NEW.tot_ll_abast = NEW.tot_ll_abast + NEW.aba_ll_domiciliar; END IF;

	NEW.tot_s_sin_abast = 0;
	IF (NEW.aba_s_nacimiento IS NOT NULL) THEN NEW.tot_s_sin_abast = NEW.tot_s_sin_abast + NEW.aba_s_nacimiento; END IF;
	IF (NEW.aba_s_rio IS NOT NULL) THEN NEW.tot_s_sin_abast = NEW.tot_s_sin_abast + NEW.aba_s_rio; END IF;
	IF (NEW.aba_s_quebrada IS NOT NULL) THEN NEW.tot_s_sin_abast = NEW.tot_s_sin_abast + NEW.aba_s_quebrada; END IF;
	IF (NEW.aba_s_lluvia IS NOT NULL) THEN NEW.tot_s_sin_abast = NEW.tot_s_sin_abast + NEW.aba_s_lluvia; END IF;
	IF (NEW.aba_s_broquel IS NOT NULL) THEN NEW.tot_s_sin_abast = NEW.tot_s_sin_abast + NEW.aba_s_broquel; END IF;
	IF (NEW.aba_s_broquel_com IS NOT NULL) THEN NEW.tot_s_sin_abast = NEW.tot_s_sin_abast + NEW.aba_s_broquel_com; END IF;
	IF (NEW.aba_s_compra IS NOT NULL) THEN NEW.tot_s_sin_abast = NEW.tot_s_sin_abast + NEW.aba_s_compra; END IF;
	IF (NEW.aba_s_vecino IS NOT NULL) THEN NEW.tot_s_sin_abast = NEW.tot_s_sin_abast + NEW.aba_s_vecino; END IF;

	NEW.tot_ll_sin_abast = 0;
	IF (NEW.aba_ll_nacimiento IS NOT NULL) THEN NEW.tot_ll_sin_abast = NEW.tot_ll_sin_abast + NEW.aba_ll_nacimiento; END IF;
	IF (NEW.aba_ll_rio IS NOT NULL) THEN NEW.tot_ll_sin_abast = NEW.tot_ll_sin_abast + NEW.aba_ll_rio; END IF;
	IF (NEW.aba_ll_quebrada IS NOT NULL) THEN NEW.tot_ll_sin_abast = NEW.tot_ll_sin_abast + NEW.aba_ll_quebrada; END IF;
	IF (NEW.aba_ll_lluvia IS NOT NULL) THEN NEW.tot_ll_sin_abast = NEW.tot_ll_sin_abast + NEW.aba_ll_lluvia; END IF;
	IF (NEW.aba_ll_broquel IS NOT NULL) THEN NEW.tot_ll_sin_abast = NEW.tot_ll_sin_abast + NEW.aba_ll_broquel; END IF;
	IF (NEW.aba_ll_broquel_com IS NOT NULL) THEN NEW.tot_ll_sin_abast = NEW.tot_ll_sin_abast + NEW.aba_ll_broquel_com; END IF;
	IF (NEW.aba_ll_compra IS NOT NULL) THEN NEW.tot_ll_sin_abast = NEW.tot_ll_sin_abast + NEW.aba_ll_compra; END IF;
	IF (NEW.aba_ll_vecino IS NOT NULL) THEN NEW.tot_ll_sin_abast = NEW.tot_ll_sin_abast + NEW.aba_ll_vecino; END IF;

	IF ((NEW.f_indus_for IS NOT NULL) AND (NEW.f_indus_inf IS NOT NULL)) THEN NEW.f_industria = NEW.f_indus_for + NEW.f_indus_inf; END IF;
	IF ((NEW.f_const_for IS NOT NULL) AND (NEW.f_const_inf IS NOT NULL)) THEN NEW.f_construccion = NEW.f_const_for + NEW.f_const_inf; END IF;
	IF ((NEW.f_comer_for IS NOT NULL) AND (NEW.f_comer_inf IS NOT NULL)) THEN NEW.f_comercio = NEW.f_comer_for + NEW.f_comer_inf; END IF;
	
        RETURN NEW;
    END;
$comunidades_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS comunidades_compute_fields_trigger ON fonsagua.comunidades;
CREATE TRIGGER comunidades_compute_fields_trigger
BEFORE INSERT OR UPDATE ON fonsagua.comunidades
    FOR EACH ROW EXECUTE PROCEDURE comunidades_compute_fields_trigger();


CREATE OR REPLACE FUNCTION adescos_compute_fields_trigger() RETURNS TRIGGER AS $adescos_compute_fields_trigger$
    BEGIN
	NEW.tot_miembros = 0;
	IF (NEW.n_hombres IS NOT NULL) THEN NEW.tot_miembros = NEW.tot_miembros + NEW.n_hombres; END IF;
	IF (NEW.n_mujeres IS NOT NULL) THEN NEW.tot_miembros = NEW.tot_miembros + NEW.n_mujeres; END IF;
        RETURN NEW;
    END;
$adescos_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS adescos_compute_fields_trigger ON fonsagua.adescos;
CREATE TRIGGER adescos_compute_fields_trigger
BEFORE INSERT OR UPDATE ON fonsagua.adescos
    FOR EACH ROW EXECUTE PROCEDURE adescos_compute_fields_trigger();


CREATE OR REPLACE FUNCTION cobertura_compute_fields_trigger() RETURNS TRIGGER AS $cobertura_compute_fields_trigger$
    BEGIN
	IF ((NEW.acometidas IS NOT NULL) AND (NEW.viviendas IS NOT NULL) AND (NEW.viviendas > 0)) THEN NEW.cobertura = NEW.acometidas * 100 / NEW.viviendas; ELSE NEW.cobertura = NULL; END IF;
        RETURN NEW;
    END;
$cobertura_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS cobertura_compute_fields_trigger ON fonsagua.cobertura;
CREATE TRIGGER cobertura_compute_fields_trigger
BEFORE INSERT OR UPDATE ON fonsagua.cobertura
    FOR EACH ROW EXECUTE PROCEDURE cobertura_compute_fields_trigger();


CREATE OR REPLACE FUNCTION gest_comercial_compute_fields_trigger() RETURNS TRIGGER AS $gest_comercial_compute_fields_trigger$
    BEGIN
	IF ((NEW.produccion IS NOT NULL) AND (NEW.facturacion IS NOT NULL)) THEN NEW.a_no_contabilizada = NEW.produccion - NEW.facturacion; ELSE NEW.a_no_contabilizada = NULL; END IF;

	IF ((NEW.a_no_contabilizada IS NOT NULL) AND (NEW.produccion > 0)) THEN NEW.pct_a_no_contabilizada = NEW.a_no_contabilizada * 100 / NEW.produccion; ELSE NEW.pct_a_no_contabilizada = NULL; END IF;

	IF ((NEW.con_medidor IS NOT NULL) AND (NEW.acometidas IS NOT NULL) AND (NEW.acometidas > 0)) THEN NEW.micromedicion = NEW.con_medidor * 100 / NEW.acometidas; ELSE NEW.micromedicion = NULL; END IF;
        RETURN NEW;
    END;
$gest_comercial_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS gest_comercial_compute_fields_trigger ON fonsagua.gest_comercial;
CREATE TRIGGER gest_comercial_compute_fields_trigger
BEFORE INSERT OR UPDATE ON fonsagua.gest_comercial
    FOR EACH ROW EXECUTE PROCEDURE gest_comercial_compute_fields_trigger();

