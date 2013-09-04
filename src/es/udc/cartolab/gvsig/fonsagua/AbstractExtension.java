package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;

import es.udc.cartolab.gvsig.users.utils.DBSession;

public abstract class AbstractExtension extends Extension {

    /**
     * icon identifier in config, filename on disk (withou extension)
     */
    protected String id;

    /**
     * Register the file images/[iconName].png with the name [iconName] in the
     * icon theme
     * 
     */
    protected void registerIcon(String iconName) {
	PluginServices.getIconTheme().registerDefault(
		iconName,
		this.getClass().getClassLoader()
			.getResource("images/" + iconName + ".png"));
    }

    @Override
    public void initialize() {
	registerIcon(id);
    }

    @Override
    public boolean isEnabled() {
	return DBSession.isActive();
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
