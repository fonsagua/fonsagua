CREATE OR REPLACE FUNCTION fonsagua.alternativas_compute_field_trigger() RETURNS TRIGGER AS $alternativas_compute_field_trigger$
       DECLARE
	tasa_crec_t REAL;
	f_var_est_t REAL;
	ano_horiz_sist_t REAL;
	dot_sist_domiciliar_t REAL;
	dot_sist_cantareras_t REAL;

	dotacion REAL;

       BEGIN
	SELECT COALESCE(f_var_est, 0), COALESCE(tasa_crec, 0), COALESCE(ano_horiz_sist, 0), COALESCE(dot_sist_domiciliar, 0), COALESCE(dot_sist_cantareras, 0) INTO f_var_est_t, tasa_crec_t, ano_horiz_sist_t, dot_sist_domiciliar_t, dot_sist_cantareras_t FROM fonsagua.preferencias WHERE NEW.cod_alternativa = cod_alternativa;

	IF (NEW.pobl_actual IS NULL OR NEW.pobl_actual < 0) THEN NEW.pobl_actual = 0; END IF;

	NEW.pobl_futura = ceil(NEW.pobl_actual * (1 + (tasa_crec_t * ano_horiz_sist_t / 100)));

	IF (NEW.tipo_distribucion = 'Domiciliar') THEN dotacion = dot_sist_domiciliar_t;
	ELSIF (NEW.tipo_distribucion = 'Cantareras') THEN dotacion = dot_sist_cantareras_t;
	ELSE dotacion = 0;
	END IF;

	NEW.dem_poblacion = f_var_est_t * dotacion * NEW.pobl_futura / 86400;

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