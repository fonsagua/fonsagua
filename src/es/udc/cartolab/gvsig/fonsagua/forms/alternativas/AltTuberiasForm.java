package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AltTuberiasForm extends BasicAbstractForm {

    public static final String NAME = "alt_tuberias";
    public static String[] colNames = {
"existencia_elemento", "tipologia_tuberia", "sistema", "tuberia_comercial", "long_tuberia", "velocidad"
    };
    public static String[] colAlias = {
"Estado", "Tipología", "Sistema", "Modelo", "Longitud (m)", "Vel. (m/s)"
    };
    public AltTuberiasForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}
