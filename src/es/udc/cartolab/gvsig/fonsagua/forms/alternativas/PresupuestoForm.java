package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.navtable.format.DoubleFormatNT;
import es.udc.cartolab.gvsig.users.utils.DBSession;

@SuppressWarnings("serial")
public class PresupuestoForm extends BasicAbstractSubForm implements KeyListener,
	FocusListener {

    public static final String NAME = "presupuesto";
    private String[] widgets = { "total_disenho", "total_distribucion",
	    "total_proteccion", "total_conduccion", "total_pilas",
	    "total_implementacion", "total_personal", "trampas", "biofiltros",
	    "resumidero", "let_fosa", "let_cierre", "let_aboneras", "cuota",
	    "aporte_comunidad" };
    private int poblacion;
    private Map<String, String> values = new HashMap<String, String>();
    // Had to add a NumberFormat because of the diferent decimal separators that
    // may be used. Take care with that.
    private NumberFormat format;

    public PresupuestoForm() {
	super();
	format = DoubleFormatNT.getDisplayingFormat();
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    public void fillValues() {
	super.fillValues();
	try {
	    String[] fields = { AlternativasForm.POBLACIONACTUALFIELD };
	    String codAlt = ((JTextField) getWidgets().get("cod_alternativa"))
		    .getText();
	    String[][] result = DBSession.getCurrentSession()
		    .getTable(
			    AlternativasForm.NAME,
			    FonsaguaConstants.dataSchema,
			    fields,
			    "WHERE " + AlternativasForm.PKFIELD + " = '"
				    + codAlt + "'", new String[0], false);
	    if (result.length > 0) {
		if (result[0].length > 0) {
		    poblacion = Integer.parseInt(result[0][0]);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	}
	computeFields();
    }

    private void computeFields() {
	for (String widget : widgets) {
	    values.put(widget,
		    ((JTextField) getWidgets().get(widget)).getText());
	}
	computeTotalAbastecimiento();
	computeTotalSaneamiento();
	computeTotalAbastSan();
	computeTotal();
	computeTotalPersona();
	computeCuotaPersona();
    }

    @Override
    public void setListeners() {
	super.setListeners();
	for (String widget : widgets) {
	    ((JTextField) getWidgets().get(widget)).addKeyListener(this);
	    ((JTextField) getWidgets().get(widget)).addFocusListener(this);
	}
    }

    @Override
    public void removeListeners() {
	super.removeListeners();
	for (String widget : widgets) {
	    ((JTextField) getWidgets().get(widget)).removeKeyListener(this);
	    ((JTextField) getWidgets().get(widget)).removeFocusListener(this);
	}
    }

    private void computeTotalAbastecimiento() {
	double a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0;

	try {
	    a = format.parse(values.get("total_disenho")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    b = format.parse(values.get("total_conduccion")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    c = format.parse(values.get("total_distribucion")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    d = format.parse(values.get("total_pilas")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    e = format.parse(values.get("total_proteccion")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    f = format.parse(values.get("total_implementacion")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    g = format.parse(values.get("total_personal")).doubleValue();
	} catch (ParseException ex) {
	}

	String result = format.format(new Double(a + b + c + d + e + f + g));
	values.put("total_abastecimiento", result);
	getFormController().setValue("total_abastecimiento", result);
	((JTextField) getWidgets().get("total_abastecimiento")).setText(result);
    }

    private void computeTotalSaneamiento() {
	double a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;

	try {
	    a = format.parse(values.get("trampas")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    b = format.parse(values.get("biofiltros")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    c = format.parse(values.get("resumidero")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    d = format.parse(values.get("let_fosa")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    e = format.parse(values.get("let_cierre")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    f = format.parse(values.get("let_aboneras")).doubleValue();
	} catch (ParseException ex) {
	}

	String result = format.format(new Double(a + b + c + d + e + f));
	values.put("total_saneamiento", result);
	getFormController().setValue("total_saneamiento", result);
	((JTextField) getWidgets().get("total_saneamiento")).setText(result);
    }

    private void computeTotalAbastSan() {
	double a = 0, b = 0;

	try {
	    a = format.parse(values.get("total_abastecimiento")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    b = format.parse(values.get("total_saneamiento")).doubleValue();
	} catch (ParseException ex) {
	}

	String result = format.format(new Double(a + b));
	values.put("total_abast_san", result);
	getFormController().setValue("total_abast_san", result);
	((JTextField) getWidgets().get("total_abast_san")).setText(result);
    }

    private void computeTotal() {
	double a = 0, b = 0;

	try {
	    a = format.parse(values.get("total_abast_san")).doubleValue();
	} catch (ParseException ex) {
	}

	try {
	    b = format.parse(values.get("aporte_comunidad")).doubleValue();
	} catch (ParseException ex) {
	}

	String result = format.format(new Double(a - b));
	values.put("total", result);
	getFormController().setValue("total", result);
	((JTextField) getWidgets().get("total")).setText(result);
    }

    private void computeTotalPersona() {
	double a = 0;

	if (poblacion > 0) {
	    try {
		a = format.parse(values.get("total")).doubleValue();
	    } catch (ParseException ex) {
	    }

	    String result = format.format(new Double(a / poblacion));
	    values.put("total_persona", result);
	    getFormController().setValue("total_persona", result);
	    ((JTextField) getWidgets().get("total_persona")).setText(result);
	}
    }

    private void computeCuotaPersona() {
	double a = 0;

	if (poblacion > 0) {
	    try {
		a = format.parse(values.get("cuota")).doubleValue();
	    } catch (ParseException ex) {
	    }

	    String result = format.format(new Double(a / poblacion));
	    values.put("cuota_persona", result);
	    getFormController().setValue("cuota_persona", result);
	    ((JTextField) getWidgets().get("cuota_persona")).setText(result);
	}
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
	computeFields();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
	computeFields();
    }
}
