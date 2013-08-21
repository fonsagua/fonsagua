package es.udc.cartolab.gvsig.fonsagua.utils;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.icarto.gvsig.navtableforms.DataBaseProperties;
import es.icarto.gvsig.navtableforms.Drivers;
import es.icarto.gvsig.navtableforms.TestProperties;
import es.udc.cartolab.gvsig.users.utils.DBSession;
import es.udc.cartolab.gvsig.users.utils.DBSessionPostGIS;

public class DatabaseDirectAccesQueriesTest {

    @BeforeClass
    public static void doSetupBeforeClass() {
	Drivers.initgvSIGDrivers(TestProperties.driversPath);
	try {
	    DBSessionPostGIS.createConnection(DataBaseProperties.server,
		    DataBaseProperties.port, DataBaseProperties.dbname, null,
		    DataBaseProperties.user, DataBaseProperties.userpwd);
	} catch (DBException e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void insertDefaultPreferences() throws SQLException {
	DatabaseDirectAccessQueries
		.insertDefaultPreferences("TEST-INSERT-DEFAULT-PREFERENCES");

	final String whereClause = "where cod_alternativa='TEST-INSERT-DEFAULT-PREFERENCES'";
	String[][] table = DBSession.getCurrentSession().getTable(
		FonsaguaConstants.preferencesTable,
		FonsaguaConstants.dataSchema,
		new String[] { "ano_horiz_sist" }, whereClause, null, true);
	assertEquals("20", table[0][0]);
	DBSession.getCurrentSession().deleteRows(FonsaguaConstants.dataSchema,
		FonsaguaConstants.preferencesTable, whereClause);
    }
}
