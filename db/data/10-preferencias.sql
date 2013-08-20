
CREATE TABLE fonsagua.preferencias (
       gid SERIAL PRIMARY KEY,
       cod_alternativa VARCHAR
       		       NOT NULL
		       REFERENCES fonsagua.alternativas(cod_alternativa),
       tasa_crec NUMERIC(5,2) DEFAULT 2,
       ano_horiz_sist INTEGER DEFAULT 20,
       coef_q_eco NUMERIC(5,2) DEFAULT 0.4,
       ano_hor_bomba INTEGER DEFAULT 10,
       n_integr_fam INTEGER DEFAULT 6,
       dot_sist_domiciliar INTEGER DEFAULT 90,
       dot_sist_cantareras INTEGER DEFAULT 40,
       ren_bomba NUMERIC(5,2) DEFAULT 0.6,
       pvp_kwh NUMERIC(5,2) DEFAULT 4,
       per_puntutal NUMERIC(5,2) DEFAULT 1,
       f_var_hor NUMERIC(5,2) DEFAULT 2.25,
       f_var_est NUMERIC(5,2) DEFAULT 1.2,
       v_min NUMERIC(5,2) DEFAULT 0.5,
       v_max NUMERIC(5,2) DEFAULT 2,
       pd_min INTEGER DEFAULT 10,
       pd_max INTEGER DEFAULT 50

);
ALTER TABLE fonsagua.preferencias OWNER TO fonsagua;
