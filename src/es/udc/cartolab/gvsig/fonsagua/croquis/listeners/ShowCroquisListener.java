package es.udc.cartolab.gvsig.fonsagua.croquis.listeners;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.commons.image.ImageWindow;

public class ShowCroquisListener extends BaseCroquisListener {

    public static final int IMAGE_WIDTH = 750;
    public static final int IMAGE_HEIGHT = 550;

    public ShowCroquisListener(String comunidadId) {
        super(comunidadId);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            InputStream is = readCroquis();
            if (is == null) {
                JOptionPane.showMessageDialog(null, PluginServices.getText(this, "croquis_msg_not_exits_croquis"));
            } else {
                Image image = ImageIO.read(is);
                ImageIcon imageIcon = new ImageIcon(image);
                ImageWindow panel = new ImageWindow("Croquis Comunidad", IMAGE_WIDTH, IMAGE_HEIGHT);
                panel.setImage(imageIcon);
                PluginServices.getMDIManager().addWindow(panel);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
