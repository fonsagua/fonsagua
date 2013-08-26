package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.iver.andami.PluginServices;

import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.AbstractIWindow;

@SuppressWarnings("serial")
public abstract class SelectElementForAlternativeDialog extends AbstractIWindow
	implements ActionListener {

    protected JTable table;
    private JButton okButton;
    private JButton cancelButton;
    protected DefaultTableModel model;
    private DefaultTableModel filteredModel;
    protected String code;

    public SelectElementForAlternativeDialog() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
	filteredModel = null;
	if (e.getSource() == okButton) {
	    try {
		filteredModel = removeRowsWithZero(model);
		removeAndInsertModel(filteredModel, code);
	    } catch (SQLException e1) {
		filteredModel = null;
		e1.printStackTrace();
	    }
	}

	PluginServices.getMDIManager().closeWindow(this);
    }

    protected double getNumericValueAt(TableModel tmodel, int row, int column) {
	double doubleValue = 0;
	try {
	    Object value = tmodel.getValueAt(row, column);
	    if (value instanceof String) {
		doubleValue = Double.parseDouble((String) value);
	    } else if (value instanceof Integer) {
		doubleValue = (Integer) value;
	    } else if (value instanceof Double) {
		doubleValue = (Double) value;
	    }
	} catch (NumberFormatException e) {
	    doubleValue = 0;
	}
	return doubleValue;
    }

    public DefaultTableModel getFilteredTableModel() {
	return filteredModel;
    }

    public abstract void update(String code);

    public abstract void removeAndInsertModel(TableModel filteredModel,
	    String code) throws SQLException;

    public abstract DefaultTableModel removeRowsWithZero(DefaultTableModel model);
}
