package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public class JuntasAguaForm extends AbstractSubForm {

    public static final String NAME = "juntas_agua";
    public static String[] colNames = { "ubicacion", "contacto", "n_miembros",
	    "n_mujeres", "per_juridica" };
    public static String[] colAlias = { "Ubicación (Comunidad)", "Contacto",
	    "Nº Miembros", "Nº Mujeres", "Personalidad jurídica" };

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
