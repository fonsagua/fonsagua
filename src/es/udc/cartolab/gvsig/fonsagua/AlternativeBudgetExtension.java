package es.udc.cartolab.gvsig.fonsagua;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.gui.tables.model.AlphanumericTableModel;
import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelFactory;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.PresupuestoForm;

public class AlternativeBudgetExtension extends AbstractExtension {

    protected AbstractSubForm form = null;
    private String altCode;

    @Override
    public void initialize() {
	id = "alternative_budget";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {

	FormFactory.checkAndLoadTableRegistered(PresupuestoForm.NAME);
	if (form == null) {
	    form = FormFactory.createSubFormRegistered(PresupuestoForm.NAME);
	} else {
	    form.setListeners();
	}
	try {
	    AlphanumericTableModel model = TableModelFactory
		    .createFromTableWithFilter(PresupuestoForm.NAME,
			    AlternativasForm.PKFIELD, altCode, new String[0],
			    new String[0]);
	    if (model.getRowCount() > 0) {
		form.setModel(model);
		form.actionUpdateRecord(model.convertRowIndexToModel(0));
	    }
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public boolean isEnabled() {
	altCode = OpenAlternativeExtension.getCode();
	return (altCode != null);
    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
