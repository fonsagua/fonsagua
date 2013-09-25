package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.Launcher;
import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.gui.cad.tools.SelectionCADTool;

import es.udc.cartolab.gvsig.fonsagua.config.EpanetConfiguration;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.tools.CopyFeaturesExtension;
import es.udc.cartolab.gvsig.users.PostGISDBConnectionExtension;

public class InitializeExtension extends Extension {

    @Override
    public void initialize() {
	EpanetConfiguration epanetConfig = new EpanetConfiguration();
	epanetConfig.setConfig();
	OpenAlternativeExtension.setCode(null);
	OpenAlternativeExtension.setValidAlternative(false);
    }

    @Override
    public void postInitialize() {
	// Workaround to increase the snap tolerance
	SelectionCADTool.tolerance = 12;

	// Workaround to open CopyFeatures dialog in a predefined folder
	CopyFeaturesExtension.setDefaultPath(Launcher.getAppHomeDir()
		+ FonsaguaConstants.GPS_MATCHING_FILES);

	// Remove project manager
	PluginServices.getMDIManager().closeAllWindows();

	// Opens the connection dialog. The start up preferences configuration
	// is not valid, because the closeAllWindows close this dialog also
	PostGISDBConnectionExtension connectionDialog = (PostGISDBConnectionExtension) PluginServices
		.getExtension(PostGISDBConnectionExtension.class);
	connectionDialog.execute(null);

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
