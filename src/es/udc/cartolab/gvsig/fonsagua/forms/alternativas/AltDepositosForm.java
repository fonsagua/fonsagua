package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AltDepositosForm extends BasicAbstractForm {

    public static final String NAME = "alt_depositos";
    public static String[] colNames = {
"denominacion", "existencia_elemento", "tipo_deposito", "tipo_construccion", "volumen"
    };
    public static String[] colAlias = {
"Denominación", "Estado", "Tipo de depósito", "Tipo de construcción", "Volumen depósito (m2)"
    };
    public AltDepositosForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}
