DROP TRIGGER IF EXISTS gest_comercial_compute_fields_insert_trigger;
CREATE TRIGGER gest_comercial_compute_fields_insert_trigger
AFTER INSERT ON gest_comercial
FOR EACH ROW BEGIN
	UPDATE gest_comercial SET a_no_contabilizada = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET a_no_contabilizada = produccion - facturacion WHERE gid = NEW.gid AND produccion IS NOT NULL AND facturacion IS NOT NULL;

	UPDATE gest_comercial SET pct_a_no_contabilizada = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET pct_a_no_contabilizada = a_no_contabilizada * 100 / produccion WHERE gid = NEW.gid AND a_no_contabilizada IS NOT NULL;

	UPDATE gest_comercial SET micromedicion = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET micromedicion = con_medidor * 100 / acometidas WHERE gid = NEW.gid AND con_medidor IS NOT NULL AND acometidas IS NOT NULL AND acometidas > 0;
END;

DROP TRIGGER IF EXISTS gest_comercial_compute_fields_update_trigger;
CREATE TRIGGER gest_comercial_compute_fields_update_trigger
AFTER UPDATE ON gest_comercial
FOR EACH ROW BEGIN
	UPDATE gest_comercial SET a_no_contabilizada = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET a_no_contabilizada = produccion - facturacion WHERE gid = NEW.gid AND produccion IS NOT NULL AND facturacion IS NOT NULL;

	UPDATE gest_comercial SET pct_a_no_contabilizada = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET pct_a_no_contabilizada = a_no_contabilizada * 100 / produccion WHERE gid = NEW.gid AND a_no_contabilizada IS NOT NULL;

	UPDATE gest_comercial SET micromedicion = NULL WHERE gid = NEW.gid;
	UPDATE gest_comercial SET micromedicion = con_medidor * 100 / acometidas WHERE gid = NEW.gid AND con_medidor IS NOT NULL AND acometidas IS NOT NULL AND acometidas > 0;
END;


ALTER TABLE comunidades ADD COLUMN veg_coniferas NUMERIC(10,3);
ALTER TABLE comunidades ADD COLUMN veg_latifoliado NUMERIC(10,3);
ALTER TABLE comunidades ADD COLUMN veg_mixto NUMERIC(10,3);
ALTER TABLE comunidades ADD COLUMN veg_mangle NUMERIC(10,3);
ALTER TABLE comunidades ADD COLUMN veg_matorral NUMERIC(10,3);
ALTER TABLE comunidades ADD COLUMN veg_pasto NUMERIC(10,3);
ALTER TABLE comunidades ADD COLUMN veg_otros NUMERIC(10,3);


UPDATE version SET version = '20151103';
