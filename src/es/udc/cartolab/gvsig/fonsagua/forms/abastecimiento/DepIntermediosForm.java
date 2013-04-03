package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class DepIntermediosForm extends BasicAbstractForm {

    public static final String NAME = "dep_intermedios";
    public static String[] colNames = { "cod_abastecimiento",
	    "cod_dep_intermedio", "denominacion", "funcion", "ubicacion" };
    public static String[] colAlias = { "Código de abastecimiento",
	    "Código de captación", "Denominación", "Función", "Ubicación" };

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
