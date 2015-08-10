package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class SubcuencasForm extends BasicAbstractSubForm {

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
