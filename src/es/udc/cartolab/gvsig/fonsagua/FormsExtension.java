package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.utiles.extensionPoints.ExtensionPoints;
import com.iver.utiles.extensionPoints.ExtensionPointsSingleton;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.utils.AvailableForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaTocMenuEntry;

/**
 * This extension will be enabled when the form of any of the active layers
 * could be opened: # "comunity layers" in all situations #
 * "alternatives layers" only when a alternative was previously open
 * 
 * execute method will try to open a form for all the "valid" active layers
 * 
 */
public class FormsExtension extends Extension {

    private FLyrVect[] layers;

    @Override
    public boolean isVisible() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	layers = new TOCLayerManager().getActiveLayers();
	for (FLyrVect layer : layers) {
	    if (AvailableForm.forLayer(layer)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public void execute(String actionCommand) {
	for (FLyrVect layer : layers) {
	    if (AvailableForm.forLayer(layer)) {
		AbstractForm dialog = FormFactory.createFormRegistered(layer);

		if ((dialog != null) && (dialog.init())) {
		    PluginServices.getMDIManager().addWindow(dialog);
		}
	    }
	}
    }

    @Override
    public void initialize() {
	// We need this block in order to register the factory as the default
	// one
	FonsaguaFormFactory.registerFormFactory();

	registerIcons();
	// Entry at TOC contextual menu
	ExtensionPoints extensionPoints = ExtensionPointsSingleton
		.getInstance();
	extensionPoints.add("View_TocActions", "Fonsagua",
		new FonsaguaTocMenuEntry());
    }

    protected void registerIcons() {
	PluginServices.getIconTheme()
		.registerDefault(
			"forms-icon",
			this.getClass().getClassLoader()
				.getResource("images/form.png"));
    }

}
