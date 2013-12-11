DROP TRIGGER IF EXISTS preferencias_disenho_compute_fields_insert_trigger;
CREATE TRIGGER preferencias_disenho_compute_fields_insert_trigger
AFTER INSERT ON preferencias_disenho
FOR EACH ROW BEGIN
       UPDATE fuentes_implicadas SET fuente = fuente WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alternativas SET departamento = departamento WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_bombeos SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_conexiones SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_depositos SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_embalses SET embalse = embalse WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_fuentes SET fuente = fuente WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_tuberias SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_valvulas SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
END;

DROP TRIGGER IF EXISTS preferencias_disenho_compute_fields_update_trigger;
CREATE TRIGGER preferencias_disenho_compute_fields_update_trigger
AFTER UPDATE ON preferencias_disenho
FOR EACH ROW BEGIN
       UPDATE fuentes_implicadas SET fuente = fuente WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alternativas SET departamento = departamento WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_bombeos SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_conexiones SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_depositos SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_embalses SET embalse = embalse WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_fuentes SET fuente = fuente WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_tuberias SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
       UPDATE alt_valvulas SET denominacion = denominacion WHERE cod_alternativa = NEW.cod_alternativa;
END;
