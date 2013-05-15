package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.andami.ui.mdiManager.SingletonWindow;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class SingletonComunidadesForm extends ComunidadesForm implements
	SingletonWindow {

    public SingletonComunidadesForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    public Object getWindowModel() {
	return getBasicName();
    }

}
