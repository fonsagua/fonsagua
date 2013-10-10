DROP TRIGGER IF EXISTS alt_fuentes_compute_fields_insert_trigger;
CREATE TRIGGER alt_fuentes_compute_fields_insert_trigger
AFTER INSERT ON alt_fuentes
FOR EACH ROW BEGIN
	UPDATE alt_fuentes SET aforo = 0 WHERE aforo < 0 AND gid = NEW.gid;
	UPDATE alt_fuentes SET q_ecologico = NULL WHERE tipo_fuente_alternativa = 'Pozo' AND gid = NEW.gid;
	UPDATE alt_fuentes SET q_usar = 0 WHERE tipo_fuente_alternativa = 'Pozo' AND gid = NEW.gid AND q_usar IS NULL;
	UPDATE alt_fuentes SET q_ecologico = (SELECT IFNULL(coef_q_ecologico,0) FROM preferencias_disenho WHERE cod_alternativa = alt_fuentes.cod_alternativa) * aforo WHERE tipo_fuente_alternativa != 'Pozo' AND gid = NEW.gid AND (SELECT IFNULL(coef_q_ecologico,0) FROM preferencias_disenho WHERE cod_alternativa = alt_fuentes.cod_alternativa) >= 0;
	UPDATE alt_fuentes SET q_ecologico = 0 WHERE tipo_fuente_alternativa != 'Pozo' AND gid = NEW.gid AND (SELECT IFNULL(coef_q_ecologico,0) FROM preferencias_disenho WHERE cod_alternativa = alt_fuentes.cod_alternativa) >= 0;
	UPDATE alt_fuentes SET q_usar = q_ecologico WHERE tipo_fuente_alternativa != 'Pozo' AND gid = NEW.gid AND (q_usar IS NULL OR q_usar > q_ecologico);
	UPDATE alt_fuentes SET q_calculo = -q_usar WHERE gid = NEW.gid;
END;

DROP TRIGGER IF EXISTS alt_fuentes_compute_fields_update_trigger;
CREATE TRIGGER alt_fuentes_compute_fields_update_trigger
AFTER UPDATE ON alt_fuentes
FOR EACH ROW BEGIN
	UPDATE alt_fuentes SET aforo = 0 WHERE aforo < 0 AND gid = NEW.gid;
	UPDATE alt_fuentes SET q_ecologico = NULL WHERE tipo_fuente_alternativa = 'Pozo' AND gid = NEW.gid;
	UPDATE alt_fuentes SET q_usar = 0 WHERE tipo_fuente_alternativa = 'Pozo' AND gid = NEW.gid AND q_usar IS NULL;
	UPDATE alt_fuentes SET q_ecologico = (SELECT IFNULL(coef_q_ecologico,0) FROM preferencias_disenho WHERE cod_alternativa = alt_fuentes.cod_alternativa) * aforo WHERE tipo_fuente_alternativa != 'Pozo' AND gid = NEW.gid AND (SELECT IFNULL(coef_q_ecologico,0) FROM preferencias_disenho WHERE cod_alternativa = alt_fuentes.cod_alternativa) >= 0;
	UPDATE alt_fuentes SET q_ecologico = 0 WHERE tipo_fuente_alternativa != 'Pozo' AND gid = NEW.gid AND (SELECT IFNULL(coef_q_ecologico,0) FROM preferencias_disenho WHERE cod_alternativa = alt_fuentes.cod_alternativa) >= 0;
	UPDATE alt_fuentes SET q_usar = q_ecologico WHERE tipo_fuente_alternativa != 'Pozo' AND gid = NEW.gid AND (q_usar IS NULL OR q_usar > q_ecologico);
	UPDATE alt_fuentes SET q_calculo = -q_usar WHERE gid = NEW.gid;
END;
