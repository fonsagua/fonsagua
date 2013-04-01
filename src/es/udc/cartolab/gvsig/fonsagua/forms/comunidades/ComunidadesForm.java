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
    private TableHandler otrasOrganizacionesHandler;
    private TableHandler tiposCultivosHandler;
    private TableHandler produccionConsumoHandler;
    private TableHandler ganaderiaHandler;
    private TableHandler cooperativasHandler;

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
	otrasOrganizacionesHandler = new TableHandler(
		OtrasOrganizacionesForm.NAME, getWidgetComponents(),
		"cod_comunidad", OtrasOrganizacionesForm.colNames,
		OtrasOrganizacionesForm.colAlias);
	tiposCultivosHandler = new TableHandler(TiposCultivosForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		TiposCultivosForm.colNames, TiposCultivosForm.colAlias);
	produccionConsumoHandler = new TableHandler(ProduccionConsumoForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		ProduccionConsumoForm.colNames, ProduccionConsumoForm.colAlias);
	ganaderiaHandler = new TableHandler(GanaderiaForm.NAME,
		getWidgetComponents(), "cod_comunidad", GanaderiaForm.colNames,
		GanaderiaForm.colAlias);
	cooperativasHandler = new TableHandler(CooperativasForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		CooperativasForm.colNames, CooperativasForm.colAlias);
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
	otrasOrganizacionesHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	tiposCultivosHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	produccionConsumoHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	ganaderiaHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	cooperativasHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
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
	otrasOrganizacionesHandler.reload(new OtrasOrganizacionesForm());
	tiposCultivosHandler.reload(new TiposCultivosForm());
	produccionConsumoHandler.reload(new ProduccionConsumoForm());
	ganaderiaHandler.reload(new GanaderiaForm());
	cooperativasHandler.reload(new CooperativasForm());
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
	otrasOrganizacionesHandler.removeListeners();
	tiposCultivosHandler.removeListeners();
	produccionConsumoHandler.removeListeners();
	ganaderiaHandler.removeListeners();
	cooperativasHandler.removeListeners();
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
	otrasOrganizacionesHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	tiposCultivosHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	produccionConsumoHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	ganaderiaHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	cooperativasHandler.fillValues(getFormController().getValue(
		"cod_comunidad"));
	this.repaint(); // will force embedded tables to refresh
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
