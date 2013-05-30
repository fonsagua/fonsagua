package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AmenazasForm extends BasicAbstractForm {

    public static final String NAME = "amenazas";
    public static String[] colNames = { "cod_comunidad", "tipo_amenaza",
	    "n_fam_afectadas" };
    public static String[] colAlias = { "Código de comunidad", "Tipo",
	    "Nº familias afectadas" };

    public AmenazasForm(FLyrVect layer) {
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
