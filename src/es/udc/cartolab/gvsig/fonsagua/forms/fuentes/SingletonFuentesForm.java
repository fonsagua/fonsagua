package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import com.iver.andami.ui.mdiManager.SingletonWindow;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class SingletonFuentesForm extends FuentesForm implements
	SingletonWindow {

    public SingletonFuentesForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    public Object getWindowModel() {
	return getBasicName();
    }

}
