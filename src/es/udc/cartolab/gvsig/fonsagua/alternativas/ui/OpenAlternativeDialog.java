package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiFrame.MDIFrame;

import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class OpenAlternativeDialog {

    private String code;

    public OpenAlternativeDialog() {
	String tableName = AlternativasForm.NAME;
	String schema = FonsaguaConstants.dataSchema;
	String fieldName = FonsaguaConstants.COD_ALTERNATIVA;

	String[] options;
	try {
	    options = DBSession.getCurrentSession().getDistinctValues(
		    tableName, schema, fieldName);
	    if (options.length == 0) {
		JOptionPane.showMessageDialog(
			(MDIFrame) PluginServices.getMainFrame(),
			"No hay alternativas en el sistema");
		code = null;
		return;
	    }
	    code = (String) JOptionPane.showInputDialog(
		    (MDIFrame) PluginServices.getMainFrame(),
		    "Escoja alternativa", "", JOptionPane.QUESTION_MESSAGE,
		    null, options, options[0]);
	} catch (SQLException e) {
	    code = null;
	    throw new RuntimeException(
		    "Error externo. No se puede abrir la alternativa", e);
	}

    }

    public String getCode() {
	return code;
    }

}
