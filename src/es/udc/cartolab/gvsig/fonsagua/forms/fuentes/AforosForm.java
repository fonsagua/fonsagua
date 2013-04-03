package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class AforosForm extends AbstractSubForm {

    public static final String NAME = "aforos";
    public static String[] colNames = { "cod_fuente", "aforo", "fecha", "hora" };
    public static String[] colAlias = { "Código fuente",
	    "Aforo(caudal, nivel freático, capacidad)", "Fecha", "Hora" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
