package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class CooperativasForm extends BasicAbstractSubForm {

    public static final String NAME = "cooperativas";
    public static String[] colNames = {
"tipo", "n_asociados", "recursos", "rubros"
    };
    public static String[] colAlias = {
"Tipo", "Nº de asociados", "Recursos", "Rubros"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
