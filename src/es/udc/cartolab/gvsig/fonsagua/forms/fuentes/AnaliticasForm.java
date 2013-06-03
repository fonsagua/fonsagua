package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class AnaliticasForm extends AbstractSubForm {

    public static final String NAME = "analiticas";
    public static String[] colNames = {
"fecha_muestra", "ph", "oxigeno_disuelto", "coli_totales"
    };
    public static String[] colAlias = {
"Fecha Muestra", "PH", "OD", "Coliformes Totales"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
