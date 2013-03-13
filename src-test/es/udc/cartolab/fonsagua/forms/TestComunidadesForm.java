package es.udc.cartolab.fonsagua.forms;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import es.icarto.gvsig.navtableforms.CommonMethodsForTestDBForms;
import es.udc.cartolab.gvsig.fonsagua.utils.croquis;
import es.udc.cartolab.gvsig.users.utils.DBSession;

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
    public void imageInsertedOnDb() {
	try {
	File image = new File("/tmp/bl1280.ppg");
	Connection connection = DBSession.getCurrentSession().getJavaConnection();
	String query = "SELECT id_comunidad FROM comunidades WHERE id_comunidad = '1'";
	PreparedStatement statement = connection.prepareStatement(query);
	statement.execute();
	ResultSet rs = statement.getResultSet();
	if (!rs.next()) {
	    query = "INSERT INTO comunidades(id_comunidad) VALUES('1')";
	    statement = connection.prepareStatement(query);
	    statement.execute();
	}
	croquis.insertCroquisIntoDb("1", image);
	query = "SELECT image FROM comunidades_croquis WHERE id_comunidad = '1'";
	statement = connection.prepareStatement(query);
	statement.execute();
	rs = statement.getResultSet();
	rs.next();
	InputStream streamFromDb = rs.getBinaryStream(1);
	FileInputStream inputStream = new FileInputStream(image);
	assertTrue(streamFromDb.equals(inputStream));
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

}
