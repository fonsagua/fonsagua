package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class TuberiasForm extends BasicAbstractForm {

    public static final String NAME = "tuberias";
    public static String[] colNames = {
"denominacion", "tipologia_tuberia", "sistema", "material", "diametro"
    };
    public static String[] colAlias = {
"Denominaci�n", "Tipolog�a", "Sistema", "Material", "Di�metro"
    };

    public TuberiasForm(FLyrVect layer) {
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
