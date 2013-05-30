package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class TuberiasForm extends BasicAbstractForm {

    public static final String NAME = "tuberias";
    public static String[] colNames = { "cod_abastecimiento", "denominacion",
	    "tipologia_tuberia", "sistema", "material", "diametro" };
    public static String[] colAlias = { "Código de abastecimiento",
	    "Denominación", "Tipología", "Sistema", "Material",
	    "Diámetro (pul.)" };

    public TuberiasForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
