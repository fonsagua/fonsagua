package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class PuntosViviendasForm extends BasicAbstractForm {

    public static final String NAME = "puntos_viviendas";
    public static String[] colNames = { "cod_comunidad", "cod_vivienda", "tipo", "descripcion"};
    public static String[] colAlias = { "Código de comunidad", "Código de vivienda", "Tipo", "Descripción" };

    public PuntosViviendasForm(FLyrVect layer) {
	super(layer);
	initWindow();
    }

    private void initWindow() {
	viewInfo.setHeight(550);
	viewInfo.setWidth(550);
	viewInfo.setTitle("Puntos Viviendas");
    }

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
