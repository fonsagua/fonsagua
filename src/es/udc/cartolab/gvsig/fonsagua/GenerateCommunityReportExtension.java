package es.udc.cartolab.gvsig.fonsagua;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.fonsagua.reports.ui.GenerateCommunityReportDialog;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFilterFields;

public class GenerateCommunityReportExtension extends AbstractExtension {

    @Override
    public void initialize() {
	id = "generate_community_report";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	try {
	    Map<String, String> departNames = FonsaguaFilterFields
		    .getDepartments();
	    Map<String, String> municNames = FonsaguaFilterFields
		    .getMuniccipalities();
	    Map<String, String> cantonNames = FonsaguaFilterFields
		    .getCantones();
	    String[][] communities = FonsaguaFilterFields.getComunidades();
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes = FonsaguaFilterFields
		    .getDivCodes(communities);
	    GenerateCommunityReportDialog dialog = new GenerateCommunityReportDialog(
		    divsCodes, departNames, municNames, cantonNames);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

}
