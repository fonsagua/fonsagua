package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import es.icarto.gvsig.navtableforms.BasicAbstractSubForm;

@SuppressWarnings("serial")
public class PersonalTecnicoForm extends BasicAbstractSubForm {

    public static final String NAME = "personal_tecnico";
    public static String[] colNames = { "nombre", "genero", "origen", "cargo" };
    public static String[] colAlias = { "Nombre", "G�nero", "Origen", "Cargo" };

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }
}
