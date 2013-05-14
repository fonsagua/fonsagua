package es.udc.cartolab.gvsig.fonsagua.forms.relationship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.JTableUtils;
import es.udc.cartolab.gvsig.navtable.dataacces.IController;

public class JTableRelationshipContextualMenu implements MouseListener {
    private static final int NO_ROW_SELECTED = -1;
    private static final int BUTTON_RIGHT = 3;

    private JTable table;
    private JMenuItem newMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem deleteMenuItem;
    private JPopupMenu popupMenu;

    private TableRelationship tableRelationship;
    private BasicAbstractForm dialog;
    private boolean dialogInitialized = false;

    public JTableRelationshipContextualMenu(
	    TableRelationship tableRelationship, BasicAbstractForm dialog) {
	this.dialog = dialog;
	this.tableRelationship = tableRelationship;
	initContextualMenu();
    }

    private void initContextualMenu() {
	popupMenu = new JPopupMenu();

	newMenuItem = new JMenuItem("Crear nuevo");
	newMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		new TableRelationshipForm(tableRelationship).addAction();
	    }
	});
	popupMenu.add(newMenuItem);

	openMenuItem = new JMenuItem("Abrir formulario");
	openMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		openDialog();
	    }
	});
	popupMenu.add(openMenuItem);

	deleteMenuItem = new JMenuItem("Borrar registro");
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
	    } else {
		deleteMenuItem.setEnabled(true);
	    }
	    popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
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
		.getValueAt(table.getSelectedRow(), 0).toString();
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
