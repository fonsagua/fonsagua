package es.icarto.gvsig.navtableforms.gui.tables;

import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableContextualMenu;
import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableUpdateContextualMenu;
import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelAlphanumeric;
import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelFactory;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class AlphanumericRelNNTableHandler {

    private String source;
    private AbstractSubForm form;
    private JTable jtable;
    private String originKey;
    private String relTable;
    private String dbSchema;
    private String destinyKey;
    private String[] colNames;
    private String[] colAliases;
    private JTableContextualMenu listener;

    public AlphanumericRelNNTableHandler(String sourceTableName,
	    HashMap<String, JComponent> widgets, String dbSchema,
	    String originKey, String relTable, String destinyKey,
	    String[] colNames, String[] colAliases) {
	this.source = sourceTableName;
	jtable = (JTable) widgets.get(sourceTableName);
	jtable.setAutoCreateRowSorter(true);
	this.originKey = originKey;
	this.dbSchema = dbSchema;
	this.relTable = relTable;
	this.destinyKey = destinyKey;
	this.colNames = colNames;
	this.colAliases = colAliases;
    }

    public void reload(AbstractSubForm form) {
	this.form = form;
	listener = new JTableUpdateContextualMenu(form);
	jtable.addMouseListener(listener);
	reloadGUI();
    }

    public void reloadGUI() {
	// for the popUp to work on empty tables
	jtable.setFillsViewportHeight(true);
    }

    public void removeListeners() {
	jtable.removeMouseListener(listener);
    }

    public void fillValues(String value) {
	try {
	    DBSession session = DBSession.getCurrentSession();
	    if (session != null) {
		String[] secPkValues = session.getDistinctValues(relTable,
			dbSchema, destinyKey, false, false, "WHERE "
				+ originKey + "='" + value + "'");
		TableModelAlphanumeric model = TableModelFactory
			.createFromTableWithOrFilter(source, destinyKey,
				secPkValues, colNames, colAliases);
		jtable.setModel(model);
		((DefaultTableCellRenderer) jtable.getTableHeader()
			.getDefaultRenderer())
			.setHorizontalAlignment(JLabel.CENTER);
		if (form != null) {
		    form.setModel(model);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
