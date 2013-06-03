package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class CargosPublicosForm extends AbstractSubForm {

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
