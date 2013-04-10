package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class GestionFinancieraForm extends AbstractSubForm {

    public static final String NAME = "gest_financiera";
    public static String[] colNames = { "fecha", "cost_produccion",
	    "ingr_produccion" };
    public static String[] colAlias = { "Fecha", "Coste unitario ($/m3)",
	    "Ingreso unitario ($/m3)" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
