package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.fmap.MapControl;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.tools.Behavior.PointBehavior;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.utils.FormPointListener;

//TODO: Too much shared coded with FormsExtension

public class FormPointExtension extends Extension {
    private final String iconPath = "images/form-point.png";
    private final String iconCode = "form-point";

    @Override
    public void initialize() {
	PluginServices.getIconTheme().registerDefault(iconCode,
		this.getClass().getClassLoader().getResource(iconPath));
    }

    @Override
    public void execute(String actionCommand) {
	View view = (View) PluginServices.getMDIManager().getActiveWindow();
	MapControl mc = view.getMapControl();
	if (!mc.getNamesMapTools().containsKey(iconCode)) {
	    FormPointListener fpl = new FormPointListener(mc);
	    mc.addMapTool(iconCode, new PointBehavior(fpl));
	}

	mc.setTool(iconCode);

    }

    @Override
    public boolean isEnabled() {
	return isActiveLayerValid();
    }

    private boolean isActiveLayerValid() {
	FLyrVect[] layers = new TOCLayerManager().getActiveLayers();
	boolean layerWithForm = false;
	for (FLyrVect layer : layers) {
	    layerWithForm |= FormFactory.hasMainFormRegistered(layer.getName());
	}
	return layerWithForm;
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}