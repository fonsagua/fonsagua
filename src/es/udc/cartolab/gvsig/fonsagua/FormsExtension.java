package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.fonsagua.forms.ComunidadesForm;

public class FormsExtension extends Extension {

    private FLyrVect layer;

    @Override
    public void execute(String actionCommand) {
	layer = getLayerFromTOC();
	ComunidadesForm dialog = new ComunidadesForm(layer);
	if (dialog.init()) {
	    PluginServices.getMDIManager().addWindow(dialog);
	}
    }

    private FLyrVect getLayerFromTOC() {
	String layerName = ComunidadesForm.NAME;
	TOCLayerManager toc = new TOCLayerManager();
	return toc.getLayerByName(layerName);
    }

    protected void registerIcons() {
	PluginServices.getIconTheme().registerDefault(
		"example1-ntforms",
		this.getClass().getClassLoader()
			.getResource("images/example1.png"));
    }

    @Override
    public void initialize() {
	registerIcons();
    }

    @Override
    public boolean isEnabled() {
	if (isExampleDataSetLoaded()) {
	    return true;
	}
	return false;
    }

    private boolean isExampleDataSetLoaded() {
	if (getLayerFromTOC() == null) {
	    return false;
	}
	return true;
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
