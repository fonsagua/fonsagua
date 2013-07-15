package es.icarto.gvsig.navtableforms.gui.tables;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableContextualMenu;
import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelFactory;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class TableRelationship {

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

    public TableRelationship(String sourceTableName,
	    HashMap<String, JComponent> widgets, String dbSchema,
	    String originKey, String relTable, String destinationKey,
	    String[] colNames, String[] colAliases) {
	FonsaguaTableFormFactory.getInstance()
		.checkLayerLoaded(sourceTableName);
	this.originKey = originKey;
	this.relTable = relTable;
	this.destinationKey = destinationKey;
	this.sourceTableName = sourceTableName;
	this.dbSchema = dbSchema;
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
	jtable = (JTable) widgets.get(relTable);
    }

    public void fillValues(String value) {
	try {
	    originKeyValue = value;
	    DBSession session = DBSession.getCurrentSession();
	    if (session != null) {
		destinationKeyValues = session.getDistinctValues(relTable,
			dbSchema, destinationKey, false, false, "WHERE "
				+ originKey + "='" + originKeyValue + "'");
		TableModel model = TableModelFactory
			.createFromLayerWithOrFilter(sourceTableName,
				destinationKey, destinationKeyValues, colNames,
				colAliases);
		jtable.setModel(model);
		((DefaultTableCellRenderer) jtable.getTableHeader()
			.getDefaultRenderer())
			.setHorizontalAlignment(JLabel.CENTER);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public void reload(AbstractForm dialog) {
	listener = new JTableRelationshipContextualMenu(this, dialog, keyColumn);
	jtable.addMouseListener(listener);
	jtable.setFillsViewportHeight(true);
    }

    public void removeListeners() {
	jtable.removeMouseListener(listener);
    }

    public List<String> getUnlinkedSecondaryValues() {
	try {
	    String where = "";
	    if (destinationKeyValues.length > 0) {
		where = " WHERE " + destinationKey + " NOT IN (";
		for (String val : destinationKeyValues) {
		    where += "'" + val + "', ";
		}
		where = where.substring(0, where.length() - 2) + ")";
	    }
	    String[] values = DBSession.getCurrentSession().getDistinctValues(
		    relTable, dbSchema, destinationKey, false, false,
		    where);
	    return Arrays.asList(values);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public void insertRow(String secondaryPKValue) {
	try {
	    String[] columns = { originKey, destinationKey };
	    String[] values = { originKeyValue, secondaryPKValue };
	    DBSession.getCurrentSession().insertRow(dbSchema, relTable,
		    columns, values);
	    fillValues(originKeyValue);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void deleteRow(String secondaryPKValue) {
	try {
	    String where = "WHERE " + originKey + " = '" + originKeyValue
		    + "' AND " + destinationKey + " = '" + secondaryPKValue
		    + "'";
	    DBSession.getCurrentSession().deleteRows(dbSchema, relTable, where);
	    fillValues(originKeyValue);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
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
