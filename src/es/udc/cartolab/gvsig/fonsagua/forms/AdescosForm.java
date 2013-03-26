package es.udc.cartolab.gvsig.fonsagua.forms;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class AdescosForm extends AbstractSubForm {

    public static final String NAME = "adescos";
    public static String[] colNames = { "nombre", "anho_const", "legalizada",
	    "n_socios", "tot_miembros" };
    public static String[] colAlias = { "Nombre", "A�o constituci�n",
	    "Legalizada", "N� de socios", "N� total de miembros" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
