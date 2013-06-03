package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class BombeosForm extends BasicAbstractForm {

    public static final String NAME = "bombeos";
    public static String[] colNames = {
"denominacion", "tipologia_bomba", "potencia", "caudal", "altura"
    };
    public static String[] colAlias = {
"Denominación", "Tipología", "Potencia (CV)", "Q (l/min)", "Altura (m)"
    };

    public BombeosForm(FLyrVect layer) {
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
