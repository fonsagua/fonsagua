DROP TRIGGER IF EXISTS alt_conexiones_compute_fields_insert_trigger;
CREATE TRIGGER alt_conexiones_compute_fields_insert_trigger
AFTER INSERT ON alt_conexiones
FOR EACH ROW BEGIN
	UPDATE alt_bombeos SET potencia = (SELECT IFNULL(potencia, 0) FROM preferencias_bombas WHERE id_bomba = alt_bombeos.bomba_comercial) WHERE gid = NEW.gid;
END;

DROP TRIGGER IF EXISTS alt_conexiones_compute_fields_update_trigger;
CREATE TRIGGER alt_conexiones_compute_fields_update_trigger
AFTER UPDATE ON alt_conexiones
FOR EACH ROW BEGIN
	UPDATE alt_bombeos SET potencia = (SELECT IFNULL(potencia, 0) FROM preferencias_bombas WHERE id_bomba = alt_bombeos.bomba_comercial) WHERE gid = NEW.gid;
END;
