package es.udc.cartolab.gvsig.fonsagua.prediseno.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.SingletonWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.ValidatorComponent;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.ValidatorDomain;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.ValidatorForm;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.rules.DoublePositiveRule;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.rules.IntegerPositiveRule;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.rules.PercentageRule;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.rules.ValidationRule;
import es.icarto.gvsig.navtableforms.utils.AbeilleParser;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class PredesignDialog extends JPanel implements ActionListener,
	SingletonWindow, KeyListener, FocusListener {

    public static String NAME = "predisenho";

    /*
     * The next properties are the ones related to the interface itself, so they
     * don't need a real explanation
     */

    private WindowInfo windowInfo = null;
    private ValidatorForm validator;
    private Map<String, JComponent> widgets;
    private JButton closeButton;
    private static boolean combosLoaded = false;

    /*
     * There is no need to read and parse the xml each time we press the button,
     * so we can keep an already built instance and show it as a singleton
     * window.
     */
    private static PredesignDialog instance = new PredesignDialog();

    public static void showDialog() {
	if (!combosLoaded) {
	    instance.loadCombos();
	    combosLoaded = true;
	}
	PluginServices.getMDIManager().addWindow(instance);
    }

    private PredesignDialog() {
	FormPanel formBody;
	InputStream stream = getClass().getClassLoader().getResourceAsStream(
		"ui/" + NAME + ".xml");
	try {
	    formBody = new FormPanel(stream);
	    widgets = AbeilleParser.getWidgetsFromContainer(formBody);
	    JScrollPane scrollPane = new JScrollPane(formBody);
	    MigLayout thisLayout = new MigLayout("inset 0, align center",
		    "[grow]", "[][grow][]");
	    this.setLayout(thisLayout);
	    this.add(scrollPane, "shrink, growx, growy, wrap");
	    JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    closeButton = new JButton(PluginServices.getText(this, "close"));
	    closeButton.setPreferredSize(new Dimension(80, 30));
	    buttons.add(closeButton);
	    this.add(buttons, "shrink, growx, h 40!, wrap");
	    createValidator();
	    setListeners();
	    super.repaint();
	    super.setVisible(true);
	} catch (FormException e) {
	    e.printStackTrace();
	}

    }

    private void loadCombos() {
	try {
	    String[] fields = {
		    FonsaguaConstants.tuberiasComercialesId,
		    FonsaguaConstants.tuberiasComercialesDiametro };
	    String[][] values = DBSession.getCurrentSession().getTable(
		    FonsaguaConstants.tuberiasComercialesTable,
		    FonsaguaConstants.dataSchema, fields, "", fields, false);
	    DefaultComboBoxModel model1 = new DefaultComboBoxModel();
	    model1.addElement(" ");
	    DefaultComboBoxModel model2 = new DefaultComboBoxModel();
	    model2.addElement(" ");
	    for (String[] row : values) {
		model1.addElement(new Item(row[0], row[1]));
		model2.addElement(new Item(row[0], row[1]));
	    }
	    ((JComboBox) widgets.get("diametro_impulsion")).setModel(model1);
	    ((JComboBox) widgets.get("diametro_impulsion_2")).setModel(model2);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private void createValidator() {
	validator = new ValidatorForm();
	Set<ValidationRule> vr = new HashSet<ValidationRule>();
	vr.add(new PercentageRule());
	ValidatorDomain vd = new ValidatorDomain(vr);
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("q_entra_gravedad"), vd));
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("q_entra_bombeo"), vd));
	vr = new HashSet<ValidationRule>();
	vr.add(new DoublePositiveRule());
	vd = new ValidatorDomain(vr);
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("altura_bombeo"), vd));
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("longitud"), vd));
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("caudal"), vd));
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("tiempo_bombeo"), vd));
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("tiempo_bombeo_2"), vd));
	vr = new HashSet<ValidationRule>();
	vr.add(new IntegerPositiveRule());
	vd = new ValidatorDomain(vr);
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("poblacion_disenho"), vd));
	validator.addComponentValidator(new ValidatorComponent(widgets
		.get("poblacion_sistema"), vd));
    }

    private void setListeners() {
	closeButton.addActionListener(this);
	for (String key : widgets.keySet()) {
	    widgets.get(key).addKeyListener(this);
	}
    }

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.PALETTE
		    | WindowInfo.RESIZABLE);
	    windowInfo.setTitle(PluginServices.getText(this, "predesign"));
	    windowInfo.setWidth(655);
	    windowInfo.setHeight(645);
	}
	return windowInfo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(closeButton)) {
	    PluginServices.getMDIManager().closeWindow(this);
	}

    }

    private void validateAndCompute() {
	validator.validate();
	if (!validator.hasValidationErrors()) {
	    compute();
	}
    }

    private void compute() {
	String caudal_s = ((JTextField) widgets.get("caudal")).getText();
	String t_bombeo_1 = ((JTextField) widgets.get("tiempo_bombeo"))
		.getText();
	String t_bombeo_2 = ((JTextField) widgets.get("tiempo_bombeo_2"))
		.getText();
	if ((caudal_s.length() > 0) && (t_bombeo_1.length() > 0)) {
	    Double aux = Double.parseDouble(caudal_s) * 24
		    / Double.parseDouble(t_bombeo_1);
	    ((JTextField) widgets.get("caudal_bombeo")).setText(aux.toString());
	} else {
	    ((JTextField) widgets.get("caudal_bombeo")).setText("");
	}
	if ((caudal_s.length() > 0) && (t_bombeo_2.length() > 0)) {
	    Double aux = Double.parseDouble(caudal_s) * 24
		    / Double.parseDouble(t_bombeo_2);
	    ((JTextField) widgets.get("caudal_bombeo_2")).setText(aux
		    .toString());
	} else {
	    ((JTextField) widgets.get("caudal_bombeo_2")).setText("");
	}
    }

    @Override
    public Object getWindowProfile() {
	return null;
    }

    @Override
    public Object getWindowModel() {
	return NAME;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
	validateAndCompute();
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
	validateAndCompute();
    }

    private class Item {
	private Double value;
	private String description;

	public Item(String description, String value) {
	    try {
		this.value = Double.parseDouble(value);
	    } catch (NumberFormatException e) {
		this.value = null;
	    }
	    this.description = description;
	}

	@Override
	public String toString() {
	    return description;
	}

	public double getValue() {
	    return value;
	}
    }

}
