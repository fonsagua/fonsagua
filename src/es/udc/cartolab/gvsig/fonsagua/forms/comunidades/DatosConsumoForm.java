package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class DatosConsumoForm extends AbstractSubForm {

    public static final String NAME = "datos_consumo";
    public static String[] colNames = {
"cod_comunidad", "tipo_abastecimiento", "consumo", "n_miembros"
    };
    public static String[] colAlias = {
"ID Comunidad", "Tipo abastecimiento", "Consumo (l/día)", "Nº miembros familia"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
