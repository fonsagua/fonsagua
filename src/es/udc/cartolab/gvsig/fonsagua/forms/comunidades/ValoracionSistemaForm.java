package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class ValoracionSistemaForm extends AbstractSubForm {

    public static final String NAME = "valoracion_sistema";
    public static String[] colNames = { "cod_abastecimiento", "sist_cobros",
	    "agua_suf", "serv_continuo" };
    public static String[] colAlias = { "Cód. abastecimiento",
	    "Sistema de cobros", "Agua suficiente", "Servicio continuo" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
