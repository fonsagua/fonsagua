package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class AdescosForm extends AbstractSubForm {

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
