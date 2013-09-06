package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;
import es.udc.cartolab.gvsig.navtable.dataacces.IController;

@SuppressWarnings("serial")
public class SelectComunitiesForAlternativeDialog extends
	SelectElementForAlternativeDialog {

    public SelectComunitiesForAlternativeDialog(int editableColumnIdx) {
	super(editableColumnIdx);
    }

    @Override
    public void update(String code) {
	this.code = code;
	try {
	    model = DatabaseDirectAccessQueries
		    .getComunitiesIntersectingAlternative(code);
	    table.setModel(model);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public DefaultTableModel removeRowsWithZero(DefaultTableModel org) {

	final DefaultTableModel copy = new DefaultTableModel();
	for (int column = 0; column < org.getColumnCount(); column++) {
	    copy.addColumn(org.getColumnName(column));
	}

	for (int row = 0; row < org.getRowCount(); row++) {
	    double editableColumnValue = getNumericValueAt(org, row,
		    getEditableColumnIdx());
	    double baseValue = getNumericValueAt(org, row,
		    getEditableColumnIdx() - 1);

	    if (editableColumnValue > 0 && editableColumnValue <= baseValue) {
		Object rowData = org.getDataVector().get(row);
		copy.addRow((Vector) rowData);
	    }
	}

	if (copy.getRowCount() == 0) {
	    copy.addRow(new Vector<Object>());
	}
	return copy;
    }

    @Override
    public void removeAndInsertModel(TableModel filteredModel, String code)
	    throws SQLException {
	DatabaseDirectAccessQueries.removeAndInsertModelComunidades(
		filteredModel, code);
    }

    @Override
    public void setAutomaticValue(IController layerController,
	    HashMap<String, JComponent> widgets) {
	final String fieldName = "pobl_actual";
	String poblActual = Integer.toString((int) getSumEditableColumnValue());
	layerController.setValue(fieldName, poblActual);
	((JTextField) widgets.get(fieldName)).setText(poblActual);
    }
}