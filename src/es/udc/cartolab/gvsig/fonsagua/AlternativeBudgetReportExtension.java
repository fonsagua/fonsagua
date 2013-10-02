package es.udc.cartolab.gvsig.fonsagua;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;

import es.icarto.gvsig.fonsagua.reports.ui.GenerateAlternativeBudgetReportDialog;
import es.udc.cartolab.gvsig.epanet.exceptions.ExternalError;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFilterFields;

public class AlternativeBudgetReportExtension extends AbstractExtension {

    private static final Logger logger = Logger
	    .getLogger(AlternativeBudgetReportExtension.class);

    @Override
    public void initialize() {
	id = "alternative_budget_report";
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
	    String[][] alternativas = FonsaguaFilterFields.getAlternatives();
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes = FonsaguaFilterFields
		    .getDivCodes(alternativas);
	    GenerateAlternativeBudgetReportDialog dialog = new GenerateAlternativeBudgetReportDialog(
		    divsCodes, departNames, municNames, cantonNames);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (SQLException e) {
	    logger.error("Error", e);
	    NotificationManager
		    .addWarning("Error accediendo a la base de datos");
	} catch (ExternalError ee) {
	    logger.error("Error", ee);
	    NotificationManager.addWarning("Error desconocido");
	}
    }

    @Override
    public boolean isEnabled() {
	return OpenAlternativeExtension.getCode() != null;
    }

}
