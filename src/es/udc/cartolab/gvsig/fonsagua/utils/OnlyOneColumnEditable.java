package es.udc.cartolab.gvsig.fonsagua.utils;

import javax.swing.table.DefaultTableModel;

/**
 * OnlyOneColumnEditable is used to made just one column of the JTables editable
 * and the rest non editables.
 * 
 * @author Francisco Puga <fpuga@cartolab.es>
 * 
 */
@SuppressWarnings("serial")
public class OnlyOneColumnEditable extends DefaultTableModel {

    // Indicates which column of the table is editable
    private int editableColumn;

    public OnlyOneColumnEditable(int editableColumn) {
	super();
	this.editableColumn = editableColumn;
    }

    public void setEditableColumn(int editableColumn) {
	this.editableColumn = editableColumn;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
	if (col == this.editableColumn) {
	    return true;
	}
	return false;
    }
}
