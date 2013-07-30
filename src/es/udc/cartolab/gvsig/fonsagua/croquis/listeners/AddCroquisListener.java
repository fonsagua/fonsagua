package es.udc.cartolab.gvsig.fonsagua.croquis.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;

import es.udc.cartolab.gvsig.fonsagua.croquis.dao.ICroquisDAO;
import es.udc.cartolab.gvsig.fonsagua.croquis.dao.PostgresCroquisDAO;
import es.udc.cartolab.gvsig.fonsagua.croquis.dao.SQLiteCroquisDAO;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class AddCroquisListener implements ActionListener {

    private final String comunidadId;
    private Connection connection;
    private ICroquisDAO dao;

    public AddCroquisListener(String comunidadId) {
	this.comunidadId = comunidadId;
	connection = DBSession.getCurrentSession().getJavaConnection();
	String driver = DBSession.getCurrentSession().getDriverName();
	if (driver.equals("SpatiaLite JDBC Driver")
		|| driver.equals("SQLite Alphanumeric")) {
	    try {
		connection.commit();
	    } catch (SQLException e) {
		// Probably there was no commit created
	    }
	    dao = new SQLiteCroquisDAO();
	} else {
	    dao = new PostgresCroquisDAO();
	}
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	Connection connection = DBSession.getCurrentSession()
		.getJavaConnection();

	if (hasAlreadyCroquis()) {
	    Object[] overwriteCroquisOptions = {
		    PluginServices.getText(this, "croquis_msg_overwrite"),
		    PluginServices.getText(this, "croquis_msg_cancel") };

	    int m = JOptionPane.showOptionDialog(null,
		    PluginServices.getText(this, "croquis_msg_already_exists"),
		    null, JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.INFORMATION_MESSAGE, null,
		    overwriteCroquisOptions, overwriteCroquisOptions[1]);

	    if (m == JOptionPane.OK_OPTION) {
		addCroquis(true);
	    }
	} else {
	    addCroquis(false);
	}
    }

    private void addCroquis(boolean update) {
	final JFileChooser fileChooser = new JFileChooser();
	int returnVal = fileChooser.showOpenDialog(null);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    File croquis = fileChooser.getSelectedFile();
	    dao.insertCroquisIntoDb(connection,
		    comunidadId, croquis, update);
	    JOptionPane.showMessageDialog(null,
		    PluginServices.getText(this, "croquis_msg_added_croquis"));
	}
    }

    private boolean hasAlreadyCroquis() {
	try {
	    byte[] croquis = dao.readCroquisFromDb(
		    connection, comunidadId);
	    if (croquis != null) {
		return true;
	    } else {
		return false;
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	return false;
    }
}
