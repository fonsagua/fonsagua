package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class DatosConsumoForm extends AbstractSubForm {

    public static final String NAME = "datos_consumo";
    public static String[] colNames = { "cod_abastecimiento",
	    "tipo_abastecimiento", "consumo", "n_miembros" };
    public static String[] colAlias = { "Cód. abastecimiento",
	    "Tipo abastecimiento", "Consumo (l/dia)", "Nº miembros familia" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
