package es.icarto.gvsig.fonsagua.reports.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.PreferenciasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.PresupuestoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.FuentesForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class ReportDAO {

    private static DBSession session = DBSession.getCurrentSession();

    public static ResultSet getCommunityValues(String communityCode) {
	return getFonsaguaTableValues(ComunidadesForm.NAME,
		ComunidadesForm.PKFIELD, communityCode);
    }

    public static String getCommunityValueByColumnName(String columnName,
	    String communityCode) {
	return getFonsaguaTableValueByColumnName(ComunidadesForm.NAME,
		ComunidadesForm.PKFIELD, communityCode, columnName);
    }

    public static ResultSet getAlternativeValues(String alternativeCode) {
	return getFonsaguaTableValues(AlternativasForm.NAME,
		AlternativasForm.PKFIELD, alternativeCode);
    }

    public static String getAlternativeValueByColumnName(String columnName,
	    String alternativeCode) {
	return getFonsaguaTableValueByColumnName(AlternativasForm.NAME,
		AlternativasForm.PKFIELD, alternativeCode, columnName);
    }

    public static ResultSet getPresupuestoValues(String alternativeCode) {
	return getFonsaguaTableValues(PresupuestoForm.NAME,
		AlternativasForm.PKFIELD, alternativeCode);
    }

    public static ResultSet getFonsaguaTableValues(String tableName,
	    String pkName, String pkValue) {
	try {
	    return DBSession.getCurrentSession().getTableAsResultSet(tableName,
		    FonsaguaConstants.dataSchema,
		    " WHERE " + pkName + " = '" + pkValue + "'");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getFonsaguaTableValueByColumnName(String tableName,
	    String pkName, String pkValue, String columnName) {
	ResultSet rs = getFonsaguaTableValues(tableName, pkName, pkValue);
	String value;
	try {
	    rs.next();
	    value = rs.getString(columnName);
	    return value;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String[][] getDataForCommunityRelatedTable(String tableName,
	    String[] fieldNames, String communityCode) {
	String whereClause = ComunidadesForm.PKFIELD + " = '" + communityCode
		+ "'";
	try {
	    return DBSession.getCurrentSession().getTable(tableName,
		    FonsaguaConstants.dataSchema, fieldNames, whereClause,
		    null, false);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String[][] getDataForAlternativeRelatedTable(
	    String tableName, String[] fieldNames, String alternativeCode) {
	String whereClause = AlternativasForm.PKFIELD + " = '"
		+ alternativeCode + "'";
	try {
	    return DBSession.getCurrentSession().getTable(tableName,
		    FonsaguaConstants.dataSchema, fieldNames, whereClause,
		    null, false);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static int getNumberOfElementsFromRelationshipTable(
	    String relationshipTableName, String codeField, String codeValue) {
	try {
	    return session.getTable(relationshipTableName,
		    FonsaguaConstants.dataSchema, " WHERE " + codeField
			    + " = '" + codeValue + "'").length;
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return -1;
    }

    // TODO: Try to make a generic method
    public static String[][] getDataFromAbastecimientosTableByCommunity(
	    String communityCode) {
	String[] tableNames = { AbastecimientosForm.NAME,
		"r_abastecimientos_comunidades", ComunidadesForm.NAME };
	String[] joinFields = { "a." + AbastecimientosForm.PKFIELD,
		"b." + AbastecimientosForm.PKFIELD,
		"b." + ComunidadesForm.PKFIELD, "c." + ComunidadesForm.PKFIELD };
	String[] fieldNames = { "abastecimiento", "a.cod_abastecimiento" };
	try {
	    return session.getTableWithJoin(tableNames, new String[3],
		    joinFields, fieldNames, "WHERE c."
			    + ComunidadesForm.PKFIELD + " = '" + communityCode
			    + "'", new String[0], false);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // TODO: Try to make a generic method
    public static String[][] getDataOfElementOfAbastecimientoByCommunity(
	    String elementTableName, String[] colNames, String communityCode) {
	String[] tableNames = { AbastecimientosForm.NAME,
		"r_abastecimientos_comunidades", ComunidadesForm.NAME,
		elementTableName };
	String[] joinFields = { "a." + AbastecimientosForm.PKFIELD,
		"b." + AbastecimientosForm.PKFIELD,
		"b." + ComunidadesForm.PKFIELD, "c." + ComunidadesForm.PKFIELD,
		"a." + AbastecimientosForm.PKFIELD,
		"d." + AbastecimientosForm.PKFIELD };
	try {
	    return session.getTableWithJoin(tableNames, new String[3],
		    joinFields, colNames, "WHERE c." + ComunidadesForm.PKFIELD
			    + " = '" + communityCode + "'", new String[0],
		    false);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // TODO: Try to make a generic method
    public static String[][] getDataOfFuentesByCommunity(String communityCode) {
	String[] tableNames = { FuentesForm.NAME, "r_abastecimientos_fuentes",
		"r_abastecimientos_comunidades" };
	String[] colNames = { "a.fuente", "a." + FuentesForm.PKFIELD };
	String[] joinFields = { "a." + FuentesForm.PKFIELD,
		"b." + FuentesForm.PKFIELD, "b." + AbastecimientosForm.PKFIELD,
		"c." + AbastecimientosForm.PKFIELD };
	try {
	    return session.getTableWithJoin(tableNames, new String[3],
		    joinFields, colNames, "WHERE c." + ComunidadesForm.PKFIELD
			    + " = '" + communityCode + "'", new String[0],
		    false);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    // TODO: Try to make a generic method
    public static String[][] getDataOfElementOfFuentesByCommunity(
	    String elementTableName, String[] colNames, String communityCode) {
	String[] tableNames = { FuentesForm.NAME, "r_abastecimientos_fuentes",
		"r_abastecimientos_comunidades", elementTableName };
	String[] joinFields = { "a." + FuentesForm.PKFIELD,
		"b." + FuentesForm.PKFIELD, "b." + AbastecimientosForm.PKFIELD,
		"c." + AbastecimientosForm.PKFIELD, "a." + FuentesForm.PKFIELD,
		"d." + FuentesForm.PKFIELD };
	try {
	    return session.getTableWithJoin(tableNames, new String[3],
		    joinFields, colNames, "WHERE c." + ComunidadesForm.PKFIELD
			    + " = '" + communityCode + "'", new String[0],
		    false);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getDotacion(String alternativeCode) {
	String tipoDistribucion = getFonsaguaTableValueByColumnName(
		AlternativasForm.NAME, AlternativasForm.PKFIELD,
		alternativeCode, "tipo_distribucion");
	if (tipoDistribucion.equalsIgnoreCase("Cantareras")) {
	    return getFonsaguaTableValueByColumnName(PreferenciasForm.NAME,
		    AlternativasForm.PKFIELD, alternativeCode, "dot_cantareras");
	} else if (tipoDistribucion.equalsIgnoreCase("Domiciliar")) {
	    return getFonsaguaTableValueByColumnName(PreferenciasForm.NAME,
		    AlternativasForm.PKFIELD, alternativeCode, "dot_domiciliar");
	} else {
	    return null;
	}
    }
}
