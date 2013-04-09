package es.udc.cartolab.gvsig.fonsagua.croquis.listeners;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;

import es.udc.cartolab.gvsig.fonsagua.croquis.dao.DBFacade;
import es.udc.cartolab.gvsig.fonsagua.croquis.dao.PostgresCroquisDAO;
import es.udc.cartolab.gvsig.fonsagua.utils.ImageUtils;

public class ShowCroquisListener implements ActionListener {

    private final String comunidadId;
    private Connection connection;

    public ShowCroquisListener(String comunidadId) {
	this.comunidadId = comunidadId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	try {
	    DBFacade dbFacade = new DBFacade();
	    connection = dbFacade.getConnection();

	    @SuppressWarnings("serial")
	    class CroquisWindow extends JPanel implements IWindow {

		@Override
		public WindowInfo getWindowInfo() {
		    WindowInfo viewInfo = null;
		    if (viewInfo == null) {
			viewInfo = new WindowInfo(WindowInfo.MODELESSDIALOG
				| WindowInfo.RESIZABLE | WindowInfo.PALETTE
				| WindowInfo.MAXIMIZABLE);
			viewInfo.setTitle("Croquis Comunidad");
			viewInfo.setWidth(800);
			viewInfo.setHeight(600);
		    }
		    return viewInfo;
		}

		@Override
		public Object getWindowProfile() {
		    return null;
		}

	    }

	    byte[] imageBytes = new PostgresCroquisDAO().readCroquisFromDb(
		    connection, comunidadId);
	    if (imageBytes == null) {
		JOptionPane.showMessageDialog(null, PluginServices.getText(
			this, "croquis_msg_not_exits_croquis"));
	    } else {
		Image image = ImageUtils.convertByteaToImage(imageBytes);
		JLabel label = new JLabel(new ImageIcon(image));
		CroquisWindow panel = new CroquisWindow();
		panel.add(label, BorderLayout.CENTER);
		PluginServices.getMDIManager().addWindow(panel);
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	} finally {
	    try {
		connection.close();
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	}

    }
}
