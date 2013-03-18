package es.udc.cartolab.fonsagua.forms;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Test;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.croquis.PostgresCroquis;
import es.udc.cartolab.gvsig.fonsagua.utils.ImageUtils;

public class TestComunidadesForm extends CommonMethodsForTestDBForms {

    @Override
    protected String getSchema() {
	return "public";
    }

    @Override
    protected String getTableName() {
	return ComunidadesForm.NAME;
    }

    @Test
    public void testingInsertAndReadCroquis() throws SQLException,
	    ClassNotFoundException {
	String url = "jdbc:postgresql://localhost:5432/fonsagua";
	String user = "postgres";
	String passwd = "postgres";
	// postgresql-9.1-903.jdbc3.jar needs to be in the classpasth before th
	// other gvSIG jars related to pgsql.
	// Configure that in your classpath tab if you use eclipse
	Class.forName("org.postgresql.Driver");

	Connection connection = DriverManager.getConnection(url, user, passwd);
	connection.setAutoCommit(false);

	try {
	    // TODO: Change by example croquis
	    File image = new File("/tmp/test.jpg");
	    String query = "SELECT id_comunidad FROM comunidades WHERE id_comunidad = 1";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.execute();
	    ResultSet rs = statement.getResultSet();

	    if (!rs.next()) {
		query = "INSERT INTO comunidades(id_comunidad) VALUES (1)";
		statement = connection.prepareStatement(query);
		statement.execute();
		connection.commit();
	    }

	    PostgresCroquis postgresCroquis = new PostgresCroquis();
	    postgresCroquis.insertCroquisIntoDb(connection, 1, image);
	    byte[] imageDbBytes = postgresCroquis.readCroquisFromDb(connection,
		    1);

	    byte[] imageMockBytes = ImageUtils.convertImageToBytea(image);

	    assertTrue(Arrays.equals(imageDbBytes, imageMockBytes));
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    connection.rollback();
	}
    }

}
