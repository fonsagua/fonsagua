DROP TRIGGER IF EXISTS alt_depositos_compute_fields_insert_trigger;
CREATE TRIGGER alt_depositos_compute_fields_insert_trigger
AFTER INSERT ON alt_depositos
FOR EACH ROW BEGIN
	UPDATE alt_depositos SET volumen = 3.1416 * ((diametro/2.0) * (diametro/2.0)) * (nivel_maximo - nivel_minimo) WHERE gid = NEW.gid;
END;

DROP TRIGGER IF EXISTS alt_depositos_compute_fields_update_trigger;
CREATE TRIGGER alt_depositos_compute_fields_update_trigger
AFTER UPDATE ON alt_depositos
FOR EACH ROW BEGIN
	UPDATE alt_depositos SET volumen = 3.1416 * ((diametro/2.0) * (diametro/2.0)) * (nivel_maximo - nivel_minimo) WHERE gid = NEW.gid;
END;
