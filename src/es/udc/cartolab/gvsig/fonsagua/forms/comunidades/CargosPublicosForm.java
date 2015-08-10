package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class CargosPublicosForm extends BasicAbstractSubForm {

    public static final String NAME = "cargos_publicos";
    public static String[] colNames = {
"nombre", "cargo"
    };
    public static String[] colAlias = {
"Nombre", "Cargo"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
