package es.udc.cartolab.gvsig.fonsagua.utils;

import java.awt.Color;

public interface FonsaguaConstants {

    public static final Color NOT_ENABLED_COLOR = new Color(240, 240, 240);

    public static final String dataSchema = "fonsagua";
    public static final String baseSchema = "c_base";
    public static final String limitsSchema = "limites_administrativos";

    public static final String departamentosTable = "departamentos";
    public static final String departamentosPK = "cod";
    public static final String departamentosName = "depto";

    public static final String municipiosTable = "municipios";
    public static final String municipiosPK = "cod_muni";
    public static final String municipiosName = "nombre";

    public static final String cantonesTable = "cantones";
    public static final String cantonesPK = "cod_aldea";
    public static final String cantonesName = "nombre";

    public static final String CROQUIS_TABLENAME = "comunidades_croquis";
    public static final String CROQUIS_FIELDNAME = "croquis";
    public static final String CROQUIS_COMUNIDAD_FK_FIELDNAME = "cod_comunidad";

    public static final String GPS_MATCHING_FILES = "gps-matching-files";

    public static final String COMUNIDADES_IMPLICADAS = "comunidades_implicadas";
    public static final String COMUNIDADES_IMPLICADAS_PK_FIELD = "gid";
    public static final String[] COMUNIDADES_IMPLICADAS_COL_ALIAS = { "Nombre",
	    "Habitantes totales", "Habitantes alternativa" };
    public static final String[] COMUNIDADES_IMPLICADAS_COL_NAMES = {
	    "comunidad", "n_habitantes", "n_hab_alternativa" };
    public static final String FUENTES_IMPLICADAS = "fuentes_implicadas";

    // These ELLE Map are disjunctive, you should load each.
    public static final String BaseMap = "base";
    public static final String AlternativesMap = "alternativas";
    public static final String GeneralMap = "general";
    // Group name of the alternative layers: 'alternativas', 'alt_bombeos', ...
    public static final String AlternativeGroup = "Alternativa";
    public static final String TUBERIAS_COMERCIALES_NAME = "preferencias_tuberias";
    public static final String[] TUBERIAS_COMERCIALES_TABLE_FIELDS = {
	    "id_tub", "material", "diametro", "presion", "rugosidad",
	    "precio_tubo", "precio_m" };
    public static final String[] TUBERIAS_COMERCIALES_TABLE_ALIAS = { "ID",
	    "Material", "Diámetro (mm)", "Presión (m.c.a)", "Rugosidad (D-W)",
	    "Precio (L/6m)", "Precio (L/m)" };
    public static final String BOMBAS_COMERCIALES_NAME = "preferencias_bombas";
    public static final String[] BOMBAS_COMERCIALES_TABLE_FIELDS = {
	    "id_bomba", "potencia", "precio_m" };
    public static final String[] BOMBAS_COMERCIALES_TABLE_ALIAS = { "ID",
	    "Potencia (CV)", "Precio (L)" };

}
