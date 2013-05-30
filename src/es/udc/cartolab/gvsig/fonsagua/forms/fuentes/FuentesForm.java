package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import javax.swing.JTextField;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;
import es.udc.cartolab.gvsig.fonsagua.forms.relationship.TableRelationship;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class FuentesForm extends BasicAbstractForm {

    public static final String NAME = "fuentes";
    public static final String PKNAME = "cod_fuente";

    private TableHandler aforosHandler;
    private TableHandler analiticasHandler;
    private TableRelationship abastecimientosRelationship;

    public FuentesForm(FLyrVect layer) {
	super(layer);
	aforosHandler = new TableHandler(FonsaguaConstants.dataSchema,
		AforosForm.NAME, getWidgetComponents(), PKNAME,
		AforosForm.colNames, AforosForm.colAlias);
	analiticasHandler = new TableHandler(FonsaguaConstants.dataSchema,
		AnaliticasForm.NAME, getWidgetComponents(), PKNAME,
		AnaliticasForm.colNames, AnaliticasForm.colAlias);
	abastecimientosRelationship = new TableRelationship(
		getWidgetComponents(), NAME, PKNAME, AbastecimientosForm.NAME,
		AbastecimientosForm.PKFIELD, "r_abastecimientos_fuentes",
		FonsaguaConstants.dataSchema);
    }

    @Override
    protected void fillSpecificValues() {
	aforosHandler.fillValues(getFormController().getValue(PKNAME));
	analiticasHandler.fillValues(getFormController().getValue(PKNAME));

	abastecimientosRelationship
		.fillValues(((JTextField) getWidgetComponents().get(PKNAME))
			.getText());
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
	aforosHandler.fillValues(getFormController().getValue(PKNAME));
	analiticasHandler.fillValues(getFormController().getValue(PKNAME));
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
