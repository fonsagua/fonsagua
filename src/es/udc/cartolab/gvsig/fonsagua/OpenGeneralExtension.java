package es.udc.cartolab.gvsig.fonsagua;

import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class OpenGeneralExtension extends OpenAbstractExtension {

    @Override
    public void initialize() {
	id = "open_all_alternatives";
	OpenAlternativeExtension.setValidAlternative(false);
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	MapDAO mapDAO = MapDAO.getInstance();
	try {
	    final View view = createViewIfNeeded("Todas las alternativas");
	    ELLEMap map = mapDAO.getMap(view, FonsaguaConstants.GeneralMap,
		    LoadLegend.DB_LEGEND, FonsaguaConstants.GeneralMap);
	    map.load(view.getProjection());
	    zoomToLayer(view, ComunidadesForm.NAME);
	} catch (Exception e) {
	    // TODO: catch error
	    e.printStackTrace();
	}
    }

}
