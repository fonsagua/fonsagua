package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.Launcher;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.plugins.ExtensionDecorator;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.gui.cad.tools.SelectionCADTool;
import com.iver.utiles.extensionPoints.ExtensionPoints;
import com.iver.utiles.extensionPoints.ExtensionPointsSingleton;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaTocMenuEntry;
import es.udc.cartolab.gvsig.tools.CopyFeaturesExtension;
import es.udc.cartolab.gvsig.users.SpatiaLiteDBConnectionExtension;

public class FormsExtension extends Extension {

    private FLyrVect[] layers;

    @Override
    public void execute(String actionCommand) {
	for (FLyrVect layer : layers) {
	    AbstractForm dialog = FormFactory.createFormRegistered(layer);

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
	// We need this block in order to register the factory as the default
	// one
	FonsaguaFormFactory.registerFormFactory();

	registerIcons();
	// Entry at TOC contextual menu
	ExtensionPoints extensionPoints = ExtensionPointsSingleton
		.getInstance();
	extensionPoints.add("View_TocActions", "Fonsagua",
		new FonsaguaTocMenuEntry());

	// Workaround to use another icon for CopyFeatures extension instead of
	// the default
	PluginServices.getIconTheme()
		.registerDefault(
			CopyFeaturesExtension.COPY_FEATURES_ICON,
			this.getClass().getClassLoader()
				.getResource("images/copy.png"));
    }

    @Override
    public void postInitialize() {
	// Workaround to increase the snap tolerance
	SelectionCADTool.tolerance = 12;

	// Workaround to open CopyFeatures dialog in a predefined foldder
	CopyFeaturesExtension cfe = (CopyFeaturesExtension) PluginServices
		.getExtension(CopyFeaturesExtension.class);
	cfe.setDefaultPath(Launcher.getAppHomeDir()
		+ FonsaguaConstants.GPS_MATCHING_FILES);

	// Workarount to hide the connection dialog for spatialite db
	ExtensionDecorator spliteExtension = PluginServices
		.getDecoratedExtension(SpatiaLiteDBConnectionExtension.class);
	spliteExtension.setVisibility(ExtensionDecorator.ALWAYS_INVISIBLE);
    }

    @Override
    public boolean isEnabled() {
	return isActiveLayerValid();
    }

    private boolean isActiveLayerValid() {
	layers = new TOCLayerManager().getActiveLayers();
	boolean layerWithForm = false;
	for (FLyrVect layer : layers) {
	    layerWithForm |= FormFactory.hasMainFormRegistered(layer.getName());
	}
	return layerWithForm;
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
