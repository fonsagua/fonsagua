package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.andami.ui.mdiManager.SingletonWindow;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class SingletonAbastecimientosForm extends AbastecimientosForm implements
	SingletonWindow {

    public SingletonAbastecimientosForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    public Object getWindowModel() {
	return getBasicName();
    }

}
