package es.udc.cartolab.gvsig.fonsagua;

import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class OpenAllAlternativesExtension extends OpenAbstractExtension {

    @Override
    public void initialize() {
	id = "open_general";
	OpenAlternativeExtension.setValidAlternative(false);
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	MapDAO mapDAO = MapDAO.getInstance();
	try {
	    final View view = createViewIfNeeded("Vista General");
	    ELLEMap map = mapDAO.getMap(view,
		    FonsaguaConstants.AlternativesMap, LoadLegend.DB_LEGEND,
		    FonsaguaConstants.AlternativesMap);
	    map.load(view.getProjection());
	    zoomToLayer(view, AlternativasForm.NAME);
	} catch (Exception e) {
	    // TODO: catch error
	    e.printStackTrace();
	}
    }

}
