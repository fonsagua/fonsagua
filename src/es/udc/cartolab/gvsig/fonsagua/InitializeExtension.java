package es.udc.cartolab.gvsig.fonsagua;

import java.util.ArrayList;
import java.util.List;

import com.iver.andami.Launcher;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.plugins.ExtensionDecorator;
import com.iver.andami.plugins.IExtension;
import com.iver.cit.gvsig.gui.cad.tools.SelectionCADTool;

import es.udc.cartolab.gvsig.fonsagua.config.EpanetConfiguration;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.tools.CopyFeaturesExtension;
import es.udc.cartolab.gvsig.users.PostGISDBConnectionExtension;
import es.udc.cartolab.gvsig.users.SpatiaLiteDBConnectionExtension;

public class InitializeExtension extends Extension {

    @Override
    public void initialize() {
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

	hideExtensions(false);

	// Remove project manager
	PluginServices.getMDIManager().closeAllWindows();

	// Opens the connection dialog. The start up preferences configuration
	// is not valid, because the closeAllWindows close this dialog also
	PostGISDBConnectionExtension connectionDialog = (PostGISDBConnectionExtension) PluginServices
		.getExtension(PostGISDBConnectionExtension.class);
	connectionDialog.execute(null);

    }

    private void hideExtensions(boolean hide) {
	if (!hide) {
	    return;
	}
	// Hide extensions that we only use as libraries and not as extensions
	// to be "clicked" by the users
	List<Class<? extends IExtension>> l = new ArrayList<Class<? extends IExtension>>();
	l.add(SpatiaLiteDBConnectionExtension.class);
	// l.add(DeleteAllLegendsExtension.class);
	// l.add(DeleteMapExtension.class);
	// l.add(LoadAllLegendsExtension.class);
	// l.add(LoadMapExtension.class);
	// l.add(SaveAllLegendsExtension.class);
	// l.add(SaveMapExtension.class);

	for (Class<? extends IExtension> c : l) {
	    PluginServices.getDecoratedExtension(c).setVisibility(
		    ExtensionDecorator.ALWAYS_INVISIBLE);
	}
    }

    @Override
    public void execute(String actionCommand) {
	throw new AssertionError();
    }

    @Override
    public boolean isEnabled() {
	return false;
    }

    @Override
    public boolean isVisible() {
	return false;
    }

}
