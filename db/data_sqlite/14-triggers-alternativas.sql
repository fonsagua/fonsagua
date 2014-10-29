DROP TRIGGER IF EXISTS alternativas_compute_fields_insert_trigger;
CREATE TRIGGER alternativas_compute_fields_insert_trigger
AFTER INSERT ON alternativas
FOR EACH ROW BEGIN
	UPDATE alternativas SET pobl_actual = 0 WHERE (pobl_actual IS NULL OR pobl_actual < 0) AND gid = NEW.gid;
	UPDATE alternativas SET n_cent_educativos = 0 WHERE (n_cent_educativos IS NULL OR n_cent_educativos < 0) AND gid = NEW.gid;
	UPDATE alternativas SET n_cent_salud = 0 WHERE (n_cent_salud IS NULL OR n_cent_salud < 0) AND gid = NEW.gid;
	UPDATE alternativas SET n_cent_otros = 0 WHERE (n_cent_otros IS NULL OR n_cent_otros < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_cent_educativos = 0 WHERE (dot_cent_educativos IS NULL OR dot_cent_educativos < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_cent_salud = 0 WHERE (dot_cent_salud IS NULL OR dot_cent_salud < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_cent_otros = 0 WHERE (dot_cent_otros IS NULL OR dot_cent_otros < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_sec_primario = 0 WHERE (dot_sec_primario IS NULL OR dot_sec_primario < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_sec_secundario = 0 WHERE (dot_sec_secundario IS NULL OR dot_sec_secundario < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_sec_terciario = 0 WHERE (dot_sec_terciario IS NULL OR dot_sec_terciario < 0) AND gid = NEW.gid;
	UPDATE alternativas SET pobl_actual = (SELECT IFNULL(SUM(n_hab_alternativa), 0) FROM comunidades_implicadas WHERE cod_alternativa = alternativas.cod_alternativa) WHERE gid = NEW.gid;

	UPDATE alternativas SET pobl_futura = (SELECT CEIL(pobl_actual * (1.0 + (COALESCE(tasa_crecimiento, 0) * COALESCE(ano_horiz_sistema, 0) / 100.0))) FROM preferencias_disenho a JOIN alternativas b ON a.cod_alternativa = b.cod_alternativa WHERE b.gid = alternativas.gid) WHERE gid = NEW.gid;

	UPDATE alternativas SET dem_poblacion = (SELECT COALESCE(f_var_estacional, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * (SELECT COALESCE(dot_cantareras, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * pobl_futura / 86400.0 WHERE gid = NEW.gid AND tipo_distribucion = 'Cantareras';
	UPDATE alternativas SET dem_poblacion = (SELECT COALESCE(f_var_estacional, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * (SELECT COALESCE(dot_domiciliar, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * pobl_futura / 86400.0 WHERE gid = NEW.gid AND tipo_distribucion = 'Llave pública';
	UPDATE alternativas SET dem_poblacion = 0 WHERE gid = NEW.gid AND tipo_distribucion != 'Llave pública' AND tipo_distribucion != 'Cantareras';

	UPDATE alternativas SET dem_centros = (SELECT COALESCE(f_var_estacional, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * (n_cent_educativos * dot_cent_educativos + n_cent_salud * dot_cent_salud + n_cent_otros * dot_cent_otros) / 86400.0 WHERE gid = NEW.gid;

	UPDATE alternativas SET dem_econ = (dot_sec_primario + dot_sec_secundario + dot_sec_terciario) / 86400.0 WHERE gid = NEW.gid;

	UPDATE alternativas SET demanda = dem_centros + dem_econ + dem_poblacion WHERE gid = NEW.gid;
	INSERT INTO preferencias_disenho (cod_alternativa) VALUES (NEW.cod_alternativa);
	INSERT INTO presupuesto (cod_alternativa) VALUES (NEW.cod_alternativa);
END;

DROP TRIGGER IF EXISTS alternativas_compute_fields_update_trigger;
CREATE TRIGGER alternativas_compute_fields_update_trigger
AFTER UPDATE ON alternativas
FOR EACH ROW BEGIN
	UPDATE alternativas SET pobl_actual = 0 WHERE (pobl_actual IS NULL OR pobl_actual < 0) AND gid = NEW.gid;
	UPDATE alternativas SET n_cent_educativos = 0 WHERE (n_cent_educativos IS NULL OR n_cent_educativos < 0) AND gid = NEW.gid;
	UPDATE alternativas SET n_cent_salud = 0 WHERE (n_cent_salud IS NULL OR n_cent_salud < 0) AND gid = NEW.gid;
	UPDATE alternativas SET n_cent_otros = 0 WHERE (n_cent_otros IS NULL OR n_cent_otros < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_cent_educativos = 0 WHERE (dot_cent_educativos IS NULL OR dot_cent_educativos < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_cent_salud = 0 WHERE (dot_cent_salud IS NULL OR dot_cent_salud < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_cent_otros = 0 WHERE (dot_cent_otros IS NULL OR dot_cent_otros < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_sec_primario = 0 WHERE (dot_sec_primario IS NULL OR dot_sec_primario < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_sec_secundario = 0 WHERE (dot_sec_secundario IS NULL OR dot_sec_secundario < 0) AND gid = NEW.gid;
	UPDATE alternativas SET dot_sec_terciario = 0 WHERE (dot_sec_terciario IS NULL OR dot_sec_terciario < 0) AND gid = NEW.gid;
	UPDATE alternativas SET pobl_actual = (SELECT IFNULL(SUM(n_hab_alternativa), 0) FROM comunidades_implicadas WHERE cod_alternativa = alternativas.cod_alternativa) WHERE gid = NEW.gid;

	UPDATE alternativas SET pobl_futura = (SELECT CEIL(pobl_actual * (1.0 + (COALESCE(tasa_crecimiento, 0) * COALESCE(ano_horiz_sistema, 0) / 100.0))) FROM preferencias_disenho a JOIN alternativas b ON a.cod_alternativa = b.cod_alternativa WHERE b.gid = alternativas.gid) WHERE gid = NEW.gid;

	UPDATE alternativas SET dem_poblacion = (SELECT COALESCE(f_var_estacional, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * (SELECT COALESCE(dot_cantareras, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * pobl_futura / 86400.0 WHERE gid = NEW.gid AND tipo_distribucion = 'Cantareras';
	UPDATE alternativas SET dem_poblacion = (SELECT COALESCE(f_var_estacional, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * (SELECT COALESCE(dot_domiciliar, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * pobl_futura / 86400.0 WHERE gid = NEW.gid AND tipo_distribucion = 'Llave pública';
	UPDATE alternativas SET dem_poblacion = 0 WHERE gid = NEW.gid AND tipo_distribucion != 'Llave pública' AND tipo_distribucion != 'Cantareras';

	UPDATE alternativas SET dem_centros = (SELECT COALESCE(f_var_estacional, 0) FROM preferencias_disenho WHERE cod_alternativa = alternativas.cod_alternativa) * (n_cent_educativos * dot_cent_educativos + n_cent_salud * dot_cent_salud + n_cent_otros * dot_cent_otros) / 86400.0 WHERE gid = NEW.gid;

	UPDATE alternativas SET dem_econ = (dot_sec_primario + dot_sec_secundario + dot_sec_terciario) / 86400.0 WHERE gid = NEW.gid;

	UPDATE alternativas SET demanda = dem_centros + dem_econ + dem_poblacion WHERE gid = NEW.gid;
END;
