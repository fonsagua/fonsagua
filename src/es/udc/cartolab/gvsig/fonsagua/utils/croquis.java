package es.udc.cartolab.gvsig.fonsagua.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.users.utils.DBSession;

public class croquis {
    
    public static void insertCroquisIntoDb(String comunidadId, File image) {
	try {
	    Connection connection = DBSession.getCurrentSession()
		    .getJavaConnection();
	    FileInputStream inputStream = new FileInputStream(image);
	    PreparedStatement statement;
	    statement = connection.prepareStatement("INSERT INTO comunidades_croquis VALUES (?, ?)");
	    statement.setString(1, comunidadId);
	    statement.setBinaryStream(2, inputStream, image.length());
	    statement.executeUpdate();
	    statement.close();
	    statement.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

}
