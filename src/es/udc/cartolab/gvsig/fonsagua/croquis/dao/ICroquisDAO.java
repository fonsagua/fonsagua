package es.udc.cartolab.gvsig.fonsagua.croquis.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public interface ICroquisDAO {

    public void insertCroquisIntoDb(Connection connection, int comunidadId,
	    File image, boolean update);

    public byte[] readCroquisFromDb(Connection connection, int comunidadId)
	    throws SQLException;

}
