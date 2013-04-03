package es.udc.cartolab.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.AforosForm;

public class TestAforosForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return "public";
    }

    @Override
    protected String getTableName() {
	return AforosForm.NAME;
    }

}
