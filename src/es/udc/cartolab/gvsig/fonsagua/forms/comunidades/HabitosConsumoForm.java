package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class HabitosConsumoForm extends AbstractSubForm {

    public static final String NAME = "habitos_consumo";
    public static String[] colNames = { "consumo", "n_miembros" };
    public static String[] colAlias = { "Consumo (l/d�a)",
	    "N� miembros familia" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
