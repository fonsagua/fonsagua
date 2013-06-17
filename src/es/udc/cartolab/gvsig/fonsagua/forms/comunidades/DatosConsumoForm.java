package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.listeners.DependentComboboxesHandler;

@SuppressWarnings("serial")
public class DatosConsumoForm extends AbstractSubForm {

    public static final String NAME = "datos_consumo";
    public static String[] colNames = { "cod_comunidad", "tipo_abastecimiento",
	    "consumo", "n_miembros" };
    public static String[] colAlias = { "ID Comunidad", "Tipo abastecimiento",
	    "Consumo (l/d�a)", "N� miembros familia" };

    private JComponent codComunidad;
    private DependentComboboxesHandler abastecimientosDomainHandler;

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
	abastecimientosDomainHandler.updateComboBoxValues();
    }

    @Override
    public void setListeners() {
	super.setListeners();
	codComunidad = getWidgets().get("cod_comunidad");
	JComboBox codAbastecimiento = (JComboBox) getWidgets().get(
		"cod_abastecimiento");
	abastecimientosDomainHandler = new DependentComboboxesHandler(this,
		codComunidad, codAbastecimiento);
	((JTextField) codComunidad)
		.addActionListener(abastecimientosDomainHandler);
    }

    @Override
    public void removeListeners() {
	super.removeListeners();
	((JTextField) codComunidad)
		.addActionListener(abastecimientosDomainHandler);
    }
}
