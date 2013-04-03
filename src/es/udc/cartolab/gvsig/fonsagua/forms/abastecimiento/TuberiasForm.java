package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class TuberiasForm extends BasicAbstractForm {

    public static final String NAME = "tuberias";
    public static String[] colNames = { "cod_abastecimiento",
 "cod_tuberia",
	    "denominacion", "tipologia", "sistema" };
    public static String[] colAlias = { "C�digo de abastecimiento",
	    "C�digo de tuber�a", "Denominaci�n", "Tipolog�a", "Sistema" };

    public TuberiasForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Tuber�as");
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
	// TODO Auto-generated method stub

    }

}
