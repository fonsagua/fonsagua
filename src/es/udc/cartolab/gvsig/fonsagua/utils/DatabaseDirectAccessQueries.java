package es.udc.cartolab.gvsig.fonsagua.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import es.icarto.gvsig.navtableforms.gui.tables.model.NotEditableTableModel;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.users.utils.DBSession;
import es.udc.cartolab.gvsig.users.utils.DBSessionSpatiaLite;

public class DatabaseDirectAccessQueries {

    public static void insertDefaultPreferences(String codAlt)
	    throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	session.insertRow(FonsaguaConstants.dataSchema,
		FonsaguaConstants.preferencesTable,
		new String[] { AlternativasForm.PKFIELD },
		new String[] { codAlt });
    }

    public static DefaultTableModel getComunitiesIntersectingAlternative(
	    String codAlt) throws SQLException {

	String query = "SELECT c.comunidad, c.n_habitantes, ci.n_hab_alternativa FROM fonsagua.comunidades AS c JOIN fonsagua.alternativas AS a ON st_intersects(a.geom, c.geom) FULL OUTER JOIN fonsagua.comunidades_implicadas AS ci ON ci.comunidad = c.comunidad WHERE a.cod_alternativa = '####';";
	ResultSet rs = convertAndExecuteQuery(codAlt, query);

	DefaultTableModel modelo = new OnlyOneColumnEditable(2);
	ConversorResultSetADefaultTableModel.rellena(rs, modelo);
	return modelo;
    }

    public static TableModel getComunidadesImplicadasTable(String codAlt)
	    throws SQLException {

	String query = "SELECT comunidad, n_habitantes, n_hab_alternativa FROM fonsagua.comunidades_implicadas WHERE cod_alternativa = '####'";
	ResultSet rs = convertAndExecuteQuery(codAlt, query);

	DefaultTableModel model = new NotEditableTableModel();
	ConversorResultSetADefaultTableModel.rellena(rs, model);
	if (model.getRowCount() == 0) {
	    model.addRow(new Vector<Object>());
	}

	return model;
    }

    public static void removeAndInsertModel(TableModel model, String code)
	    throws SQLException {
	DBSession session = DBSession.getCurrentSession();

	String whereClause = getQueryWithCodeInsteadOfPlaceHolders(code,
		"WHERE cod_alternativa = '####'");
	session.deleteRows(FonsaguaConstants.dataSchema,
		FonsaguaConstants.COMUNIDADES_IMPLICADAS, whereClause);

	final String[] columnNames = { "cod_alternativa", "comunidad",
		"n_habitantes", "n_hab_alternativa" };

	Object[] values;
	for (int row = 0; row < model.getRowCount(); row++) {
	    values = new Object[model.getColumnCount() + 1];
	    values[0] = code;
	    values[1] = model.getValueAt(row, 0);
	    values[2] = model.getValueAt(row, 1);
	    values[3] = model.getValueAt(row, 2);
	    session.insertRow(FonsaguaConstants.dataSchema,
		    FonsaguaConstants.COMUNIDADES_IMPLICADAS, columnNames,
		    values);
	}

    }

    public static ResultSet convertAndExecuteQuery(String code, String query)
	    throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	query = getQueryWithCodeInsteadOfPlaceHolders(code, query);
	query = getQueryWithOutDataSchemaIfSQLiteSession(query);

	Statement statement = session.getJavaConnection().createStatement();
	ResultSet rs = statement.executeQuery(query);
	return rs;
    }

    public static String getQueryWithCodeInsteadOfPlaceHolders(String code,
	    String query) {
	query = query.replace("####", code);
	return query;
    }

    public static String getQueryWithOutDataSchemaIfSQLiteSession(String sql) {
	DBSession session = DBSession.getCurrentSession();
	if (session instanceof DBSessionSpatiaLite) {
	    return sql.replace(FonsaguaConstants.dataSchema, "");
	}
	return sql;
    }

}
