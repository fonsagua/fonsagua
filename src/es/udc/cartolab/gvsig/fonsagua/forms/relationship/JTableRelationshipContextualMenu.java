package es.udc.cartolab.gvsig.fonsagua.forms.relationship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import es.icarto.gvsig.navtableforms.gui.tables.JTableUtils;

public class JTableRelationshipContextualMenu implements MouseListener {
    private static final int NO_ROW_SELECTED = -1;
    private static final int BUTTON_RIGHT = 3;

    private JTable table;
    private JMenuItem openMenuItem;
    private JMenuItem deleteMenuItem;
    private JPopupMenu popupMenu;

    private TableRelationship tableRelationship;

    public JTableRelationshipContextualMenu(TableRelationship tableRelationship) {
	this.tableRelationship = tableRelationship;
	initContextualMenu();
    }

    private void initContextualMenu() {
	popupMenu = new JPopupMenu();

	openMenuItem = new JMenuItem("Crear nuevo");
	openMenuItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		new TableRelationshipForm(tableRelationship).addAction();
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
	if (e.getButton() == BUTTON_RIGHT) {
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

}
