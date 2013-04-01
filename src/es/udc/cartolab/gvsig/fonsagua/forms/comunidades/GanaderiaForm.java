package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class GanaderiaForm extends AbstractSubForm {

    public static final String NAME = "ganaderia";
    public static String[] colNames = { "tipo", "n_familias", "f_propietarias",
	    "f_arrendatarias", "areafam" };
    public static String[] colAlias = { "Tipo", "N� de familias",
	    "N� f propietarias", "N� f arrendatarias", "N� cabezas totales" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
