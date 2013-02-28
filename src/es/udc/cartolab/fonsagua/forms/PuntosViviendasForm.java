package es.udc.cartolab.fonsagua.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class PuntosViviendasForm extends BasicAbstractForm {

    public static final String NAME = "puntos_viviendas";

    public PuntosViviendasForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
