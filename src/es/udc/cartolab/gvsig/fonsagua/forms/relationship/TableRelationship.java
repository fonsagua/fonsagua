package es.udc.cartolab.gvsig.fonsagua.forms.relationship;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.NotEditableTableModel;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class TableRelationship {

    private String primaryTableName;
    private String primaryPKName;
    private String secondaryTableName;
    private String secondaryPKName;
    private String relationTableName;
    private String dbSchema;
    private String[] secPkValues;

    private JTable relationJTable;
    private String primaryPKValue;
    private JTableRelationshipContextualMenu listener;

    public TableRelationship(HashMap<String, JComponent> widgets,
	    String primaryTableName, String primaryPKName,
	    String secondaryTableName, String secondaryPKName,
	    String relationTableName, String dbSchema) {
	this.primaryTableName = primaryTableName;
	this.primaryPKName = primaryPKName;
	this.secondaryTableName = secondaryTableName;
	this.secondaryPKName = secondaryPKName;
	this.relationTableName = relationTableName;
	this.dbSchema = dbSchema;
	relationJTable = (JTable) widgets.get(relationTableName);
    }

    public void fillValues(String primaryPKValue) {
	this.primaryPKValue = primaryPKValue;
	DefaultTableModel tableModel = new NotEditableTableModel();
	tableModel.addColumn("Código");
	relationJTable.setModel(tableModel);
	fillRows();
    }

    private void fillRows() {
	try {
	    secPkValues = DBSession.getCurrentSession().getDistinctValues(
		    relationTableName, dbSchema, secondaryPKName, false, false,
		    "WHERE " + primaryPKName + "='" + primaryPKValue + "'");
	    String[] auxValue = new String[1];
	    for (String val : secPkValues) {
		auxValue[0] = val;
		((DefaultTableModel) relationJTable.getModel())
			.addRow(auxValue);
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void reload(BasicAbstractForm dialog) {
	listener = new JTableRelationshipContextualMenu(this, dialog);
	relationJTable.addMouseListener(listener);
	relationJTable.setFillsViewportHeight(true);
    }

    public void removeListeners() {
	relationJTable.removeMouseListener(listener);
    }

    public List<String> getSecondaryValues() {
	try {
	    String where = "";
	    if (secPkValues.length > 0) {
		where = " WHERE " + secondaryPKName + " NOT IN (";
		for (String val : secPkValues) {
		    where += "'" + val + "', ";
		}
		where = where.substring(0, where.length() - 2) + ")";
	    }
	    String[] values = DBSession.getCurrentSession().getDistinctValues(
		    secondaryTableName, dbSchema, secondaryPKName, false, false,
		    where);
	    return Arrays.asList(values);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public void insertRow(String secondaryPKValue) {
	try {
	    String[] columns = { primaryPKName, secondaryPKName };
	    String[] values = { primaryPKValue, secondaryPKValue };
	    DBSession.getCurrentSession().insertRow(dbSchema,
		    relationTableName, columns, values);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void deleteRow(String secondaryPKValue) {
	try {
	    String where = "WHERE " + primaryPKName + " = '" + primaryPKValue
		    + "' AND " + secondaryPKName + " = '" + secondaryPKValue
		    + "'";
	    DBSession.getCurrentSession().deleteRows(dbSchema,
		    relationTableName, where);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public String getPrimaryTableName() {
	return primaryTableName;
    }

    public void setPrimaryTableName(String primaryTableName) {
	this.primaryTableName = primaryTableName;
    }

    public String getPrimaryPKName() {
	return primaryPKName;
    }

    public void setPrimaryPKName(String primaryPKName) {
	this.primaryPKName = primaryPKName;
    }

    public String getSecondaryTableName() {
	return secondaryTableName;
    }

    public void setSecondaryTableName(String secondaryTableName) {
	this.secondaryTableName = secondaryTableName;
    }

    public String getSecondaryPKName() {
	return secondaryPKName;
    }

    public void setSecondaryPKName(String secondaryPKName) {
	this.secondaryPKName = secondaryPKName;
    }

    public String getRelationTableName() {
	return relationTableName;
    }

    public void setRelationTableName(String relationTableName) {
	this.relationTableName = relationTableName;
    }

    public String getRelationTableSchema() {
	return dbSchema;
    }

    public void setRelationTableSchema(String relationTableSchema) {
	this.dbSchema = relationTableSchema;
    }

    public JTable getRelationJTable() {
	return relationJTable;
    }

    public void setRelationJTable(JTable relationJTable) {
	this.relationJTable = relationJTable;
    }

    public JTableRelationshipContextualMenu getListener() {
	return listener;
    }

    public void setListener(JTableRelationshipContextualMenu listener) {
	this.listener = listener;
    }

}
