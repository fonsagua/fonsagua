package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class ProduccionConsumoForm extends BasicAbstractSubForm {

    public static final String NAME = "produccion_consumo";
    public static String[] colNames = {
"n_miembros", "produccion", "consumo"
    };
    public static String[] colAlias = {
"Nº de miembros", "Producción (qq/año)", "Consumo (qq/año)"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
