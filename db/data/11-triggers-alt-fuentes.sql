CREATE OR REPLACE FUNCTION fonsagua.alt_fuentes_compute_field_trigger() RETURNS TRIGGER AS $alt_fuentes_compute_field_trigger$
       DECLARE
	coef_q_eco_t REAL;
       BEGIN
	SELECT COALESCE(coef_q_eco,0) INTO coef_q_eco_t FROM fonsagua.preferencias WHERE cod_alternativa = NEW.cod_alternativa;

	IF (coef_q_eco_t > 0 AND NEW.aforo > 0) THEN NEW.q_ecologico = coef_q_eco_t * NEW.AFORO; ELSE NEW.q_ecologico = NULL; END IF;

	IF (NEW.q_usar IS NULL OR NEW.q_usar > coef_q_eco_t  OR NEW.q_usar < 0) THEN NEW.q_usar = NEW.q_ecologico; END IF;

	NEW.q_calculo = -NEW.q_usar;

	RETURN NEW;
       END;
$alt_fuentes_compute_field_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS alt_fuentes_compute_field_trigger ON fonsagua.alt_fuentes;
CREATE TRIGGER alt_fuentes_compute_field_trigger
BEFORE INSERT OR UPDATE ON fonsagua.alt_fuentes
FOR EACH ROW EXECUTE PROCEDURE fonsagua.alt_fuentes_compute_field_trigger();