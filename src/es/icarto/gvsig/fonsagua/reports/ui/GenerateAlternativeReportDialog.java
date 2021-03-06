package es.icarto.gvsig.fonsagua.reports.ui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.WindowInfo;

import es.icarto.gvsig.commons.gui.OkCancelPanel;
import es.icarto.gvsig.commons.gui.SaveFileDialog;
import es.icarto.gvsig.fonsagua.reports.AlternativeRTFReport;
import es.udc.cartolab.gvsig.fonsagua.utils.FilteredDialog;

@SuppressWarnings("serial")
public class GenerateAlternativeReportDialog extends FilteredDialog {

    public GenerateAlternativeReportDialog(
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes,
	    Map<String, String> departNames, Map<String, String> municNames,
	    Map<String, String> cantonNames) {
	super(divsCodes, departNames, municNames, cantonNames);
	setWindowTitle(PluginServices.getText(this,
		"generate_alternative_report_title"));
	setWindowInfoProperties(WindowInfo.MODALDIALOG | WindowInfo.PALETTE
		| WindowInfo.NOTCLOSABLE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals(OkCancelPanel.OK_ACTION_COMMAND)) {
	    String alternativeCode = elementCombo.getSelectedItem().toString();
	    SaveFileDialog sfd = new SaveFileDialog(PluginServices.getText(
		    this, "rtfFiles"), "rtf");
	    sfd.setAskForOverwrite(true);
	    File fileName = sfd.showDialog();
	    if (fileName != null) {
		new AlternativeRTFReport(fileName.getAbsolutePath(),
			alternativeCode);
		PluginServices.getMDIManager().closeWindow(this);
		JOptionPane.showMessageDialog(null,
			PluginServices.getText(this, "generated_report_msg"));
	    }
	}
	PluginServices.getMDIManager().closeWindow(this);
    }

    @Override
    protected String getElementLabel() {
	return "Cod_alternativa";
    }

}
