package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class CapacitacionesRiesgosForm extends BasicAbstractSubForm {

    public static final String NAME = "capacitaciones_riesgos";
    public static String[] colNames = {
"institucion", "fecha", "temas"
    };
    public static String[] colAlias = {
"Institución", "Fecha", "Temas de capacitación"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
