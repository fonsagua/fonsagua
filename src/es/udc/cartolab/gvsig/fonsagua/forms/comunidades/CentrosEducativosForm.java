package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class CentrosEducativosForm extends BasicAbstractForm {

    public static final String NAME = "centros_educativos";
    public static String[] colNames = { "cod_comunidad", "nombre", "niveles",
	    "tot_alumnos", "n_profesores" };
    public static String[] colAlias = { "Código de comunidad", "Nombre",
	    "Niveles de enseñanza", "Alumnos", "Profesores" };

    public CentrosEducativosForm(FLyrVect layer) {
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
