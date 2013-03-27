package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class EntrevistadosForm extends AbstractSubForm {

    public static final String NAME = "entrevistados";
    public static String[] colNames = { "nombre", "cargo", "telefono", "fecha" };
    public static String[] colAlias = { "Nombre", "Cargo", "Teléfono",
	    "Fecha entrevista" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
