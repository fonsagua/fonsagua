package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class OngsForm extends AbstractSubForm {

    public static final String NAME = "ongs";
    public static String[] colNames = { "ong", "fechas", "tipo_proy",
	    "capacitacion" };
    public static String[] colAlias = { "ONG", "Período del proyecto",
	    "Tipo de proyecto", "Capacitaciones" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
