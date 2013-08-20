CREATE OR REPLACE FUNCTION fonsagua.alt_depositos_compute_field_trigger() RETURNS TRIGGER AS $alt_depositos_compute_field_trigger$
       BEGIN
       NEW.volumen = 3.1416 * ((NEW.diametro/1000/2) ^ 2) * (NEW.nivel_maximo - NEW.nivel_minimo);
	RETURN NEW;
       END;
$alt_depositos_compute_field_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS alt_depositos_compute_field_trigger ON fonsagua.alt_depositos;
CREATE TRIGGER alt_depositos_compute_field_trigger
BEFORE INSERT OR UPDATE ON fonsagua.alt_depositos
FOR EACH ROW EXECUTE PROCEDURE fonsagua.alt_depositos_compute_field_trigger();


