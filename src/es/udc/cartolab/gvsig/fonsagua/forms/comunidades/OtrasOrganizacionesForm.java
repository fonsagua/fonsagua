package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class OtrasOrganizacionesForm extends BasicAbstractSubForm {

    public static final String NAME = "otras_organizaciones";
    public static String[] colNames = {
"tipo_organizacion", "nombre", "f_creacion", "actividad"
    };
    public static String[] colAlias = {
"Tipo", "Nombre", "Fecha", "Actividad"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
