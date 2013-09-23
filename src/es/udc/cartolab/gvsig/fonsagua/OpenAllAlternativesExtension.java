package es.udc.cartolab.gvsig.fonsagua;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class OpenAllAlternativesExtension extends OpenAbstractExtension {

    private static Logger logger = Logger
	    .getLogger(OpenAllAlternativesExtension.class);

    @Override
    public void initialize() {
	id = "open_general";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	PluginServices.getMDIManager().setWaitCursor();
	MapDAO mapDAO = MapDAO.getInstance();
	try {
	    final View view = createViewIfNeeded("Vista General");
	    ELLEMap map = mapDAO.getMap(view,
		    FonsaguaConstants.AlternativesMap, LoadLegend.DB_LEGEND,
		    FonsaguaConstants.AlternativesMap);
	    map.load(view.getProjection());
	    zoomToLayer(view, AlternativasForm.NAME);
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
