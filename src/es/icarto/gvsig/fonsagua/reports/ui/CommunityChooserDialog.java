package es.icarto.gvsig.fonsagua.reports.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;
import com.iver.utiles.swing.JComboBox;

import es.icarto.gvsig.fonsagua.reports.CommunityRTFReport;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.AbstractIWindow;
import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;
import es.udc.cartolab.gvsig.fonsagua.utils.SaveFileDialog;

@SuppressWarnings("serial")
public class CommunityChooserDialog extends AbstractIWindow implements
	ActionListener {

    private JComboBox cb;

    public CommunityChooserDialog() {
	super();
	setWindowTitle(PluginServices.getText(this, "choose_community"));
	addCommunityCB();
	addAcceptCancelPanel(this, this);
    }

    private void addCommunityCB() {
	String[] communitiesCodes = DatabaseDirectAccessQueries
		.getCommunitiesCodes();
	cb = new JComboBox(communitiesCodes);
	add(new JLabel("C�digo de comunidad: "));
	add(cb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	if (e.getActionCommand().equals(AcceptCancelPanel.OK_ACTION_COMMAND)) {
	    String communityCode = cb.getSelectedItem().toString();
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

}