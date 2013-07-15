package es.icarto.gvsig.navtableforms.gui.tables;

import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelFactory;
import es.icarto.gvsig.navtableforms.gui.tables.model.VectorialTableModel;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;

public class VectorialTableHandler {

    private String layerName;
    private JTable jtable;
    private String foreignKeyId;
    private String[] colNames;
    private String[] colAliases;
    private MouseListener listener;

    public VectorialTableHandler(String layerName,
	    HashMap<String, JComponent> widgets, String foreignKeyId,
	    String[] colNames, String[] colAliases) {
	FonsaguaTableFormFactory.getInstance().checkLayerLoaded(layerName);
	this.layerName = layerName;
	jtable = (JTable) widgets.get(layerName);
	jtable.setAutoCreateRowSorter(true);
	this.foreignKeyId = foreignKeyId;
	this.colNames = colNames;
	this.colAliases = colAliases;
    }

    public void reload(String layerName, TableFormFactory factory) {
	listener = new JTableVectorialContextualMenu(layerName, factory);
	jtable.addMouseListener(listener);
	jtable.setFillsViewportHeight(true);
    }

    public void removeListeners() {
	jtable.removeMouseListener(listener);
    }

    public void fillValues(String value) {
	String[] auxColNames = colNames, auxColAliases = colAliases;
	int foreignKeyColumn = -1;
	boolean eraseForeignKey = false;
	if (colNames != null) {
	    for (int a = 0, columns = colNames.length; a < columns; a++) {
		if (colNames[a].equals(foreignKeyId)) {
		    foreignKeyColumn = a;
		}
	    }

	    // If the table model doesn't include the foreign key, we add
	    // it, use it to filter and remove it afterwards.
	    if (foreignKeyColumn == -1) {
		auxColNames = new String[colNames.length + 1];
		for (int a = 0, columns = colNames.length; a < columns; a++) {
		    auxColNames[a] = colNames[a];
		}
		auxColNames[auxColNames.length - 1] = foreignKeyId;
		auxColAliases = new String[colAliases.length + 1];
		for (int a = 0, columns = colAliases.length; a < columns; a++) {
		    auxColAliases[a] = colAliases[a];
		}
		auxColAliases[auxColAliases.length - 1] = foreignKeyId;
		eraseForeignKey = true;
		foreignKeyColumn = auxColNames.length - 1;
	    }
	}
	VectorialTableModel model = TableModelFactory.createFromLayer(
		layerName, auxColNames, auxColAliases);
	jtable.setModel(model);
	((DefaultTableCellRenderer) jtable.getTableHeader()
		.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
	TableRowSorter sorter = new TableRowSorter<VectorialTableModel>(model);
	if (foreignKeyColumn > -1) {
	    sorter.setRowFilter(RowFilter.regexFilter("^" + value + "$",
		    foreignKeyColumn));
	    jtable.setRowSorter(sorter);
	    // We remove the temporal column used for filtering
	    if (eraseForeignKey) {
		jtable.removeColumn(jtable.getColumn(jtable
			.getColumnName(foreignKeyColumn)));
	    }
	}

    }

}
