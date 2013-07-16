package es.icarto.gvsig.navtableforms.gui.tables;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;

import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableContextualMenu;
import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelFactory;
import es.icarto.gvsig.navtableforms.gui.tables.model.VectorialTableModel;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;

public class VectorialTableHandler {

    private String layerName;
    private JTable jtable;
    private String foreignKeyId;
    private String[] colNames;
    private String[] colAliases;
    private JTableContextualMenu listener;

    public VectorialTableHandler(String layerName,
	    HashMap<String, JComponent> widgets, String foreignKeyId,
	    String[] colNames, String[] colAliases) {
	FonsaguaFormFactory.getInstance().checkLayerLoaded(layerName);
	this.layerName = layerName;
	jtable = (JTable) widgets.get(layerName);
	this.foreignKeyId = foreignKeyId;
	this.colNames = colNames;
	this.colAliases = colAliases;
    }

    public void reload(String layerName, FormFactory factory) {
	listener = new JTableVectorialContextualMenu(layerName, factory);
	jtable.addMouseListener(listener);
	// for the popUp to work on empty tables
	jtable.setFillsViewportHeight(true);
    }

    public void removeListeners() {
	jtable.removeMouseListener(listener);
    }

    public void fillValues(String foreignKeyValue) {
	try {
	    VectorialTableModel model = TableModelFactory
		    .createFromLayerWithFilter(layerName, foreignKeyId,
			    foreignKeyValue, colNames, colAliases);
	    jtable.setModel(model);
	    ((DefaultTableCellRenderer) jtable.getTableHeader()
		    .getDefaultRenderer())
		    .setHorizontalAlignment(JLabel.CENTER);
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}

    }

}
