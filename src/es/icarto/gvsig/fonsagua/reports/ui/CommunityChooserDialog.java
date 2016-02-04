package es.icarto.gvsig.fonsagua.reports.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;
import com.iver.utiles.swing.JComboBox;

import es.icarto.gvsig.commons.gui.AbstractIWindow;
import es.icarto.gvsig.commons.gui.OkCancelPanel;
import es.icarto.gvsig.commons.gui.WidgetFactory;
import es.icarto.gvsig.fonsagua.reports.CommunityRTFReport;
import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;
import es.udc.cartolab.gvsig.fonsagua.utils.SaveFileDialog;

@SuppressWarnings("serial")
public class CommunityChooserDialog extends AbstractIWindow implements
	ActionListener {

    private JComboBox cb;
    private final OkCancelPanel okPanel;

    public CommunityChooserDialog() {
	super();
	setWindowTitle(PluginServices.getText(this, "choose_community"));
	addCommunityCB();
	okPanel = WidgetFactory.okCancelPanel(this, this, this);
    }

    private void addCommunityCB() {
	String[] communitiesCodes = DatabaseDirectAccessQueries
		.getCommunitiesCodes();
	cb = new JComboBox(communitiesCodes);
	add(new JLabel("Código de comunidad: "));
	add(cb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	if (e.getActionCommand().equals(OkCancelPanel.OK_ACTION_COMMAND)) {
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

    @Override
    protected JButton getDefaultButton() {
	return okPanel.getOkButton();
    }

    @Override
    protected Component getDefaultFocusComponent() {
	return null;
    }

}
