CREATE TABLE comunidades_croquis (
       id_comunidad INTEGER REFERENCES comunidades(id_comunidad),
       croquis bytea
);