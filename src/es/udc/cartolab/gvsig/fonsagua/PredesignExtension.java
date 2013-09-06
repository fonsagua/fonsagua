package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;

import es.udc.cartolab.gvsig.fonsagua.prediseno.ui.PredesignDialog;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class PredesignExtension extends Extension {

    @Override
    public void initialize() {
	registerIcons();
    }

    protected void registerIcons() {
	PluginServices.getIconTheme().registerDefault(
		"predesign",
		this.getClass().getClassLoader()
			.getResource("images/predesign.png"));
    }

    @Override
    public void execute(String actionCommand) {
	PredesignDialog.showDialog();
    }

    @Override
    public boolean isEnabled() {
	return (DBSession.getCurrentSession() != null);
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
