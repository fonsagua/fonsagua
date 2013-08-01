package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.EntrevistadoresForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class TestEntrevistadoresForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return FonsaguaConstants.dataSchema;
    }

    @Override
    protected String getTableName() {
	return EntrevistadoresForm.NAME;
    }

}
