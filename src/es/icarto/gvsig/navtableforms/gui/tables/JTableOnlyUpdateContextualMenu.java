package es.icarto.gvsig.navtableforms.gui.tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class JTableOnlyUpdateContextualMenu implements MouseListener {

    private static final int NO_ROW_SELECTED = -1;
    private static final int BUTTON_RIGHT = 3;

    private JTable table;
    private IForm form;
    private JMenuItem updateMenuItem;
    private JPopupMenu popupMenu;

    /**
     * When attaching this listener to your table, you should care if it fills
     * the whole space of its viewport or on empty tables it won't show up.
     * 
     * So, for this to work on empty tables, you should make:
     * yourTable.setFillsViewportHeight(true);
     * 
     * More info:
     * http://docs.oracle.com/javase/6/docs/api/javax/swing/JTable.html
     * #setFillsViewportHeight(boolean)
     * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4310721
     */
    public JTableOnlyUpdateContextualMenu(IForm form) {
	this.form = form;
	initContextualMenu();
    }

    public void mouseClicked(MouseEvent e) {
	// Make sure the JTable fills the parent viewport for this listener to
	// work on empty tables (see comment on constructor):
	//
	// yourTable.setFillsViewportHeight(true);
	//
	table = (JTable) e.getComponent();
	if ((e.getClickCount() == 2) && (table.getSelectedRow() > -1)) {
	    int rowIndex;
	    TableModel model = table.getModel();
	    if (model instanceof TableModelAlphanumeric) {
		rowIndex = ((TableModelAlphanumeric) model)
			.convertRowIndexToModel(table.getSelectedRow());
	    } else {
		rowIndex = table.convertRowIndexToModel(table.getSelectedRow());
	    }
	    form.actionUpdateRecord(rowIndex);
	} else if (e.getButton() == BUTTON_RIGHT) {
	    if (!JTableUtils.hasRows(table)
		    || (table.getSelectedRow() == NO_ROW_SELECTED)) {
		updateMenuItem.setEnabled(false);
	    } else {
		updateMenuItem.setEnabled(true);
	    }
	    popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
    }

    private void initContextualMenu() {
	popupMenu = new JPopupMenu();

	updateMenuItem = new JMenuItem("Actualizar registro");
	updateMenuItem.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		form.actionUpdateRecord(((TableModelAlphanumeric) table
			.getModel()).convertRowIndexToModel(table
			.getSelectedRow()));
	    }
	});
	popupMenu.add(updateMenuItem);
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

}
