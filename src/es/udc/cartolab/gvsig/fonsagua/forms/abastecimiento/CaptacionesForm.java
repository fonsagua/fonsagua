package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class CaptacionesForm extends BasicAbstractForm {

    public static final String NAME = "captaciones";
    public static String[] colNames = { "cod_abastecimiento", "denominacion",
	    "tipo_fuente", "sistema", "volumen" };
    public static String[] colAlias = { "Código de abastecimiento",
	    "Denominación", "Tipo de fuente", "Sistema", "Volumen (m³)" };

    public CaptacionesForm(FLyrVect layer) {
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
