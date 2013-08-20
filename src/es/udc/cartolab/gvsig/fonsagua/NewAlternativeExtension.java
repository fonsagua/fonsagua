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
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

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
	return FormFactory.checkLayerLoadedRegistered(AlternativasForm.NAME);
    }

    @Override
    public boolean isVisible() {
	return true;
    }

    private class NewAlternativeEndGeometryListener implements
	    EndGeometryListener {

	@Override
	public void endGeometry(FLayer layer, String cadToolKey) {
	    if ((layer instanceof FLyrVect)
		    && (layer.getName().equals(AlternativasForm.NAME))) {
		try {
		    String[] fields = new String[2];
		    String[] orderBy = new String[1];
		    Map<String, Map<String, List<String>>> divsCodes = new HashMap<String, Map<String, List<String>>>();
		    Map<String, String> departNames = new HashMap<String, String>();
		    fields[0] = FonsaguaConstants.departamentosPK;
		    fields[1] = FonsaguaConstants.departamentosName;
		    orderBy[0] = FonsaguaConstants.departamentosPK;
		    String[][] rows = DBSession.getCurrentSession()
			    .getTable(FonsaguaConstants.departamentosTable,
				    FonsaguaConstants.baseSchema, fields, "",
				    orderBy, false);
		    for (String[] depart : rows) {
			departNames.put(depart[0], depart[1]);
			divsCodes.put(depart[0],
				new HashMap<String, List<String>>());
		    }
		    Map<String, String> municNames = new HashMap<String, String>();
		    fields[0] = FonsaguaConstants.municipiosPK;
		    fields[1] = FonsaguaConstants.municipiosName;
		    orderBy[0] = FonsaguaConstants.municipiosPK;
		    rows = DBSession.getCurrentSession()
			    .getTable(FonsaguaConstants.municipiosTable,
				    FonsaguaConstants.baseSchema, fields, "",
				    orderBy, false);
		    String departCode;
		    for (String[] munic : rows) {
			if ((munic[0] != null) && (munic[0].length() >= 2)) {
			    municNames.put(munic[0], munic[1]);
			    departCode = munic[0].substring(0, 2);
			    if (divsCodes.containsKey(departCode)) {
				divsCodes.get(departCode).put(munic[0],
					new ArrayList<String>());
			    }
			}

		    }
		    Map<String, String> cantonNames = new HashMap<String, String>();
		    fields[0] = FonsaguaConstants.cantonesPK;
		    fields[1] = FonsaguaConstants.cantonesName;
		    orderBy[0] = FonsaguaConstants.cantonesPK;
		    rows = DBSession.getCurrentSession()
			    .getTable(FonsaguaConstants.cantonesTable,
				    FonsaguaConstants.baseSchema, fields, "",
				    orderBy, false);
		    String municCode;
		    for (String[] canton : rows) {
			if ((canton[0] != null) && (canton[0].length() >= 4)) {
			    cantonNames.put(canton[0], canton[1]);
			    departCode = canton[0].substring(0, 2);
			    municCode = canton[0].substring(0, 4);
			    if ((divsCodes.containsKey(departCode))
				    && (divsCodes.get(departCode)
					    .containsKey(municCode))) {
				divsCodes.get(departCode).get(municCode)
					.add(canton[0]);
			    }
			}
		    }
		    NewAlternativeDialog dialog = new NewAlternativeDialog(
			    ((FLyrVect) layer), divsCodes, departNames,
			    municNames, cantonNames);
		    PluginServices.getMDIManager().addWindow(dialog);

		    view.hideConsole();
		    view.getMapControl().setTool("zoomIn");
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	    }
	}
    }

}
