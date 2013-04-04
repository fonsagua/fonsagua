package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class DepIntermediosForm extends BasicAbstractForm {

    public static final String NAME = "dep_intermedios";
    public static String[] colNames = { "cod_abastecimiento", "denominacion",
	    "ubicacion", "sistema", "anho_construccion", "volumen" };
    public static String[] colAlias = { "Código de abastecimiento",
	    "Denominación", "Ubicación", "Sistema", "Año de construcción",
	    "Volumen (m³)" };

    public DepIntermediosForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Dep Intermedios");
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
