package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class ProduccionConsumoForm extends AbstractSubForm {

    public static final String NAME = "produccion_consumo";
    public static String[] colNames = { "n_miembros", "produccion", "consumo" };
    public static String[] colAlias = { "Nº de miembros",
	    "Producción (qq/año)", "Consumo (qq/año)" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
