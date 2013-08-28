package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AltEmbalsesForm extends BasicAbstractForm {

    public static final String NAME = "alt_embalses";
    public static String[] colNames = {
"embalse", "existencia_elemento", "q_usar"
    };
    public static String[] colAlias = {
"Nombre", "Estado", "Caudal a usar (l/s)"
    };
    public AltEmbalsesForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}
