package es.udc.cartolab.gvsig.fonsagua;

import java.util.ArrayList;
import java.util.List;

import com.iver.andami.Launcher;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.plugins.ExtensionDecorator;
import com.iver.andami.plugins.IExtension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.gui.cad.tools.SelectionCADTool;
import com.iver.utiles.extensionPoints.ExtensionPoints;
import com.iver.utiles.extensionPoints.ExtensionPointsSingleton;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.elle.DeleteAllLegendsExtension;
import es.udc.cartolab.gvsig.elle.DeleteMapExtension;
import es.udc.cartolab.gvsig.elle.LoadAllLegendsExtension;
import es.udc.cartolab.gvsig.elle.LoadMapExtension;
import es.udc.cartolab.gvsig.elle.SaveAllLegendsExtension;
import es.udc.cartolab.gvsig.elle.SaveMapExtension;
import es.udc.cartolab.gvsig.fonsagua.config.EpanetConfiguration;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
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
	PluginServices.getIconTheme().registerDefault(
		CopyFeaturesExtension.COPY_FEATURES_ICON,
		this.getClass().getClassLoader()
			.getResource("images/copy_features.png"));
	EpanetConfiguration epanetConfig = new EpanetConfiguration();
	epanetConfig.setConfig();
    }

    @Override
    public void postInitialize() {
	// Workaround to increase the snap tolerance
	SelectionCADTool.tolerance = 12;

	// Workaround to open CopyFeatures dialog in a predefined folder
	CopyFeaturesExtension.setDefaultPath(Launcher.getAppHomeDir()
		+ FonsaguaConstants.GPS_MATCHING_FILES);

	// Hide extensions that we only use as libraries and not as extensions
	// to be "clicked" by the users
	List<Class<? extends IExtension>> l = new ArrayList<Class<? extends IExtension>>();
	l.add(SpatiaLiteDBConnectionExtension.class);
	l.add(DeleteAllLegendsExtension.class);
	l.add(DeleteMapExtension.class);
	l.add(LoadAllLegendsExtension.class);
	l.add(LoadMapExtension.class);
	l.add(SaveAllLegendsExtension.class);
	l.add(SaveMapExtension.class);

	for (Class<? extends IExtension> c : l) {
	    PluginServices.getDecoratedExtension(c).setVisibility(
		    ExtensionDecorator.ALWAYS_INVISIBLE);
	}

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
