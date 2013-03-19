package es.udc.cartolab.gvsig.fonsagua.croquis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.users.utils.DBSession;

public class DBFacade {

    private Connection connection;

    public DBFacade() {
	String server = DBSession.getCurrentSession().getServer();
	int port = DBSession.getCurrentSession().getPort();
	String database = DBSession.getCurrentSession().getDatabase();

	String url = "jdbc:postgresql://" + server + ":" + port + "/"
		+ database;
	String user = DBSession.getCurrentSession().getUserName();
	String passwd = DBSession.getCurrentSession().getPassword();

	try {
	    Class.forName("org.postgresql.Driver");
	    connection = DriverManager.getConnection(url, user, passwd);
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public Connection getConnection() {
	return connection;
    }
}
