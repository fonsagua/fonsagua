package es.udc.cartolab.gvsig.fonsagua.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class OtrosServiciosForm extends BasicAbstractForm {

    public static final String NAME = "otros_servicios";

    public OtrosServiciosForm(FLyrVect layer) {
	super(layer);
	initWindow();
    }

    private void initWindow() {
	viewInfo.setHeight(500);
	viewInfo.setWidth(600);
	viewInfo.setTitle("Otros Servicios");
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
