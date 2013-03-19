package es.udc.cartolab.gvsig.fonsagua.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class AmenazasForm extends BasicAbstractForm {

    public static final String NAME = "amenazas";

    public AmenazasForm(FLyrVect layer) {
	super(layer);
	initWindow();
    }

    private void initWindow() {
	viewInfo.setHeight(500);
	viewInfo.setWidth(650);
	viewInfo.setTitle("Amenazas");
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
