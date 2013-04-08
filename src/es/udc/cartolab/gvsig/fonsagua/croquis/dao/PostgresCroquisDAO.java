package es.udc.cartolab.gvsig.fonsagua.croquis.dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.utils.ImageUtils;

public class PostgresCroquisDAO implements ICroquisDAO {

    private final String CROQUIS_TABLENAME = "comunidades_croquis";
    private final String CROQUIS_FIELDNAME = "croquis";
    private final String CROQUIS_COMUNIDAD_FK_FIELDNAME = "cod_comunidad";

    @Override
    public void insertCroquisIntoDb(Connection connection, String comunidadId,
	    File image, boolean update) {
	try {
	    byte[] imageBytes = ImageUtils.convertImageToBytea(image);
	    PreparedStatement statement;
	    if (update) {
		statement = connection.prepareStatement("UPDATE "
			+ FonsaguaConstants.dataSchema + "."
			+ CROQUIS_TABLENAME + " SET " + CROQUIS_FIELDNAME
			+ " = " + "? WHERE " + CROQUIS_COMUNIDAD_FK_FIELDNAME
			+ " = ?");
		statement.setBytes(1, imageBytes);
		statement.setString(2, comunidadId);
	    } else {
		statement = connection.prepareStatement("INSERT INTO "
			+ FonsaguaConstants.dataSchema + "."
			+ CROQUIS_TABLENAME + " VALUES (?, ?)");
		statement.setString(1, comunidadId);
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
    public byte[] readCroquisFromDb(Connection connection, String comunidadId)
	    throws SQLException {
	PreparedStatement statement = null;
	try {
	    statement = connection.prepareStatement("SELECT "
		    + CROQUIS_FIELDNAME + " FROM "
		    + FonsaguaConstants.dataSchema + "." + CROQUIS_TABLENAME
		    + " WHERE " + CROQUIS_COMUNIDAD_FK_FIELDNAME + " = ?");
	    statement.setString(1, comunidadId);
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
