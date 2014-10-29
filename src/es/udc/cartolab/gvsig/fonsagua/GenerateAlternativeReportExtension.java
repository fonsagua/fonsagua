package es.udc.cartolab.gvsig.fonsagua;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.fonsagua.reports.ui.GenerateAlternativeReportDialog;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFilterFields;

public class GenerateAlternativeReportExtension extends AbstractExtension {

    @Override
    public void initialize() {
	id = "generate_alternative_report";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	try {
	    Map<String, String> departNames = FonsaguaFilterFields
		    .getDepartments();
	    Map<String, String> municNames = FonsaguaFilterFields
		    .getMunicipalities();
	    Map<String, String> cantonNames = FonsaguaFilterFields
		    .getCantones();
	    String[][] alternativas = FonsaguaFilterFields.getAlternatives();
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes = FonsaguaFilterFields
		    .getDivCodes(alternativas);
	    GenerateAlternativeReportDialog dialog = new GenerateAlternativeReportDialog(
		    divsCodes, departNames, municNames, cantonNames);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
