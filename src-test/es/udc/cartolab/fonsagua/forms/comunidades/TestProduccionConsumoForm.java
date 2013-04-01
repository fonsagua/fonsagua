package es.udc.cartolab.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ProduccionConsumoForm;

public class TestProduccionConsumoForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return "public";
    }

    @Override
    protected String getTableName() {
	return ProduccionConsumoForm.NAME;
    }

}
