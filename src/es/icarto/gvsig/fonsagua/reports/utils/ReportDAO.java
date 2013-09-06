package es.icarto.gvsig.fonsagua.reports.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class ReportDAO {

    private static Connection connection = DBSession.getCurrentSession()
	    .getJavaConnection();

    public static ResultSet getCommunityValues(String communityCode) {
	PreparedStatement statement = null;

	try {
	    String query = "SELECT * FROM " + FonsaguaConstants.dataSchema
		    + "." + ComunidadesForm.NAME + " WHERE "
		    + ComunidadesForm.PKFIELD + " = ?";
	    statement = connection.prepareStatement(query);
	    statement.setString(1, communityCode);
	    statement.execute();
	    ResultSet rs = statement.getResultSet();
	    return rs;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getCommunityName(String communityCode) {
	ResultSet rs = getCommunityValues(communityCode);
	String communityName;
	try {
	    rs.next();
	    communityName = rs.getString("comunidad");
	    return communityName;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getCommunityMunicipality(String communityCode) {
	ResultSet rs = getCommunityValues(communityCode);
	String communityMunicipality;
	try {
	    rs.next();
	    communityMunicipality = rs.getString("municipio");
	    return communityMunicipality;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
