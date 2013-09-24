package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;


@SuppressWarnings("serial")
public class SelectCommunityForAltPriorDialog extends JPanel implements IWindow,
	ActionListener {
    private Logger logger = Logger.getLogger("AlternativePriorExtension");

    /*
     * The next properties are the ones related to the interface itself, so they
     * don't need a real explanation
     */

    private WindowInfo windowInfo = null;
    private JButton cancelButton = null;
    protected JButton okButton = null;
    // Código comunidad -> nombre comunidad
    private Map<String, String> comunidades;

    protected JComboBox comunidadCombo = null;

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.MODALDIALOG
		    | WindowInfo.PALETTE | WindowInfo.NOTCLOSABLE);
	    windowInfo.setTitle(PluginServices.getText(this,
		    "comunidad_alt_prior_dialog"));
	    Dimension dim = getPreferredSize();
	    int width, height = 0;
	    if (dim.getHeight() > 250) {
		height = 250;
	    } else {
		height = 30;
	    }
	    if (dim.getWidth() > 550) {
		width = 550;
	    } else {
		width = new Double(dim.getWidth()).intValue() + 5;
	    }
	    windowInfo.setWidth(width);
	    windowInfo.setHeight(height);
	}
	return windowInfo;
    }

    public SelectCommunityForAltPriorDialog(Map<String, String> comunidades) {
	super();
	this.comunidades = comunidades;
	try {
	    initialize();
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
    }

    private void initialize() throws Exception {

	this.setLayout(new MigLayout());

	JLabel label = new JLabel(PluginServices.getText(this, "comunidad"));
	this.add(label);

	Vector<Item> items = new Vector<Item>();
	for (String comunidadCod : comunidades.keySet()) {
	    items.add(new Item(comunidadCod, comunidades.get(comunidadCod)));
	}

	comunidadCombo = new JComboBox(items);
	this.add(comunidadCombo, "w 220!, wrap");

	okButton = new JButton();
	okButton.setText(PluginServices.getText(this, "Ok"));
	okButton.addActionListener(this);

	cancelButton = new JButton();
	cancelButton.setText(PluginServices.getText(this, "Cancel"));
	cancelButton.addActionListener(this);

	this.add(okButton, "right, bottom, cell 1 5");
	this.add(cancelButton, "right, bottom, cell 1 5");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == okButton) {
	    String communityCode = ((Item) comunidadCombo.getSelectedItem()).id;
	    String communityName = ((Item) comunidadCombo.getSelectedItem()).description;
	    if ((communityCode != null) && (communityCode.length() > 0)) {
		    PluginServices.getMDIManager()
			    .addWindow(
				    new PriorizacionDialog(communityCode,
					    communityName));
	    }
	    PluginServices.getMDIManager().closeWindow(this);
	}
    }

    @Override
    public Object getWindowProfile() {
	return null;
    }

    private class Item {
	private String id;
	private String description;

	public Item(String id, String description) {
	    this.id = id;
	    this.description = description;
	}

	@Override
	public String toString() {
	    return description;
	}
    }

}
