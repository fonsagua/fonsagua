CREATE TABLE _map (
    mapa VARCHAR(255) NOT NULL,
    nombre_capa VARCHAR(255) NOT NULL,
    nombre_tabla VARCHAR(255),
    posicion integer DEFAULT 0 NOT NULL,
    visible VARCHAR(5) DEFAULT 'false',
    max_escala VARCHAR(50),
    min_escala VARCHAR(50),
    grupo VARCHAR,
    schema VARCHAR,
    localizador VARCHAR(5) DEFAULT 'false',
    CONSTRAINT _map_pkey PRIMARY KEY (mapa, nombre_capa)
);


CREATE TABLE _map_overview (
    mapa VARCHAR NOT NULL,
    nombre_capa VARCHAR NOT NULL,
    schema VARCHAR,
    posicion integer,
    nombre_tabla VARCHAR,
    CONSTRAINT _map_overview_pkey PRIMARY KEY (mapa, nombre_capa)
);


CREATE TABLE _map_overview_style (
    nombre_capa VARCHAR NOT NULL,
    nombre_estilo VARCHAR NOT NULL,
    tipo VARCHAR(3),
    definicion xml,
    CONSTRAINT _map_overview_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo)
);


CREATE TABLE _map_style (
    nombre_capa VARCHAR NOT NULL,
    nombre_estilo VARCHAR NOT NULL,
    type VARCHAR(3),
    definicion xml,
    label xml,
    CONSTRAINT _map_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo)
);


INSERT INTO "_map" VALUES('general','centros_educativos','centros_educativos',32,'true','49999.0',NULL,'datos_comunidades','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','centros_salud','centros_salud',31,'true','49999.0',NULL,'datos_comunidades','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','comunidades','comunidades',35,'true','49999.0',NULL,'datos_comunidades','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','puntos_viviendas','puntos_viviendas',34,'true','49999.0',NULL,'datos_comunidades','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','dep_intermedios','dep_intermedios',24,'true','49999.0',NULL,'sistemas_abastecimiento','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','abastecimientos','abastecimientos',26,'true','49999.0',NULL,'sistemas_abastecimiento','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','amenazas','amenazas',29,'true','49999.0',NULL,'datos_comunidades','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','areas_potenciales_riego','areas_potenciales_riego',33,'true','49999.0',NULL,'datos_comunidades','fonsagua',NULL);
INSERT INTO "_map" VALUES('base','rios','rios',14,'true','50000.0',NULL,'medio_fisico','c_base',NULL);
INSERT INTO "_map" VALUES('base','curvas_nivel_10m','curvas_nivel_10m',9,'true','30000.0',NULL,'medio_fisico','c_base',NULL);
INSERT INTO "_map" VALUES('base','departamentos','departamentos',3,'true',NULL,'200000','limites_administrativos','limites_administrativos',NULL);
INSERT INTO "_map" VALUES('base','carreteras','carreteras',11,'true','50000.0',NULL,'infraestructuras','c_base',NULL);
INSERT INTO "_map" VALUES('base','aldeas','cantones',5,'true','75000',NULL,'limites_administrativos','limites_administrativos',NULL);
INSERT INTO "_map" VALUES('base','municipios','municipios',4,'true','300000',NULL,'limites_administrativos','limites_administrativos',NULL);
INSERT INTO "_map" VALUES('general','bombeos','bombeos',22,'true','49999.0',NULL,'sistemas_abastecimiento','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','captaciones','captaciones',25,'true','49999.0',NULL,'sistemas_abastecimiento','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','dep_distribucion','dep_distribucion',23,'true','49999.0',NULL,'sistemas_abastecimiento','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','fuentes_contaminacion','fuentes_contaminacion',28,'true','49999.0',NULL,'datos_comunidades','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','otros_servicios','otros_servicios',30,'true','49999.0',NULL,'datos_comunidades','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','tuberias','tuberias',21,'true','49999.0',NULL,'sistemas_abastecimiento','fonsagua',NULL);
INSERT INTO "_map" VALUES('alternativas','alternativas','alternativas',43,'true','49999.0',NULL,'Alternativa','fonsagua',NULL);
INSERT INTO "_map" VALUES('alternativas','alt_fuentes','alt_fuentes',42,'true','49999.0',NULL,'Alternativa','fonsagua',NULL);
INSERT INTO "_map" VALUES('alternativas','alt_embalses','alt_embalses',41,'true','49999.0',NULL,'Alternativa','fonsagua',NULL);
INSERT INTO "_map" VALUES('alternativas','alt_valvulas','alt_valvulas',40,'true','49999.0',NULL,'Alternativa','fonsagua',NULL);
INSERT INTO "_map" VALUES('alternativas','alt_bombeos','alt_bombeos',39,'true','49999.0',NULL,'Alternativa','fonsagua',NULL);
INSERT INTO "_map" VALUES('alternativas','alt_depositos','alt_depositos',38,'true','49999.0',NULL,'Alternativa','fonsagua',NULL);
INSERT INTO "_map" VALUES('alternativas','alt_conexiones','alt_conexiones',37,'true','49999.0',NULL,'Alternativa','fonsagua',NULL);
INSERT INTO "_map" VALUES('alternativas','alt_tuberias','alt_tuberias',36,'true','49999.0',NULL,'Alternativa','fonsagua',NULL);
INSERT INTO "_map" VALUES('general','fuentes','fuentes',27,'true','49999.0',NULL,'sistemas_abastecimiento','fonsagua',NULL);
INSERT INTO "_map" VALUES('base','caserios_comunidades','caserios_comunidades',7,'false','30000',NULL,'limites_administrativos','',NULL);
INSERT INTO "_map" VALUES('base','areas_protegidas_2011','areas_protegidas_2011',8,'false','50000','5000','medio_fisico','',NULL);
INSERT INTO "_map" VALUES('base','usos_suelo_2003','usos_suelo_2003',10,'false','30000',NULL,'medio_fisico','',NULL);
INSERT INTO "_map" VALUES('base','oceano','oceano',1,'true',NULL,NULL,'limites_administrativos','',NULL);
INSERT INTO "_map" VALUES('base','paises_limitrofes','paises_limitrofes',2,'true',NULL,NULL,'limites_administrativos','',NULL);



INSERT INTO "_map_overview" VALUES('general','departamentos','limites_administrativos',0,'departamentos');



INSERT INTO "_map_overview_style" VALUES('departamentos','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="115,178,115,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="0,0,0,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="0.5"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>
');


INSERT INTO "_map_style" VALUES('alternativas','alternativas','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,51,102,27"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="0,51,102,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('alt_embalses','alternativas','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,204,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="2.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,204,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-2.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('alt_fuentes','alternativas','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialIntervalLegend"/>
    <property key="useDefaultSymbolB" value="true"/>
    <property key="useDefaultSymbol" value="1"/>
    <property key="fieldName" value="presion"/>
    <property key="index" value="0"/>
    <property key="intervalType" value="1"/>
    <property key="numKeys" value="4"/>
    <property key="tipoValueKeys" value="com.iver.cit.gvsig.fmap.rendering.NullIntervalValue"/>
    <property key="keys" value="Default ,-1000000.0--1.99 ,-2.0-2.0 ,2.01-1000000.0"/>
    <property key="shapeType" value="1"/>
    <property key="startColor" value="255,0,0,255"/>
    <property key="endColor" value="0,0,255,255"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="size" value="8.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="2"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="size" value="8.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="2"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="size" value="8.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="2"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="size" value="8.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,102,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="2"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,102,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="size" value="8.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="2"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('alt_valvulas','alternativas','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialIntervalLegend"/>
    <property key="useDefaultSymbolB" value="true"/>
    <property key="useDefaultSymbol" value="1"/>
    <property key="fieldName" value="perdidas"/>
    <property key="index" value="0"/>
    <property key="intervalType" value="1"/>
    <property key="numKeys" value="3"/>
    <property key="tipoValueKeys" value="com.iver.cit.gvsig.fmap.rendering.NullIntervalValue"/>
    <property key="keys" value="Default ,0.0-2.0 ,2.01-1000000.0"/>
    <property key="shapeType" value="1"/>
    <property key="startColor" value="255,0,0,255"/>
    <property key="endColor" value="0,0,255,255"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,102,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('alt_depositos','alternativas','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialIntervalLegend"/>
    <property key="useDefaultSymbolB" value="true"/>
    <property key="useDefaultSymbol" value="1"/>
    <property key="fieldName" value="presion"/>
    <property key="index" value="0"/>
    <property key="intervalType" value="1"/>
    <property key="numKeys" value="3"/>
    <property key="tipoValueKeys" value="com.iver.cit.gvsig.fmap.rendering.NullIntervalValue"/>
    <property key="keys" value="Default ,0.0-15.0 ,15.01-1000000.0"/>
    <property key="shapeType" value="1"/>
    <property key="startColor" value="255,0,0,255"/>
    <property key="endColor" value="0,0,255,255"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="size" value="14.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="14.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="9.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="9.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-10.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="9.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="size" value="14.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="14.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="9.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="9.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-10.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="9.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="size" value="14.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,102,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="14.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,102,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-10.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="9.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,102,51,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="9.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="9.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="size" value="14.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="14.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-10.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="9.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="9.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="9.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('puntos_viviendas','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="102,0,51,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="1"/>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('alt_tuberias','alternativas','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialIntervalLegend"/>
    <property key="useDefaultSymbolB" value="true"/>
    <property key="useDefaultSymbol" value="1"/>
    <property key="fieldName" value="velocidad"/>
    <property key="index" value="0"/>
    <property key="intervalType" value="1"/>
    <property key="numKeys" value="4"/>
    <property key="tipoValueKeys" value="com.iver.cit.gvsig.fmap.rendering.NullIntervalValue"/>
    <property key="keys" value="Default ,0.0-0.49 ,0.5-2.0 ,2.01-1000000.0"/>
    <property key="shapeType" value="2"/>
    <property key="startColor" value="255,0,0,255"/>
    <property key="endColor" value="0,0,255,255"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="0,0,0,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="Por defecto"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="0,0,0,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="102,0,0,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="0,102,51,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="102,0,0,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('centros_educativos','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="14.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,51,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="-1.0"/>
            <property key="size" value="14.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,153,51,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-4.0"/>
            <property key="offsetY" value="2.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,153,51,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="4.0"/>
            <property key="offsetY" value="2.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('centros_salud','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="12.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="12.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="true"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="2"/>
            <property key="outlineColor" value="255,255,255,255"/>
            <property key="outlineSize" value="0.0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('comunidades','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="108,111,110,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="10.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('dep_intermedios','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,102,102,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="1.0"/>
            <property key="offsetY" value="4.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,102,102,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="1.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,153,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="-8.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="true"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
            <property key="outlineColor" value="0,0,0,255"/>
            <property key="outlineSize" value="0.0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('amenazas','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="204,204,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="6.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('captaciones','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="8.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,200,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,200,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="2"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,200,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="-3.0"/>
            <property key="offsetY" value="-3.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('fuentes','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="8.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,200,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="4"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,0,200,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="2"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('fuentes_contaminacion','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="153,0,204,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="6.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('tuberias','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="0,51,255,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('dep_distribucion','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,102,102,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="1.0"/>
            <property key="offsetY" value="4.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,102,102,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="1.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="0,153,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="-8.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="true"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
            <property key="outlineColor" value="0,0,0,255"/>
            <property key="outlineSize" value="0.0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('rios','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="153,204,255,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="0.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>',NULL);
INSERT INTO "_map_style" VALUES('curvas_nivel_10m','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialUniqueValueLegend"/>
    <property key="fieldNames" value="tipo"/>
    <property key="fieldTypes" value="12"/>
    <property key="ownOrder" value="false"/>
    <property key="orders" value=""/>
    <property key="colorScheme" value="12\,122\,12\,255 ,14\,124\,12\,255 ,17\,126\,13\,255 ,19\,129\,14\,255 ,22\,131\,14\,255 ,25\,133\,15\,255 ,28\,135\,16\,255 ,31\,137\,17\,255 ,34\,140\,17\,255 ,37\,142\,18\,255 ,40\,144\,19\,255 ,43\,146\,20\,255 ,46\,148\,20\,255 ,49\,151\,21\,255 ,53\,153\,22\,255 ,56\,155\,23\,255 ,59\,157\,24\,255 ,63\,159\,25\,255 ,66\,162\,25\,255 ,70\,164\,26\,255 ,74\,166\,27\,255 ,77\,168\,28\,255 ,81\,171\,29\,255 ,85\,173\,30\,255 ,89\,175\,31\,255 ,93\,177\,32\,255 ,96\,179\,33\,255 ,100\,182\,34\,255 ,104\,184\,35\,255 ,109\,186\,36\,255 ,113\,188\,37\,255 ,117\,190\,38\,255 ,121\,193\,39\,255 ,125\,195\,41\,255 ,130\,197\,42\,255 ,134\,199\,43\,255 ,138\,201\,44\,255 ,143\,204\,45\,255 ,147\,206\,46\,255 ,152\,208\,47\,255 ,156\,210\,49\,255 ,161\,213\,50\,255 ,166\,215\,51\,255 ,170\,217\,52\,255 ,175\,219\,54\,255 ,180\,221\,55\,255 ,185\,224\,56\,255 ,189\,226\,58\,255 ,194\,228\,59\,255 ,199\,230\,60\,255 ,204\,232\,62\,255 ,209\,235\,63\,255 ,214\,237\,64\,255 ,219\,239\,66\,255 ,224\,241\,67\,255 ,229\,243\,69\,255 ,234\,246\,70\,255 ,239\,248\,72\,255 ,244\,250\,73\,255 ,249\,252\,74\,255 ,255\,255\,76\,255 ,251\,248\,74\,255 ,247\,241\,72\,255 ,243\,235\,70\,255 ,240\,228\,68\,255 ,236\,222\,66\,255 ,232\,216\,64\,255 ,229\,209\,63\,255 ,225\,203\,61\,255 ,221\,197\,59\,255 ,218\,191\,57\,255 ,214\,185\,56\,255 ,210\,179\,54\,255 ,206\,173\,52\,255 ,203\,167\,51\,255 ,199\,161\,49\,255 ,195\,156\,47\,255 ,192\,150\,46\,255 ,188\,145\,44\,255 ,184\,139\,43\,255 ,181\,134\,41\,255 ,177\,129\,40\,255 ,173\,124\,38\,255 ,169\,119\,37\,255 ,166\,114\,35\,255 ,162\,109\,34\,255 ,158\,104\,33\,255 ,155\,99\,31\,255 ,151\,95\,30\,255 ,147\,90\,29\,255 ,144\,86\,28\,255 ,140\,81\,26\,255 ,136\,77\,25\,255 ,132\,73\,24\,255 ,129\,69\,23\,255 ,125\,65\,22\,255 ,121\,61\,21\,255 ,118\,57\,20\,255 ,114\,54\,19\,255 ,110\,50\,18\,255"/>
    <property key="labelfield"/>
    <property key="labelFieldHeight"/>
    <property key="labelFieldRotation"/>
    <property key="useDefaultSymbol" value="false"/>
    <property key="numKeys" value="2"/>
    <property key="tipoValueKeys" value="com.hardcode.gdbms.engine.values.NullValue"/>
    <property key="keys" value="maestra ,normal"/>
    <property key="values" value="maestra ,normal"/>
    <property key="typeKeys" value="-1 ,-1"/>
    <property key="typeValues" value="-1 ,-1"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="128,128,128,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.0"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="maestra"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="153,102,0,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="0.5"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value="normal"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="255,153,102,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="0.2"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>',NULL);
INSERT INTO "_map_style" VALUES('departamentos','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="115,178,115,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="255,255,255,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="depto"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="1"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="0,51,0,255"/>
    <property key="fixedSize" value="10.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>');
INSERT INTO "_map_style" VALUES('abastecimientos','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,153,153,51"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="62,125,103,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('alt_bombeos','alternativas','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc" value=""/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,204,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,204,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="4.5"/>
            <property key="offsetY" value="-2.0"/>
            <property key="size" value="7.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('alt_conexiones','alternativas','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialIntervalLegend"/>
    <property key="useDefaultSymbolB" value="true"/>
    <property key="useDefaultSymbol" value="1"/>
    <property key="fieldName" value="presion"/>
    <property key="index" value="0"/>
    <property key="intervalType" value="1"/>
    <property key="numKeys" value="5"/>
    <property key="tipoValueKeys" value="com.iver.cit.gvsig.fmap.rendering.NullIntervalValue"/>
    <property key="keys" value="Default ,-1000000.0--0.01 ,0.0-9.99 ,10.0-50.0 ,50.01-1000000.0"/>
    <property key="shapeType" value="1"/>
    <property key="startColor" value="255,0,0,255"/>
    <property key="endColor" value="0,0,255,255"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value="Por defecto"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="0,0,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value="Por defecto"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="0,0,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="102,0,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="204,204,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="51,102,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc" value=""/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="102,0,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="8.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="0"/>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('areas_potenciales_riego','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="153,204,255,130"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="51,153,255,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="0.5"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('bombeos','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="11.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,0,51,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="11.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="4.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);
INSERT INTO "_map_style" VALUES('aldeas','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="133,136,136,255"/>
        <property key="hasFill" value="false"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="dashArray" value="3.0,2.0,"/>
                <property key="lineWidth" value="0.5"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="-0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="canton"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="102,102,0,255"/>
    <property key="fixedSize" value="9.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>');
INSERT INTO "_map_style" VALUES('carreteras','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="255,153,153,255"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
            <property key="desc"/>
            <property key="lineWidth" value="1.0"/>
            <property key="dashPhase" value="0.0"/>
            <property key="endCap" value="0"/>
            <property key="lineJoin" value="2"/>
            <property key="miterLimit" value="10.0"/>
            <property key="offset" value="0.0"/>
            <property key="unit" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>',NULL);
INSERT INTO "_map_style" VALUES('municipios','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,255,204,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="51,51,0,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="munic"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="1"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="51,51,0,255"/>
    <property key="fixedSize" value="10.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>');
INSERT INTO "_map_style" VALUES('otros_servicios','general','gvl','<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="11.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,153,153,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="2.0"/>
            <property key="size" value="8.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="1"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="153,51,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="-6.0"/>
            <property key="size" value="11.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="5"/>
        </xml-tag>
    </xml-tag>
</xml-tag>
',NULL);

INSERT INTO "_map_style" VALUES('caserios_comunidades','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.MultiLayerMarkerSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="size" value="10.0"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="255,255,255,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="10.0"/>
            <property key="outline" value="true"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
            <property key="outlineColor" value="0,0,0,255"/>
            <property key="outlineSize" value="0.0"/>
        </xml-tag>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
            <property key="desc"/>
            <property key="isShapeVisible" value="true"/>
            <property key="color" value="102,0,0,255"/>
            <property key="rotation" value="0.0"/>
            <property key="offsetX" value="0.0"/>
            <property key="offsetY" value="0.0"/>
            <property key="size" value="7.0"/>
            <property key="outline" value="false"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="markerStyle" value="0"/>
        </xml-tag>
    </xml-tag>
</xml-tag>','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="caserio"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="0"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="51,0,0,255"/>
    <property key="fixedSize" value="8.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>');
INSERT INTO "_map_style" VALUES('areas_protegidas_2011','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="204,255,153,129"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="51,153,0,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="dashArray" value="2.0,2.0,"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="-0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>',NULL);
INSERT INTO "_map_style" VALUES('usos_suelo_2003','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.VectorialUniqueValueLegend"/>
    <property key="fieldNames" value="class_name"/>
    <property key="fieldTypes" value="12"/>
    <property key="ownOrder" value="false"/>
    <property key="orders" value=""/>
    <property key="colorScheme" value="12\,122\,12\,255 ,14\,124\,12\,255 ,17\,126\,13\,255 ,19\,129\,14\,255 ,22\,131\,14\,255 ,25\,133\,15\,255 ,28\,135\,16\,255 ,31\,137\,17\,255 ,34\,140\,17\,255 ,37\,142\,18\,255 ,40\,144\,19\,255 ,43\,146\,20\,255 ,46\,148\,20\,255 ,49\,151\,21\,255 ,53\,153\,22\,255 ,56\,155\,23\,255 ,59\,157\,24\,255 ,63\,159\,25\,255 ,66\,162\,25\,255 ,70\,164\,26\,255 ,74\,166\,27\,255 ,77\,168\,28\,255 ,81\,171\,29\,255 ,85\,173\,30\,255 ,89\,175\,31\,255 ,93\,177\,32\,255 ,96\,179\,33\,255 ,100\,182\,34\,255 ,104\,184\,35\,255 ,109\,186\,36\,255 ,113\,188\,37\,255 ,117\,190\,38\,255 ,121\,193\,39\,255 ,125\,195\,41\,255 ,130\,197\,42\,255 ,134\,199\,43\,255 ,138\,201\,44\,255 ,143\,204\,45\,255 ,147\,206\,46\,255 ,152\,208\,47\,255 ,156\,210\,49\,255 ,161\,213\,50\,255 ,166\,215\,51\,255 ,170\,217\,52\,255 ,175\,219\,54\,255 ,180\,221\,55\,255 ,185\,224\,56\,255 ,189\,226\,58\,255 ,194\,228\,59\,255 ,199\,230\,60\,255 ,204\,232\,62\,255 ,209\,235\,63\,255 ,214\,237\,64\,255 ,219\,239\,66\,255 ,224\,241\,67\,255 ,229\,243\,69\,255 ,234\,246\,70\,255 ,239\,248\,72\,255 ,244\,250\,73\,255 ,249\,252\,74\,255 ,255\,255\,76\,255 ,251\,248\,74\,255 ,247\,241\,72\,255 ,243\,235\,70\,255 ,240\,228\,68\,255 ,236\,222\,66\,255 ,232\,216\,64\,255 ,229\,209\,63\,255 ,225\,203\,61\,255 ,221\,197\,59\,255 ,218\,191\,57\,255 ,214\,185\,56\,255 ,210\,179\,54\,255 ,206\,173\,52\,255 ,203\,167\,51\,255 ,199\,161\,49\,255 ,195\,156\,47\,255 ,192\,150\,46\,255 ,188\,145\,44\,255 ,184\,139\,43\,255 ,181\,134\,41\,255 ,177\,129\,40\,255 ,173\,124\,38\,255 ,169\,119\,37\,255 ,166\,114\,35\,255 ,162\,109\,34\,255 ,158\,104\,33\,255 ,155\,99\,31\,255 ,151\,95\,30\,255 ,147\,90\,29\,255 ,144\,86\,28\,255 ,140\,81\,26\,255 ,136\,77\,25\,255 ,132\,73\,24\,255 ,129\,69\,23\,255 ,125\,65\,22\,255 ,121\,61\,21\,255 ,118\,57\,20\,255 ,114\,54\,19\,255 ,110\,50\,18\,255"/>
    <property key="labelfield"/>
    <property key="labelFieldHeight"/>
    <property key="labelFieldRotation"/>
    <property key="useDefaultSymbol" value="false"/>
    <property key="numKeys" value="17"/>
    <property key="tipoValueKeys" value="com.hardcode.gdbms.engine.values.NullValue"/>
    <property key="keys" value="Acuicultura y salinas ,Bosque de coniferas denso ,Bosque de coniferas ralo ,Bosque de frondosas latifoliadas ,Bosque mixto ,Infraestrcuturas viales y otras ,Infraestructuras localizadas (ae ,Manglares ,S.A. Erial a pastizal ,S.A. Pastizales y praderas en fo ,S.A. Pastizales\, potreros ,S.A. Ãreas cultivadas. RÃ©gimen ,S.A. Ãreas cultivadas. RÃ©gimen Intensivo ,Sabanas ,Superficies de agua ,Zonas pantanosas y marismas ,Zonas urbanas."/>
    <property key="values" value="Acuicultura y salinas ,Bosque de coniferas denso ,Bosque de coniferas ralo ,Bosque de frondosas latifoliadas ,Bosque mixto ,Infraestrcuturas viales y otras ,Infraestructuras localizadas (ae ,Manglares ,S.A. Erial a pastizal ,S.A. Pastizales y praderas en fo ,S.A. Pastizales\, potreros ,S.A. Ãreas cultivadas. RÃ©gimen ,S.A. Ãreas cultivadas. RÃ©gimen Intensivo ,Sabanas ,Superficies de agua ,Zonas pantanosas y marismas ,Zonas urbanas."/>
    <property key="typeKeys" value="-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1"/>
    <property key="typeValues" value="-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1 ,-1"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="198,231,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Acuicultura y salinas"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="198,231,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Acuicultura y salinas"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,102,102,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Bosque de coniferas denso"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,153,102,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Bosque de coniferas ralo"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="100,182,34,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Bosque de frondosas latifoliadas"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="203,167,51,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Bosque mixto"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="102,102,102,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Infraestrcuturas viales y otras"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="204,204,204,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Infraestructuras localizadas (ae"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,153,153,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Manglares"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="51,204,0,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="S.A. Erial a pastizal"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="199,230,60,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="S.A. Pastizales y praderas en fo"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="225,203,61,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="S.A. Pastizales, potreros"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="204,153,0,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="S.A. Ãreas cultivadas. RÃ©gimen"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="144,86,28,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="S.A. Ãreas cultivadas. RÃ©gimen Intensivo"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,153,153,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Sabanas"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="102,153,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Superficies de agua"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="0,102,204,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Zonas pantanosas y marismas"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="204,51,0,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc" value="Zonas urbanas."/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>',NULL);
INSERT INTO "_map_style" VALUES('oceano','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="153,204,255,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="false"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="128,128,128,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>',NULL);

INSERT INTO "_map_style" VALUES('paises_limitrofes','base','gvl','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="255,220,180,255"/>
        <property key="hasFill" value="true"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="referenceSystem" value="0"/>
        <property key="unit" value="-1"/>
        <property key="hasOutline" value="true"/>
        <xml-tag>
            <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
            <property key="isShapeVisible" value="true"/>
            <property key="desc"/>
            <property key="unit" value="-1"/>
            <property key="referenceSystem" value="0"/>
            <property key="color" value="102,51,0,255"/>
            <xml-tag>
                <property key="className" value="com.iver.cit.gvsig.fmap.core.styles.SimpleLineStyle"/>
                <property key="desc"/>
                <property key="lineWidth" value="1.0"/>
                <property key="dashPhase" value="0.0"/>
                <property key="endCap" value="0"/>
                <property key="lineJoin" value="2"/>
                <property key="miterLimit" value="10.0"/>
                <property key="offset" value="0.0"/>
                <property key="unit" value="0"/>
            </xml-tag>
        </xml-tag>
    </xml-tag>
</xml-tag>','<?xml version="1.0" encoding="ISO-8859-1"?>
<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.styling.labeling.AttrInTableLabelingStrategy"/>
    <property key="labelingStrategy" value="labelingStrategy"/>
    <property key="TextField" value="pais"/>
    <property key="fontSize" value="8"/>
    <property key="fontName" value="Arial"/>
    <property key="fontStyle" value="1"/>
    <property key="useFixedSize" value="true"/>
    <property key="useFixedColor" value="true"/>
    <property key="fixedColor" value="102,102,102,255"/>
    <property key="fixedSize" value="10.0"/>
    <property key="Unit" value="-1"/>
    <property key="referenceSystem" value="0"/>
</xml-tag>');
