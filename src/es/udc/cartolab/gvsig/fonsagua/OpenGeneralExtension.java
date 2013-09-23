package es.udc.cartolab.gvsig.fonsagua;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class OpenGeneralExtension extends OpenAbstractExtension {

    private static Logger logger = Logger.getLogger(OpenGeneralExtension.class);

    @Override
    public void initialize() {
	id = "open_all_alternatives";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	PluginServices.getMDIManager().setWaitCursor();
	MapDAO mapDAO = MapDAO.getInstance();
	try {
	    final View view = createViewIfNeeded(FonsaguaConstants.GeneralMap);
	    ELLEMap map = mapDAO.getMap(view, FonsaguaConstants.GeneralMap,
		    LoadLegend.DB_LEGEND, FonsaguaConstants.GeneralMap);

	    map.load(view.getProjection());
	    zoomToLayer(view, ComunidadesForm.NAME);
	    OpenAlternativeExtension.setValidAlternative(false);
	    OpenAlternativeExtension.setCode((String) null);
	} catch (Exception e) {
	    NotificationManager.addError(
		    "Error desconocido cargando las capas", e);
	    logger.error(e);
	} finally {
	    PluginServices.getMDIManager().restoreCursor();
	}
    }
}
