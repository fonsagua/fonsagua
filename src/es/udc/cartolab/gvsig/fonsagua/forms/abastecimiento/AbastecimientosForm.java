package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class AbastecimientosForm extends BasicAbstractForm {

    public static final String NAME = "abastecimientos";
    private TableHandler juntasAguaHandler;
    private TableHandler coberturaHandler;
    private TableHandler gestionComercialHandler;
    private TableHandler gestionFinancieraHandler;

    public AbastecimientosForm(FLyrVect layer) {
	super(layer);
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
    }

    @Override
    protected void fillSpecificValues() {
	juntasAguaHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
	coberturaHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
	gestionComercialHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
	gestionFinancieraHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	juntasAguaHandler.reload(new JuntasAguaForm());
	coberturaHandler.reload(new CoberturaForm());
	gestionComercialHandler.reload(new GestionComercialForm());
	gestionFinancieraHandler.reload(new GestionFinancieraForm());
    }

    @Override
    protected void removeListeners() {
	super.removeListeners();
	juntasAguaHandler.removeListeners();
	coberturaHandler.removeListeners();
	gestionComercialHandler.removeListeners();
	gestionFinancieraHandler.removeListeners();
    }

    @Override
    public void onPositionChange(PositionEvent e) {
	super.onPositionChange(e);
	juntasAguaHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
	coberturaHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
	gestionComercialHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
	gestionFinancieraHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
