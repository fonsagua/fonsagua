package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class GestionComercialForm extends AbstractSubForm {

    public static final String NAME = "gest_comercial";
    public static String[] colNames = { "fecha", "produccion", "facturacion",
	    "pct_a_no_contabilizada", "micromedicion" };
    public static String[] colAlias = { "Fecha", "Producci�n (m3)",
	    "Facturaci�n (m3)", "Agua no contabilizada (%)",
	    "Micromedici�n (%)" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
