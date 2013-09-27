
-- This trigger is used as a workaround to recalculate q_ecol and q_usar when the user changes the 
-- preferences. From preferences table a "mock" uptade is done on the implicated tables to force to
-- recalculate the triggers
CREATE OR REPLACE FUNCTION fonsagua.fuentes_implicadas_compute_field_trigger() RETURNS TRIGGER AS $BODY$

       DECLARE
	coef_q_ecologico_t FLOAT;
       BEGIN
       SELECT coef_q_ecologico INTO coef_q_ecologico_t FROM fonsagua.preferencias_disenho WHERE cod_alternativa = NEW.cod_alternativa;

	IF NEW.tipo_fuente IN ('Manantial', 'Punto rio') THEN
	   NEW.q_ecol = coef_q_ecologico_t * NEW.aforo;
	   IF NEW.q_usar > NEW.q_ecol THEN
	      NEW.q_usar = 0;
	   END IF;
	END IF;
	RETURN NEW;
       END;
$BODY$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS fuentes_implicadas_compute_field_trigger ON fonsagua.fuentes_implicadas;
CREATE TRIGGER fuentes_implicadas_compute_field_trigger
BEFORE UPDATE ON fonsagua.fuentes_implicadas
FOR EACH ROW EXECUTE PROCEDURE fonsagua.fuentes_implicadas_compute_field_trigger();


