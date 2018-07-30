package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class EntrevistadoresForm extends BasicAbstractSubForm {

    public static final String NAME = "entrevistadores";
    public static String[] colNames = {
"nombre", "cargo", "instit", "telefono"
    };
    public static String[] colAlias = {
"Nombre", "Cargo ", "Instituci�n", "Tel�fono"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
