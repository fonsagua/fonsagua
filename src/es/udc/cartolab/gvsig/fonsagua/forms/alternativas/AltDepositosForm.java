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
"Denominaci�n", "Estado", "Tipo de dep�sito", "Tipo de construcci�n", "Volumen dep�sito (m2)"
    };
    public AltDepositosForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}
