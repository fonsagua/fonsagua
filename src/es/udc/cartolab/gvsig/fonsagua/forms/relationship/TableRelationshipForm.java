package es.udc.cartolab.gvsig.fonsagua.forms.relationship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

@SuppressWarnings("serial")
public class TableRelationshipForm extends JPanel implements IWindow,
	ActionListener {

    private WindowInfo viewInfo;

    private TableRelationship tableRelationship;

    private FormPanel formPanel;
    private JComboBox secondaryPKValueCB;
    private JButton addButton;

    public TableRelationshipForm(TableRelationship tableRelationship) {
	this.tableRelationship = tableRelationship;
	viewInfo = this.getWindowInfo();
	createForm();
    }

    private void createForm() {
	InputStream stream = getClass().getClassLoader().getResourceAsStream(
		"ui/r_add_subform.xml");
	try {
	    formPanel = new FormPanel(stream);
	    this.add(formPanel);
	} catch (FormException e) {
	    e.printStackTrace();
	}
	secondaryPKValueCB = (JComboBox) formPanel
		.getComponentByName("secondaryPKValueCB");
	addButton = (JButton) formPanel.getComponentByName("addButton");
	for (String value : tableRelationship.getSecondaryTableValues()) {
	    secondaryPKValueCB.addItem(value);
	}
	addButton.addActionListener(this);
    }

    public void open() {
	PluginServices.getMDIManager().addCentredWindow(this);
    }

    public void addAction() {
	open();
    }

    public void deleteAction() {
	int row = tableRelationship.getRelationJTable().getSelectedRow();
	DefaultTableModel tableModel = (DefaultTableModel) tableRelationship
		.getRelationJTable().getModel();
	String secondaryPKValue = (String) tableModel.getValueAt(row, 0);
	tableModel.removeRow(row);
	tableRelationship.deleteRow(secondaryPKValue);
    }

    @Override
    public WindowInfo getWindowInfo() {
	if (viewInfo == null) {
	    viewInfo = new WindowInfo(WindowInfo.MODELESSDIALOG
		    | WindowInfo.RESIZABLE | WindowInfo.PALETTE);
	}
	viewInfo.setHeight(75);
	viewInfo.setWidth(200);
	return viewInfo;
    }

    @Override
    public Object getWindowProfile() {
	return null;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
	if (event.getSource() == addButton) {
	    tableRelationship.insertRow(secondaryPKValueCB.getSelectedItem()
		    .toString());
	    DefaultTableModel tableModel = (DefaultTableModel) tableRelationship
		    .getRelationJTable().getModel();
	    Vector<String> newRow = new Vector<String>();
	    newRow.add(secondaryPKValueCB.getSelectedItem().toString());
	    tableModel.addRow(newRow);
	}
    }
}
