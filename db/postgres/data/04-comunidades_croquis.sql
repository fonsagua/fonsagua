CREATE TABLE fonsagua.comunidades_croquis (
       cod_comunidad VARCHAR REFERENCES fonsagua.comunidades(cod_comunidad)
       		     	     ON DELETE CASCADE ON UPDATE CASCADE,
       croquis bytea
);

ALTER TABLE fonsagua.comunidades_croquis OWNER TO fonsagua;