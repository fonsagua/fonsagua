--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: elle; Type: SCHEMA; Schema: -; Owner: fonsagua
--

CREATE SCHEMA elle;


ALTER SCHEMA elle OWNER TO fonsagua;

SET search_path = elle, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: _map; Type: TABLE; Schema: elle; Owner: fonsagua; Tablespace: 
--

CREATE TABLE _map (
    mapa character varying(255) NOT NULL,
    nombre_capa character varying(255) NOT NULL,
    nombre_tabla character varying(255),
    posicion integer DEFAULT 0 NOT NULL,
    visible boolean,
    max_escala character varying(50),
    min_escala character varying(50),
    grupo character varying,
    schema character varying,
    localizador boolean
);


ALTER TABLE elle._map OWNER TO fonsagua;

--
-- Name: _map_overview; Type: TABLE; Schema: elle; Owner: fonsagua; Tablespace: 
--

CREATE TABLE _map_overview (
    mapa character varying NOT NULL,
    nombre_capa character varying NOT NULL,
    schema character varying,
    posicion integer,
    nombre_tabla character varying
);


ALTER TABLE elle._map_overview OWNER TO fonsagua;

--
-- Name: _map_overview_style; Type: TABLE; Schema: elle; Owner: fonsagua; Tablespace: 
--

CREATE TABLE _map_overview_style (
    nombre_capa character varying NOT NULL,
    nombre_estilo character varying NOT NULL,
    tipo character varying(3),
    definicion xml
);


ALTER TABLE elle._map_overview_style OWNER TO fonsagua;

--
-- Name: _map_style; Type: TABLE; Schema: elle; Owner: fonsagua; Tablespace: 
--

CREATE TABLE _map_style (
    nombre_capa character varying NOT NULL,
    nombre_estilo character varying NOT NULL,
    type character varying(3),
    definicion xml
);


ALTER TABLE elle._map_style OWNER TO fonsagua;

--
-- Data for Name: _map; Type: TABLE DATA; Schema: elle; Owner: fonsagua
--

INSERT INTO _map VALUES ('Vista general', 'fuentes', 'fuentes', 15, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'bombeos', 'bombeos', 14, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'dep_distribucion', 'dep_distribucion', 13, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'dep_intermedios', 'dep_intermedios', 12, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'captaciones', 'captaciones', 11, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'otros_servicios', 'otros_servicios', 10, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'centros_salud', 'centros_salud', 9, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'centros_educativos', 'centros_educativos', 8, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'amenazas', 'amenazas', 7, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'fuentes_contaminacion', 'fuentes_contaminacion', 6, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'puntos_viviendas', 'puntos_viviendas', 5, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'comunidades', 'comunidades', 4, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'tuberias', 'tuberias', 3, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'areas_potenciales_riego', 'areas_potenciales_riego', 2, true, NULL, NULL, '', 'fonsagua', NULL);
INSERT INTO _map VALUES ('Vista general', 'abastecimientos', 'abastecimientos', 1, true, NULL, NULL, '', 'fonsagua', NULL);


--
-- Data for Name: _map_overview; Type: TABLE DATA; Schema: elle; Owner: fonsagua
--



--
-- Data for Name: _map_overview_style; Type: TABLE DATA; Schema: elle; Owner: fonsagua
--



--
-- Data for Name: _map_style; Type: TABLE DATA; Schema: elle; Owner: fonsagua
--

INSERT INTO _map_style VALUES ('fuentes', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('bombeos', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('dep_distribucion', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('dep_intermedios', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('captaciones', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('otros_servicios', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('centros_salud', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('centros_educativos', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('amenazas', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('fuentes_contaminacion', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('puntos_viviendas', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('comunidades', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleMarkerSymbol"/>
        <property key="desc"/>
        <property key="isShapeVisible" value="true"/>
        <property key="color" value="255,0,0,255"/>
        <property key="rotation" value="0.0"/>
        <property key="offsetX" value="0.0"/>
        <property key="offsetY" value="0.0"/>
        <property key="size" value="10.0"/>
        <property key="outline" value="false"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="markerStyle" value="5"/>
    </xml-tag>
</xml-tag>
');
INSERT INTO _map_style VALUES ('tuberias', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleLineSymbol"/>
        <property key="isShapeVisible" value="true"/>
        <property key="desc"/>
        <property key="unit" value="-1"/>
        <property key="referenceSystem" value="0"/>
        <property key="color" value="60,235,235,255"/>
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
');
INSERT INTO _map_style VALUES ('areas_potenciales_riego', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="60,235,235,255"/>
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
</xml-tag>
');
INSERT INTO _map_style VALUES ('abastecimientos', 'Vista general', 'gvl', '<xml-tag xmlns="http://www.gvsig.gva.es">
    <property key="className" value="com.iver.cit.gvsig.fmap.rendering.SingleSymbolLegend"/>
    <xml-tag>
        <property key="className" value="com.iver.cit.gvsig.fmap.core.symbols.SimpleFillSymbol"/>
        <property key="color" value="204,255,255,77"/>
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
</xml-tag>
');


--
-- Name: _map_overview_pkey; Type: CONSTRAINT; Schema: elle; Owner: fonsagua; Tablespace: 
--

ALTER TABLE ONLY _map_overview
    ADD CONSTRAINT _map_overview_pkey PRIMARY KEY (mapa, nombre_capa);


--
-- Name: _map_overview_style_pkey; Type: CONSTRAINT; Schema: elle; Owner: fonsagua; Tablespace: 
--

ALTER TABLE ONLY _map_overview_style
    ADD CONSTRAINT _map_overview_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo);


--
-- Name: _map_pkey; Type: CONSTRAINT; Schema: elle; Owner: fonsagua; Tablespace: 
--

ALTER TABLE ONLY _map
    ADD CONSTRAINT _map_pkey PRIMARY KEY (mapa, nombre_capa);


--
-- Name: _map_style_pkey; Type: CONSTRAINT; Schema: elle; Owner: fonsagua; Tablespace: 
--

ALTER TABLE ONLY _map_style
    ADD CONSTRAINT _map_style_pkey PRIMARY KEY (nombre_capa, nombre_estilo);


--
-- PostgreSQL database dump complete
--

