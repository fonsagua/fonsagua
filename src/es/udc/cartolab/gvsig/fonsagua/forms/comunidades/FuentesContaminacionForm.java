package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class FuentesContaminacionForm extends AbstractSubForm {

    public static final String NAME = "fuentes_contaminacion";
    public static String[] colNames = { "tipo_contaminacion", "n_fam_vierten" };
    public static String[] colAlias = { "Tipo", "Nº familias que vierten" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
