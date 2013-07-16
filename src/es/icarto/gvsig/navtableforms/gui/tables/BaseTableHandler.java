package es.icarto.gvsig.navtableforms.gui.tables;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;

import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableContextualMenu;
import es.icarto.gvsig.navtableforms.gui.tables.model.BaseTableModel;

public abstract class BaseTableHandler {

    protected String tableName;
    protected JTable jtable;
    protected BaseTableModel model;
    protected String foreignKeyId;
    protected String foreignKeyValue;
    protected String[] colNames;
    protected String[] colAliases;
    protected JTableContextualMenu listener;

    public BaseTableHandler(String tableName,
	    HashMap<String, JComponent> widgets, String foreignKeyId,
	    String[] colNames, String[] colAliases) {
	this.tableName = tableName;
	getJTable(widgets);
	this.foreignKeyId = foreignKeyId;
	this.colNames = colNames;
	this.colAliases = colAliases;
    }

    protected void getJTable(HashMap<String, JComponent> widgets) {
	jtable = (JTable) widgets.get(tableName);
    }

    protected abstract void createTableModel() throws ReadDriverException;

    protected abstract void createTableListener();

    protected void reload() {
	createTableListener();
	if (listener != null) {
	    jtable.addMouseListener(listener);
	}
	reloadGUI();
    }

    public void reloadGUI() {
	// for the popUp to work on empty tables
	jtable.setFillsViewportHeight(true);
    }

    public void fillValues(String foreignKeyValue) {
	this.foreignKeyValue = foreignKeyValue;
	// Map<String, String> foreignKey = new HashMap<String, String>(1);
	// foreignKey.put(foreignKeyId, foreignKeyValue);
	// form.setForeingKey(foreignKey);
	try {
	    createTableModel();
	    ((DefaultTableCellRenderer) jtable.getTableHeader()
		    .getDefaultRenderer())
		    .setHorizontalAlignment(JLabel.CENTER);
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}

    }

    public void removeListeners() {
	jtable.removeMouseListener(listener);
    }

}
