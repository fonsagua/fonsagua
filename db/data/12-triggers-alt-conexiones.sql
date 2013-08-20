CREATE OR REPLACE FUNCTION fonsagua.alt_conexiones_compute_field_trigger() RETURNS TRIGGER AS $alt_conexiones_compute_field_trigger$
       DECLARE
	f_var_hor_t REAL;
	f_var_est_t REAL;
	tasa_crec_t REAL;
	ano_horizonte REAL;
	dot_sist_domiciliar_t REAL;
	dot_sist_cantareras_t REAL;
	dotacion REAL;
	tipo_distribucion_t VARCHAR;
	hab_conectados_t INTEGER;
       BEGIN
	SELECT COALESCE(f_var_hor,0), COALESCE(f_var_est, 0), COALESCE(tasa_crec, 0), COALESCE(ano_horiz_sist, 0), COALESCE(dot_sist_domiciliar, 0), COALESCE(dot_sist_cantareras, 0) INTO f_var_hor_t, f_var_est_t, tasa_crec_t, ano_horizonte, dot_sist_domiciliar_t, dot_sist_cantareras_t FROM fonsagua.preferencias WHERE NEW.cod_alternativa = cod_alternativa;

	SELECT tipo_distribucion INTO tipo_distribucion_t FROM fonsagua.alternativas WHERE NEW.cod_alternativa = cod_alternativa;

	IF (tipo_distribucion_t = 'Domiciliar') THEN dotacion = dot_sist_domiciliar_t; ELSE dotacion = dot_sist_cantareras_t; END IF;

	IF (NEW.hab_conectados IS NULL OR NEW.hab_conectados < 0) THEN hab_conectados_t = 0; ELSE hab_conectados_t = NEW.hab_conectados; END IF;
	RAISE LOG 'f_var_hor (%)', f_var_hor_t;	
	RAISE LOG 'dotacion (%)', dotacion;
	RAISE LOG 'hab_conectados_t (%)', hab_conectados_t;
	RAISE LOG 'tasa cre (%)', tasa_crec_t;
	RAISE LOG 'ano horizonte (%)', ano_horizonte;

--	NEW.demanda = f_var_hor_t * COALESCE(dotacion, 0) * (hab_conectados_t * (1 + tasa_crec_t * ano_horizonte / 100)) / 86400;

	NEW.demanda = f_var_hor_t * f_var_est_t * COALESCE(dotacion, 0) * (hab_conectados_t *(1 + (tasa_crec_t * ano_horizonte / 100))) / 86400;

	RETURN NEW;
       END;
$alt_conexiones_compute_field_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS alt_conexiones_compute_field_trigger ON fonsagua.alt_conexiones;
CREATE TRIGGER alt_conexiones_compute_field_trigger
BEFORE INSERT OR UPDATE ON fonsagua.alt_conexiones
FOR EACH ROW EXECUTE PROCEDURE fonsagua.alt_conexiones_compute_field_trigger();