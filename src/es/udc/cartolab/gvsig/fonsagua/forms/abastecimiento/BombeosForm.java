package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class BombeosForm extends BasicAbstractForm {

    public static final String NAME = "bombeos";
    public static String[] colNames = { "cod_abastecimiento", "cod_bombeo",
	    "denominacion", "tipologia", "energia" };
    public static String[] colAlias = { "C�digo de abastecimiento",
	    "C�digo de bombeo", "Denominaci�n", "Tipolog�a", "Energ�a" };

    public BombeosForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Bombeos");
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
