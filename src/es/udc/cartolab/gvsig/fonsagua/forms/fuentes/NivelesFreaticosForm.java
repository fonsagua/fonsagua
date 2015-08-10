package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class NivelesFreaticosForm extends BasicAbstractSubForm {

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
