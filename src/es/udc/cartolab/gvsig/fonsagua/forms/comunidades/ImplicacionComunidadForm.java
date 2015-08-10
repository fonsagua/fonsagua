package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class ImplicacionComunidadForm extends BasicAbstractSubForm {

    public static final String NAME = "implicacion_comunidad";
    public static String[] colNames = {
"cod_comunidad", "dinero_inv", "tiempo_inv"
    };
    public static String[] colAlias = {
"ID Comunidad", "En dinero invertido", "En tiempo invertido"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
