package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class OtrasOrganizacionesForm extends AbstractSubForm {

    public static final String NAME = "otras_organizaciones";
    public static String[] colNames = { "tipo_organizacion", "nombre",
	    "f_creacion", "actividad" };
    public static String[] colAlias = { "Organización", "Nombre",
	    "Fecha creación", "Actividades" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
