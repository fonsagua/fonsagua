package es.udc.cartolab.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.JuntasAguaForm;

public class TestJuntasAguaForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return FonsaguaConstants.dataSchema;
    }

    @Override
    protected String getTableName() {
	return JuntasAguaForm.NAME;
    }

}
