package es.icarto.gvsig.navtableforms.gui.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableContextualMenu;
import es.udc.cartolab.gvsig.navtable.dataacces.IController;

public class JTableRelationshipContextualMenu extends JTableContextualMenu {

    protected int keyColumn = 0;
    protected TableRelationship tableRelationship;
    protected BasicAbstractForm dialog;
    protected boolean dialogInitialized = false;

    public JTableRelationshipContextualMenu(
	    TableRelationship tableRelationship, BasicAbstractForm dialog) {
	newMenuItem
		.setText(PluginServices.getText(this, "create_new_relation"));
	updateMenuItem.setText(PluginServices.getText(this,
		"update_item_relation"));
	deleteMenuItem.setText(PluginServices.getText(this,
		"delete_item_relation"));
	this.dialog = dialog;
	this.tableRelationship = tableRelationship;
	initContextualMenu();
    }

    public JTableRelationshipContextualMenu(
	    TableRelationship tableRelationship, BasicAbstractForm dialog,
	    int keyColumn) {
	newMenuItem
		.setText(PluginServices.getText(this, "create_new_relation"));
	updateMenuItem.setText(PluginServices.getText(this,
		"update_item_relation"));
	deleteMenuItem.setText(PluginServices.getText(this,
		"delete_item_relation"));
	this.dialog = dialog;
	this.keyColumn = keyColumn;
	this.tableRelationship = tableRelationship;
	initContextualMenu();
    }

    protected void initContextualMenu() {
	newMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		new TableRelationshipForm(tableRelationship).addAction();
	    }
	});
	popupMenu.add(newMenuItem);

	updateMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		openDialog();
	    }
	});
	popupMenu.add(updateMenuItem);

	deleteMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		new TableRelationshipForm(tableRelationship).deleteAction();
	    }
	});
	popupMenu.add(deleteMenuItem);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	table = (JTable) e.getComponent();
	if ((e.getClickCount() == 2) && (table.getSelectedRow() > -1)) {
	    openDialog();
	} else if (e.getButton() == BUTTON_RIGHT) {
	    if (!JTableUtils.hasRows(table)
		    || (table.getSelectedRow() == NO_ROW_SELECTED)) {
		deleteMenuItem.setEnabled(false);
		updateMenuItem.setEnabled(false);
	    } else {
		deleteMenuItem.setEnabled(true);
		updateMenuItem.setEnabled(true);
	    }
	    popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
    }

    private void openDialog() {
	if (!dialogInitialized) {
	    if ((dialog != null) && (dialog.init())) {
		dialogInitialized = true;
		readSelectedPosition();
		PluginServices.getMDIManager().addWindow(dialog);
	    }
	} else {
	    readSelectedPosition();
	    PluginServices.getMDIManager().addWindow(dialog);
	}
    }

    private void readSelectedPosition() {
	IController controller = dialog.getFormController();
	String keySelected = table.getModel()
		.getValueAt(
			table.convertRowIndexToModel(table.getSelectedRow()),
			keyColumn)
		.toString();
	try {
	    for (long i = controller.getRowCount() - 1; i >= 0; i--) {
		controller.read(i);
		if (controller.getValue(tableRelationship.getSecondaryPKName())
			.equals(keySelected)) {
		    dialog.setPosition(i);
		    break;
		}
	    }
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}
    }

}
