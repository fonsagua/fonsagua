package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class BombeosForm extends BasicAbstractForm {

    public static final String NAME = "bombeos";
    public static String[] colNames = { "cod_abastecimiento", "denominacion",
	    "tipologia_bomba", "potencia", "caudal", "altura" };
    public static String[] colAlias = { "Código de abastecimiento",
	    "Denominación", "Tipología", "Potencia (CV)", "Caudal (l/min)",
	    "Altura (m)" };

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
