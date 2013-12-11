DROP TRIGGER IF EXISTS fuentes_implicadas_compute_fields_insert_trigger;
CREATE TRIGGER fuentes_implicadas_compute_fields_insert_trigger
AFTER INSERT ON fuentes_implicadas
FOR EACH ROW BEGIN
	UPDATE fuentes_implicadas SET q_ecol = aforo * (SELECT coef_q_ecologico FROM preferencias_disenho WHERE cod_alternativa = fuentes_implicadas.cod_alternativa) WHERE gid = NEW.gid AND tipo_fuente IN ('Manantial', 'Punto rio');
	UPDATE fuentes_implicadas SET q_usar = 0 WHERE gid = NEW.gid AND q_usar > q_ecol;
END;

DROP TRIGGER IF EXISTS fuentes_implicadas_compute_fields_update_trigger;
CREATE TRIGGER fuentes_implicadas_compute_fields_update_trigger
AFTER UPDATE ON fuentes_implicadas
FOR EACH ROW BEGIN
	UPDATE fuentes_implicadas SET q_ecol = aforo * (SELECT coef_q_ecologico FROM preferencias_disenho WHERE cod_alternativa = fuentes_implicadas.cod_alternativa) WHERE gid = NEW.gid AND tipo_fuente IN ('Manantial', 'Punto rio');
	UPDATE fuentes_implicadas SET q_usar = 0 WHERE gid = NEW.gid AND q_usar > q_ecol;
END;
