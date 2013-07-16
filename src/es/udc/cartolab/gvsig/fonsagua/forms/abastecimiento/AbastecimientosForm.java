package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.AlphanumericNotEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.VectorialEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.VectorialTableHandler;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AdescosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.DatosConsumoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ImplicacionComunidadForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ValoracionSistemaForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.FuentesForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class AbastecimientosForm extends BasicAbstractForm {

    public static final String NAME = "abastecimientos";
    public static final String PKFIELD = "cod_abastecimiento";
    public static String[] colNames = { "abastecimiento", "cod_abastecimiento" };
    public static String[] colAlias = { "Abastecimiento", "Código" };

    private AlphanumericTableHandler juntasAguaHandler;
    private AlphanumericTableHandler coberturaHandler;
    private AlphanumericTableHandler gestionComercialHandler;
    private AlphanumericTableHandler gestionFinancieraHandler;
    private AlphanumericTableHandler evaluacionHandler;
    private VectorialTableHandler captacionesHandler;
    private VectorialTableHandler depIntermediosHandler;
    private VectorialTableHandler depDistribucionHandler;
    private VectorialTableHandler tuberiasHandler;
    private VectorialTableHandler bombeosHandler;
    private AlphanumericNotEditableNNRelTableHandler adescosHandler;
    private AlphanumericNotEditableNNRelTableHandler implicacionComunidadHandler;
    private AlphanumericNotEditableNNRelTableHandler datosConsumoHandler;
    private AlphanumericNotEditableNNRelTableHandler valoracionSistemaHandler;
    private VectorialEditableNNRelTableHandler comunidadesRelationship;
    private VectorialEditableNNRelTableHandler fuentesRelationship;

    public AbastecimientosForm(FLyrVect layer) {
	super(layer);

	juntasAguaHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		JuntasAguaForm.NAME, getWidgetComponents(), PKFIELD,
		JuntasAguaForm.colNames, JuntasAguaForm.colAlias);
	coberturaHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		CoberturaForm.NAME, getWidgetComponents(), PKFIELD,
		CoberturaForm.colNames, CoberturaForm.colAlias);
	gestionComercialHandler = new AlphanumericTableHandler(
		FonsaguaConstants.dataSchema, GestComercialForm.NAME,
		getWidgetComponents(), PKFIELD, GestComercialForm.colNames,
		GestComercialForm.colAlias);
	gestionFinancieraHandler = new AlphanumericTableHandler(
		FonsaguaConstants.dataSchema, GestFinancieraForm.NAME,
		getWidgetComponents(), PKFIELD, GestFinancieraForm.colNames,
		GestFinancieraForm.colAlias);
	evaluacionHandler = new AlphanumericTableHandler(FonsaguaConstants.dataSchema,
		EvaluacionForm.NAME, getWidgetComponents(), PKFIELD,
		EvaluacionForm.colNames, EvaluacionForm.colAlias);
	captacionesHandler = new VectorialTableHandler(CaptacionesForm.NAME,
		getWidgetComponents(), PKFIELD, CaptacionesForm.colNames,
		CaptacionesForm.colAlias);
	depIntermediosHandler = new VectorialTableHandler(
		DepIntermediosForm.NAME, getWidgetComponents(), PKFIELD,
		DepIntermediosForm.colNames, DepIntermediosForm.colAlias);
	depDistribucionHandler = new VectorialTableHandler(
		DepDistribucionForm.NAME, getWidgetComponents(), PKFIELD,
		DepDistribucionForm.colNames,
		DepDistribucionForm.colAlias);
	tuberiasHandler = new VectorialTableHandler(TuberiasForm.NAME,
		getWidgetComponents(), PKFIELD, TuberiasForm.colNames,
		TuberiasForm.colAlias);
	bombeosHandler = new VectorialTableHandler(BombeosForm.NAME,
		getWidgetComponents(), PKFIELD, BombeosForm.colNames,
		BombeosForm.colAlias);
	comunidadesRelationship = new VectorialEditableNNRelTableHandler(ComunidadesForm.NAME,
		getWidgetComponents(), FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		ComunidadesForm.colNames, ComunidadesForm.colAlias);
	fuentesRelationship = new VectorialEditableNNRelTableHandler(FuentesForm.NAME,
		getWidgetComponents(), FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_fuentes", FuentesForm.PKFIELD,
		FuentesForm.colNames, FuentesForm.colAlias);
	loadTable(AdescosForm.NAME);
	adescosHandler = new AlphanumericNotEditableNNRelTableHandler(
		AdescosForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		AdescosForm.colNames, AdescosForm.colAlias);
	loadTable(ImplicacionComunidadForm.NAME);
	implicacionComunidadHandler = new AlphanumericNotEditableNNRelTableHandler(
		ImplicacionComunidadForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		ImplicacionComunidadForm.colNames,
		ImplicacionComunidadForm.colAlias);
	loadTable(DatosConsumoForm.NAME);
	datosConsumoHandler = new AlphanumericNotEditableNNRelTableHandler(
		DatosConsumoForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		DatosConsumoForm.colNames, DatosConsumoForm.colAlias);
	loadTable(ValoracionSistemaForm.NAME);
	valoracionSistemaHandler = new AlphanumericNotEditableNNRelTableHandler(
		ValoracionSistemaForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		ValoracionSistemaForm.colNames, ValoracionSistemaForm.colAlias);
    }

    @Override
    protected void fillSpecificValues() {
	final String codAbastecimiento = getFormController().getValue(PKFIELD);
	juntasAguaHandler.fillValues(codAbastecimiento);
	coberturaHandler.fillValues(codAbastecimiento);
	gestionComercialHandler.fillValues(codAbastecimiento);
	gestionFinancieraHandler.fillValues(codAbastecimiento);
	evaluacionHandler.fillValues(codAbastecimiento);
	captacionesHandler.fillValues(codAbastecimiento);
	depIntermediosHandler.fillValues(codAbastecimiento);
	depDistribucionHandler.fillValues(codAbastecimiento);
	tuberiasHandler.fillValues(codAbastecimiento);
	bombeosHandler.fillValues(codAbastecimiento);
	comunidadesRelationship.fillValues(codAbastecimiento);
	fuentesRelationship.fillValues(codAbastecimiento);
	adescosHandler.fillValues(codAbastecimiento);
	implicacionComunidadHandler.fillValues(codAbastecimiento);
	datosConsumoHandler.fillValues(codAbastecimiento);
	valoracionSistemaHandler.fillValues(codAbastecimiento);
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	juntasAguaHandler.reload(new JuntasAguaForm());
	coberturaHandler.reload(new CoberturaForm());
	gestionComercialHandler.reload(new GestComercialForm());
	gestionFinancieraHandler.reload(new GestFinancieraForm());
	evaluacionHandler.reload(new EvaluacionForm());
	captacionesHandler.reload(CaptacionesForm.NAME,
		FonsaguaFormFactory.getInstance());
	depIntermediosHandler.reload(DepIntermediosForm.NAME,
		FonsaguaFormFactory.getInstance());
	depDistribucionHandler.reload(DepDistribucionForm.NAME,
		FonsaguaFormFactory.getInstance());
	tuberiasHandler.reload(TuberiasForm.NAME,
		FonsaguaFormFactory.getInstance());
	bombeosHandler.reload(BombeosForm.NAME,
		FonsaguaFormFactory.getInstance());
	comunidadesRelationship.reload(FonsaguaFormFactory.getInstance()
		.createSingletonForm(
			new TOCLayerManager()
				.getLayerByName(ComunidadesForm.NAME)));
	fuentesRelationship
		.reload(FonsaguaFormFactory.getInstance()
			.createSingletonForm(
			new TOCLayerManager().getLayerByName(FuentesForm.NAME)));
	adescosHandler.reload(new AdescosForm());
	implicacionComunidadHandler.reloadGUI();
	datosConsumoHandler.reloadGUI();
	valoracionSistemaHandler.reloadGUI();
    }

    @Override
    protected void removeListeners() {
	super.removeListeners();
	juntasAguaHandler.removeListeners();
	coberturaHandler.removeListeners();
	gestionComercialHandler.removeListeners();
	gestionFinancieraHandler.removeListeners();
	evaluacionHandler.removeListeners();
	captacionesHandler.removeListeners();
	depIntermediosHandler.removeListeners();
	depDistribucionHandler.removeListeners();
	tuberiasHandler.removeListeners();
	bombeosHandler.removeListeners();
	comunidadesRelationship.removeListeners();
	fuentesRelationship.removeListeners();
	adescosHandler.removeListeners();
	implicacionComunidadHandler.removeListeners();
	datosConsumoHandler.removeListeners();
	valoracionSistemaHandler.removeListeners();
    }

    @Override
    public void onPositionChange(PositionEvent e) {
	super.onPositionChange(e);
	fillSpecificValues();
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
