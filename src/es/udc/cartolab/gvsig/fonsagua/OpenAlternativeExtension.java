package es.udc.cartolab.gvsig.fonsagua;

import java.util.Set;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.documents.ProjectDocument;
import com.iver.cit.gvsig.project.documents.ProjectDocumentFactory;
import com.iver.cit.gvsig.project.documents.view.ProjectViewFactory;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.OpenAlternativeDialog;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class OpenAlternativeExtension extends Extension {

    private static String code;

    @Override
    public void initialize() {
	PluginServices.getIconTheme().registerDefault(
		"open_alternative",
		this.getClass().getClassLoader()
			.getResource("images/open_alternative.png"));
    }

    @Override
    public void execute(String actionCommand) {
	OpenAlternativeDialog dialog = new OpenAlternativeDialog();
	OpenAlternativeExtension.code = dialog.getCode();
	if (OpenAlternativeExtension.code == null) {
	    // TODO: catch error
	    return;
	}
	// TODO: What must be done if we already opened an alternative and the
	// user press the button again.
	// PluginServices.getMDIManager().closeAllWindows();
	openAlternative(code);
    }

    public static void openAlternative(String code) {
	MapDAO mapDAO = MapDAO.getInstance();
	try {
	    final View view = createViewIfNeeded();
	    ELLEMap map = mapDAO.getMap(view,
		    FonsaguaConstants.AlternativesMap, LoadLegend.DB_LEGEND,
		    FonsaguaConstants.AlternativesMap);

	    // TODO: This strings should not be hard coded
	    final String sqlWhere = "where " + AlternativasForm.PKFIELD
		    + " = '" + code + "'";
	    map.getLayer("alt_bombeos").setWhere(sqlWhere);
	    map.getLayer("alt_conexiones").setWhere(sqlWhere);
	    map.getLayer("alt_depositos").setWhere(sqlWhere);
	    map.getLayer("alt_fuentes").setWhere(sqlWhere);
	    map.getLayer("alt_tuberias").setWhere(sqlWhere);
	    map.getLayer("alt_valvulas").setWhere(sqlWhere);
	    map.getLayer("alt_embalses").setWhere(sqlWhere);
	    map.getLayer("alternativas").setWhere(sqlWhere);
	    map.load(view.getProjection());
	} catch (Exception e) {
	    // TODO: catch error
	    e.printStackTrace();
	}
    }

    /**
     * If the active window is a View returns it, if not creates a new one, adds
     * it to the project and returns it
     */
    private static View createViewIfNeeded() {

	// TODO: fpuga: Check what happens when exists a view in the project but
	// is not active
	IWindow iWindow = PluginServices.getMDIManager().getActiveWindow();
	View view = null;

	if (iWindow instanceof View) {
	    view = (View) iWindow;
	    cleanViewIfNeeded(view);
	} else {
	    Project project = ((ProjectExtension) PluginServices
		    .getExtension(ProjectExtension.class)).getProject();
	    ProjectDocumentFactory viewFactory = Project
		    .getProjectDocumentFactory(ProjectViewFactory.registerName);
	    ProjectDocument projectDocument = viewFactory.create(project);
	    projectDocument.setName("Alternativas");
	    project.addDocument(projectDocument);
	    view = (View) projectDocument.createWindow();
	    view.getWindowInfo().setMaximized(true);
	    PluginServices.getMDIManager().addWindow(view);
	}
	return view;
    }

    /**
     * If the current View has the layers we are going to load again with the
     * SQL restriction, we remove them
     */
    private static void cleanViewIfNeeded(View view) {
	if (FonsaguaFormFactory.getInstance().allAlternativasLayersLoaded()) {
	    Set<String> layers = FonsaguaFormFactory.getInstance()
		    .getAllAlternativasLayersNames();
	    FLayers layersGroup = view.getMapControl().getMapContext()
		    .getLayers();
	    for (String layer : layers) {
		layersGroup.removeLayer(layer);
	    }
	}
    }

    @Override
    public boolean isEnabled() {
	return DBSession.isActive();
    }

    @Override
    public boolean isVisible() {
	return true;
    }

    public static String getCode() {
	return code;
    }

}
