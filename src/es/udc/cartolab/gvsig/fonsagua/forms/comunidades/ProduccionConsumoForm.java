package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class ProduccionConsumoForm extends AbstractSubForm {

    public static final String NAME = "produccion_consumo";
    public static String[] colNames = { "n_miembros", "produccion", "consumo" };
    public static String[] colAlias = { "N� de miembros",
	    "Producci�n (qq/a�o)", "Consumo (qq/a�o)" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
