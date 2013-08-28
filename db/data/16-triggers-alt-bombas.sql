CREATE OR REPLACE FUNCTION fonsagua.alt_bombeos_compute_field_trigger() RETURNS TRIGGER AS $alt_bombeos_compute_field_trigger$
       BEGIN
	SELECT COALESCE(potencia, 0) INTO NEW.potencia FROM fonsagua.preferencias_bombas WHERE id_bomba = NEW.bomba_comercial;
	RETURN NEW;
       END;
$alt_bombeos_compute_field_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS alt_bombeos_compute_field_trigger ON fonsagua.alt_bombeos;
CREATE TRIGGER alt_bombeos_compute_field_trigger
BEFORE INSERT OR UPDATE ON fonsagua.alt_bombeos
FOR EACH ROW EXECUTE PROCEDURE fonsagua.alt_bombeos_compute_field_trigger();


