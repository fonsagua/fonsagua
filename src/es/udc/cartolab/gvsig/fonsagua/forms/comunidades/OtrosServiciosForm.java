package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class OtrosServiciosForm extends BasicAbstractForm {

    public static final String NAME = "otros_servicios";
    public static String[] colNames = { "cod_comunidad", "cod_servicio",
	    "nombre", "tipo" };
    public static String[] colAlias = { "Código de comunidad",
	    "Código del servicio", "Nombre", "Tipo" };

    public OtrosServiciosForm(FLyrVect layer) {
	super(layer);
	initWindow();
    }

    private void initWindow() {
	viewInfo.setHeight(500);
	viewInfo.setWidth(600);
	viewInfo.setTitle("Otros Servicios");
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
