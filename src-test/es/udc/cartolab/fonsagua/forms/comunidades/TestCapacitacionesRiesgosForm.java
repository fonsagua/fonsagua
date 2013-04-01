package es.udc.cartolab.fonsagua.forms.comunidades;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CapacitacionesRiesgosForm;

public class TestCapacitacionesRiesgosForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return "public";
    }

    @Override
    protected String getTableName() {
	return CapacitacionesRiesgosForm.NAME;
    }

}
