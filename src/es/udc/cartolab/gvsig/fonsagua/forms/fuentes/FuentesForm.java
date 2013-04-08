package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import javax.swing.JTextField;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.relationship.TableRelationship;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class FuentesForm extends BasicAbstractForm {

    public static final String NAME = "fuentes";
    private TableHandler aforosHandler;
    private TableHandler analiticasHandler;
    private TableRelationship abastecimientosRelationship;

    public FuentesForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Fuentes");
	aforosHandler = new TableHandler(AforosForm.NAME,
		getWidgetComponents(), "cod_fuente", AforosForm.colNames,
		AforosForm.colAlias);
	analiticasHandler = new TableHandler(AnaliticasForm.NAME,
		getWidgetComponents(), "cod_fuente", AnaliticasForm.colNames,
		AnaliticasForm.colAlias);

	abastecimientosRelationship = new TableRelationship(
		getWidgetComponents(), NAME, "cod_fuente",
		AbastecimientosForm.NAME, "cod_abastecimiento",
		"r_abastecimientos_fuentes");
    }

    @Override
    protected void fillSpecificValues() {
	aforosHandler.fillValues(getFormController().getValue("cod_fuente"));
	analiticasHandler
		.fillValues(getFormController().getValue("cod_fuente"));

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
	aforosHandler.fillValues(getFormController().getValue("cod_fuente"));
	analiticasHandler
		.fillValues(getFormController().getValue("cod_fuente"));
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
