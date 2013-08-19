package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.plugins.Extension;

public class EmptyExtension extends Extension {

    @Override
    public void initialize() {
    }

    @Override
    public void execute(String actionCommand) {
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

    @Override
    public boolean isVisible() {
	return false;
    }

}
