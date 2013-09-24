package es.icarto.gvsig.fonsagua.reports.ui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.fonsagua.reports.CommunityRTFReport;
import es.udc.cartolab.gvsig.fonsagua.utils.FilteredDialog;
import es.udc.cartolab.gvsig.fonsagua.utils.SaveFileDialog;

@SuppressWarnings("serial")
public class GenerateCommunityReportDialog extends FilteredDialog {

    public GenerateCommunityReportDialog(
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes,
	    Map<String, String> departNames, Map<String, String> municNames,
	    Map<String, String> cantonNames) {
	super(divsCodes, departNames, municNames, cantonNames);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == okButton) {
	    String communityCode = elementCombo.getSelectedItem().toString();
	    SaveFileDialog sfd = new SaveFileDialog(PluginServices.getText(
		    this, "rtfFiles"), "rtf");
	    File fileName = sfd.showDialog();
	    if (fileName != null) {
		new CommunityRTFReport(fileName.getAbsolutePath(),
			communityCode);
		PluginServices.getMDIManager().closeWindow(this);
		JOptionPane.showMessageDialog(null,
			PluginServices.getText(this, "generated_report_msg"));
	    }
	}
	PluginServices.getMDIManager().closeWindow(this);
    }

    @Override
    protected Logger getLogger() {
	return Logger.getLogger("GenerateCommunityReportExtension");
    }

    @Override
    protected String getElementLabel() {
	return "Cod_community";
    }

    @Override
    protected String getDialogTitle() {
	return "generate_community_report_title";
    }

}