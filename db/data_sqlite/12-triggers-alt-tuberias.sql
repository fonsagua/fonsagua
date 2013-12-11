DROP TRIGGER IF EXISTS alt_tuberias_compute_fields_insert_trigger;
CREATE TRIGGER alt_tuberias_compute_fields_insert_trigger
AFTER INSERT ON alt_tuberias
FOR EACH ROW BEGIN
	UPDATE alt_tuberias SET long_tuberia = GLength(geom), material = (SELECT COALESCE(material, '') FROM preferencias_tuberias WHERE id_tub = alt_tuberias.tuberia_comercial), rugosidad = (SELECT COALESCE(rugosidad, 0) FROM preferencias_tuberias WHERE id_tub = alt_tuberias.tuberia_comercial), diametro = (SELECT COALESCE(diametro, 0) FROM preferencias_tuberias WHERE id_tub = alt_tuberias.tuberia_comercial) WHERE gid = NEW.gid;
END;

DROP TRIGGER IF EXISTS alt_tuberias_compute_fields_update_trigger;
CREATE TRIGGER alt_tuberias_compute_fields_update_trigger
AFTER UPDATE ON alt_tuberias
FOR EACH ROW BEGIN
	UPDATE alt_tuberias SET long_tuberia = GLength(geom), material = (SELECT COALESCE(material, '') FROM preferencias_tuberias WHERE id_tub = alt_tuberias.tuberia_comercial), rugosidad = (SELECT COALESCE(rugosidad, 0) FROM preferencias_tuberias WHERE id_tub = alt_tuberias.tuberia_comercial), diametro = (SELECT COALESCE(diametro, 0) FROM preferencias_tuberias WHERE id_tub = alt_tuberias.tuberia_comercial) WHERE gid = NEW.gid;
END;


