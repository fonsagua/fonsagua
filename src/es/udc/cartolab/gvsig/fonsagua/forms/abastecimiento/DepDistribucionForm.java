package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class DepDistribucionForm extends BasicAbstractForm {

    public static final String NAME = "dep_distribucion";
    public static String[] colNames = { "cod_abastecimiento", "denominacion",
	    "ubicacion", "tipo_construccion", "anho_construccion", "volumen" };
    public static String[] colAlias = { "C�digo de abastecimiento",
	    "Denominaci�n", "Tipo de construcci�n", "Sistema",
	    "A�o de construcci�n", "Volumen (m�)" };

    public DepDistribucionForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Dep Distribuci�n");
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
