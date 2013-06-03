package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

@SuppressWarnings("serial")
public class DepDistribucionForm extends BasicAbstractForm {

    public static final String NAME = "dep_distribucion";
    public static String[] colNames = {
"denominacion", "altura", "tipo_construccion", "volumen"
    };
    public static String[] colAlias = {
"Denominación", "Altura (m)", "Tipo de construcción", "Volumen"
    };

    public DepDistribucionForm(FLyrVect layer) {
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
