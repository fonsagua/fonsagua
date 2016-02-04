package es.udc.cartolab.gvsig.fonsagua.utils;

import java.awt.Component;
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

import org.apache.log4j.Logger;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.commons.gui.AbstractIWindow;
import es.icarto.gvsig.commons.gui.WidgetFactory;

@SuppressWarnings("serial")
public abstract class FilteredDialog extends AbstractIWindow implements
ActionListener {
    private final static Logger logger = Logger.getLogger(FilteredDialog.class);

    /*
     * The next properties are the ones related to the interface itself, so they
     * don't need a real explanation
     */

    // Here we store the relationships departamento -> municipio -> cantón ->
    // element filtered by those fields
    private final Map<String, Map<String, Map<String, List<String>>>> divsCodes;
    // Translation from cod_departamento to nombre_departamento
    private final Map<String, String> departNames;
    // Translation from cod_municipio to nombre_municipio
    private final Map<String, String> municNames;
    // Translation from cod_canton to nombre_canton
    private final Map<String, String> cantonNames;

    private JComboBox departCombo = null;
    private JComboBox municCombo = null;
    private JComboBox cantonCombo = null;
    protected JComboBox elementCombo = null;

    protected abstract String getElementLabel();

    public FilteredDialog(
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
	    WidgetFactory.okCancelPanel(this, this, this);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
    }

    private void initialize() throws Exception {

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

	label = new JLabel(PluginServices.getText(this, getElementLabel()));
	this.add(label);

	elementCombo = new JComboBox();
	this.add(elementCombo, "w 220!, wrap");
	updateAlternativaCombo();
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
	elementCombo.setModel(new DefaultComboBoxModel(items));
	if (items.size() > 0) {
	    elementCombo.setSelectedIndex(0);
	}
    }

    private class Item {
	private final String id;
	private final String description;

	public Item(String id, String description) {
	    this.id = id;
	    this.description = description;
	}

	@Override
	public String toString() {
	    return description;
	}
    }

    @Override
    protected JButton getDefaultButton() {
	return null;
    }

    @Override
    protected Component getDefaultFocusComponent() {
	return null;
    }

}
