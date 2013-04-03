package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class CaptacionesForm extends BasicAbstractForm {

    public static final String NAME = "captaciones";
    public static String[] colNames = { "cod_abastecimiento", "cod_captacion",
	    "denominacion", "tipo_fuente", "sistema" };
    public static String[] colAlias = { "C�digo de abastecimiento",
	    "C�digo de captaci�n", "Denominaci�n", "Tipo de fuente", "Sistema" };

    public CaptacionesForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Captaciones");
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
