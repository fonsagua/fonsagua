package es.udc.cartolab.gvsig.fonsagua.croquis.listeners;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;

public class ShowCroquisListener extends BaseCroquisListener {

    public ShowCroquisListener(String comunidadId) {
	super(comunidadId);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	try {
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

	    InputStream is = readCroquis();
	    if (is == null) {
		JOptionPane.showMessageDialog(null, PluginServices.getText(
			this, "croquis_msg_not_exits_croquis"));
	    } else {
		Image image = ImageIO.read(is);
		JLabel label = new JLabel(new ImageIcon(image));
		CroquisWindow panel = new CroquisWindow();
		panel.add(label, BorderLayout.CENTER);
		PluginServices.getMDIManager().addWindow(panel);
	    }
	} catch (SQLException e1) {
	    e1.printStackTrace();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

    }
}
