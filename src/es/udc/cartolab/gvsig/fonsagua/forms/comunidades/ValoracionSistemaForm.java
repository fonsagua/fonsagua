package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class ValoracionSistemaForm extends AbstractSubForm {

    public static final String NAME = "valoracion_sistema";
    public static String[] colNames = { "sist_cobros", "nivel_serv",
	    "agua_suf", "serv_continuo", "acceso_tomas" };
    public static String[] colAlias = { "Sistema de cobros",
	    "Nivel de servicio", "Agua suficiente", "Servicio continuo",
	    "Acceso a las tomas de Agua" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
