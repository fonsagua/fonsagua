package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableFormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;

public class FormsMenuExtension extends Extension {

    private final TableFormFactory factory = FonsaguaTableFormFactory
	    .getInstance();

    @Override
    public void execute(String actionCommand) {
	AbstractForm dialog = null;
	if (factory.hasMainForm(actionCommand)) {
	    FLyrVect layer = new TOCLayerManager()
		    .getLayerByName(actionCommand);
	    if (layer != null) {
		dialog = factory.createForm(layer);
	    }
	}

	if ((dialog != null) && (dialog.init())) {
	    PluginServices.getMDIManager().addWindow(dialog);
	}
    }

    @Override
    public boolean isEnabled() {
	return factory.allLayersLoaded();
    }

    @Override
    public boolean isVisible() {
	return true;
    }

    @Override
    public void initialize() {
    }

}
