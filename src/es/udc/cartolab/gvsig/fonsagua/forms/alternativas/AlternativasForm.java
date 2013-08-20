package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class AlternativasForm extends BasicAbstractForm {

    public static final String NAME = "alternativas";
    public static final String PKFIELD = "cod_alternativa";
    public static final String DEPARTFK = "departamento";
    public static final String MUNICFK = "municipio";
    public static final String CANTONFK = "canton";

    public AlternativasForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }
}
