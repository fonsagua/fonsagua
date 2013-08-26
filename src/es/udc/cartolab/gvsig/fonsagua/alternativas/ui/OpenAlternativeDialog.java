package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;

import es.udc.cartolab.gvsig.fonsagua.OpenAlternativeExtension;
import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;

public class OpenAlternativeDialog extends JPanel implements IWindow,
	ActionListener {

    private static Logger logger = Logger.getLogger("AlternativesExtension");

    /*
     * The next properties are the ones related to the interface itself, so they
     * don't need a real explanation
     */

    private WindowInfo windowInfo = null;
    private JButton cancelButton = null;
    private JButton okButton = null;
    // Here we store the relationships departamento -> municipio -> cantón ->
    // alternativa
    private Map<String, Map<String, Map<String, List<String>>>> divsCodes;
    // Translation from cod_departamento to nombre_departamento
    private Map<String, String> departNames;
    // Translation from cod_municipio to nombre_municipio
    private Map<String, String> municNames;
    // Translation from cod_canton to nombre_canton
    private Map<String, String> cantonNames;

    private JComboBox departCombo = null;
    private JComboBox municCombo = null;
    private JComboBox cantonCombo = null;
    private JComboBox alternCombo = null;

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.MODALDIALOG
		    | WindowInfo.PALETTE | WindowInfo.NOTCLOSABLE);
	    windowInfo.setTitle(PluginServices.getText(this,
		    "new_alternative_title"));
	    Dimension dim = getPreferredSize();
	    int width, height = 0;
	    if (dim.getHeight() > 550) {
		height = 550;
	    } else {
		height = 110;
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

    public OpenAlternativeDialog(
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes,
	    Map<String, String> departNames, Map<String, String> municNames,
	    Map<String, String> cantonNames) {
	super();
	this.divsCodes = divsCodes;
	this.departNames = departNames;
	this.municNames = municNames;
	this.cantonNames = cantonNames;
	try {
	    initialize();
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
    }

    private void initialize() throws Exception {

	this.setLayout(new MigLayout());

	JLabel label = new JLabel(PluginServices.getText(this, "Cod_depart"));
	this.add(label);

	Vector<Item> items = new Vector<Item>();
	for (String departCod : divsCodes.keySet()) {
	    items.add(new Item(departCod, departNames.get(departCod)));
	}

	departCombo = new JComboBox(items);
	departCombo.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
		    updateMunicCombo();
		    updateCantonCombo();
		    updateAlternativaCombo();
		}
	    }
	});
	this.add(departCombo, "w 220!, wrap");

	label = new JLabel(PluginServices.getText(this, "Cod_munic"));
	this.add(label);

	municCombo = new JComboBox();
	municCombo.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
		    updateCantonCombo();
		    updateAlternativaCombo();
		}
	    }
	});
	this.add(municCombo, "w 220!, wrap");
	updateMunicCombo();

	label = new JLabel(PluginServices.getText(this, "Cod_canton"));
	this.add(label);

	cantonCombo = new JComboBox();
	cantonCombo.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
		    updateAlternativaCombo();
		}
	    }
	});
	this.add(cantonCombo, "w 220!, wrap");
	updateCantonCombo();

	label = new JLabel(PluginServices.getText(this, "Cod_alternativa"));
	this.add(label);

	alternCombo = new JComboBox();
	this.add(alternCombo, "w 220!, wrap");
	updateAlternativaCombo();

	okButton = new JButton();
	okButton.setText(PluginServices.getText(this, "Ok"));
	okButton.addActionListener(this);

	cancelButton = new JButton();
	cancelButton.setText(PluginServices.getText(this, "Cancel"));
	cancelButton.addActionListener(this);

	this.add(okButton, "right, bottom, cell 1 5");
	this.add(cancelButton, "right, bottom, cell 1 5");

    }

    private void updateMunicCombo() {
	Vector<Item> items = new Vector<Item>();
	String selDepart = ((Item) departCombo.getSelectedItem()).id;
	if (divsCodes.containsKey(selDepart)) {
	    for (String municCod : divsCodes.get(selDepart).keySet()) {
		items.add(new Item(municCod, municNames.get(municCod)));
	    }
	}
	municCombo.setModel(new DefaultComboBoxModel(items));
	municCombo.setSelectedIndex(0);
    }

    private void updateCantonCombo() {
	Vector<Item> items = new Vector<Item>();
	String selDepart = ((Item) departCombo.getSelectedItem()).id;
	String selMunic = ((Item) municCombo.getSelectedItem()).id;
	if ((divsCodes.containsKey(selDepart))
		&& (divsCodes.get(selDepart).containsKey(selMunic))) {
	    for (String cantonCod : divsCodes.get(selDepart).get(selMunic)
		    .keySet()) {
		items.add(new Item(cantonCod, cantonNames.get(cantonCod)));
	    }
	}
	cantonCombo.setModel(new DefaultComboBoxModel(items));
	cantonCombo.setSelectedIndex(0);
    }

    private void updateAlternativaCombo() {
	Vector<String> items = new Vector<String>();
	String selDepart = ((Item) departCombo.getSelectedItem()).id;
	String selMunic = ((Item) municCombo.getSelectedItem()).id;
	String selCanton = ((Item) cantonCombo.getSelectedItem()).id;
	if ((divsCodes.containsKey(selDepart))
		&& (divsCodes.get(selDepart).containsKey(selMunic))
		&& (divsCodes.get(selDepart).get(selMunic)
			.containsKey(selCanton))) {
	    for (String alternCod : divsCodes.get(selDepart).get(selMunic)
		    .get(selCanton)) {
		items.add(alternCod);
	    }
	}
	alternCombo.setModel(new DefaultComboBoxModel(items));
	if (items.size() > 0) {
	    alternCombo.setSelectedIndex(0);
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	try {
	    if (e.getSource() == okButton) {
		String alternCod = alternCombo.getSelectedItem().toString();
		OpenAlternativeExtension.openAlternative(alternCod);
		OpenAlternativeExtension
			.setValidAlternative(DatabaseDirectAccessQueries
				.isValidAlternative(alternCod));
	    }
	} catch (Exception e1) {
	    OpenAlternativeExtension.setValidAlternative(false);
	    e1.printStackTrace();
	}
	PluginServices.getMDIManager().closeWindow(this);

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
