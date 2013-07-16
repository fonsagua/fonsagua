package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;

public class FormsMenuExtension extends Extension {

    @Override
    public void execute(String actionCommand) {
	AbstractForm dialog = null;
	if (FormFactory.hasMainFormRegistered(actionCommand)) {
	    FLyrVect layer = new TOCLayerManager()
		    .getLayerByName(actionCommand);
	    if (layer != null) {
		dialog = FormFactory.createFormRegistered(layer);
	    }
	}

	if ((dialog != null) && (dialog.init())) {
	    PluginServices.getMDIManager().addWindow(dialog);
	}
    }

    @Override
    public boolean isEnabled() {
	return FormFactory.allLayersLoadedRegistered();
    }

    @Override
    public boolean isVisible() {
	return true;
    }

    @Override
    public void initialize() {
    }

}
