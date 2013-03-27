package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvisg.navtableforms.BasicAbstractForm;

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
