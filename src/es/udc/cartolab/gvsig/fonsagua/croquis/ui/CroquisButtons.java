package es.udc.cartolab.gvsig.fonsagua.croquis.ui;

import javax.swing.JButton;

import com.iver.andami.PluginServices;

import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.AddCroquisListener;
import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.ShowCroquisListener;

public class CroquisButtons {

    private final JButton addCroquisButton;
    private final JButton showCroquisButton;

    private AddCroquisListener addCroquisListener;
    private ShowCroquisListener showCroquisListener;

    public CroquisButtons(String comunidadId) {
	addCroquisListener = new AddCroquisListener(comunidadId);
	showCroquisListener = new ShowCroquisListener(comunidadId);
	addCroquisButton = new JButton();
	addCroquisButton.setText("Añadir Croquis");
	addCroquisButton.setToolTipText(PluginServices.getText(this,
		"croquisButton_addCroquis"));
	addCroquisButton.addActionListener(addCroquisListener);

	showCroquisButton = new JButton();
	showCroquisButton.setText("Ver Croquis");
	showCroquisButton.setToolTipText(PluginServices.getText(this,
		"croquisButton_showCroquis"));
	showCroquisButton.addActionListener(showCroquisListener);
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
