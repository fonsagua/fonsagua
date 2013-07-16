package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.gui.cad.tools.SelectionCADTool;
import com.iver.utiles.extensionPoints.ExtensionPoints;
import com.iver.utiles.extensionPoints.ExtensionPointsSingleton;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaTocMenuEntry;

public class FormsExtension extends Extension {

    private FLyrVect[] layers;
    private final FormFactory factory = FonsaguaFormFactory
	    .getInstance();

    @Override
    public void execute(String actionCommand) {
	for (FLyrVect layer : layers) {
	    AbstractForm dialog = factory.createForm(layer);

	    if ((dialog != null) && (dialog.init())) {
		PluginServices.getMDIManager().addWindow(dialog);
	    }
	}
    }

    protected void registerIcons() {
	PluginServices.getIconTheme()
		.registerDefault(
			"forms-icon",
			this.getClass().getClassLoader()
				.getResource("images/form.png"));
    }

    @Override
    public void initialize() {
	registerIcons();

	// Entry at TOC contextual menu
	ExtensionPoints extensionPoints = ExtensionPointsSingleton
		.getInstance();
	extensionPoints.add("View_TocActions", "Fonsagua",
		new FonsaguaTocMenuEntry());
    }

    @Override
    public void postInitialize() {
	// Workaround to increase the snap tolerance
	SelectionCADTool.tolerance = 12;
    }

    @Override
    public boolean isEnabled() {
	return isActiveLayerValid();
    }

    private boolean isActiveLayerValid() {
	layers = new TOCLayerManager().getActiveLayers();
	boolean layerWithForm = false;
	for (FLyrVect layer : layers) {
	    layerWithForm |= factory.hasMainForm(layer.getName());
	}
	return layerWithForm;
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
