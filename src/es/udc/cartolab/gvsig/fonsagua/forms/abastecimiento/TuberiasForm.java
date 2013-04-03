package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class TuberiasForm extends BasicAbstractForm {

    public static final String NAME = "tuberias";
    public static String[] colNames = { "cod_abastecimiento",
 "cod_tuberia",
	    "denominacion", "tipologia", "sistema" };
    public static String[] colAlias = { "Código de abastecimiento",
	    "Código de tubería", "Denominación", "Tipología", "Sistema" };

    public TuberiasForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Tuberías");
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
