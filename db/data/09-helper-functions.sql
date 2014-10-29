CREATE OR REPLACE FUNCTION fonsagua.pobl_futura_function(cod_alt VARCHAR, pobl_actual INTEGER) RETURNS INTEGER AS $pobl_futura_function$
       DECLARE
	pobl_futura INTEGER;
	tasa_crecimiento_t REAL;
	ano_horiz_sistema_t REAL;
       BEGIN
	SELECT COALESCE(tasa_crecimiento, 0), COALESCE(ano_horiz_sistema, 0) INTO tasa_crecimiento_t, ano_horiz_sistema_t FROM fonsagua.preferencias_disenho WHERE cod_alternativa = cod_alt;
	pobl_futura = ceil(pobl_actual * (1 + (tasa_crecimiento_t * ano_horiz_sistema_t / 100)));
	RETURN pobl_futura;
       END;
$pobl_futura_function$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fonsagua.demanda_poblacion_function(cod_alt VARCHAR, tipo_distribucion VARCHAR, pobl INTEGER) RETURNS REAL AS $demanda_poblacion_function$
       DECLARE
	dot_sist_domiciliar_t REAL;
	dot_sist_cantareras_t REAL;
	dotacion REAL;
	demanda REAL;
       BEGIN
       	SELECT COALESCE(dot_domiciliar, 0), COALESCE(dot_cantareras, 0) INTO dot_sist_domiciliar_t, dot_sist_cantareras_t FROM fonsagua.preferencias_disenho WHERE cod_alternativa = cod_alt;

	IF (tipo_distribucion = 'Llave pública') THEN dotacion = dot_sist_domiciliar_t;
	ELSIF (tipo_distribucion = 'Cantareras') THEN dotacion = dot_sist_cantareras_t;
	ELSE dotacion = 0;
	END IF;

	demanda = dotacion * pobl / 86400;

	RETURN demanda;
       END;
$demanda_poblacion_function$
LANGUAGE plpgsql;

