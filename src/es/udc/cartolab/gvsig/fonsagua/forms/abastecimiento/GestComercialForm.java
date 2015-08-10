package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class GestComercialForm extends BasicAbstractSubForm {

    public static final String NAME = "gest_comercial";
    public static String[] colNames = {
"fecha", "produccion", "facturacion", "micromedicion"
    };
    public static String[] colAlias = {
"Fecha", "Producción (m³)", "Facturación (m³)", "Micromedición (%)"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
