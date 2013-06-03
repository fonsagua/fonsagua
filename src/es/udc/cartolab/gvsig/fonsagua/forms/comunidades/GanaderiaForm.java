package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class GanaderiaForm extends AbstractSubForm {

    public static final String NAME = "ganaderia";
    public static String[] colNames = {
"tipo", "f_propietarias", "f_arrendatarias", "areafam"
    };
    public static String[] colAlias = {
"Tipo", "Nº f propietarias", "Nº f arrendatarias", "Nº cabezas totales"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
