package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.VectorialTableHandler;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class AbastecimientosForm extends BasicAbstractForm {

    public static final String NAME = "abastecimientos";
    private TableHandler juntasAguaHandler;
    private TableHandler coberturaHandler;
    private TableHandler gestionComercialHandler;
    private TableHandler gestionFinancieraHandler;
    private TableHandler evaluacionHandler;
    private VectorialTableHandler captacionesHandler;
    private VectorialTableHandler depIntermediosHandler;
    private VectorialTableHandler depDistribucionHandler;
    private VectorialTableHandler tuberiasHandler;
    private VectorialTableHandler bombeosHandler;

    public AbastecimientosForm(FLyrVect layer) {
	super(layer);
	TOCLayerManager toc = new TOCLayerManager();
	viewInfo.setTitle("Abastecimientos");
	juntasAguaHandler = new TableHandler(JuntasAguaForm.NAME,
		getWidgetComponents(), "cod_abastecimiento",
		JuntasAguaForm.colNames, JuntasAguaForm.colAlias);
	coberturaHandler = new TableHandler(CoberturaForm.NAME,
		getWidgetComponents(), "cod_abastecimiento",
		CoberturaForm.colNames, CoberturaForm.colAlias);
	gestionComercialHandler = new TableHandler(GestionComercialForm.NAME,
		getWidgetComponents(), "cod_abastecimiento",
		GestionComercialForm.colNames, GestionComercialForm.colAlias);
	gestionFinancieraHandler = new TableHandler(GestionFinancieraForm.NAME,
		getWidgetComponents(), "cod_abastecimiento",
		GestionFinancieraForm.colNames, GestionFinancieraForm.colAlias);
	evaluacionHandler = new TableHandler(EvaluacionForm.NAME,
		getWidgetComponents(), "cod_abastecimiento",
		EvaluacionForm.colNames, EvaluacionForm.colAlias);
	captacionesHandler = new VectorialTableHandler(
		toc.getLayerByName(CaptacionesForm.NAME),
		getWidgetComponents(), "cod_abastecimiento",
		CaptacionesForm.colNames, CaptacionesForm.colAlias);
	depIntermediosHandler = new VectorialTableHandler(
		toc.getLayerByName(DepIntermediosForm.NAME),
		getWidgetComponents(), "cod_abastecimiento",
		DepIntermediosForm.colNames, DepIntermediosForm.colAlias);
	depDistribucionHandler = new VectorialTableHandler(
		toc.getLayerByName(DepDistribucionForm.NAME),
		getWidgetComponents(), "cod_abastecimiento",
		DepDistribucionForm.colNames, DepDistribucionForm.colAlias);
	tuberiasHandler = new VectorialTableHandler(
		toc.getLayerByName(TuberiasForm.NAME), getWidgetComponents(),
		"cod_abastecimiento", TuberiasForm.colNames,
		TuberiasForm.colAlias);
	bombeosHandler = new VectorialTableHandler(
		toc.getLayerByName(BombeosForm.NAME), getWidgetComponents(),
		"cod_abastecimiento", BombeosForm.colNames,
		BombeosForm.colAlias);
    }

    @Override
    protected void fillSpecificValues() {
	String codAbastecimiento = getFormController().getValue(
		"cod_abastecimiento");
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
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	juntasAguaHandler.reload(new JuntasAguaForm());
	coberturaHandler.reload(new CoberturaForm());
	gestionComercialHandler.reload(new GestionComercialForm());
	gestionFinancieraHandler.reload(new GestionFinancieraForm());
	evaluacionHandler.reload(new EvaluacionForm());
	captacionesHandler.reload(CaptacionesForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	depIntermediosHandler.reload(DepIntermediosForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	depDistribucionHandler.reload(DepDistribucionForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	tuberiasHandler.reload(TuberiasForm.NAME,
		FonsaguaTableFormFactory.getInstance());
	bombeosHandler.reload(BombeosForm.NAME,
		FonsaguaTableFormFactory.getInstance());
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
