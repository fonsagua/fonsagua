CREATE OR REPLACE FUNCTION fonsagua.preferencias_disenho_compute_fields_trigger() RETURNS TRIGGER AS $preferencias_disenho_compute_fields_trigger$
       BEGIN
       UPDATE fonsagua.fuentes_implicadas SET fuente = fuente WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE fonsagua.alternativas SET departamento = departamento WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE fonsagua.alt_bombeos SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE fonsagua.alt_conexiones SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE fonsagua.alt_depositos SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE fonsagua.alt_embalses SET embalse = embalse WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE fonsagua.alt_fuentes SET fuente = fuente WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE fonsagua.alt_tuberias SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE fonsagua.alt_valvulas SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       RETURN NULL;
       END;
$preferencias_disenho_compute_fields_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS preferencias_disenho_compute_fields_trigger ON fonsagua.preferencias_disenho;
CREATE TRIGGER preferencias_disenho_compute_fields_trigger
AFTER UPDATE ON fonsagua.preferencias_disenho
FOR EACH ROW EXECUTE PROCEDURE fonsagua.preferencias_disenho_compute_fields_trigger();