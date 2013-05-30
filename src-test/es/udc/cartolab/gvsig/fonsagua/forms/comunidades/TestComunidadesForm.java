package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.croquis.dao.PostgresCroquisDAO;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.utils.ImageUtils;

public class TestComunidadesForm extends CommonMethodsForTestDBForms {

    Connection connection;

    @Override
    protected String getSchema() {
	return FonsaguaConstants.dataSchema;
    }

    @Override
    protected String getTableName() {
	return ComunidadesForm.NAME;
    }

    @Before
    public void doSetupCroquis() {
	String url = "jdbc:postgresql://localhost:5432/fonsagua";
	String user = "postgres";
	String passwd = "postgres";
	// postgresql-9.1-903.jdbc3.jar needs to be in the classpasth before th
	// other gvSIG jars related to pgsql.
	// Configure that in your classpath tab if you use eclipse
	try {
	    Class.forName("org.postgresql.Driver");
	    connection = DriverManager.getConnection(url, user, passwd);
	    connection.setAutoCommit(false);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	String query = "INSERT INTO fonsagua.comunidades(cod_comunidad, cod_departamento,"
		+ " cod_municipio, cod_canton, cod_caserio) VALUES ('1', '1', '1', '1', '1')";
	PreparedStatement statement;
	try {
	    statement = connection.prepareStatement(query);
	    statement.execute();
	    connection.commit();
	} catch (SQLException e) {
	    try {
		connection.close();
		connection = DriverManager.getConnection(url, user, passwd);
		connection.setAutoCommit(false);
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	}
    }

    @Test
    public void testingInsertAndReadCroquis() throws Exception {

	try {
	    // TODO: Change by example croquis
	    File image = new File("data-test/test.jpg");
	    String query = "DELETE FROM fonsagua.comunidades_croquis";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.execute();
	    connection.commit();

	    PostgresCroquisDAO postgresCroquis = new PostgresCroquisDAO();
	    postgresCroquis.insertCroquisIntoDb(connection, "1", image, false);
	    byte[] imageDbBytes = postgresCroquis.readCroquisFromDb(connection,
		    "1");

	    byte[] imageMockBytes = ImageUtils.convertImageToBytea(image);

	    assertTrue(Arrays.equals(imageDbBytes, imageMockBytes));
	} finally {
	    connection.rollback();
	}
    }

    @Test
    public void testingUpdateAndReadCroquis() throws Exception {

	try {
	    // TODO: Change by example croquis
	    File image = new File("data-test/test.jpg");
	    String query = "DELETE FROM fonsagua.comunidades_croquis";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.execute();
	    connection.commit();

	    PostgresCroquisDAO postgresCroquis = new PostgresCroquisDAO();
	    postgresCroquis.insertCroquisIntoDb(connection, "1", image, false);
	    File imageToUpdate = new File("data-test/test2.jpg");
	    postgresCroquis.insertCroquisIntoDb(connection, "1", imageToUpdate,
		    true);

	    byte[] imageDbBytes = postgresCroquis.readCroquisFromDb(connection,
		    "1");

	    byte[] imageMockBytes = ImageUtils
		    .convertImageToBytea(imageToUpdate);

	    assertTrue(Arrays.equals(imageDbBytes, imageMockBytes));
	} finally {
	    connection.rollback();
	}
    }

}
