package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class CoberturaForm extends AbstractSubForm {

    public static final String NAME = "cobertura";
    public static String[] colNames = { "fecha", "acometidas", "viviendas",
	    "cobertura" };
    public static String[] colAlias = { "Fecha", "Acometidas", "Viviendas",
	    "Cobertura  (%)" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
