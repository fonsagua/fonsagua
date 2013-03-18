package es.udc.cartolab.gvsig.fonsagua.croquis;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public interface ICroquis {

    public void insertCroquisIntoDb(Connection connection, int comunidadId,
	    File image);

    public byte[] readCroquisFromDb(Connection connection, int comunidadId) throws SQLException;

}
