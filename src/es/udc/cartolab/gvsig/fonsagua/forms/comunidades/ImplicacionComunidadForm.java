package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class ImplicacionComunidadForm extends AbstractSubForm {

    public static final String NAME = "implicacion_comunidad";
    public static String[] colNames = { "dinero_inv", "tiempo_inv" };
    public static String[] colAlias = { "En dinero invertido",
	    "En tiempo invertido" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
