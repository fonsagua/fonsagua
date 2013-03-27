package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvisg.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.udc.cartolab.gvsig.fonsagua.croquis.ui.CroquisButtons;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class ComunidadesForm extends BasicAbstractForm {

    public static final String NAME = "comunidades";
    private TableHandler adescosHandler;
    private TableHandler entrevistadoresHandler;
    private TableHandler entrevistadosHandler;

    public ComunidadesForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Comunidades");
	addCroquisButtons();
	adescosHandler = new TableHandler(AdescosForm.NAME,
		getWidgetComponents(), "cod_comunidad", AdescosForm.colNames,
		AdescosForm.colAlias);
	entrevistadoresHandler = new TableHandler(EntrevistadoresForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		EntrevistadoresForm.colNames, EntrevistadoresForm.colAlias);
	entrevistadosHandler = new TableHandler(EntrevistadosForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		EntrevistadosForm.colNames, EntrevistadosForm.colAlias);
    }

    private void addCroquisButtons() {
	JPanel actionsToolBar = this.getActionsToolBar();
	String comunidadId = ((JTextField) getFormBody().getComponentByName(
		"cod_comunidad")).getText();
	actionsToolBar.add(new CroquisButtons(comunidadId)
		.getAddCroquisButton());
	actionsToolBar.add(new CroquisButtons(comunidadId)
		.getShowCroquisButton());
    }

    @Override
    protected void fillSpecificValues() {
	adescosHandler
		.fillValues(getFormController().getValue("cod_comunidad"));
	entrevistadoresHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	entrevistadosHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	adescosHandler.reload(new AdescosForm());
	entrevistadoresHandler.reload(new EntrevistadoresForm());
	entrevistadosHandler.reload(new EntrevistadosForm());
    }

    @Override
    protected void removeListeners() {
	super.removeListeners();
	adescosHandler.removeListeners();
	entrevistadoresHandler.removeListeners();
	entrevistadosHandler.removeListeners();
    }

    @Override
    public void onPositionChange(PositionEvent e) {
	super.onPositionChange(e);
	adescosHandler
		.fillValues(getFormController().getValue("cod_comunidad"));
	entrevistadoresHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	entrevistadosHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	this.repaint(); // will force embedded tables to refresh
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
