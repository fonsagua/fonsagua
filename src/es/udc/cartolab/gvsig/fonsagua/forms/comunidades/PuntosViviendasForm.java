package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class PuntosViviendasForm extends BasicAbstractForm {

    public static final String NAME = "puntos_viviendas";
    public static String[] colNames = {
"tipo", "descripcion"
    };
    public static String[] colAlias = {
"Tipo de punto", "Descripción"
    };

    public PuntosViviendasForm(FLyrVect layer) {
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
