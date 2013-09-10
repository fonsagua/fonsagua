package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AltConexionesForm extends BasicAbstractForm {

    public static final String NAME = "alt_conexiones";
    public static String[] colNames = {
"denominacion", "hab_conectados", "demanda", "presion"
    };
    public static String[] colAlias = {
"Denominación", "Hab. conectados", "Demanda agua (l/s)", "Presión (m.c.a.)"
    };
    public AltConexionesForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}
