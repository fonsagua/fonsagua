package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class NivelesFreaticosForm extends AbstractSubForm {

    public static final String NAME = "niveles_freaticos";
    public static String[] colNames = { "nivel", "fecha", "hora" };
    public static String[] colAlias = { "Nivel (m)", "Fecha", "Hora" };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
