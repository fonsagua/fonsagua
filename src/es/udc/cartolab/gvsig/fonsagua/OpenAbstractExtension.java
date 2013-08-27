package es.udc.cartolab.gvsig.fonsagua;

import java.util.Set;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.project.Project;
import com.iver.cit.gvsig.project.documents.ProjectDocument;
import com.iver.cit.gvsig.project.documents.ProjectDocumentFactory;
import com.iver.cit.gvsig.project.documents.view.ProjectViewFactory;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public abstract class OpenAbstractExtension extends Extension {

    /**
     * icon identifier in config, filename on disk (withou extension)
     */
    protected String id;

    /**
     * Register the file images/[iconName].png with the name [iconName] in the
     * icon theme
     * 
     */
    protected void registerIcon(String iconName) {
	PluginServices.getIconTheme().registerDefault(
		iconName,
		this.getClass().getClassLoader()
			.getResource("images/" + iconName + ".png"));
    }

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
	    cleanView(view);
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

    /**
     * If the current View has the layers we are going to load again with the
     * SQL restriction, we remove them
     */
    private static void cleanView(View view) {
	FLayers layersGroup = view.getMapControl().getMapContext().getLayers();
	layersGroup.setAllVisibles(false);
	removeLayers(layersGroup);

	if (FonsaguaFormFactory.getInstance().allAlternativasLayersLoaded()) {
	    Set<String> layers = FonsaguaFormFactory.getInstance()
		    .getAllAlternativasLayersNames();

	    for (String layer : layers) {
		layersGroup.removeLayer(layer);
	    }
	}
    }

    private static void removeLayers(FLayers layersGroup) {
	for (int i = 0; i < layersGroup.getLayersCount(); i++) {
	    FLayer layer = layersGroup.getLayer(i);
	    if (layer instanceof FLayers) {
		removeLayers((FLayers) layer);
	    } else {
		layersGroup.removeLayer(i);
	    }
	}
    }

    @Override
    public void initialize() {
	registerIcon(id);
    }

    @Override
    public boolean isEnabled() {
	return DBSession.isActive();
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
