package es.udc.cartolab.gvsig.fonsagua.croquis.listeners;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;

import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class AddCroquisListener extends BaseCroquisListener {

    public AddCroquisListener(String comunidadId) {
	super(comunidadId);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

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
	    DBSession session = DBSession.getCurrentSession();
	    try {
		if (update) {
		    FileInputStream fis = new FileInputStream(croquis);
		    session.updateWithBinaryStream(
			    FonsaguaConstants.CROQUIS_TABLENAME,
			    FonsaguaConstants.dataSchema,
			    FonsaguaConstants.CROQUIS_FIELDNAME,
			    fis,
			    (int) croquis.length(),
			    new String[0],
			    new Object[0],
			    "WHERE "
				    + FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
				    + " = '" + comunidadId + "'");
		} else {
		    Object[] values = { comunidadId };
		    String[] columns = { FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME };
		    FileInputStream fis = new FileInputStream(croquis);
		    session.insertWithBinaryStream(
			    FonsaguaConstants.CROQUIS_TABLENAME,
			    FonsaguaConstants.dataSchema,
			    FonsaguaConstants.CROQUIS_FIELDNAME, fis,
			    (int) croquis.length(), columns, values);
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    JOptionPane.showMessageDialog(null,
		    PluginServices.getText(this, "croquis_msg_added_croquis"));
	}
    }

    private boolean hasAlreadyCroquis() {
	try {
	    return (readCroquis() != null);
	} catch (SQLException e1) {
	    e1.printStackTrace();
	}
	return false;
    }
}
