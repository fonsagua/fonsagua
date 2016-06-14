CREATE TABLE carreteras(
gid INTEGER PRIMARY KEY,
"CODIGO" DOUBLE,
"DESCRIPC" TEXT,
"TIPO" TEXT,
);
SELECT addgeometrycolumn('carreteras', 'geom', 32616, 'MULTILINESTRING', 2);
SELECT CreateSpatialIndex('carreteras', 'geom');


CREATE TABLE curvas_nivel_10m(
gid INTEGER PRIMARY KEY,
"ID" INTEGER,
"elevacion" INTEGER,
"tipo" TEXT
);
SELECT addgeometrycolumn('curvas_nivel_10m', 'geom', 32616, 'MULTILINESTRING', 2);
SELECT CreateSpatialIndex('curvas_nivel_10m', 'geom');


CREATE TABLE rios(
gid INTEGER PRIMARY KEY,
"Codigo" INTEGER,
"Descripc" TEXT,
);
SELECT addgeometrycolumn('rios', 'geom', 32616, 'MULTILINESTRING', 2);
SELECT CreateSpatialIndex('rios', 'geom');


CREATE TABLE caserios_comunidades(
gid INTEGER PRIMARY KEY,
"COD_CASERI" TEXT,
"CASERIO" TEXT,
"SEX_H" DOUBLE,
"SEX_M" DOUBLE,
"ED_0A10" DOUBLE,
"ED_11A20" DOUBLE,
"ED_21A30" DOUBLE,
"ED_31A40" DOUBLE,
"ED_41A50" DOUBLE,
"ED_51A60" DOUBLE,
"ED_61A70" DOUBLE,
"ED_71A80" DOUBLE,
"ED_81A90" DOUBLE,
"ED_91A100" DOUBLE,
"ED__101" DOUBLE,
"DD_CODIGO" DOUBLE,
"DM_CODIGO" DOUBLE,
"DA_CODIGO" DOUBLE,
"DC_CODIGO" DOUBLE,
"COD_MUNI" TEXT,
"COD_ALDEA" TEXT,
"POB_TOTAL" DOUBLE,
"CATEGORíA" TEXT,
"MUNICIPIO" TEXT,
"DEPARTAMEN" TEXT,
"ALDEA" TEXT,
"COD_DPTO" TEXT
);
SELECT addgeometrycolumn('caserios_comunidades', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('caserios_comunidades', 'geom');


CREATE TABLE oceano(
gid INTEGER PRIMARY KEY,
"Nombre" TEXT
);
SELECT addgeometrycolumn('oceano', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('oceano', 'geom');


CREATE TABLE honduras(
gid INTEGER PRIMARY KEY,
"PAIS" TEXT,
"CODPAIS" TEXT
);
SELECT addgeometrycolumn('honduras', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('honduras', 'geom');



CREATE TABLE paises_limitrofes(
gid INTEGER PRIMARY KEY,
"PAIS" TEXT,
"CODPAIS" TEXT
);
SELECT addgeometrycolumn('paises_limitrofes', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('paises_limitrofes', 'geom');

