package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class GestFinancieraForm extends BasicAbstractSubForm {

    public static final String NAME = "gest_financiera";
    public static String[] colNames = {
"fecha", "cost_produccion", "ingr_produccion", "razon_liquidez"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
