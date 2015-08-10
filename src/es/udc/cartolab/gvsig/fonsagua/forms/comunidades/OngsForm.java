package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class OngsForm extends BasicAbstractSubForm {

    public static final String NAME = "ongs";
    public static String[] colNames = {
"ong", "fechas", "tipo_proy", "valoracion"
    };
    public static String[] colAlias = {
"ONG", "Fecha", "Tipo proyecto", "Valoración"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
