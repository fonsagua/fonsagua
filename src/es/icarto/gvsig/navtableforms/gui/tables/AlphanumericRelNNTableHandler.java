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

    private String sourceTableName;
    private JTable jtable;
    private String originKey;
    private String destinationKey;
    private String relTable;
    private String dbSchema;
    private String[] colNames;
    private String[] colAliases;
    private String[] destinationKeyValues;
    private int keyColumn = 0;
    private String originKeyValue;
    private JTableContextualMenu listener;
    private AbstractSubForm form;

    public AlphanumericRelNNTableHandler(String sourceTableName,
	    HashMap<String, JComponent> widgets, String dbSchema,
	    String originKey, String relTable, String destinationKey,
	    String[] colNames, String[] colAliases) {
	this.sourceTableName = sourceTableName;
	this.originKey = originKey;
	this.dbSchema = dbSchema;
	this.relTable = relTable;
	this.destinationKey = destinationKey;
	this.colNames = colNames;
	this.colAliases = colAliases;
	if (colNames != null) {
	    for (int i = 0, columns = colNames.length; i < columns; i++) {
		if (colNames[i].equals(destinationKey)) {
		    keyColumn = i;
		    break;
		}
	    }
	}
	jtable = (JTable) widgets.get(sourceTableName);
    }

    public void fillValues(String value) {
	try {
	    originKeyValue = value;
	    DBSession session = DBSession.getCurrentSession();
	    if (session != null) {
		destinationKeyValues = session.getDistinctValues(relTable,
			dbSchema, destinationKey, false, false, "WHERE "
				+ originKey + "='" + originKeyValue + "'");
		TableModelAlphanumeric model = TableModelFactory
			.createFromTableWithOrFilter(sourceTableName,
				destinationKey, destinationKeyValues, colNames,
				colAliases);
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

    public String getOriginKey() {
	return originKey;
    }

    public void setOriginKey(String originKey) {
	this.originKey = originKey;
    }

    public String getSourceTableName() {
	return sourceTableName;
    }

    public void setSourceTableName(String sourceTableName) {
	this.sourceTableName = sourceTableName;
    }

    public String getDestinationKey() {
	return destinationKey;
    }

    public void setDestinationKey(String destinationKey) {
	this.destinationKey = destinationKey;
    }

    public String getRelationTableName() {
	return relTable;
    }

    public void setRelationTableName(String relTable) {
	this.relTable = relTable;
    }

    public String getDbSchema() {
	return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
	this.dbSchema = dbSchema;
    }

    public JTable getRelationJTable() {
	return jtable;
    }

    public void setRelationJTable(JTable relationJTable) {
	this.jtable = relationJTable;
    }

    public JTableContextualMenu getListener() {
	return listener;
    }

    public void setListener(JTableContextualMenu listener) {
	this.listener = listener;
    }

}
