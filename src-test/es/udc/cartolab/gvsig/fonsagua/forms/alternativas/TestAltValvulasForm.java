package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class TestAltValvulasForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return FonsaguaConstants.dataSchema;
    }

    @Override
    protected String getTableName() {
	return AltValvulasForm.NAME;
    }

}
