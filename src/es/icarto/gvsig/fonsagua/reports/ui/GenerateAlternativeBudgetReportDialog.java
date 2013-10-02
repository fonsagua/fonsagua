package es.icarto.gvsig.fonsagua.reports.ui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.WindowInfo;

import es.udc.cartolab.gvsig.fonsagua.utils.FilteredDialog;
import es.udc.cartolab.gvsig.fonsagua.utils.SaveFileDialog;

@SuppressWarnings("serial")
public class GenerateAlternativeBudgetReportDialog extends FilteredDialog {

    public GenerateAlternativeBudgetReportDialog(
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes,
	    Map<String, String> departNames, Map<String, String> municNames,
	    Map<String, String> cantonNames) {
	super(divsCodes, departNames, municNames, cantonNames);
	setWindowTitle(PluginServices.getText(this,
		"generate_alternative_report_budget_title"));
	setWindowInfoProperties(WindowInfo.MODALDIALOG | WindowInfo.PALETTE
		| WindowInfo.NOTCLOSABLE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals(AcceptCancelPanel.OK_ACTION_COMMAND)) {
	    String alternativeCode = elementCombo.getSelectedItem().toString();
	    SaveFileDialog sfd = new SaveFileDialog(PluginServices.getText(
		    this, "csvFiles"), "csv");
	    File fileName = sfd.showDialog();
	    if (fileName != null) {
		new AlternativeBudgetCSVReport(fileName.getAbsolutePath(),
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
