package es.udc.cartolab.gvsig.fonsagua.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import es.udc.cartolab.gvsig.fonsagua.AlternativeBudgetReportExtension;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public abstract class Utils {

    private static final Logger logger = Logger
	    .getLogger(AlternativeBudgetReportExtension.class);

    private final static List<String> reservedColumns = Arrays
	    .asList(new String[] { "gid", "the_geom", "geom" });

    public static List<String> getFields(String schema,
	    String table, List<String> ignoreColumns) {
	List<String> fields = new ArrayList<String>();
	try {
	    DBSession session = DBSession.getCurrentSession();
	    String[] columns = session.getColumns(schema, table);
	    List<String> asList = Arrays.asList(columns);

	    for (String c : asList) {
		if (ignoreColumns.contains(c)) {
		    continue;
		}
		fields.add(c);
	    }
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	}
	return fields;
    }

    public static List<String> getFields(String schema, String table) {
	return getFields(schema, table, reservedColumns);
    }

}
