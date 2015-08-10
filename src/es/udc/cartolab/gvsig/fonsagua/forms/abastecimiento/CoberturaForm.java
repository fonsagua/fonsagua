package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class CoberturaForm extends BasicAbstractSubForm {

    public static final String NAME = "cobertura";
    public static String[] colNames = {
"fecha", "acometidas", "viviendas", "cobertura"
    };
    public static String[] colAlias = {
"Fecha", "Acometidas", "Viviendas", "Cobertura (%)"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
