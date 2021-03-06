package es.udc.cartolab.gvsig.fonsagua;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class OpenAllAlternativesExtension extends OpenAbstractExtension {

    private static Logger logger = Logger
	    .getLogger(OpenAllAlternativesExtension.class);

    @Override
    public void initialize() {
	id = "open_all_alternatives";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	PluginServices.getMDIManager().setWaitCursor();
	try {
	    final View view = createViewIfNeeded("Mapa de Alternativas");
	    loadMap(view, FonsaguaConstants.BaseMap);
	    loadMap(view, FonsaguaConstants.GeneralMap);
	    removeGroupAlternative(view);
	    loadGroupAlternative(view, "");
	    zoomToLayer(view, FonsaguaConstants.departamentosTable);
	    OpenAlternativeExtension.setCode((String) null);
	    OpenAlternativeExtension.setValidAlternative(false);
	} catch (Exception e) {
	    NotificationManager.addError(
		    "Error desconocido cargando las capas", e);
	    logger.error(e);
	} finally {
	    PluginServices.getMDIManager().restoreCursor();
	}
    }

}
