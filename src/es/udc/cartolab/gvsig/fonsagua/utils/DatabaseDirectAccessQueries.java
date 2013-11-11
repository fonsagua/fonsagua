package es.udc.cartolab.gvsig.fonsagua.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import es.icarto.gvsig.fonsagua.reports.utils.ReportUtils;
import es.icarto.gvsig.navtableforms.gui.tables.model.NotEditableTableModel;
import es.udc.cartolab.gvsig.epanet.exceptions.ExternalError;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.users.utils.DBSession;
import es.udc.cartolab.gvsig.users.utils.DBSessionSpatiaLite;

public class DatabaseDirectAccessQueries {

    private DatabaseDirectAccessQueries() {
	throw new AssertionError(
		"Suppress default constructor for noninstantiability");
    }

    public static DefaultTableModel getFuentesIntersectingAlternative(
	    String codAlt) throws SQLException {

	String query = "SELECT fuentes.fuente AS \"Fuente\", fuentes.tipo_fuente AS \"Tipo fuente\", fuentes.aforo AS \"Aforo\", fuentes.q_ecologico AS \"Q eco (l/s)\", (SELECT q_usar FROM fonsagua.fuentes_implicadas WHERE fuente = fuentes.fuente) AS \"Q usar (l/s)\" FROM ( SELECT fuente, tipo_fuente, COALESCE(aforo.aforo,0) AS aforo,  CASE WHEN tipo_fuente IN ('Manantial', 'Punto rio') THEN COALESCE(aforo.aforo,0) * (SELECT coef_q_ecologico FROM fonsagua.preferencias_disenho WHERE cod_alternativa = '####') ELSE NULL END AS q_ecologico FROM fonsagua.fuentes AS fuente JOIN fonsagua.alternativas AS alt ON st_intersects(alt.geom, fuente.geom) FULL OUTER JOIN (select cod_fuente, min(aforo) as aforo from fonsagua.aforos group by cod_fuente) AS aforo ON fuente.cod_fuente = aforo.cod_fuente WHERE alt.cod_alternativa = '####' UNION SELECT fuente, tipo_fuente_alternativa AS tipo_fuente, aforo, q_ecologico FROM fonsagua.alt_fuentes WHERE cod_alternativa = '####' AND existencia_elemento <> 'Existente' UNION SELECT embalse, 'Embalse', aforo, NULL FROM fonsagua.alt_embalses WHERE cod_alternativa = '####' AND existencia_elemento <> 'Existente' ) AS fuentes;";
	ResultSet rs = convertAndExecuteQuery(codAlt, query);

	DefaultTableModel modelo = new OnlyOneColumnEditable(4);
	ConversorResultSetADefaultTableModel.rellena(rs, modelo);
	return modelo;

    }

    public static DefaultTableModel getComunitiesIntersectingAlternative(
	    String codAlt) throws SQLException {

	String query = "SELECT c.comunidad AS \"Comunidad\", c.cod_comunidad AS \"Cod. Comunidad\", c.n_habitantes AS \"Habitantes totales\", ci.n_hab_alternativa AS \"Habitantes alternativa\", ci.gid FROM fonsagua.comunidades AS c JOIN fonsagua.alternativas AS a ON st_intersects(a.geom, c.geom) FULL OUTER JOIN fonsagua.comunidades_implicadas AS ci ON ci.cod_comunidad = c.cod_comunidad AND a.cod_alternativa = ci.cod_alternativa WHERE a.cod_alternativa = '####';";
	ResultSet rs = convertAndExecuteQuery(codAlt, query);

	DefaultTableModel modelo = new OnlyOneColumnEditable(3);
	ConversorResultSetADefaultTableModel.rellena(rs, modelo);
	return modelo;
    }

    public static DefaultTableModel getFuentesImplicadasTable(String codAlt)
	    throws SQLException {
	String query = "SELECT fuente AS \"Fuente\", tipo_fuente AS \"Tipo fuente\", aforo AS \"Aforo\", q_ecol AS \"Q eco (l/s)\", q_usar AS \"Q usar (l/s)\" FROM fonsagua.fuentes_implicadas WHERE cod_alternativa = '####'";
	ResultSet rs = convertAndExecuteQuery(codAlt, query);

	DefaultTableModel model = new NotEditableTableModel();
	ConversorResultSetADefaultTableModel.rellena(rs, model);
	if (model.getRowCount() == 0) {
	    model.addRow(new Vector<Object>());
	}

	return model;
    }

    public static DefaultTableModel getComunidadesImplicadasTable(String codAlt)
	    throws SQLException {

	String query = "SELECT comunidad AS \"Comunidad\", cod_comunidad AS \"Cod. Comunidad\", n_habitantes AS \"Habitantes totales\", n_hab_alternativa AS \"Habitantes alternativa\" FROM fonsagua.comunidades_implicadas WHERE cod_alternativa = '####'";
	ResultSet rs = convertAndExecuteQuery(codAlt, query);

	DefaultTableModel model = new NotEditableTableModel();
	ConversorResultSetADefaultTableModel.rellena(rs, model);
	if (model.getRowCount() == 0) {
	    model.addRow(new Vector<Object>());
	}

	return model;
    }

    public static boolean isValidAlternative(String codAlt) throws SQLException {

	String query = "SELECT demanda, caudal_fuentes FROM fonsagua.alternativas WHERE cod_alternativa = '####'";
	ResultSet rs = convertAndExecuteQuery(codAlt, query);

	while (rs.next()) {
	    final double demanda = rs.getDouble("demanda");
	    final double caudalFuentes = rs.getDouble("caudal_fuentes");

	    if (demanda > 0 && caudalFuentes > 0 && demanda <= caudalFuentes) {
		return true;
	    }
	}
	return false;
    }

    public static List<String[]> getTuberiasForBudget(String codAlt)
	    throws SQLException {
	String query = "SELECT pt.id_tub, pt.material, pt.diametro, COALESCE(longitud_total,0) FROM ##dataSchema##.preferencias_tuberias pt LEFT JOIN (SELECT tuberia_comercial, sum(long_tuberia) AS longitud_total FROM ##dataSchema##.alt_tuberias WHERE cod_alternativa = '####' GROUP BY tuberia_comercial) t ON pt.id_tub = t.tuberia_comercial ORDER BY pt.id_tub ;";
	ResultSet rs = convertAndExecuteQuery(codAlt, query);

	List<String[]> list = new ArrayList<String[]>();

	while (rs.next()) {
	    list.add(new String[] {
		    ReportUtils.getValueFormatted(rs.getString(1)),
		    ReportUtils.getValueFormatted(rs.getString(2)),
		    ReportUtils.getValueFormatted(rs.getString(3)),
		    ReportUtils.getValueFormatted(rs.getString(4)) });
	}
	return list;
    }

    public static void removeAndInsertModelFuentes(TableModel model, String code)
	    throws SQLException {
	DBSession session = DBSession.getCurrentSession();

	String whereClause = getQueryWithCodeInsteadOfPlaceHolders(code,
		"WHERE cod_alternativa = '####'");
	session.deleteRows(FonsaguaConstants.dataSchema,
		FonsaguaConstants.FUENTES_IMPLICADAS, whereClause);

	final String[] columnNames = { "cod_alternativa", "fuente",
		"tipo_fuente", "aforo", "q_ecol", "q_usar" };

	Object[] values;
	for (int row = 0; row < model.getRowCount(); row++) {
	    values = new Object[model.getColumnCount() + 1];
	    values[0] = code;
	    values[1] = model.getValueAt(row, 0);
	    values[2] = model.getValueAt(row, 1);
	    values[3] = model.getValueAt(row, 2);
	    values[4] = model.getValueAt(row, 3);
	    values[5] = model.getValueAt(row, 4);
	    session.insertRow(FonsaguaConstants.dataSchema,
		    FonsaguaConstants.FUENTES_IMPLICADAS, columnNames, values);
	}

    }

    public static void updateModelComunidades(TableModel model, String code)
	    throws SQLException {
	DBSession session = DBSession.getCurrentSession();

	String whereClause = getQueryWithCodeInsteadOfPlaceHolders(code,
		"WHERE cod_alternativa = '####' ");
	if (model.getRowCount() > 0) {
	    String comunidades = "";
	    for (int row = 0; row < model.getRowCount(); row++) {
		comunidades += "'" + model.getValueAt(row, 1) + "', ";
	    }
	    if (comunidades.endsWith(", ")) {
		comunidades = comunidades
			.substring(0, comunidades.length() - 2);
	    }

	    whereClause = whereClause + " AND cod_comunidad NOT IN ("
		    + comunidades + ")";
	}
	session.deleteRows(FonsaguaConstants.dataSchema,
		FonsaguaConstants.COMUNIDADES_IMPLICADAS, whereClause);

	final String[] columnNames = { "cod_alternativa", "comunidad",
		"cod_comunidad", "n_habitantes", "n_hab_alternativa" };

	Object[] values;
	for (int row = 0; row < model.getRowCount(); row++) {
	    values = new Object[model.getColumnCount()];
	    values[0] = code;
	    values[1] = model.getValueAt(row, 0);
	    values[2] = model.getValueAt(row, 1);
	    values[3] = model.getValueAt(row, 2);
	    values[4] = model.getValueAt(row, 3);
	    if (model.getValueAt(row, 4) == null) {
		session.insertRow(FonsaguaConstants.dataSchema,
			FonsaguaConstants.COMUNIDADES_IMPLICADAS, columnNames,
			values);
	    } else {
		session.updateRows(
			FonsaguaConstants.dataSchema,
			FonsaguaConstants.COMUNIDADES_IMPLICADAS,
			columnNames,
			values,
			"WHERE "
				+ FonsaguaConstants.COMUNIDADES_IMPLICADAS_PK_FIELD
				+ " = '" + model.getValueAt(row, 4) + "'");
	    }
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
	query = query.replace("##dataSchema##", FonsaguaConstants.dataSchema);
	query = query.replace("##preferencesTable##",
		AlternativesPreferences.ALTERNATIVAS_PREFERENCES_TABLE);
	return query;
    }

    public static String getQueryWithOutDataSchemaIfSQLiteSession(String sql) {
	DBSession session = DBSession.getCurrentSession();
	if (session instanceof DBSessionSpatiaLite) {
	    return sql.replace(FonsaguaConstants.dataSchema, "");
	}
	return sql;
    }

    public static String[] getCommunitiesCodes() {
	String[] communities = new String[0];
	try {
	    DBSession session = DBSession.getCurrentSession();

	    String[][] table = session.getTable(ComunidadesForm.NAME,
		    FonsaguaConstants.dataSchema,
		    new String[] { ComunidadesForm.PKFIELD }, null,
		    new String[] { ComunidadesForm.PKFIELD }, true);
	    communities = new String[table.length];
	    int i = 0;
	    for (String[] row : table) {
		communities[i++] = row[0];
	    }
	} catch (SQLException e) {
	    throw new ExternalError(e);
	}

	return communities;
    }

}
