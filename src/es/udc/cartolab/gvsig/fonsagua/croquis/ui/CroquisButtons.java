package es.udc.cartolab.gvsig.fonsagua.croquis.ui;

import javax.swing.JButton;

import com.iver.andami.PluginServices;

import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.AddCroquisListener;
import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.ShowCroquisListener;

public class CroquisButtons {

    private final JButton addCroquisButton;
    private final JButton showCroquisButton;

    public CroquisButtons(int comunidadId) {
	addCroquisButton = new JButton();
	addCroquisButton.setText("Añadir Croquis");
	addCroquisButton.setToolTipText(PluginServices.getText(this,
		"croquisButton_addCroquis"));
	addCroquisButton.addActionListener(new AddCroquisListener(comunidadId));

	showCroquisButton = new JButton();
	showCroquisButton.setText("Ver Croquis");
	showCroquisButton.setToolTipText(PluginServices.getText(this,
		"croquisButton_showCroquis"));
	showCroquisButton
		.addActionListener(new ShowCroquisListener(comunidadId));
    }

    public JButton getAddCroquisButton() {
	return addCroquisButton;
    }

    public JButton getShowCroquisButton() {
	return showCroquisButton;
    }

}
