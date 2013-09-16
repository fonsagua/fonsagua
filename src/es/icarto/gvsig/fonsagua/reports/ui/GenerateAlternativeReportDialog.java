package es.icarto.gvsig.fonsagua.reports.ui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.fonsagua.reports.CommunityRTFReport;
import es.udc.cartolab.gvsig.fonsagua.utils.FilteredDialog;
import es.udc.cartolab.gvsig.fonsagua.utils.SaveFileDialog;

@SuppressWarnings("serial")
public class GenerateAlternativeReportDialog extends FilteredDialog {

    public GenerateAlternativeReportDialog(
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes,
	    Map<String, String> departNames, Map<String, String> municNames,
	    Map<String, String> cantonNames) {
	super(divsCodes, departNames, municNames, cantonNames);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	String alternativeCode = elementCombo.getSelectedItem().toString();
	SaveFileDialog sfd = new SaveFileDialog(PluginServices.getText(this,
		"rtfFiles"), "rtf");
	File fileName = sfd.showDialog();
	new CommunityRTFReport(fileName.getAbsolutePath(), alternativeCode);
    }

    @Override
    protected Logger getLogger() {
	return Logger.getLogger("GenerateAlternativeReportExtension");
    }

    @Override
    protected String getElementLabel() {
	return "Cod_alternativa";
    }

    @Override
    protected String getDialogTitle() {
	return "generate_alternative_report_title";
    }

}
