package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AreasPotencialesRiegoForm extends BasicAbstractForm {

    public static final String NAME = "areas_potenciales_riego";
    public static String[] colNames = {
"descripcion"
    };
    public static String[] colAlias = {
"Áreas potenciales riego"
    };

    public AreasPotencialesRiegoForm(FLyrVect layer) {
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
