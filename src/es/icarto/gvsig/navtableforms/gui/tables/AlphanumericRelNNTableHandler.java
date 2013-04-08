package es.icarto.gvsig.navtableforms.gui.tables;

import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTable;

import com.iver.cit.gvsig.fmap.edition.IEditableSource;
import com.iver.cit.gvsig.project.documents.table.ProjectTable;

import es.udc.cartolab.gvsig.users.utils.DBSession;

public class AlphanumericRelNNTableHandler {

    private IEditableSource source;
    private AbstractSubForm form;
    private JTable jtable;
    private String originTable;
    private String originKey;
    private String relTable;
    private String destinyKey;
    private String[] colNames;
    private String[] colAliases;
    private MouseListener listener;

    public AlphanumericRelNNTableHandler(ProjectTable sourceTable,
	    HashMap<String, JComponent> widgets, String originTable,
	    String originKey, String relTable, String destinyKey,
	    String[] colNames, String[] colAliases) {
	this.source = sourceTable.getModelo();
	jtable = (JTable) widgets.get(sourceTable.getName());
	jtable.setAutoCreateRowSorter(true);
	this.originTable = originTable;
	this.originKey = originKey;
	this.relTable = relTable;
	this.destinyKey = destinyKey;
	this.colNames = colNames;
	this.colAliases = colAliases;
    }

    public void reload(AbstractSubForm form) {
	this.form = form;
	listener = new JTableOnlyUpdateContextualMenu(form);
	jtable.addMouseListener(listener);
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
		String sql = getSQLSentence(value);
		ResultSet results = session.getJavaConnection()
			.createStatement().executeQuery(sql);
		int foreignKeyColumn = source.getRecordset()
			.getFieldIndexByName(destinyKey);
		if (foreignKeyColumn > -1) {
		    String destinyValue;
		    List<IRowFilter> filters = new ArrayList<IRowFilter>();
		    while (results.next()) {
			destinyValue = results.getString(1);
			filters.add(new IRowFilterImplementer(foreignKeyColumn,
				destinyValue));
		    }
		    TableModelAlphanumeric model = new TableModelAlphanumeric(
			    source,
			    new IRowMultipleOrFilterImplementer(filters),
			    colNames, colAliases);
		    jtable.setModel(model);
		    form.setModel(model);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private String getSQLSentence(String value) {
	return "SELECT b." + destinyKey + " FROM " + originTable + " a JOIN "
		+ relTable + " b ON a." + originKey + " = b." + originKey
		+ " WHERE a." + originKey + " = '" + value + "';";
    }

}
