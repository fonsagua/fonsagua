package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class TuberiasForm extends BasicAbstractForm {

    public static final String NAME = "tuberias";
    public static String[] colNames = { "cod_abastecimiento", "denominacion",
	    "tipologia", "sistema", "material", "diametro", "anho_construccion" };
    public static String[] colAlias = { "C�digo de abastecimiento",
	    "Denominaci�n", "Tipolog�a", "Sistema", "Material",
	    "Di�metro (pulgadas)", "A�o de construcci�n" };

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
