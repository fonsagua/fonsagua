package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class AdescosForm extends BasicAbstractSubForm {

    public static final String NAME = "adescos";
    public static String[] colNames = {
"cod_comunidad", "nombre", "anho_const", "legalizada", "n_socios"
    };
    public static String[] colAlias = {
"ID Comunidad", "Nombre", "Año", "Legalizada", "Nº Socios"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
