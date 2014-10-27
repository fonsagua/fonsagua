package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;

public class FormsGeneralMenuExtension extends Extension {

    @Override
    public void execute(String actionCommand) {
	openForm(actionCommand);
    }

    @Override
    public boolean isEnabled() {
	return FonsaguaFormFactory.getInstance().allGeneralLayersLoaded();
    }

    @Override
    public boolean isVisible() {
	return true;
    }

    @Override
    public void initialize() {
    }

    public static AbstractForm openForm(String layerName) {
	AbstractForm dialog = null;
	if (FormFactory.hasMainFormRegistered(layerName)) {
	    FLyrVect layer = new TOCLayerManager().getLayerByName(layerName);
	    if (layer != null) {
		dialog = FormFactory.createFormRegistered(layer);
	    }
	}

	if ((dialog != null) && (dialog.init())) {
	    PluginServices.getMDIManager().addWindow(dialog);
	}
	return dialog;
    }

}
