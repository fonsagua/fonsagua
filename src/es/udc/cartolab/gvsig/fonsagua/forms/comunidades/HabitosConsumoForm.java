package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class HabitosConsumoForm extends BasicAbstractSubForm {

    public static final String NAME = "habitos_consumo";
    public static String[] colNames = {
"consumo", "n_miembros"
    };
    public static String[] colAlias = {
"Consumo (l/día)", "Nº miembros familia"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
