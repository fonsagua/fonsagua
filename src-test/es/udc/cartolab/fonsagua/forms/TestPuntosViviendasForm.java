package es.udc.cartolab.fonsagua.forms;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;

public class TestPuntosViviendasForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return "public";
    }

    @Override
    protected String getTableName() {
	return PuntosViviendasForm.NAME;
    }

}