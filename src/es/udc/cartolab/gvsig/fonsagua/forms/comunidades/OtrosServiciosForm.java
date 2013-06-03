package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class OtrosServiciosForm extends BasicAbstractForm {

    public static final String NAME = "otros_servicios";
    public static String[] colNames = {
"nombre", "tipo_servicio"
    };
    public static String[] colAlias = {
"Nombre", "Tipo"
    };

    public OtrosServiciosForm(FLyrVect layer) {
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
