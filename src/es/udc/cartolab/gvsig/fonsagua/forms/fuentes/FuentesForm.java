package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.TableRelationship;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class FuentesForm extends BasicAbstractForm {

    public static final String NAME = "fuentes";
    public static final String PKFIELD = "cod_fuente";
    public static String[] colNames = { "fuente", "cod_fuente" };
    public static String[] colAlias = { "Fuente", "Código" };

    private TableHandler aforosHandler;
    private TableHandler analiticasHandler;
    private TableRelationship abastecimientosRelationship;

    public FuentesForm(FLyrVect layer) {
	super(layer);
	aforosHandler = new TableHandler(FonsaguaConstants.dataSchema,
		AforosForm.NAME, getWidgetComponents(), PKFIELD,
		AforosForm.colNames, AforosForm.colAlias);
	analiticasHandler = new TableHandler(FonsaguaConstants.dataSchema,
		AnaliticasForm.NAME, getWidgetComponents(), PKFIELD,
		AnaliticasForm.colNames, AnaliticasForm.colAlias);
	abastecimientosRelationship = new TableRelationship(
		getWidgetComponents(), NAME, PKFIELD, AbastecimientosForm.NAME,
		AbastecimientosForm.PKFIELD, "r_abastecimientos_fuentes",
		FonsaguaConstants.dataSchema, AbastecimientosForm.colNames,
		AbastecimientosForm.colAlias);
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

	abastecimientosRelationship.reload(FonsaguaTableFormFactory
		.getInstance().createSingletonForm(
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
