package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.iver.andami.PluginServices;

import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.AbstractIWindow;
import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;

@SuppressWarnings("serial")
public class SelectComunitiesForAlternativeDialog extends AbstractIWindow
	implements ActionListener {

    private static final int EDITABLE_COLUMN = 2;
    private JTable table;
    private JButton okButton;
    private JButton cancelButton;
    private DefaultTableModel model;
    private TableModel filteredModel;
    private String code;

    public SelectComunitiesForAlternativeDialog() {
	super(new BorderLayout());

	addTablePanel();
	addButtons();

    }

    private void addButtons() {
	okButton = new JButton("Aceptar");
	cancelButton = new JButton("Cancelar");
	okButton.addActionListener(this);
	cancelButton.addActionListener(this);
	add(okButton, BorderLayout.EAST);
	add(cancelButton, BorderLayout.WEST);
    }

    private void addTablePanel() {
	table = new JTable();
	JScrollPane scroll = new JScrollPane(table);
	add(scroll, BorderLayout.NORTH);
    }

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
    public void actionPerformed(ActionEvent e) {
	filteredModel = null;
	if (e.getSource() == okButton) {
	    try {
		filteredModel = removeRowsWithZero(model);
		DatabaseDirectAccessQueries.removeAndInsertModel(filteredModel,
			code);
	    } catch (SQLException e1) {
		filteredModel = null;
	    }
	}

	PluginServices.getMDIManager().closeWindow(this);
    }

    public TableModel removeRowsWithZero(DefaultTableModel org) {

	final DefaultTableModel copy = new DefaultTableModel();
	for (int column = 0; column < org.getColumnCount(); column++) {
	    copy.addColumn(org.getColumnName(column));
	}

	for (int row = 0; row < org.getRowCount(); row++) {
	    int editableColumnValue = getIntValueAt(row, EDITABLE_COLUMN);
	    int baseValue = getIntValueAt(row, EDITABLE_COLUMN - 1);

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

    public int getIntValueAt(int row, int column) {
	int intValue = 0;
	try {
	    Object value = model.getValueAt(row, column);
	    if (value instanceof String) {
		intValue = Integer.parseInt((String) value);
	    } else if (value instanceof Integer) {
		intValue = (Integer) value;
	    }
	} catch (NumberFormatException e) {
	    intValue = 0;
	}
	return intValue;
    }

    public TableModel getFilteredTableModel() {
	return filteredModel;
    }

}
