package es.icarto.gvsig.navtableforms.gui.tables;

import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;

public class VectorialTableHandler {

    private FLyrVect layer;
    private JTable jtable;
    private String foreignKeyId;
    private String[] colNames;
    private String[] colAliases;
    private MouseListener listener;

    public VectorialTableHandler(String layerName,
	    HashMap<String, JComponent> widgets, String foreignKeyId,
	    String[] colNames, String[] colAliases) {
	FonsaguaTableFormFactory.getInstance().checkLayerLoaded(layerName);
	this.layer = new TOCLayerManager().getLayerByName(layerName);
	jtable = (JTable) widgets.get(layer.getName());
	jtable.setAutoCreateRowSorter(true);
	this.foreignKeyId = foreignKeyId;
	this.colNames = colNames;
	this.colAliases = colAliases;
    }

    private FLyrVect getLayer(String layerName) {
	FLyrVect layer = new TOCLayerManager().getLayerByName(layerName);
	if (layer == null) {

	}
	return layer;
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
	try {
	    TableModelVectorial model = new TableModelVectorial(layer,
		    colNames, colAliases);
	    jtable.setModel(model);
	    TableRowSorter sorter = new TableRowSorter<TableModelVectorial>(model);
	    int foreignKeyColumn = -1;
	    for (int a = 0, columns = jtable.getModel().getColumnCount(); a < columns; a++) {
		if (model.getLayerColumnName(a).equals(foreignKeyId)) {
		    foreignKeyColumn = a;
		}
	    }
	    if (foreignKeyColumn > -1) {
		sorter.setRowFilter(RowFilter.regexFilter("^" + value + "$",
			foreignKeyColumn));
		jtable.setRowSorter(sorter);
		jtable.removeColumn(jtable.getColumn(jtable
			.getColumnName(foreignKeyColumn)));
	    }
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}

    }

}
