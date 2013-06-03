package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class EntrevistadosForm extends AbstractSubForm {

    public static final String NAME = "entrevistados";
    public static String[] colNames = {
"nombre", "cargo", "telefono", "fecha"
    };
    public static String[] colAlias = {
"Nombre", "Cargo", "Teléfono", "Fecha"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
