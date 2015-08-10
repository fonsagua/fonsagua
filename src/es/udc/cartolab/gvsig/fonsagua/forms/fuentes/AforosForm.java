package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class AforosForm extends BasicAbstractSubForm {

    public static final String NAME = "aforos";
    public static String[] colNames = {
"aforo", "fecha", "hora"
    };
    public static String[] colAlias = {
"Aforo", "Fecha", "Hora"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
