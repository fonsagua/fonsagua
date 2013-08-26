package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;
import es.udc.cartolab.gvsig.users.utils.DBSession;

@SuppressWarnings("serial")
public class SelectFuentesForAlternativeDialog extends
	SelectElementForAlternativeDialog {

    private static final int EDITABLE_COLUMN = 4;

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

	    if (editableColumnValue > 0) {
		String tipo_fuente = (String) org.getValueAt(row, 1);
		if (tipo_fuente.equals("Punto rio")
			|| tipo_fuente.equals("Manantial")) {
		    if (editableColumnValue < baseValue) {
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
}
