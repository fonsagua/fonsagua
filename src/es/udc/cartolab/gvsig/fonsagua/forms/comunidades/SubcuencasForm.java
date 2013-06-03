package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class SubcuencasForm extends AbstractSubForm {

    public static final String NAME = "subcuencas";
    public static String[] colNames = {
"subcuenca"
    };
    public static String[] colAlias = {
"Subcuencas"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
