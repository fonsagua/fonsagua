package es.udc.cartolab.gvsig.fonsagua;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.cit.gvsig.CADExtension;
import com.iver.cit.gvsig.StartEditing;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.gui.cad.tools.AreaCADTool;
import com.iver.cit.gvsig.listeners.CADListenerManager;
import com.iver.cit.gvsig.listeners.EndGeometryListener;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.NewAlternativeDialog;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFilterFields;

public class NewAlternativeExtension extends Extension {

    private static String KEY_NAME;
    private View view;

    @Override
    public void execute(String actionCommand) {

	TOCLayerManager lyrMngr = new TOCLayerManager();
	FLyrVect layer = lyrMngr.getLayerByName(AlternativasForm.NAME);
	view = lyrMngr.getView();
	NewAlternativeEndGeometryListener listener = new NewAlternativeEndGeometryListener();
	CADListenerManager.removeEndGeometryListener(KEY_NAME);
	CADListenerManager.addEndGeometryListener(KEY_NAME, listener);

	new StartEditing().startEditing(view, layer);

	CADExtension.setCADTool(AreaCADTool.AREA_ACTION_COMMAND, true);

    }

    protected void registerIcons() {
	PluginServices.getIconTheme().registerDefault(
		"new_alternative",
		this.getClass().getClassLoader()
			.getResource("images/new_alternative.png"));
    }

    @Override
    public void initialize() {
	KEY_NAME = getClass().getName();
	registerIcons();
    }

    @Override
    public boolean isEnabled() {
	return FormFactory.checkLayerLoadedRegistered(AlternativasForm.NAME)
		&& OpenAlternativeExtension.getCode() == null;
    }

    @Override
    public boolean isVisible() {
	return true;
    }

    private class NewAlternativeEndGeometryListener implements
	    EndGeometryListener {

	@Override
	public void endGeometry(FLayer layer, String cadToolKey) {
	    if (!isAlternativeLayer(layer)) {
		return;
	    }
	    Map<String, Map<String, List<String>>> divsCodes = new HashMap<String, Map<String, List<String>>>();
	    try {
		Map<String, String> departNames = FonsaguaFilterFields
			.getDepartments();
		for (String key : departNames.keySet()) {
		    divsCodes.put(key, new HashMap<String, List<String>>());
		}

		Map<String, String> municNames = FonsaguaFilterFields
			.getMuniccipalities();

		String departCode;
		for (String key : municNames.keySet()) {
		    departCode = key.substring(0, 2);
		    if (divsCodes.containsKey(departCode)) {
			divsCodes.get(departCode).put(key,
				new ArrayList<String>());
		    }
		}

		String municCode;
		Map<String, String> cantonNames = FonsaguaFilterFields
			.getCantones();
		for (String key : cantonNames.keySet()) {
		    departCode = key.substring(0, 2);
		    municCode = key.substring(0, 4);
		    if ((divsCodes.containsKey(departCode))
			    && (divsCodes.get(departCode)
				    .containsKey(municCode))) {
			divsCodes.get(departCode).get(municCode).add(key);
		    }
		}

		NewAlternativeDialog dialog = new NewAlternativeDialog(
			((FLyrVect) layer), divsCodes, departNames, municNames,
			cantonNames);
		PluginServices.getMDIManager().addWindow(dialog);

		view.hideConsole();
		view.getMapControl().setTool("zoomIn");
	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	}

	private boolean isAlternativeLayer(FLayer layer) {
	    return (layer instanceof FLyrVect)
		    && (layer.getName().equals(AlternativasForm.NAME));
	}
    }

}
