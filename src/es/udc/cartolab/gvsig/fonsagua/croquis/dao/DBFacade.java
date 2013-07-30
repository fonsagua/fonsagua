package es.udc.cartolab.gvsig.fonsagua.croquis.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.users.utils.DBSession;

public class DBFacade {

    private Connection connection;

    public DBFacade() {
	String url = DBSession.getCurrentSession().getConnectionString();
	String user = DBSession.getCurrentSession().getUserName();
	String passwd = DBSession.getCurrentSession().getPassword();

	try {
	    connection = DriverManager.getConnection(url, user, passwd);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public Connection getConnection() {
	return connection;
    }
}
