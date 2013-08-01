package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltDepositosForm;

public class TestAltDepositosForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return FonsaguaConstants.dataSchema;
    }

    @Override
    protected String getTableName() {
	return AltDepositosForm.NAME;
    }

}
