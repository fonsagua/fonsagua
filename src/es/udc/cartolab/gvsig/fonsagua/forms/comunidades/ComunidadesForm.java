package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import javax.swing.JPanel;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.VectorialEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.VectorialTableHandler;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.AddCroquisListener;
import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.ShowCroquisListener;
import es.udc.cartolab.gvsig.fonsagua.croquis.ui.CroquisButtons;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class ComunidadesForm extends BasicAbstractForm {

    public static final String NAME = "comunidades";
    public static final String PKFIELD = "cod_comunidad";
    public static String[] colNames = { "comunidad", "cod_comunidad" };
    public static String[] colAlias = { "Comunidad", "Código" };

    private AlphanumericTableHandler adescosHandler;
    private AlphanumericTableHandler entrevistadoresHandler;
    private AlphanumericTableHandler entrevistadosHandler;
    private AlphanumericTableHandler subcuencasHandler;
    private AlphanumericTableHandler cargosPublicosHandler;
    private AlphanumericTableHandler ongsHandler;
    private AlphanumericTableHandler otrasOrganizacionesHandler;
    private AlphanumericTableHandler tiposCultivosHandler;
    private AlphanumericTableHandler produccionConsumoHandler;
    private AlphanumericTableHandler ganaderiaHandler;
    private AlphanumericTableHandler cooperativasHandler;
    private AlphanumericTableHandler capacitacionesRiesgosHandler;
    private AlphanumericTableHandler implicacionComunidadHandler;
    private AlphanumericTableHandler valoracionSistemaHandler;
    private AlphanumericTableHandler datosConsumoHandler;
    private AlphanumericTableHandler habitosConsumoHandler;
    private VectorialTableHandler fuentesContaminacionHandler;
    private VectorialTableHandler puntosViviendasHandler;
    private VectorialTableHandler centrosEducativosHandler;
    private VectorialTableHandler centrosSaludHandler;
    private VectorialTableHandler areasPotencialesRiegoHandler;
    private VectorialTableHandler otrosServiciosHandler;
    private VectorialTableHandler amenazasHandler;

    private VectorialEditableNNRelTableHandler abastecimientosRelationship;

    private CroquisButtons croquisButtons;

    public ComunidadesForm(FLyrVect layer) {
	super(layer);
	adescosHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		AdescosForm.NAME, getWidgetComponents(), PKFIELD,
		AdescosForm.colNames, AdescosForm.colAlias);
	entrevistadoresHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		EntrevistadoresForm.NAME, getWidgetComponents(), PKFIELD,
		EntrevistadoresForm.colNames, EntrevistadoresForm.colAlias);
	entrevistadosHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		EntrevistadosForm.NAME, getWidgetComponents(), PKFIELD,
		EntrevistadosForm.colNames, EntrevistadosForm.colAlias);
	subcuencasHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		SubcuencasForm.NAME, getWidgetComponents(), PKFIELD,
		SubcuencasForm.colNames, SubcuencasForm.colAlias);
	cargosPublicosHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		CargosPublicosForm.NAME, getWidgetComponents(), PKFIELD,
		CargosPublicosForm.colNames, CargosPublicosForm.colAlias);
	ongsHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		OngsForm.NAME, getWidgetComponents(), PKFIELD,
		OngsForm.colNames, OngsForm.colAlias);
	otrasOrganizacionesHandler = new AlphanumericTableHandler(
		FonsaguaConstants.dataSchema, OtrasOrganizacionesForm.NAME,
		getWidgetComponents(), PKFIELD,
		OtrasOrganizacionesForm.colNames,
		OtrasOrganizacionesForm.colAlias);
	tiposCultivosHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		TiposCultivosForm.NAME, getWidgetComponents(), PKFIELD,
		TiposCultivosForm.colNames, TiposCultivosForm.colAlias);
	produccionConsumoHandler = new AlphanumericTableHandler(
		FonsaguaConstants.dataSchema, ProduccionConsumoForm.NAME,
		getWidgetComponents(), PKFIELD, ProduccionConsumoForm.colNames,
		ProduccionConsumoForm.colAlias);
	ganaderiaHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		GanaderiaForm.NAME, getWidgetComponents(), PKFIELD,
		GanaderiaForm.colNames, GanaderiaForm.colAlias);
	cooperativasHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		CooperativasForm.NAME, getWidgetComponents(), PKFIELD,
		CooperativasForm.colNames, CooperativasForm.colAlias);
	capacitacionesRiesgosHandler = new AlphanumericTableHandler(
		FonsaguaConstants.dataSchema, CapacitacionesRiesgosForm.NAME,
		getWidgetComponents(), PKFIELD,
		CapacitacionesRiesgosForm.colNames,
		CapacitacionesRiesgosForm.colAlias);
	implicacionComunidadHandler = new AlphanumericTableHandler(
		FonsaguaConstants.dataSchema, ImplicacionComunidadForm.NAME,
		getWidgetComponents(), PKFIELD,
		ImplicacionComunidadForm.colNames,
		ImplicacionComunidadForm.colAlias);
	valoracionSistemaHandler = new AlphanumericTableHandler(
		FonsaguaConstants.dataSchema, ValoracionSistemaForm.NAME,
		getWidgetComponents(), PKFIELD, ValoracionSistemaForm.colNames,
		ValoracionSistemaForm.colAlias);
	datosConsumoHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		DatosConsumoForm.NAME, getWidgetComponents(), PKFIELD,
		DatosConsumoForm.colNames, DatosConsumoForm.colAlias);
	habitosConsumoHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		HabitosConsumoForm.NAME, getWidgetComponents(), PKFIELD,
		HabitosConsumoForm.colNames, HabitosConsumoForm.colAlias);
	fuentesContaminacionHandler = new VectorialTableHandler(
		FuentesContaminacionForm.NAME, getWidgetComponents(), PKFIELD,
		FuentesContaminacionForm.colNames,
		FuentesContaminacionForm.colAlias);
	puntosViviendasHandler = new VectorialTableHandler(
		PuntosViviendasForm.NAME, getWidgetComponents(), PKFIELD,
		PuntosViviendasForm.colNames, PuntosViviendasForm.colAlias);
	centrosEducativosHandler = new VectorialTableHandler(
		CentrosEducativosForm.NAME, getWidgetComponents(), PKFIELD,
		CentrosEducativosForm.colNames, CentrosEducativosForm.colAlias);
	centrosSaludHandler = new VectorialTableHandler(CentrosSaludForm.NAME,
		getWidgetComponents(), PKFIELD, CentrosSaludForm.colNames,
		CentrosSaludForm.colAlias);
	areasPotencialesRiegoHandler = new VectorialTableHandler(
		AreasPotencialesRiegoForm.NAME,
		getWidgetComponents(), PKFIELD,
		AreasPotencialesRiegoForm.colNames,
		AreasPotencialesRiegoForm.colAlias);
	otrosServiciosHandler = new VectorialTableHandler(
		OtrosServiciosForm.NAME, getWidgetComponents(), PKFIELD,
		OtrosServiciosForm.colNames, OtrosServiciosForm.colAlias);
	amenazasHandler = new VectorialTableHandler(AmenazasForm.NAME,
		getWidgetComponents(), PKFIELD, AmenazasForm.colNames,
		AmenazasForm.colAlias);
	abastecimientosRelationship = new VectorialEditableNNRelTableHandler(
		AbastecimientosForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", AbastecimientosForm.PKFIELD,
		AbastecimientosForm.colNames, AbastecimientosForm.colAlias);

    }

    private void addCroquisButtons() {
	final String comunidadId = getFormController().getValue(PKFIELD);
	JPanel actionsToolBar = this.getActionsToolBar();
	if (croquisButtons == null) {
	    croquisButtons = new CroquisButtons(comunidadId);
	    actionsToolBar.add(croquisButtons.getAddCroquisButton());
	    actionsToolBar.add(croquisButtons.getShowCroquisButton());
	} else {
	    croquisButtons.setAddlistener(new AddCroquisListener(comunidadId));
	    croquisButtons
		    .setShowlistener(new ShowCroquisListener(comunidadId));
	}

    }

    @Override
    protected void fillSpecificValues() {
	addCroquisButtons();

	final String codComunidad = getFormController().getValue(PKFIELD);
	adescosHandler.fillValues(codComunidad);
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
	abastecimientosRelationship.fillValues(codComunidad);
    }

    @Override
    protected void setListeners() {
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
	fuentesContaminacionHandler.reload(FuentesContaminacionForm.NAME,
		FonsaguaTableFormFactory.getInstance());
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

	abastecimientosRelationship.reload(FonsaguaTableFormFactory
		.getInstance().createSingletonForm(
			new TOCLayerManager()
				.getLayerByName(AbastecimientosForm.NAME)));
	super.setListeners();
    }

    @Override
    protected void removeListeners() {
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
	super.removeListeners();
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
