package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class FuentesForm extends BasicAbstractForm {

    public static final String NAME = "fuentes";
    private TableHandler aforosHandler;
    private TableHandler analiticasHandler;

    public FuentesForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Fuentes");
	aforosHandler = new TableHandler(AforosForm.NAME,
		getWidgetComponents(), "cod_fuente", AforosForm.colNames,
		AforosForm.colAlias);
	analiticasHandler = new TableHandler(AnaliticasForm.NAME,
		getWidgetComponents(), "cod_fuente", AnaliticasForm.colNames,
		AnaliticasForm.colAlias);
    }

    @Override
    protected void fillSpecificValues() {
	aforosHandler.fillValues(getFormController().getValue("cod_fuente"));
	analiticasHandler
		.fillValues(getFormController().getValue("cod_fuente"));
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	aforosHandler.reload(new AforosForm());
	analiticasHandler.reload(new AnaliticasForm());
    }

    @Override
    protected void removeListeners() {
	super.removeListeners();
	aforosHandler.removeListeners();
	analiticasHandler.removeListeners();
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
