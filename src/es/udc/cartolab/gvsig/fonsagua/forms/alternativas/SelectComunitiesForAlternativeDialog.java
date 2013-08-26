package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;

@SuppressWarnings("serial")
public class SelectComunitiesForAlternativeDialog extends
	SelectElementForAlternativeDialog {

    private static final int EDITABLE_COLUMN = 2;

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
		    EDITABLE_COLUMN);
	    double baseValue = getNumericValueAt(org, row, EDITABLE_COLUMN - 1);

	    if (editableColumnValue > 0 && editableColumnValue < baseValue) {
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
}
