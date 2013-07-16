package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class FuentesForm extends BasicAbstractForm {

    public static final String NAME = "fuentes";
    public static final String PKFIELD = "cod_fuente";
    public static String[] colNames = { "fuente", "cod_fuente" };
    public static String[] colAlias = { "Fuente", "Código" };

    private AlphanumericTableHandler aforosHandler;
    private AlphanumericTableHandler analiticasHandler;
    private VectorialEditableNNRelTableHandler abastecimientosRelationship;

    public FuentesForm(FLyrVect layer) {
	super(layer);
	aforosHandler = new AlphanumericTableHandler(AforosForm.NAME,
		getWidgetComponents(), PKFIELD, AforosForm.colNames,
		AforosForm.colAlias);
	analiticasHandler = new AlphanumericTableHandler(AnaliticasForm.NAME,
		getWidgetComponents(), PKFIELD, AnaliticasForm.colNames,
		AnaliticasForm.colAlias);
	FormFactory.checkLayerLoadedRegistered(AbastecimientosForm.NAME);
	abastecimientosRelationship = new VectorialEditableNNRelTableHandler(
		AbastecimientosForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_fuentes", AbastecimientosForm.PKFIELD,
		AbastecimientosForm.colNames, AbastecimientosForm.colAlias);
    }

    @Override
    protected void fillSpecificValues() {
	final String codFuente = getFormController().getValue(PKFIELD);
	aforosHandler.fillValues(codFuente);
	analiticasHandler.fillValues(codFuente);
	abastecimientosRelationship.fillValues(codFuente);
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	aforosHandler.reload(new AforosForm());
	analiticasHandler.reload(new AnaliticasForm());

	abastecimientosRelationship.reload(FormFactory
		.createSingletonFormRegistered(
			new TOCLayerManager()
				.getLayerByName(AbastecimientosForm.NAME)));
    }

    @Override
    protected void removeListeners() {
	super.removeListeners();
	aforosHandler.removeListeners();
	analiticasHandler.removeListeners();

	abastecimientosRelationship.removeListeners();
    }

    @Override
    public void onPositionChange(PositionEvent e) {
	super.onPositionChange(e);
	aforosHandler.fillValues(getFormController().getValue(PKFIELD));
	analiticasHandler.fillValues(getFormController().getValue(PKFIELD));
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
