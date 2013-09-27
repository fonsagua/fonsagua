CREATE OR REPLACE FUNCTION fonsagua.alt_tuberias_compute_field_trigger() RETURNS TRIGGER AS $alt_tuberias_compute_field_trigger$
       BEGIN
	SELECT COALESCE(material, ''), COALESCE(rugosidad, 0), COALESCE(diametro,0) INTO NEW.material, NEW.rugosidad, NEW.diametro FROM fonsagua.preferencias_tuberias WHERE id_tub = NEW.tuberia_comercial;
	NEW.long_tuberia = st_length(NEW.geom);

	RETURN NEW;
       END;
$alt_tuberias_compute_field_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS alt_tuberias_compute_field_trigger ON fonsagua.alt_tuberias;
CREATE TRIGGER alt_tuberias_compute_field_trigger
BEFORE INSERT OR UPDATE ON fonsagua.alt_tuberias
FOR EACH ROW EXECUTE PROCEDURE fonsagua.alt_tuberias_compute_field_trigger();


