package es.udc.cartolab.gvsig.fonsagua.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class FonsaguaFilterFields {

    public static Map<String, String> getDepartments() throws SQLException {
	String[] fields = new String[2];
	String[] orderBy = new String[1];
	Map<String, String> departNames = new HashMap<String, String>();
	fields[0] = FonsaguaConstants.departamentosPK;
	fields[1] = FonsaguaConstants.departamentosName;
	orderBy[0] = FonsaguaConstants.departamentosPK;
	String[][] departamentos = DBSession.getCurrentSession().getTable(
		FonsaguaConstants.departamentosTable,
		FonsaguaConstants.baseSchema, fields, "", orderBy, false);
	for (String[] depart : departamentos) {
	    departNames.put(depart[0], depart[1]);
	}
	return departNames;
    }

    public static Map<String, String> getMuniccipalities() throws SQLException {
	String[] fields = new String[2];
	String[] orderBy = new String[1];
	Map<String, String> municNames = new HashMap<String, String>();
	fields[0] = FonsaguaConstants.municipiosPK;
	fields[1] = FonsaguaConstants.municipiosName;
	orderBy[0] = FonsaguaConstants.municipiosPK;
	String[][] municipios = DBSession.getCurrentSession().getTable(
		FonsaguaConstants.municipiosTable,
		FonsaguaConstants.baseSchema, fields, "", orderBy, false);
	for (String[] munic : municipios) {
	    if ((munic[0] != null) && (munic[0].length() >= 2)) {
		municNames.put(munic[0], munic[1]);
	    }

	}
	return municNames;
    }

    public static Map<String, String> getCantones() throws SQLException {
	String[] fields = new String[2];
	String[] orderBy = new String[1];
	Map<String, String> cantonNames = new HashMap<String, String>();
	fields[0] = FonsaguaConstants.cantonesPK;
	fields[1] = FonsaguaConstants.cantonesName;
	orderBy[0] = FonsaguaConstants.cantonesPK;
	String[][] cantones = DBSession.getCurrentSession().getTable(
		FonsaguaConstants.cantonesTable, FonsaguaConstants.baseSchema,
		fields, "", orderBy, false);
	for (String[] canton : cantones) {
	    if ((canton[0] != null) && (canton[0].length() >= 4)) {
		cantonNames.put(canton[0], canton[1]);
	    }
	}
	return cantonNames;
    }

    public static String[][] getAlternatives() throws SQLException {
	String[] fields = new String[2];
	String[] orderBy = new String[1];
	fields[0] = AlternativasForm.PKFIELD;
	fields[1] = AlternativasForm.CANTONFK;
	orderBy[0] = AlternativasForm.PKFIELD;
	String[][] alternativas = DBSession.getCurrentSession().getTable(
		AlternativasForm.NAME, FonsaguaConstants.dataSchema, fields,
		"", orderBy, false);
	return alternativas;
    }

    public static String[][] getComunidades() throws SQLException {
	String[] fields = new String[2];
	String[] orderBy = new String[1];
	fields[0] = ComunidadesForm.PKFIELD;
	fields[1] = ComunidadesForm.CANTONFK;
	orderBy[0] = ComunidadesForm.PKFIELD;
	String[][] comunidades = DBSession.getCurrentSession().getTable(
		ComunidadesForm.NAME, FonsaguaConstants.dataSchema, fields, "",
		orderBy, false);
	return comunidades;
    }

    public static Map<String, Map<String, Map<String, List<String>>>> getDivCodes(
	    String[][] elements) {
	String departCode;
	String municCode;
	Map<String, Map<String, Map<String, List<String>>>> divsCodes = new HashMap<String, Map<String, Map<String, List<String>>>>();
	for (String[] element : elements) {
	    if ((element[0] != null) && (element[1] != null)
		    && (element[1].length() >= 4)) {
		departCode = element[1].substring(0, 2);
		municCode = element[1].substring(0, 4);
		if (!divsCodes.containsKey(departCode)) {
		    divsCodes.put(departCode,
			    new HashMap<String, Map<String, List<String>>>());
		}
		if (!divsCodes.get(departCode).containsKey(municCode)) {
		    divsCodes.get(departCode).put(municCode,
			    new HashMap<String, List<String>>());
		}
		if (!divsCodes.get(departCode).get(municCode)
			.containsKey(element[1])) {
		    divsCodes.get(departCode).get(municCode)
			    .put(element[1], new ArrayList<String>());
		}
		divsCodes.get(departCode).get(municCode).get(element[1])
			.add(element[0]);
	    }
	}
	return divsCodes;
    }

}
