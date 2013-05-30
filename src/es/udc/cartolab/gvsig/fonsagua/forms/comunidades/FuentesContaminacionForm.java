package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class FuentesContaminacionForm extends BasicAbstractForm {

    public static final String NAME = "fuentes_contaminacion";
    public static String[] colNames = { "cod_comunidad", "tipo_contaminacion",
	    "n_fam_vierten" };
    public static String[] colAlias = { "Código de comunidad", "Tipo",
	    "Nº familias que vierten" };

    public FuentesContaminacionForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected void fillSpecificValues() {
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
