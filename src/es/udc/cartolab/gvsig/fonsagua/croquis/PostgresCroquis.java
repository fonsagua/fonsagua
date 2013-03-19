package es.udc.cartolab.gvsig.fonsagua.croquis;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.fonsagua.utils.ImageUtils;

public class PostgresCroquis implements ICroquis {

    private final String CROQUIS_TABLENAME = "comunidades_croquis";
    private final String CROQUIS_FIELDNAME = "croquis";
    private final String CROQUIS_COMUNIDAD_FK_FIELDNAME = "id_comunidad";

    @Override
    public void insertCroquisIntoDb(Connection connection, int comunidadId,
	    File image, boolean update) {
	try {
	    byte[] imageBytes = ImageUtils.convertImageToBytea(image);
	    PreparedStatement statement;
	    if (update) {
		statement = connection.prepareStatement("UPDATE "
			+ CROQUIS_TABLENAME + " SET " + CROQUIS_FIELDNAME
			+ " = " + "? WHERE " + CROQUIS_COMUNIDAD_FK_FIELDNAME
			+ " = ?");
		statement.setBytes(1, imageBytes);
		statement.setInt(2, comunidadId);
	    } else {
		statement = connection.prepareStatement("INSERT INTO "
			+ CROQUIS_TABLENAME + " VALUES (?, ?)");
		statement.setInt(1, comunidadId);
		statement.setBytes(2, imageBytes);
	    }
	    statement.executeUpdate();
	    if (!connection.getAutoCommit()) {
		connection.commit();
	    }
	    statement.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public byte[] readCroquisFromDb(Connection connection, int comunidadId)
	    throws SQLException {
	PreparedStatement statement = null;
	try {
	    statement = connection.prepareStatement("SELECT "
		    + CROQUIS_FIELDNAME + " FROM " + CROQUIS_TABLENAME
		    + " WHERE " + CROQUIS_COMUNIDAD_FK_FIELDNAME + " = ?");
	    statement.setInt(1, comunidadId);
	    ResultSet rs = statement.executeQuery();
	    if (rs.next()) {
		return rs.getBytes(1);
	    } else {
		return null;
	    }
	} finally {
	    if (statement != null) {
		try {
		    statement.close();
		} catch (SQLException ignored) {
		}
	    }
	}
    }

}
