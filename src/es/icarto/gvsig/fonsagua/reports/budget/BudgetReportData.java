package es.icarto.gvsig.fonsagua.reports.budget;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.icarto.gvsig.fonsagua.reports.utils.ReportUtils;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltBombeosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltDepositosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltFuentesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class BudgetReportData {

    private final static Locale locale = Locale.getDefault();

    private BudgetReportData() {
	throw new AssertionError(
		"Suppress default constructor for noninstantiability");
    }

    public static List<String[]> get(String codAlt) throws SQLException {
	List<String[]> list = new ArrayList<String[]>();

	NumberFormat formatter = NumberFormat.getNumberInstance(locale);
	BigDecimal a = BigDecimal.valueOf(0);

	DBSession session = DBSession.getCurrentSession();

	final String whereClause = " where cod_alternativa = '" + codAlt + "'";
	String[][] table = session.getTable(AlternativasForm.NAME,
		FonsaguaConstants.dataSchema, new String[] {
			"tipo_alternativa", "tipo_distribucion",
			"tipo_sistema", "demanda", "pobl_actual",
			"n_cent_educativos", "n_cent_salud", "n_cent_otros",
			"n_acomedidas", "tipo_saneamiento", "trat_trampa",
			"trat_biofiltros", "trat_otros", "coment_otros",
			"let_hoyo", "let_septica", "let_hidraulica",
			"let_abonera" }, whereClause,
		new String[] { "cod_alternativa" }, false);

	list.add(new String[] { "Tipo de actuación",
		ReportUtils.getValueFormatted(table[0][0]) });
	list.add(new String[] { "Tipo de distribución",
		ReportUtils.getValueFormatted(table[0][1]) });
	list.add(new String[] { "Tipo de sistema",
		ReportUtils.getValueFormatted(table[0][2]) });
	list.add(new String[] { "Demanda total(l/s)",
		ReportUtils.getValueFormatted(table[0][3]) });
	list.add(new String[] { "Número total de población implicada",
		ReportUtils.getValueFormatted(table[0][4]) });
	list.add(new String[] { "Número de centros de salud considerados",
		ReportUtils.getValueFormatted(table[0][5]) });
	list.add(new String[] { "Número de otros centros considerados",
		ReportUtils.getValueFormatted(table[0][6]) });

	list.add(new String[] { "Número de acometidas",
		ReportUtils.getValueFormatted(table[0][7]) });
	list.add(new String[] { "Nº de trampas de agua",
		ReportUtils.getValueFormatted(table[0][8]) });
	list.add(new String[] { "Nº de biofiltros",
		ReportUtils.getValueFormatted(table[0][9]) });
	list.add(new String[] { "Nº de otros tratamientos",
		ReportUtils.getValueFormatted(table[0][10]) });
	list.add(new String[] { "Otros (Tipo)",
		ReportUtils.getValueFormatted(table[0][11]) });
	list.add(new String[] { "Nº de letrinas de hoyo",
		ReportUtils.getValueFormatted(table[0][12]) });
	list.add(new String[] { "Nº de fosas sépticas",
		ReportUtils.getValueFormatted(table[0][13]) });
	list.add(new String[] { "Nº letrinas de cierre hidráulico",
		ReportUtils.getValueFormatted(table[0][14]) });
	list.add(new String[] { "Nº de letrinas aboneras",
		ReportUtils.getValueFormatted(table[0][15]) });

	list.add(new String[] { "Fuentes" });
	list.add(new String[] { "Tipo de fuente", "Estado",
		"Caudal a usar (l/s)" });
	String[][] fuentes = session.getTable(AltFuentesForm.NAME,
		FonsaguaConstants.dataSchema, new String[] {
			"tipo_fuente_alternativa", "existencia_elemento",
			"q_usar" }, whereClause,
		new String[] { "tipo_fuente_alternativa" }, false);
	fillTable(list, fuentes, 5);

	list.add(new String[] { "Depósitos" });
	list.add(new String[] { "Estado", "Tipo", "Construcción",
		"Volumen (m2)" });
	String[][] depositos = session.getTable(AltDepositosForm.NAME,
		FonsaguaConstants.dataSchema, new String[] {
			"existencia_elemento", "tipo_deposito",
			"tipo_construccion", "volumen" }, whereClause,
		new String[] { "tipo_deposito" }, false);
	fillTable(list, depositos, 5);

	list.add(new String[] { "Tuberias" });
	list.add(new String[] { "ID", "Material", "Diametro",
		"Tipo de tuberia", "Longitud total" });
	List<String[]> tuberias = DatabaseDirectAccessQueries
		.getTuberiasForBudget(codAlt);

	list.addAll(tuberias);

	list.add(new String[] { "Bombeos" });
	list.add(new String[] { "Estado", "Altura (m)", "Tiempo (h)", "Modelo",
		"Caudal (l/s)" });
	String[][] bombas = session.getTable(AltBombeosForm.NAME,
		FonsaguaConstants.dataSchema, new String[] {
			"existencia_elemento", "altura_bombeo",
			"tiempo_bombeo", "bomba_comercial", "caudal" },
		whereClause, new String[] { "bomba_comercial" }, false);
	fillTable(list, bombas, 5);

	return list;
    }

    private static void fillTable(List<String[]> list, String[][] table,
	    int minRows) {
	for (String[] row : table) {
	    for (int i = 0; i < row.length; i++) {
		row[i] = ReportUtils.getValueFormatted(row[i]);
	    }
	    list.add(row);
	}
	if (table.length < minRows) {
	    for (int i = 0; i < minRows - table.length; i++) {
		list.add(new String[] { " " });
	    }

	}
    }
}
