package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;
import es.udc.cartolab.gvsig.navtable.dataacces.IController;
import es.udc.cartolab.gvsig.users.utils.DBSession;

@SuppressWarnings("serial")
public class SelectFuentesForAlternativeDialog extends
	SelectElementForAlternativeDialog {

    public SelectFuentesForAlternativeDialog(int editableColumnIdx) {
	super(editableColumnIdx);
    }

    @Override
    public void update(String code) {
	this.code = code;
	try {
	    model = DatabaseDirectAccessQueries
		    .getFuentesIntersectingAlternative(code);
	    table.setModel(model);
	} catch (SQLException e) {
	    try {
		DBSession.reconnect();
	    } catch (DBException e1) {
	    }
	    e.printStackTrace();
	}
	table.getColumnModel().getColumn(2)
		.setCellRenderer(new DecimalFormatRenderer());
	table.getColumnModel().getColumn(3)
		.setCellRenderer(new DecimalFormatRenderer());
	table.getColumnModel().getColumn(4)
		.setCellRenderer(new DecimalFormatRenderer());
	table.getColumnModel().getColumn(4)
		.setCellEditor(new DecimalCellEditor());
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

	    if (editableColumnValue > 0) {
		String tipo_fuente = (String) org.getValueAt(row, 1);
		if (tipo_fuente.equals("Punto rio")
			|| tipo_fuente.equals("Manantial")) {
		    if (editableColumnValue <= baseValue) {
			Object rowData = org.getDataVector().get(row);
			copy.addRow((Vector) rowData);
		    }
		} else {
		    Object rowData = org.getDataVector().get(row);
		    copy.addRow((Vector) rowData);
		}
	    }
	}

	return copy;
    }

    @Override
    public void removeAndInsertModel(TableModel filteredModel, String code)
	    throws SQLException {
	DatabaseDirectAccessQueries.removeAndInsertModelFuentes(filteredModel,
		code);

    }

    @Override
    public void setAutomaticValue(IController layerController,
	    HashMap<String, JComponent> widgets) {
	final String fieldName = "caudal_fuentes";
	String caudalFuentes = Double.toString(getSumEditableColumnValue());
	layerController.setValue(fieldName, caudalFuentes);
	((JTextField) widgets.get(fieldName)).setText(caudalFuentes);
    }

}
