package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class TiposCultivosForm extends BasicAbstractSubForm {

    public static final String NAME = "tipos_cultivos";
    public static String[] colNames = {
"tipo", "f_propietarias", "f_arrendatarias", "superficie"
    };
    public static String[] colAlias = {
"Tipo", "N� f propietarias", "N� f arrendatarias", "Superficie"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
