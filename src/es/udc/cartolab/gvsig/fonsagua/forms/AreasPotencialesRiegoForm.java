package es.udc.cartolab.gvsig.fonsagua.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvisg.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AreasPotencialesRiegoForm extends BasicAbstractForm {

    public static final String NAME = "areas_potenciales_riego";

    public AreasPotencialesRiegoForm(FLyrVect layer) {
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
