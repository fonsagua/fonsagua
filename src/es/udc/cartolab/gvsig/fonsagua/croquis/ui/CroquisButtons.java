package es.udc.cartolab.gvsig.fonsagua.croquis.ui;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.iver.andami.PluginServices;

import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.AddCroquisListener;
import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.ShowCroquisListener;

public class CroquisButtons {

    private JButton addCroquisButton;
    private JButton showCroquisButton;

    private AddCroquisListener addCroquisListener;
    private ShowCroquisListener showCroquisListener;

    public CroquisButtons(String comunidadId) {
	try {
	    Image addIcon = ImageIO.read(getClass().getClassLoader()
		    .getResourceAsStream("images/croquis_add.png"));
	    Image showIcon = ImageIO.read(getClass().getClassLoader()
		    .getResourceAsStream("images/croquis_view.png"));

	    addCroquisListener = new AddCroquisListener(comunidadId);
	    showCroquisListener = new ShowCroquisListener(comunidadId);

	    addCroquisButton = new JButton();
	    addCroquisButton.setIcon(new ImageIcon(addIcon));
	    addCroquisButton.setToolTipText(PluginServices.getText(this,
		    "croquisButton_addCroquis"));
	    addCroquisButton.addActionListener(addCroquisListener);

	    showCroquisButton = new JButton();
	    showCroquisButton.setIcon(new ImageIcon(showIcon));
	    showCroquisButton.setToolTipText(PluginServices.getText(this,
		    "croquisButton_showCroquis"));
	    showCroquisButton.addActionListener(showCroquisListener);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public JButton getAddCroquisButton() {
	return addCroquisButton;
    }

    public JButton getShowCroquisButton() {
	return showCroquisButton;
    }

    public void setAddlistener(AddCroquisListener addListener) {
	addCroquisButton.removeActionListener(addCroquisListener);
	addCroquisListener = addListener;
	addCroquisButton.addActionListener(addCroquisListener);
    }

    public void setShowlistener(ShowCroquisListener showListener) {
	showCroquisButton.removeActionListener(showCroquisListener);
	showCroquisListener = showListener;
	showCroquisButton.addActionListener(showCroquisListener);
    }
}
