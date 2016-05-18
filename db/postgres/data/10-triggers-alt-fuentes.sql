CREATE OR REPLACE FUNCTION fonsagua.alt_fuentes_compute_field_trigger() RETURNS TRIGGER AS $alt_fuentes_compute_field_trigger$
       DECLARE
	coef_q_eco_t REAL;
       BEGIN
	SELECT COALESCE(coef_q_ecologico,0) INTO coef_q_eco_t FROM fonsagua.preferencias_disenho WHERE cod_alternativa = NEW.cod_alternativa;
	IF NOT (NEW.aforo >= 0) THEN NEW.aforo = 0; END IF;
	IF NOT (coef_q_eco_t >= 0) THEN coef_q_eco_t = 0; END IF;


	IF NEW.tipo_fuente_alternativa = 'Pozo' THEN
	   NEW.q_ecologico = NULL;
	   IF NEW.q_usar IS NULL THEN NEW.q_usar = 0; END IF;
	ELSE
	   NEW.q_ecologico = coef_q_eco_t * NEW.aforo;
	   IF (NEW.q_usar IS NULL) OR (NEW.q_usar > NEW.q_ecologico) THEN NEW.q_usar = NEW.q_ecologico; END IF;
	END IF;

	NEW.q_calculo = -NEW.q_usar;

	RETURN NEW;
       END;
$alt_fuentes_compute_field_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS alt_fuentes_compute_field_trigger ON fonsagua.alt_fuentes;
CREATE TRIGGER alt_fuentes_compute_field_trigger
BEFORE INSERT OR UPDATE ON fonsagua.alt_fuentes
FOR EACH ROW EXECUTE PROCEDURE fonsagua.alt_fuentes_compute_field_trigger();