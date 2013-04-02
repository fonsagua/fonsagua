package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableHandler;
import es.udc.cartolab.gvsig.navtable.listeners.PositionEvent;

@SuppressWarnings("serial")
public class AbastecimientosForm extends BasicAbstractForm {

    public static final String NAME = "abastecimientos";
    private TableHandler juntasAguaHandler;

    public AbastecimientosForm(FLyrVect layer) {
	super(layer);
	viewInfo.setTitle("Abastecimientos");
	juntasAguaHandler = new TableHandler(JuntasAguaForm.NAME,
		getWidgetComponents(), "cod_abastecimiento",
		JuntasAguaForm.colNames, JuntasAguaForm.colAlias);
    }

    @Override
    protected void fillSpecificValues() {
	juntasAguaHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
    }

    @Override
    protected void setListeners() {
	super.setListeners();
	juntasAguaHandler.reload(new JuntasAguaForm());
    }

    @Override
    protected void removeListeners() {
	super.removeListeners();
	juntasAguaHandler.removeListeners();
    }

    @Override
    public void onPositionChange(PositionEvent e) {
	super.onPositionChange(e);
	juntasAguaHandler.fillValues(getFormController().getValue(
		"cod_abastecimiento"));
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
