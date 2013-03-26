package es.udc.cartolab.gvsig.fonsagua.croquis.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public interface ICroquisDAO {

    public void insertCroquisIntoDb(Connection connection, String comunidadId,
	    File image, boolean update);

    public byte[] readCroquisFromDb(Connection connection, String comunidadId)
	    throws SQLException;

}
