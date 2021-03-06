package es.udc.cartolab.gvsig.fonsagua;

import java.util.ArrayList;

import javax.swing.JTable;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.project.documents.ProjectDocument;

import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.gui.tables.model.AlphanumericTableModel;
import es.icarto.gvsig.navtableforms.gui.tables.model.TableModelFactory;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.PreferenciasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class AlternativePreferencesExtension extends AbstractExtension {

    protected AbstractSubForm form = null;
    private String altCode;

    @Override
    public void initialize() {
	id = "alternative_preferences";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {

	removePreferencesTable();

	FormFactory.checkAndLoadTableRegistered(PreferenciasForm.NAME);
	FormFactory
		.checkAndLoadTableRegistered(FonsaguaConstants.TUBERIAS_COMERCIALES_NAME);
	FormFactory
		.checkAndLoadTableRegistered(FonsaguaConstants.BOMBAS_COMERCIALES_NAME);
	if (form == null) {
	    form = FormFactory.createSubFormRegistered(PreferenciasForm.NAME);
	}
	try {
	    AlphanumericTableModel model = TableModelFactory
		    .createFromTableWithFilter(PreferenciasForm.NAME,
			    AlternativasForm.PKFIELD, altCode, new String[0],
			    new String[0]);
	    if (model.getRowCount() > 0) {
		form.setModel(model);
		int posInRecordset = model.convertRowIndexToModel(0);
		model = TableModelFactory.createFromTableWithNotFilter(
			FonsaguaConstants.TUBERIAS_COMERCIALES_NAME,
			FonsaguaConstants.TUBERIAS_COMERCIALES_TABLE_FIELDS[0],
			"",
			FonsaguaConstants.TUBERIAS_COMERCIALES_TABLE_FIELDS,
			FonsaguaConstants.TUBERIAS_COMERCIALES_TABLE_ALIAS);
		((JTable) form.getWidgets().get(
			FonsaguaConstants.TUBERIAS_COMERCIALES_NAME))
			.setModel(model);
		model = TableModelFactory.createFromTableWithNotFilter(
			FonsaguaConstants.BOMBAS_COMERCIALES_NAME,
			FonsaguaConstants.BOMBAS_COMERCIALES_TABLE_FIELDS[0],
			"", FonsaguaConstants.BOMBAS_COMERCIALES_TABLE_FIELDS,
			FonsaguaConstants.BOMBAS_COMERCIALES_TABLE_ALIAS);
		((JTable) form.getWidgets().get(
			FonsaguaConstants.BOMBAS_COMERCIALES_NAME))
			.setModel(model);
		form.actionUpdateRecord(posInRecordset);
	    }
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}
    }

    /*
     * Cerrar y abrir la tabla de preferencias. Llega con esto, para poder
     * recargar la tabla tras meter los datos en el trigger de alternativas.
     */
    private void removePreferencesTable() {
	ProjectExtension pExt = (ProjectExtension) PluginServices
		.getExtension(ProjectExtension.class);
	ArrayList<ProjectDocument> tables = pExt.getProject()
		.getDocumentsByType("ProjectTable");
	for (ProjectDocument doc : tables) {
	    if (doc.getName().equals(PreferenciasForm.NAME)) {
		PluginServices.getMDIManager().closeSingletonWindow(doc);
		pExt.getProject().delDocument(doc);
	    }
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
