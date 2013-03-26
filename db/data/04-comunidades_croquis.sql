CREATE TABLE comunidades_croquis (
       cod_comunidad VARCHAR REFERENCES comunidades(cod_comunidad),
       croquis bytea
);