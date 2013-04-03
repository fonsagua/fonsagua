package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.VectorialTableHandler;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.croquis.ui.CroquisButtons;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;
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
    private TableHandler capacitacionesRiesgosHandler;
    private TableHandler implicacionComunidadHandler;
    private TableHandler valoracionSistemaHandler;
    private TableHandler datosConsumoHandler;
    private TableHandler habitosConsumoHandler;
    private TableHandler fuentesContaminacionHandler;
    private VectorialTableHandler puntosViviendasHandler;
    private VectorialTableHandler centrosEducativosHandler;
    private VectorialTableHandler centrosSaludHandler;
    private VectorialTableHandler areasPotencialesRiegoHandler;
    private VectorialTableHandler otrosServiciosHandler;
    private VectorialTableHandler amenazasHandler;

    public ComunidadesForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Comunidades");
	addCroquisButtons();
	TOCLayerManager toc = new TOCLayerManager();
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
	capacitacionesRiesgosHandler = new TableHandler(
		CapacitacionesRiesgosForm.NAME, getWidgetComponents(),
		"cod_comunidad", CapacitacionesRiesgosForm.colNames,
		CapacitacionesRiesgosForm.colAlias);
	implicacionComunidadHandler = new TableHandler(
		ImplicacionComunidadForm.NAME, getWidgetComponents(),
		"cod_comunidad", ImplicacionComunidadForm.colNames,
		ImplicacionComunidadForm.colAlias);
	valoracionSistemaHandler = new TableHandler(ValoracionSistemaForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		ValoracionSistemaForm.colNames, ValoracionSistemaForm.colAlias);
	datosConsumoHandler = new TableHandler(DatosConsumoForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		DatosConsumoForm.colNames, DatosConsumoForm.colAlias);
	habitosConsumoHandler = new TableHandler(HabitosConsumoForm.NAME,
		getWidgetComponents(), "cod_comunidad",
		HabitosConsumoForm.colNames, HabitosConsumoForm.colAlias);
	fuentesContaminacionHandler = new TableHandler(
		FuentesContaminacionForm.NAME, getWidgetComponents(),
		"cod_comunidad", FuentesContaminacionForm.colNames,
		FuentesContaminacionForm.colAlias);
	puntosViviendasHandler = new VectorialTableHandler(toc.getLayerByName(PuntosViviendasForm.NAME),
		getWidgetComponents(), "cod_comunidad", PuntosViviendasForm.colNames,
		PuntosViviendasForm.colAlias);
	centrosEducativosHandler = new VectorialTableHandler(
		toc.getLayerByName(CentrosEducativosForm.NAME),
		getWidgetComponents(), "cod_comunidad",
		CentrosEducativosForm.colNames, CentrosEducativosForm.colAlias);
	centrosSaludHandler = new VectorialTableHandler(
		toc.getLayerByName(CentrosSaludForm.NAME),
		getWidgetComponents(), "cod_comunidad",
		CentrosSaludForm.colNames, CentrosSaludForm.colAlias);
	areasPotencialesRiegoHandler = new VectorialTableHandler(
		toc.getLayerByName(AreasPotencialesRiegoForm.NAME),
		getWidgetComponents(), "cod_comunidad",
		AreasPotencialesRiegoForm.colNames,
		AreasPotencialesRiegoForm.colAlias);
	otrosServiciosHandler = new VectorialTableHandler(
		toc.getLayerByName(OtrosServiciosForm.NAME),
		getWidgetComponents(), "cod_comunidad",
		OtrosServiciosForm.colNames, OtrosServiciosForm.colAlias);
	amenazasHandler = new VectorialTableHandler(
		toc.getLayerByName(AmenazasForm.NAME), getWidgetComponents(),
		"cod_comunidad", AmenazasForm.colNames, AmenazasForm.colAlias);
	
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
	String codComunidad = getFormController().getValue("cod_comunidad");
	adescosHandler
		.fillValues(codComunidad);
	entrevistadoresHandler.fillValues(codComunidad);
	entrevistadosHandler.fillValues(codComunidad);
	subcuencasHandler.fillValues(codComunidad);
	cargosPublicosHandler.fillValues(codComunidad);
	ongsHandler.fillValues(codComunidad);
	otrasOrganizacionesHandler.fillValues(codComunidad);
	tiposCultivosHandler.fillValues(codComunidad);
	produccionConsumoHandler.fillValues(codComunidad);
	ganaderiaHandler.fillValues(codComunidad);
	cooperativasHandler.fillValues(codComunidad);
	capacitacionesRiesgosHandler.fillValues(codComunidad);
	implicacionComunidadHandler.fillValues(codComunidad);
	valoracionSistemaHandler.fillValues(codComunidad);
	datosConsumoHandler.fillValues(codComunidad);
	habitosConsumoHandler.fillValues(codComunidad);
	fuentesContaminacionHandler.fillValues(codComunidad);
	puntosViviendasHandler.fillValues(codComunidad);
	centrosEducativosHandler.fillValues(codComunidad);
	centrosSaludHandler.fillValues(codComunidad);
	areasPotencialesRiegoHandler.fillValues(codComunidad);
	otrosServiciosHandler.fillValues(codComunidad);
	amenazasHandler.fillValues(codComunidad);
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	TOCLayerManager toc = new TOCLayerManager();
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
	capacitacionesRiesgosHandler.reload(new CapacitacionesRiesgosForm());
	implicacionComunidadHandler.reload(new ImplicacionComunidadForm());
	valoracionSistemaHandler.reload(new ValoracionSistemaForm());
	datosConsumoHandler.reload(new DatosConsumoForm());
	habitosConsumoHandler.reload(new HabitosConsumoForm());
	fuentesContaminacionHandler.reload(new FuentesContaminacionForm());
	puntosViviendasHandler.reload(PuntosViviendasForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	centrosEducativosHandler.reload(CentrosEducativosForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	centrosSaludHandler.reload(CentrosSaludForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	areasPotencialesRiegoHandler.reload(AreasPotencialesRiegoForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	otrosServiciosHandler.reload(OtrosServiciosForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	amenazasHandler.reload(AmenazasForm.NAME,
		FonsaguaTableFormFactory.getInstance());
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
	capacitacionesRiesgosHandler.removeListeners();
	implicacionComunidadHandler.removeListeners();
	valoracionSistemaHandler.removeListeners();
	datosConsumoHandler.removeListeners();
	habitosConsumoHandler.removeListeners();
	fuentesContaminacionHandler.removeListeners();
	puntosViviendasHandler.removeListeners();
	centrosEducativosHandler.removeListeners();
	centrosSaludHandler.removeListeners();
	areasPotencialesRiegoHandler.removeListeners();
	otrosServiciosHandler.removeListeners();
	amenazasHandler.removeListeners();
    }

    @Override
    public void onPositionChange(PositionEvent e) {
	super.onPositionChange(e);
	fillSpecificValues();
	this.repaint(); // will force embedded tables to refresh
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
