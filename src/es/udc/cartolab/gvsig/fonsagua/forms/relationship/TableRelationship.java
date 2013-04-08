package es.udc.cartolab.gvsig.fonsagua.forms.relationship;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.udc.cartolab.gvsig.users.utils.DBSession;

public class TableRelationship {

    private String primaryTableName;
    private String primaryPKName;
    private String secondaryTableName;
    private String secondaryPKName;
    private String relationTableName;

    private JTable relationJTable;
    private String primaryPKValue;
    private ArrayList<String> secondaryTableValues;
    private JTableRelationshipContextualMenu listener;

    public TableRelationship(HashMap<String, JComponent> widgets,
	    String primaryTableName, String primaryPKName,
	    String secondaryTableName, String secondaryPKName,
	    String relationTableName) {
	this.primaryTableName = primaryTableName;
	this.primaryPKName = primaryPKName;
	this.secondaryTableName = secondaryTableName;
	this.secondaryPKName = secondaryPKName;
	this.relationTableName = relationTableName;
	if (widgets != null) {
	    relationJTable = (JTable) widgets.get(relationTableName);
	}
	secondaryTableValues = getSecondaryValues();
	if (relationJTable != null) {
	    initializeJTable();
	}
    }

    private void initializeJTable() {
	@SuppressWarnings("serial")
	DefaultTableModel tableModel = new DefaultTableModel() {
	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;
	    }
	};
	tableModel.addColumn("C�digo");
	relationJTable.setModel(tableModel);
	fillRows();
    }

    private void fillRows() {
	try {
	    PreparedStatement statement;
	    String query = "SELECT * FROM " + relationTableName + ";";
	    statement = DBSession.getCurrentSession().getJavaConnection()
		    .prepareStatement(query);
	    statement.execute();
	    ResultSet rs = statement.getResultSet();
	    while (rs.next()) {
		Vector<String> rowValues = new Vector<String>();
		rowValues.add(rs.getString(secondaryPKName));
		((DefaultTableModel) relationJTable.getModel())
			.addRow(rowValues);
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void reload() {
	listener = new JTableRelationshipContextualMenu(this);
	relationJTable.addMouseListener(listener);
	relationJTable.setFillsViewportHeight(true);
    }

    public void removeListeners() {
	relationJTable.removeMouseListener(listener);
    }

    private ArrayList<String> getSecondaryValues() {
	try {
	    PreparedStatement statement;
	    String query = "SELECT " + secondaryPKName + " FROM "
		    + secondaryTableName + ";";
	    statement = DBSession.getCurrentSession().getJavaConnection()
		    .prepareStatement(query);
	    statement.execute();
	    ResultSet rs = statement.getResultSet();
	    ArrayList<String> values = new ArrayList<String>();
	    while (rs.next()) {
		values.add(rs.getString(secondaryPKName));
	    }
	    return values;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public void insertRow(String secondaryPKValue) {
	try {
	    PreparedStatement statement;
	    String query = "INSERT INTO " + relationTableName + " VALUES ( "
		    + "'" + secondaryPKValue + "','" + primaryPKValue + "');";
	    statement = DBSession.getCurrentSession().getJavaConnection()
		    .prepareStatement(query);
	    statement.execute();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void deleteRow(String secondaryPKValue) {
	try {
	    PreparedStatement statement;
	    String query = "DELETE FROM " + relationTableName + " WHERE "
		    + secondaryPKName + " = '" + secondaryPKValue + "';";
	    statement = DBSession.getCurrentSession().getJavaConnection()
		    .prepareStatement(query);
	    statement.execute();
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

    public JTable getRelationJTable() {
	return relationJTable;
    }

    public void setRelationJTable(JTable relationJTable) {
	this.relationJTable = relationJTable;
    }

    public String getPrimaryPKValue() {
	return primaryPKValue;
    }

    public void setPrimaryPKValue(String primaryPKValue) {
	this.primaryPKValue = primaryPKValue;
    }

    public JTableRelationshipContextualMenu getListener() {
	return listener;
    }

    public void setListener(JTableRelationshipContextualMenu listener) {
	this.listener = listener;
    }

    public ArrayList<String> getSecondaryTableValues() {
	return secondaryTableValues;
    }

    public void setSecondaryTableValues(ArrayList<String> secondaryTableValues) {
	this.secondaryTableValues = secondaryTableValues;
    }

}
