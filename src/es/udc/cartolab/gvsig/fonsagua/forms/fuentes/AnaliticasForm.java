package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class AnaliticasForm extends BasicAbstractSubForm {

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
