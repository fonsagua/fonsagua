package es.udc.cartolab.gvsig.fonsagua;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;

import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.SelectCommunityForAltPriorDialog;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.utils.EnabilityConditions;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class AlternativePriorExtension extends Extension {

    @Override
    public void initialize() {
    }

    @Override
    public void execute(String actionCommand) {
	try {
	    String[] fields = { ComunidadesForm.NAMEFIELD,
		    ComunidadesForm.PKFIELD };
	    String[][] rows = DBSession.getCurrentSession().getTable(
		    FonsaguaConstants.COMUNIDADES_IMPLICADAS,
		    FonsaguaConstants.dataSchema, fields, "", fields, false);
	    Map<String, String> comunidades = new HashMap<String, String>();
	    for (String[] row : rows) {
		comunidades.put(row[1], row[0]);
	    }
	    SelectCommunityForAltPriorDialog dialog = new SelectCommunityForAltPriorDialog(
		    comunidades);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public boolean isEnabled() {
	return EnabilityConditions.isAllAlternativesMode();
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
