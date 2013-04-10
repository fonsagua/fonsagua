package es.udc.cartolab.gvsig.fonsagua;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.iver.andami.Launcher;
import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiFrame.JToolBarToggleButton;
import com.iver.andami.ui.mdiFrame.MDIFrame;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.listeners.CADListenerManager;
import com.iver.cit.gvsig.listeners.EndGeometryListener;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableFormFactory;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;

public class AutoFormsExtension extends Extension {

    private static String KEY_NAME;
    private boolean formsEnabled = false;
    private File fileFlag;
    private final TableFormFactory factory = FonsaguaTableFormFactory
	    .getInstance();

    @Override
    public void initialize() {
	KEY_NAME = getClass().getName();
	fileFlag = new File(Launcher.getAppHomeDir() + "fonsagua-auto-forms");
	formsEnabled = getLastSessionEnabilityState();

	if (formsEnabled) {
	    NTEndGeometryListener listener = new NTEndGeometryListener();
	    CADListenerManager.addEndGeometryListener(KEY_NAME, listener);
	}

	registerIcon();
    }

    private boolean getLastSessionEnabilityState() {
	return fileFlag.exists();
    }

    private void registerIcon() {
	URL icon = this.getClass().getClassLoader()
		.getResource("images/auto-forms.png");
	PluginServices.getIconTheme().registerDefault("auto-forms", icon);
    }

    @Override
    public void postInitialize() {
	toggleButton(formsEnabled);
    }

    @Override
    public void execute(String actionCommand) {

	if (!formsEnabled) {
	    NTEndGeometryListener listener = new NTEndGeometryListener();
	    CADListenerManager.addEndGeometryListener(KEY_NAME, listener);
	    NotificationManager.addInfo("Formularios automáticos activados");
	    formsEnabled = true;
	} else {
	    CADListenerManager.removeEndGeometryListener(KEY_NAME);
	    NotificationManager.addInfo("Formularios automáticos desactivados");
	    formsEnabled = false;
	}

	toggleButton(formsEnabled);
	persistsEnabilityState();

    }

    public void persistsEnabilityState() {
	try {
	    if (formsEnabled) {
		fileFlag.createNewFile();

	    } else {
		fileFlag.delete();
	    }
	} catch (IOException e) {
	}
    }

    private void toggleButton(boolean pushed) {
	String tooltip;

	MDIFrame f = ((MDIFrame) PluginServices.getMainFrame());

	if (f.getSelectedTools() == null) {
	    f.setSelectedTools(f.getInitialSelectedTools());
	}

	if (!pushed) {
	    f.setSelectedTool("fonsagua", "empty");
	    tooltip = PluginServices.getText(this, "enable_auto_forms");
	} else {
	    f.setSelectedTool("fonsagua", "auto-forms");
	    tooltip = PluginServices.getText(this, "disable_auto_forms");
	}
	JToolBarToggleButton throwNTButton = (JToolBarToggleButton) f
		.getComponentByName("auto-forms");
	if (throwNTButton != null) {
	    throwNTButton.setToolTip(tooltip);
	}
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public boolean isVisible() {
	return true;
    }

    private class NTEndGeometryListener implements EndGeometryListener {

	@Override
	public void endGeometry(FLayer layer, String cadToolKey) {
	    if (layer instanceof FLyrVect) {
		BasicAbstractForm form = factory.createForm((FLyrVect) layer);
		if (form.init()) {
		    form.last();
		    PluginServices.getMDIManager().addWindow(form);
		}
	    }
	}
    }
}
