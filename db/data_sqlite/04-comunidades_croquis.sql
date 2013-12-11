CREATE TABLE comunidades_croquis (
       cod_comunidad VARCHAR REFERENCES comunidades(cod_comunidad)
       		     	     ON DELETE CASCADE ON UPDATE CASCADE,
       croquis BLOB
);
