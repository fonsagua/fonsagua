package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableFormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;

public class FormsExtension extends Extension {

    private FLyrVect[] layers;
    private final TableFormFactory factory = FonsaguaTableFormFactory
	    .getInstance();

    @Override
    public void execute(String actionCommand) {
	for (FLyrVect layer : layers) {
	    BasicAbstractForm dialog = factory.createForm(layer);

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
