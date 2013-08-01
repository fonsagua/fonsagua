package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.FuentesContaminacionForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class TestFuentesContaminacionForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return FonsaguaConstants.dataSchema;
    }

    @Override
    protected String getTableName() {
	return FuentesContaminacionForm.NAME;
    }

}
