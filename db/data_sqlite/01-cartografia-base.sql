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


CREATE TABLE equipamientos(
gid INTEGER PRIMARY KEY,
"CABECERA" INTEGER,
"POLICIA" TEXT,
"CODMUN" TEXT,
"NOMBRE" TEXT,
"INFLUENCIA" TEXT,
"Juzgados" TEXT,
"TIPO" TEXT
);
SELECT addgeometrycolumn('equipamientos', 'geom', 32616, 'POINT', 2);
SELECT CreateSpatialIndex('equipamientos', 'geom');


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


CREATE TABLE areas_protegidas_2011(
gid INTEGER PRIMARY KEY,
"NUMEROS" DOUBLE,
"NOMBRE" TEXT,
"CATEGORIA" TEXT,
"DECRETO_NO" TEXT,
"OBSERVACIO" TEXT,
"AREA" DOUBLE,
"PERIMETER" DOUBLE,
"ACRES" DOUBLE,
"Hectares" DOUBLE,
"CUENCA" TEXT,
"ENDFAUNA" TEXT,
"ENDFLORA" TEXT,
"ECOREGIÓN" TEXT,
"ADMINISTRA" TEXT,
"TIPO_ADMON" TEXT,
"SP_PEXTINC" TEXT,
"CATEG_UICN" TEXT,
"ENFOQUE" TEXT,
"DESCRIPCIO" TEXT,
"PRES_INSTI" TEXT,
"UBICACIÓN" TEXT,
"Shape_Leng" DOUBLE,
"Shape_Area" DOUBLE
);
SELECT addgeometrycolumn('areas_protegidas_2011', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('areas_protegidas_2011', 'geom');


CREATE TABLE usos_suelo_2003(
gid INTEGER PRIMARY KEY,
"ID" DOUBLE,
"GRIDCODE" DOUBLE,
"Class_name" TEXT,
"Area" DOUBLE,
"Hectares" DOUBLE,
"Shape_Leng" DOUBLE,
"Shape_Area" DOUBLE
);
SELECT addgeometrycolumn('usos_suelo_2003', 'geom', 32616, 'MULTIPOLYGON', 2);
SELECT CreateSpatialIndex('usos_suelo_2003', 'geom');


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

