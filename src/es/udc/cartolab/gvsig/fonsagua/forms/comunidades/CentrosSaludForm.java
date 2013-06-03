package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class CentrosSaludForm extends BasicAbstractForm {

    public static final String NAME = "centros_salud";
    public static String[] colNames = {
"nombre"
    };
    public static String[] colAlias = {
"Nombre"
    };

    public CentrosSaludForm(FLyrVect layer) {
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
