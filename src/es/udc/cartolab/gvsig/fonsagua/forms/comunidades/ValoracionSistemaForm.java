package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;
import es.icarto.gvsig.navtableforms.ormlite.domainvalidator.listeners.DependentComboboxHandler;

@SuppressWarnings("serial")
public class ValoracionSistemaForm extends BasicAbstractSubForm {

    public static final String NAME = "valoracion_sistema";
    public static String[] colNames = { "cod_comunidad", "cod_abastecimiento",
	    "sist_cobros", "nivel_serv" };
    public static String[] colAlias = { "ID Comunidad", "C�d. Abastecimiento",
	    "Cobros", "Servicio" };

    private JComponent codComunidad;
    private DependentComboboxHandler abastecimientosDomainHandler;

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
	abastecimientosDomainHandler = new DependentComboboxHandler(this,
		codComunidad, codAbastecimiento);
	((JTextField) codComunidad)
		.addFocusListener(abastecimientosDomainHandler);
    }

    @Override
    public void removeListeners() {
	super.removeListeners();
	((JTextField) codComunidad)
		.removeFocusListener(abastecimientosDomainHandler);
    }
}
