package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AltBombeosForm extends BasicAbstractForm {

    public static final String NAME = "alt_bombeos";
    public static String[] colNames = {
"existencia_elemento", "altura_bombeo", "tiempo_bombeo", "bomba_comercial", "caudal"
    };
    public static String[] colAlias = {
"Estado", "Altura (m)", "Tiempo (h)", "Modelo", "Caudal (l/s)"
    };
    public AltBombeosForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}
