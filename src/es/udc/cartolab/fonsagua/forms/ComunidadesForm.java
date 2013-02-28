package es.udc.cartolab.fonsagua.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class ComunidadesForm extends BasicAbstractForm {

    public static final String NAME = "comunidades";

    public ComunidadesForm(FLyrVect layer) {
	super(layer);
	initWindow();
    }

    private void initWindow() {
	viewInfo.setHeight(750);
	viewInfo.setWidth(550);
	viewInfo.setTitle("Comunidades");
    }

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
