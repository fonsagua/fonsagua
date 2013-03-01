package es.udc.cartolab.fonsagua.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class AreasPotencialesRiegoForms extends BasicAbstractForm {

    public static final String NAME = "areas_potenciales_riego";

    public AreasPotencialesRiegoForms(FLyrVect layer) {
	super(layer);
	initWindow();

    }

    private void initWindow() {
	viewInfo.setHeight(550);
	viewInfo.setWidth(350);
	viewInfo.setTitle("Areas Potenciales Riego");
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
