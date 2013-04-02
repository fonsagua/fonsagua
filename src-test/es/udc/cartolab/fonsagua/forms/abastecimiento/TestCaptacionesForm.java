package es.udc.cartolab.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.CaptacionesForm;

public class TestCaptacionesForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return "public";
    }

    @Override
    protected String getTableName() {
	return CaptacionesForm.NAME;
    }

}