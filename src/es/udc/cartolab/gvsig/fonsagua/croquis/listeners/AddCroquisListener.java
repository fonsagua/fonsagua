package es.udc.cartolab.gvsig.fonsagua.croquis.listeners;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.commons.utils.ImageUtils;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class AddCroquisListener extends BaseCroquisListener {


    private static final Logger logger = Logger
	    .getLogger(AddCroquisListener.class);

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

	    ByteArrayOutputStream os = null;
	    ByteArrayInputStream is = null;
	    try {
		Image image = ImageUtils.getScaledAsImage(croquis
			.getAbsolutePath(), new Dimension(
			ShowCroquisListener.IMAGE_WIDTH,
			ShowCroquisListener.IMAGE_HEIGHT));
		BufferedImage bufImage = ImageUtils.getRenderedImage(image);
		os = new ByteArrayOutputStream();
		ImageIO.write(bufImage, "png", os);
		os.flush();
		is = new ByteArrayInputStream(os.toByteArray());
	    } catch (IOException e1) {
		logger.error(e1.getStackTrace(), e1);
	    }
	    if ((os == null) || (is == null)) {
		return;
	    }

	    DBSession session = DBSession.getCurrentSession();
	    try {
		if (update) {
		    session.updateWithBinaryStream(
			    FonsaguaConstants.CROQUIS_TABLENAME,
			    FonsaguaConstants.dataSchema,
			    FonsaguaConstants.CROQUIS_FIELDNAME,
			    is,
			    os.size(),
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
			    os.size(), columns, values);
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
