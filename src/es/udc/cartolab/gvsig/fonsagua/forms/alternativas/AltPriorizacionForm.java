package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class AltPriorizacionForm extends BasicAbstractSubForm {

    public static final String NAME = "priorizacion_alternativa";
    public static final String[] colNames = { "cod_alternativa",
	    "tipo_distribucion", "pobl_actual", "cuota_persona",
	    "coste_habitante", "prioridad_alt" };
    public static final String[] colAlias = { "Cod. Alternativa",
	    "Tipo Distr.", "Población", "Cuota ($/p*mes)", "Coste hab. ($/p)",
	    "Prioridad" };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
