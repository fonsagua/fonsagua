package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.fonsagua.forms.AreasPotencialesRiegoForms;
import es.udc.cartolab.fonsagua.forms.BasicAbstractForm;
import es.udc.cartolab.fonsagua.forms.ComunidadesForm;
import es.udc.cartolab.fonsagua.forms.PuntosViviendasForm;

public class FormsExtension extends Extension {

    private FLyrVect layer;

    @Override
    public void execute(String actionCommand) {

	BasicAbstractForm dialog = null;

	if (layer.getName().equals(ComunidadesForm.NAME)) {
	    dialog = new ComunidadesForm(layer);
	} else {
	    if (layer.getName().equals(AreasPotencialesRiegoForms.NAME)) {

		dialog = new AreasPotencialesRiegoForms(layer);
	    }

	    else {

		dialog = new PuntosViviendasForm(layer);
	    }
	}

	if (dialog.init()) {
	    PluginServices.getMDIManager().addWindow(dialog);
	}
    }

    private FLyrVect getLayerFromTOC(String layerName) {
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
	IWindow iWindow = PluginServices.getMDIManager().getActiveWindow();
	if (iWindow instanceof View) {
	    View view = (View) iWindow;
	    FLayers layers = view.getMapControl().getMapContext().getLayers();
	    if (layers != null) {
		if (layers.getActives().length > 0) {
		    TOCLayerManager toc = new TOCLayerManager();
		    layer = toc.getActiveLayer();
		    String layerName = layer.getName();
		    if (layerName.equals(ComunidadesForm.NAME)
			    || layerName.equals(PuntosViviendasForm.NAME)
			    || layerName
				    .equals(AreasPotencialesRiegoForms.NAME)) {
			return true;
		    }

		}
	    }

	}

	return false;

    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
