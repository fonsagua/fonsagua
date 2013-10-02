package es.icarto.gvsig.fonsagua.reports.ui;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import au.com.bytecode.opencsv.CSVWriter;
import es.icarto.gvsig.fonsagua.reports.budget.BudgetReportData;
import es.udc.cartolab.gvsig.epanet.exceptions.ExternalError;

public class AlternativeBudgetCSVReport {

    public AlternativeBudgetCSVReport(String absolutePath, String codAlt) {
	try {
	    List<String[]> list = BudgetReportData.get(codAlt);
	    CSVWriter writer = new CSVWriter(new FileWriter(absolutePath), ';');
	    writer.writeAll(list);
	    writer.close();
	} catch (SQLException e) {
	    throw new ExternalError(e);
	} catch (IOException e) {
	    throw new ExternalError(e);
	}

    }

}
