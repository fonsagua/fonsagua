package es.udc.cartolab.fonsagua.forms;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;

public class TestCentrosEducativosForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return "public";
    }

    @Override
    protected String getTableName() {
	return CentrosEducativosForm.NAME;
    }

}
