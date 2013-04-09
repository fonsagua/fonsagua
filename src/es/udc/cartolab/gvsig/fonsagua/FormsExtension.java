package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableFormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;

public class FormsExtension extends Extension {

    private FLyrVect layer;
    private final TableFormFactory factory = FonsaguaTableFormFactory
	    .getInstance();

    @Override
    public void execute(String actionCommand) {

	BasicAbstractForm dialog = null;
	dialog = factory.createForm(layer);

	if ((dialog != null) && (dialog.init())) {
	    PluginServices.getMDIManager().addWindow(dialog);
	}
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
	layer = new TOCLayerManager().getActiveLayer();
	return (layer != null) ? factory.hasMainForm(layer.getName()) : false;

    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
