package es.udc.cartolab.gvsig.fonsagua;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;

import es.icarto.gvsig.fonsagua.reports.ui.CommunityChooserDialog;
import es.udc.cartolab.gvsig.epanet.exceptions.ExternalError;

public class GenerateCommunityReportExtension extends AbstractExtension {

    private static Logger logger = Logger
	    .getLogger(GenerateCommunityReportExtension.class);

    @Override
    public void initialize() {
	id = "generate_community_report";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	try {
	    CommunityChooserDialog dialog = new CommunityChooserDialog();
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (ExternalError e) {
	    logger.error(e);
	    NotificationManager.addWarning(PluginServices.getText(this,
		    "error_opening_dialog"));
	}

    }

}
