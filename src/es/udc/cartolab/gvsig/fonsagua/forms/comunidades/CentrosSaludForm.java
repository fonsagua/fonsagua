package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class CentrosSaludForm extends BasicAbstractForm {

    public static final String NAME = "centros_salud";
    public static String[] colNames = { "cod_comunidad", "cod_c_salud",
	    "nombre" };
    public static String[] colAlias = { "C�digo de comunidad",
	    "C�digo del centro", "Nombre" };

    public CentrosSaludForm(FLyrVect layer) {
	super(layer);
	initWindow();
    }

    private void initWindow() {
	viewInfo.setHeight(500);
	viewInfo.setWidth(560);
	viewInfo.setTitle("Centros Salud");
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
