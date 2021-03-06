package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class EvaluacionForm extends BasicAbstractSubForm {

    public static final String NAME = "evaluacion";
    public static String[] colNames = {
"fecha", "calidad_agua", "evaluacion"
    };
    public static String[] colAlias = {
"Fecha", "Calidad del agua", "Evaluación"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
