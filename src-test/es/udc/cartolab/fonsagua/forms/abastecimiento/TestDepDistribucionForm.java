package es.udc.cartolab.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.DepDistribucionForm;

public class TestDepDistribucionForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return "public";
    }

    @Override
    protected String getTableName() {
	return DepDistribucionForm.NAME;
    }

}
