CREATE OR REPLACE FUNCTION fonsagua.alt_conexiones_compute_field_trigger() RETURNS TRIGGER AS $alt_conexiones_compute_field_trigger$
       DECLARE
	tipo_distribucion_t VARCHAR;
	f_var_estacional_t REAL;
	f_var_horaria_t REAL;
	hab_conectados_t INTEGER;
       BEGIN

	SELECT tipo_distribucion INTO tipo_distribucion_t FROM fonsagua.alternativas WHERE NEW.cod_alternativa = cod_alternativa;

	SELECT COALESCE(f_var_estacional, 0), COALESCE(f_var_horaria, 0) INTO f_var_estacional_t, f_var_horaria_t FROM fonsagua.preferencias_disenho WHERE cod_alternativa = NEW.cod_alternativa;

	IF (NEW.hab_conectados IS NULL OR NEW.hab_conectados < 0) THEN hab_conectados_t = 0; ELSE hab_conectados_t = NEW.hab_conectados; END IF;

	NEW.demanda = (f_var_estacional_t * f_var_horaria_t * fonsagua.demanda_poblacion_function(NEW.cod_alternativa, tipo_distribucion_t, fonsagua.pobl_futura_function(NEW.cod_alternativa, hab_conectados_t))) + COALESCE(NEW.q_extra, 0)/86400;

	RETURN NEW;
       END;
$alt_conexiones_compute_field_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS alt_conexiones_compute_field_trigger ON fonsagua.alt_conexiones;
CREATE TRIGGER alt_conexiones_compute_field_trigger
BEFORE INSERT OR UPDATE ON fonsagua.alt_conexiones
FOR EACH ROW EXECUTE PROCEDURE fonsagua.alt_conexiones_compute_field_trigger();

