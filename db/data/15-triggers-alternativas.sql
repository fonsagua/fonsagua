CREATE OR REPLACE FUNCTION fonsagua.alternativas_compute_field_trigger() RETURNS TRIGGER AS $alternativas_compute_field_trigger$
       DECLARE
	f_var_est_t REAL;
       BEGIN
	SELECT COALESCE(f_var_estacional, 0) INTO f_var_est_t FROM fonsagua.preferencias_disenho WHERE NEW.cod_alternativa = cod_alternativa;

	SELECT SUM(n_hab_alternativa) INTO NEW.pobl_actual FROM fonsagua.comunidades_implicadas WHERE cod_alternativa = NEW.cod_alternativa;
	IF (NEW.pobl_actual IS NULL OR NEW.pobl_actual < 0) THEN NEW.pobl_actual = 0; END IF;

	NEW.pobl_futura = fonsagua.pobl_futura_function(NEW.cod_alternativa, NEW.pobl_actual);
	NEW.dem_poblacion = f_var_est_t * fonsagua.demanda_poblacion_function(NEW.cod_alternativa, NEW.tipo_distribucion, NEW.pobl_futura);

	IF (NEW.n_cent_educativos IS NULL OR NEW.n_cent_educativos < 0) THEN NEW.n_cent_educativos = 0; END IF;
	IF (NEW.n_cent_salud IS NULL OR NEW.n_cent_salud < 0) THEN NEW.n_cent_salud = 0; END IF;
	IF (NEW.n_cent_otros IS NULL OR NEW.n_cent_otros < 0) THEN NEW.n_cent_otros = 0; END IF;

	IF (NEW.dot_cent_educativos IS NULL OR NEW.dot_cent_educativos < 0) THEN NEW.dot_cent_educativos = 0; END IF;
	IF (NEW.dot_cent_salud IS NULL OR NEW.dot_cent_salud < 0) THEN NEW.dot_cent_salud = 0; END IF;
	IF (NEW.dot_cent_otros IS NULL OR NEW.dot_cent_otros < 0) THEN NEW.dot_cent_otros = 0; END IF;

	NEW.dem_centros = f_var_est_t * (NEW.n_cent_educativos * NEW.dot_cent_educativos + NEW.n_cent_salud * NEW.dot_cent_salud + NEW.n_cent_otros * NEW.dot_cent_otros) / 86400.0;

	IF (NEW.dot_sec_primario IS NULL OR NEW.dot_sec_primario < 0) THEN NEW.dot_sec_primario = 0; END IF;
	IF (NEW.dot_sec_secundario IS NULL OR NEW.dot_sec_secundario < 0) THEN NEW.dot_sec_secundario = 0; END IF;
	IF (NEW.dot_sec_terciario IS NULL OR NEW.dot_sec_terciario < 0) THEN NEW.dot_sec_terciario = 0; END IF;

	NEW.dem_econ = (NEW.dot_sec_primario + NEW.dot_sec_secundario + NEW.dot_sec_terciario) / 86400.0;

	NEW.demanda = NEW.dem_centros + NEW.dem_econ + NEW.dem_poblacion;

	RETURN NEW;
       END;
$alternativas_compute_field_trigger$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS alternativas_compute_field_trigger ON fonsagua.alternativas;
CREATE TRIGGER alternativas_compute_field_trigger
BEFORE INSERT OR UPDATE ON fonsagua.alternativas
FOR EACH ROW EXECUTE PROCEDURE fonsagua.alternativas_compute_field_trigger();