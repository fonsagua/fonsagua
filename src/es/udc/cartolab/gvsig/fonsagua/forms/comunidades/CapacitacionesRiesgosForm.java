package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class CapacitacionesRiesgosForm extends AbstractSubForm {

    public static final String NAME = "capacitaciones_riesgos";
    public static String[] colNames = { "institucion", "fecha", "temas" };
    public static String[] colAlias = { "Instituci�n", "Fecha",
	    "Temas de capacitaci�n" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
