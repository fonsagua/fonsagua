package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class JuntasAguaForm extends AbstractSubForm {

    public static final String NAME = "juntas_agua";
    public static String[] colNames = {
"ubicacion", "contacto", "n_miembros", "n_mujeres"
    };
    public static String[] colAlias = {
"Ubicaci�n", "Contacto", "N� Miembros", "N� Mujeres"
    };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
