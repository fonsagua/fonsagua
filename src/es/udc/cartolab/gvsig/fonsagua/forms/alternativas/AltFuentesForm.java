package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AltFuentesForm extends BasicAbstractForm {

    public static final String NAME = "alt_fuentes";
    public static String[] colNames = {
"fuente", "tipo_fuente_alternativa", "existencia_elemento", "q_usar"
    };
    public static String[] colAlias = {
"Nombre", "Tipo de fuente", "Estado", "Caudal a usar (l/s)"
    };
    public AltFuentesForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}
