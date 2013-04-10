package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class TiposCultivosForm extends AbstractSubForm {

    public static final String NAME = "tipos_cultivos";
    public static String[] colNames = { "tipo", "f_propietarias",
	    "f_arrendatarias", "superficie" };
    public static String[] colAlias = { "Tipo", "Nº f propietarias",
	    "Nº f arrendatarias", "Superficie(mz)" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
