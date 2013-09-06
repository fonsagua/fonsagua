package es.udc.cartolab.gvsig.fonsagua;

import java.awt.geom.Rectangle2D;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.exceptions.expansionfile.ExpansionFileReadException;
import com.iver.cit.gvsig.fmap.MapContext;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.documents.ProjectDocument;
import com.iver.cit.gvsig.project.documents.ProjectDocumentFactory;
import com.iver.cit.gvsig.project.documents.view.ProjectViewFactory;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;

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
	    TOCLayerManager tocLayerManager = new TOCLayerManager();
	    tocLayerManager.removeAllLayers();
	    tocLayerManager.removeAllOverviewLayer();
	} else {
	    Project project = ((ProjectExtension) PluginServices
		    .getExtension(ProjectExtension.class)).getProject();
	    ProjectDocumentFactory viewFactory = Project
		    .getProjectDocumentFactory(ProjectViewFactory.registerName);
	    ProjectDocument projectDocument = viewFactory.create(project);
	    projectDocument.setName(viewName);
	    project.addDocument(projectDocument);
	    view = (View) projectDocument.createWindow();
	    view.getWindowInfo().setMaximized(true);
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
}
