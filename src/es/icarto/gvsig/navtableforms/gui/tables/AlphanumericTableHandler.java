package es.icarto.gvsig.navtableforms.gui.tables;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;

import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelAlphanumeric;
import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelFactory;
import es.icarto.gvsig.navtableforms.utils.TOCTableManager;

public class AlphanumericTableHandler {

    private AbstractSubForm form;
    private JTable jtable;
    private JTableContextualMenu listener;
    private String foreignKeyId;
    private String[] colNames;
    private String[] colAliases;
    private String tablename;

    public AlphanumericTableHandler(String schema, String tablename,
	    HashMap<String, JComponent> widgets, String foreignKeyId,
	    String[] colNames, String[] colAliases) {
	this.tablename = tablename;
	jtable = (JTable) widgets.get(tablename);
	this.foreignKeyId = foreignKeyId;
	this.colNames = colNames;
	this.colAliases = colAliases;
	try {
	    TOCTableManager toc = new TOCTableManager();
	    if (toc.getTableByName(tablename) == null) {
		AlphanumericTableLoader.loadTable(schema, tablename);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void reload(AbstractSubForm form) {
	this.form = form;
	listener = new JTableContextualMenu(form);
	jtable.addMouseListener(listener);
	// for the popUp to work on empty tables
	jtable.setFillsViewportHeight(true);
    }

    public void removeListeners() {
	jtable.removeMouseListener(listener);
    }

    public void fillValues(String foreingKeyValue) {

	TableModelAlphanumeric model;
	Map<String, String> foreingKey = new HashMap<String, String>(1);
	foreingKey.put(foreignKeyId, foreingKeyValue);
	form.setForeingKey(foreingKey);
	try {
	    model = TableModelFactory.createFromTableWithFilter(tablename,
		    foreignKeyId, foreingKeyValue, colNames, colAliases);
	    jtable.setModel(model);
	    ((DefaultTableCellRenderer) jtable.getTableHeader()
		    .getDefaultRenderer())
		    .setHorizontalAlignment(JLabel.CENTER);
	    form.setModel(model);
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}

    }

}
