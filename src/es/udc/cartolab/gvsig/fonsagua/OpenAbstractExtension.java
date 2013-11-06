package es.udc.cartolab.gvsig.fonsagua;

import java.awt.geom.Rectangle2D;
import java.sql.SQLException;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.exceptions.expansionfile.ExpansionFileReadException;
import com.iver.cit.gvsig.fmap.MapContext;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.documents.ProjectDocument;
import com.iver.cit.gvsig.project.documents.ProjectDocumentFactory;
import com.iver.cit.gvsig.project.documents.view.ProjectViewFactory;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public abstract class OpenAbstractExtension extends AbstractExtension {

    /**
     * If the active window is a View returns it, if not creates a new one, adds
     * it to the project and returns it
     * 
     */
    protected static View createViewIfNeeded(String viewName) {

	// TODO: fpuga: Check what happens when exists a view in the project but
	// is not active
	IWindow iWindow = PluginServices.getMDIManager().getActiveWindow();
	View view = null;

	if (iWindow instanceof View) {
	    view = (View) iWindow;
	    view.setName(viewName);
	    view.getWindowInfo().setTitle(viewName);
	} else {
	    Project project = ((ProjectExtension) PluginServices
		    .getExtension(ProjectExtension.class)).getProject();
	    ProjectDocumentFactory viewFactory = Project
		    .getProjectDocumentFactory(ProjectViewFactory.registerName);
	    ProjectDocument projectDocument = viewFactory.create(project);
	    projectDocument.setName(viewName);
	    project.addDocument(projectDocument);
	    view = (View) projectDocument.createWindow();
	    view.setName(viewName);
	    view.getWindowInfo().setMaximized(true);
	    view.getWindowInfo().setTitle(viewName);
	    PluginServices.getMDIManager().addWindow(view);
	}
	return view;
    }

    protected static void zoomToLayer(final View view, final String layername) {
	try {
	    MapContext mapContext = view.getMapControl().getMapContext();
	    Rectangle2D fullExtent = mapContext.getLayers().getLayer(layername)
		    .getFullExtent();
	    mapContext.getViewPort().setExtent(fullExtent);
	} catch (ExpansionFileReadException e) {
	    e.printStackTrace();
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}
    }

    protected static void removeGroupAlternative(final View view) {
	FLayers layers = view.getMapControl().getMapContext().getLayers();
	FLayer alternativas = layers
		.getLayer(FonsaguaConstants.AlternativeGroup);
	if (alternativas != null) {
	    layers.removeLayer(alternativas);
	}
    }

    protected static void loadGroupAlternative(View view, String sqlWhere)
	    throws Exception {
	MapDAO mapDAO = MapDAO.getInstance();
	ELLEMap alternativasELLEMap = mapDAO.getMap(view,
		FonsaguaConstants.AlternativesMap, LoadLegend.DB_LEGEND,
		FonsaguaConstants.AlternativesMap);
	alternativasELLEMap.setWhereOnAllLayers(sqlWhere);
	alternativasELLEMap.load(view.getProjection());
    }

    protected static void loadMap(final View view, final String mapName)
	    throws SQLException, Exception {
	MapDAO mapDAO = MapDAO.getInstance();
	if (!mapDAO.isMapLoaded(view, mapName)) {
	    ELLEMap base = mapDAO.getMap(view, mapName, LoadLegend.DB_LEGEND,
		    mapName);
	    base.load(view.getProjection());
	}
    }
}
