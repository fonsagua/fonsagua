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

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.utils.ImageUtils;
import es.udc.cartolab.gvsig.users.utils.DBSession;
import es.udc.cartolab.gvsig.users.utils.DBSessionPostGIS;

public class TestComunidadesCroquis {

    Connection connection;
    DBSession session;

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
	    session = DBSessionPostGIS.createConnectionFromConnString(url,
		    user, passwd);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (DBException e) {
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

	    byte[] imageBytes = ImageUtils.convertImageToBytea(image);
	    Object[] values = { "1", imageBytes };
	    String[] columns = {
		    FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME,
		    FonsaguaConstants.CROQUIS_FIELDNAME };
	    session.insertRow(FonsaguaConstants.dataSchema,
		    FonsaguaConstants.CROQUIS_TABLENAME, columns, values);

	    String[] columns3 = { FonsaguaConstants.CROQUIS_FIELDNAME };
	    Object[][] values3 = session.getTableAsObjects(
		    FonsaguaConstants.CROQUIS_TABLENAME,
		    FonsaguaConstants.dataSchema, columns3, "WHERE "
			    + FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
			    + " = '1'", new String[0], false);
	    byte[] imageDbBytes = (byte[]) values3[0][0];

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

	    byte[] imageBytes = ImageUtils.convertImageToBytea(image);
	    Object[] values = { "1", imageBytes };
	    String[] columns = {
		    FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME,
		    FonsaguaConstants.CROQUIS_FIELDNAME };
	    session.insertRow(FonsaguaConstants.dataSchema,
		    FonsaguaConstants.CROQUIS_TABLENAME, columns, values);
	    File imageToUpdate = new File("data-test/test2.jpg");
	    imageBytes = ImageUtils.convertImageToBytea(imageToUpdate);
	    Object[] values2 = { imageBytes };
	    String[] columns2 = { FonsaguaConstants.CROQUIS_FIELDNAME };
	    session.updateRows(FonsaguaConstants.dataSchema,
		    FonsaguaConstants.CROQUIS_TABLENAME, columns2, values2,
		    "WHERE " + FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
			    + " = '1'");

	    String[] columns3 = { FonsaguaConstants.CROQUIS_FIELDNAME };
	    Object[][] values3 = session.getTableAsObjects(
		    FonsaguaConstants.CROQUIS_TABLENAME,
		    FonsaguaConstants.dataSchema, columns3, "WHERE "
			    + FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
			    + " = '1'", new String[0], false);
	    byte[] imageDbBytes = (byte[]) values3[0][0];

	    byte[] imageMockBytes = ImageUtils
		    .convertImageToBytea(imageToUpdate);

	    assertTrue(Arrays.equals(imageDbBytes, imageMockBytes));
	} finally {
	    connection.rollback();
	}
    }

}
