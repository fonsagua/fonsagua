package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class AnaliticasForm extends AbstractSubForm {

    public static final String NAME = "analiticas";
    public static String[] colNames = { "fuente", "cod_fuente",
	    "fecha_muestra", "ph", "oxigeno_disuelto", "coli_totales" };
    public static String[] colAlias = { "Nombre Fuente", "C�digo Fuente",
	    "Fecha toma muestra", "PH", "Ox�geno disuelto", "Colif. totales" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
