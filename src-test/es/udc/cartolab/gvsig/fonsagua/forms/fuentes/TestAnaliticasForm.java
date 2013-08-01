package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.AnaliticasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class TestAnaliticasForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return FonsaguaConstants.dataSchema;
    }

    @Override
    protected String getTableName() {
	return AnaliticasForm.NAME;
    }

}
