package es.udc.cartolab.gvsig.fonsagua.utils;

import java.sql.SQLException;

import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class DatabaseDirectAccessQueries {

    public static void insertDefaultPreferences(String codAlt)
	    throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	session.insertRow(FonsaguaConstants.dataSchema,
		FonsaguaConstants.preferencesTable,
		new String[] { AlternativasForm.PKFIELD },
		new String[] { codAlt });
    }
}
