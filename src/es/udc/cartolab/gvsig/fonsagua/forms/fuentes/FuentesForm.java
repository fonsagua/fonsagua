package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialEditableNNRelTableHandler;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

@SuppressWarnings("serial")
public class FuentesForm extends BasicAbstractForm {

    public static final String NAME = "fuentes";
    public static final String PKFIELD = "cod_fuente";
    public static String[] colNames = { "fuente", "cod_fuente" };
    public static String[] colAlias = { "Fuente", "Código" };

    public FuentesForm(FLyrVect layer) {
	super(layer);
	addTableHandler(new AlphanumericTableHandler(AforosForm.NAME,
		getWidgetComponents(), PKFIELD, AforosForm.colNames,
		AforosForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(AnaliticasForm.NAME,
		getWidgetComponents(), PKFIELD, AnaliticasForm.colNames,
		AnaliticasForm.colAlias));
	addTableHandler(new VectorialEditableNNRelTableHandler(
		AbastecimientosForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_fuentes", AbastecimientosForm.PKFIELD,
		AbastecimientosForm.colNames, AbastecimientosForm.colAlias));
    }

    @Override
    protected String getPrimaryKeyValue() {
	return getFormController().getValue(PKFIELD);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
