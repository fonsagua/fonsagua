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
    private TableHandler subcuencasHandler;
    private TableHandler cargosPublicosHandler;
    private TableHandler ongsHandler;

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
	subcuencasHandler = new TableHandler(SubcuencasForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		SubcuencasForm.colNames, SubcuencasForm.colAlias);
	cargosPublicosHandler = new TableHandler(CargosPublicosForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		CargosPublicosForm.colNames, CargosPublicosForm.colAlias);
	ongsHandler = new TableHandler(OngsForm.NAME, getWidgetComponents(),
		"cod_comunidad", OngsForm.colNames, OngsForm.colAlias);
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
	subcuencasHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	cargosPublicosHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	ongsHandler.fillValues(getFormController().getValue("cod_comunidad"));
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	adescosHandler.reload(new AdescosForm());
	entrevistadoresHandler.reload(new EntrevistadoresForm());
	entrevistadosHandler.reload(new EntrevistadosForm());
	subcuencasHandler.reload(new SubcuencasForm());
	cargosPublicosHandler.reload(new CargosPublicosForm());
	ongsHandler.reload(new OngsForm());
    }

    @Override
    protected void removeListeners() {
	super.removeListeners();
	adescosHandler.removeListeners();
	entrevistadoresHandler.removeListeners();
	entrevistadosHandler.removeListeners();
	subcuencasHandler.removeListeners();
	cargosPublicosHandler.removeListeners();
	ongsHandler.removeListeners();
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
	subcuencasHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	cargosPublicosHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	ongsHandler.fillValues(getFormController().getValue("cod_comunidad"));
	this.repaint(); // will force embedded tables to refresh
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
