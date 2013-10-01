package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.iver.andami.PluginServices;

import es.udc.cartolab.gvsig.navtable.dataacces.IController;
import es.udc.cartolab.gvsig.navtable.format.DoubleFormatNT;

@SuppressWarnings("serial")
public abstract class SelectElementForAlternativeDialog extends AbstractIWindow
	implements ActionListener {

    protected JTable table;
    private JButton okButton;
    private JButton cancelButton;
    protected DefaultTableModel model;
    private DefaultTableModel filteredModel;
    protected String code;
    private int editableColumnIdx;

    protected final NumberFormat doubleFormat;

    public SelectElementForAlternativeDialog(int editableColumnIdx) {
	super(new BorderLayout());
	doubleFormat = DoubleFormatNT.getDisplayingFormat();
	doubleFormat.setMinimumFractionDigits(2);
	doubleFormat.setMaximumFractionDigits(2);
	this.editableColumnIdx = editableColumnIdx;
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
	table.getTableHeader().setReorderingAllowed(false);
	JScrollPane scroll = new JScrollPane(table);
	add(scroll, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == okButton) {
	    try {
		if (table.isEditing()) {
		    table.getCellEditor().stopCellEditing();
		}
		filteredModel = removeRowsWithZero(model);
		removeAndInsertModel(filteredModel, code);
	    } catch (SQLException e1) {
		filteredModel = null;
		e1.printStackTrace();
	    }
	}

	PluginServices.getMDIManager().closeWindow(this);
    }

    protected BigDecimal getNumericValueAt(TableModel tmodel, int row,
	    int column) {

	BigDecimal doubleValue = BigDecimal.valueOf(0);
	try {
	    Object value = tmodel.getValueAt(row, column);
	    if (value instanceof String) {
		doubleValue = new BigDecimal(((String) value).replace(",", "."));
	    } else if (value instanceof Number) {
		doubleValue = new BigDecimal(doubleFormat.format(value)
			.replace(",", "."));
	    }
	} catch (NumberFormatException e) {
	    doubleValue = BigDecimal.valueOf(0);
	}
	return doubleValue;
    }

    public DefaultTableModel getFilteredTableModel() {
	return filteredModel;
    }

    public int getEditableColumnIdx() {
	return editableColumnIdx;
    }

    public double getSumEditableColumnValue() {
	double sum = 0;
	for (int i = 0; i < filteredModel.getRowCount(); i++) {
	    sum += getNumericValueAt(filteredModel, i, getEditableColumnIdx())
		    .doubleValue();
	}
	return sum;
    }

    public void update(String code) {
	filteredModel = null;
    }

    public abstract void removeAndInsertModel(TableModel filteredModel,
	    String code) throws SQLException;

    public abstract DefaultTableModel removeRowsWithZero(DefaultTableModel model);

    public abstract void setAutomaticValue(IController layerController,
	    HashMap<String, JComponent> widgets);

}
