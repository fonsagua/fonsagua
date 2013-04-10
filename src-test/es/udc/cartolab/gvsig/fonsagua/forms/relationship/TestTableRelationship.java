package es.udc.cartolab.gvsig.fonsagua.forms.relationship;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iver.cit.gvsig.fmap.drivers.DBException;
import com.iver.cit.gvsig.fmap.layers.LayerFactory;

import es.icarto.gvsig.navtableforms.DataBaseProperties;
import es.icarto.gvsig.navtableforms.TestProperties;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class TestTableRelationship {

    private TableRelationship testingRelationship;

    static DBSession connection;

    @BeforeClass
    public static void doSetupBeforeClass() {
	initgvSIGDrivers();
	try {
	    connection = DBSession.createConnection(DataBaseProperties.server,
		    DataBaseProperties.port, DataBaseProperties.dbname, null,
		    DataBaseProperties.superuser, "postgres");
	} catch (DBException e) {
	    e.printStackTrace();
	}

    }

    private static void initgvSIGDrivers() {

	final String fwAndamiDriverPath = TestProperties.driversPath;

	final File baseDriversPath = new File(fwAndamiDriverPath);
	if (!baseDriversPath.exists()) {
	    throw new RuntimeException("Can't find drivers path: "
		    + fwAndamiDriverPath);
	}

	LayerFactory.setDriversPath(baseDriversPath.getAbsolutePath());
	if (LayerFactory.getDM().getDriverNames().length < 1) {
	    throw new RuntimeException("Can't find drivers in path: "
		    + fwAndamiDriverPath);
	}
    }

    @Before
    public void doSetup() {
	try {
	    PreparedStatement statement;
	    String query = "INSERT INTO fonsagua.comunidades (cod_comunidad, cod_departamento, cod_municipio, "
		    + "cod_canton, cod_caserio) VALUES ('999', '01', '01', '01', '01')";
	    String query2 = "INSERT INTO fonsagua.abastecimientos (cod_abastecimiento) VALUES ('998')";
	    statement = DBSession.getCurrentSession().getJavaConnection()
		    .prepareStatement(query);
	    statement.execute();
	    statement = DBSession.getCurrentSession().getJavaConnection()
		    .prepareStatement(query2);
	    statement.execute();
	} catch (SQLException e) {
	    try {
		connection.close();
		connection = DBSession.createConnection(
			DataBaseProperties.server, DataBaseProperties.port,
			DataBaseProperties.dbname, null,
			DataBaseProperties.superuser, "postgres");
	    } catch (DBException e1) {
		e1.printStackTrace();
	    }
	}

	testingRelationship = new TableRelationship(null, "comunidades",
		"cod_comunidad", "abastecimientos", "cod_abastecimiento",
		"r_abastecimientos_comunidades", FonsaguaConstants.dataSchema);
	testingRelationship.setPrimaryPKValue("999");
    }

    @Test
    public void testInsertRow() {
	testingRelationship.insertRow("998");
	try {
	    PreparedStatement statement;
	    String query = "SELECT cod_abastecimiento FROM fonsagua.r_abastecimientos_comunidades "
		    + "WHERE cod_comunidad='999'";
	    statement = DBSession.getCurrentSession().getJavaConnection()
		    .prepareStatement(query);
	    statement.execute();
	    ResultSet rs = statement.getResultSet();
	    while (rs.next()) {
		assertTrue(rs.getString(1).equals("998"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testdeleteRow() {
	testingRelationship.deleteRow("998");
	try {
	    PreparedStatement statement;
	    String query = "SELECT cod_abastecimiento FROM fonsagua.r_abastecimientos_comunidades "
		    + "WHERE cod_comunidad='999'";
	    statement = DBSession.getCurrentSession().getJavaConnection()
		    .prepareStatement(query);
	    statement.execute();
	    ResultSet rs = statement.getResultSet();
	    while (rs.next()) {
		assertFalse(rs.getString(1).equals("998"));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
