package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import javax.swing.JTextField;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.relationship.TableRelationship;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class FuentesForm extends BasicAbstractForm {

    public static final String NAME = "fuentes";
    public static final String PKFIELD = "cod_fuente";

    private TableHandler aforosHandler;
    private TableHandler analiticasHandler;
    private TableRelationship abastecimientosRelationship;

    public FuentesForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Fuentes");
	aforosHandler = new TableHandler(FonsaguaConstants.dataSchema,
		AforosForm.NAME, getWidgetComponents(), PKFIELD,
		AforosForm.colNames, AforosForm.colAlias);
	analiticasHandler = new TableHandler(FonsaguaConstants.dataSchema,
		AnaliticasForm.NAME, getWidgetComponents(), PKFIELD,
		AnaliticasForm.colNames, AnaliticasForm.colAlias);
	abastecimientosRelationship = new TableRelationship(
		getWidgetComponents(), NAME, PKFIELD, AbastecimientosForm.NAME,
		AbastecimientosForm.PKFIELD, "r_abastecimientos_fuentes",
		FonsaguaConstants.dataSchema);
    }

    @Override
    protected void fillSpecificValues() {
	aforosHandler.fillValues(getFormController().getValue(PKFIELD));
	analiticasHandler.fillValues(getFormController().getValue(PKFIELD));

	abastecimientosRelationship
		.setPrimaryPKValue(((JTextField) getWidgetComponents().get(
			abastecimientosRelationship.getPrimaryPKName()))
			.getText());
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	aforosHandler.reload(new AforosForm());
	analiticasHandler.reload(new AnaliticasForm());

	abastecimientosRelationship.reload();
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
