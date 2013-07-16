package es.icarto.gvsig.navtableforms.gui.tables;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;

import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableCompleteContextualMenu;
import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableContextualMenu;
import es.icarto.gvsig.navtableforms.gui.tables.model.AlphanumericTableModel;
import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelFactory;
import es.icarto.gvsig.navtableforms.utils.FormFactory;

public class AlphanumericTableHandler {

    private String tablename;
    private JTable jtable;
    private String foreignKeyId;
    private String[] colNames;
    private String[] colAliases;
    private JTableContextualMenu listener;
    private AbstractSubForm form;

    public AlphanumericTableHandler(String tableName,
	    HashMap<String, JComponent> widgets, String foreignKeyId,
	    String[] colNames, String[] colAliases) {
	this.tablename = tableName;
	jtable = (JTable) widgets.get(tableName);
	this.foreignKeyId = foreignKeyId;
	this.colNames = colNames;
	this.colAliases = colAliases;
	FormFactory.checkTableLoadedRegistered(tableName);
    }

    public void reload(AbstractSubForm form) {
	this.form = form;
	listener = new JTableCompleteContextualMenu(form);
	jtable.addMouseListener(listener);
	// for the popUp to work on empty tables
	jtable.setFillsViewportHeight(true);
    }

    public void removeListeners() {
	jtable.removeMouseListener(listener);
    }

    public void fillValues(String foreignKeyValue) {
	Map<String, String> foreignKey = new HashMap<String, String>(1);
	foreignKey.put(foreignKeyId, foreignKeyValue);
	form.setForeingKey(foreignKey);
	try {
	    AlphanumericTableModel model = TableModelFactory
		    .createFromTableWithFilter(tablename, foreignKeyId,
			    foreignKeyValue, colNames, colAliases);
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
