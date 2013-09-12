package es.icarto.gvsig.fonsagua.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;

import es.icarto.gvsig.fonsagua.reports.utils.ReportDAO;
import es.icarto.gvsig.fonsagua.reports.utils.ReportData;
import es.icarto.gvsig.fonsagua.reports.utils.ReportUtils;
import es.icarto.gvsig.fonsagua.reports.utils.RtfReportStyles;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

public class AlternativeRTFReport extends RTFReport {

    public AlternativeRTFReport(String fileName, String pkValue) {
	super(fileName, pkValue);
    }

    @Override
    protected String getTitle() {
	return "INFORME DE PROPUESTAS PARA EL ABASTECIMIENTO DE AGUA Y SANEAMIENTO";
    }

    @Override
    protected String getSubtitle() {
	return "(COMUNIDAD DE (A CUBRIR POR TÉCNICO))";
    }

    @Override
    protected String getHeaderText() {
	return "Informe de Propuestas Comunitario \n"
		+ "Fecha de levantamiento: (A CUBRIR POR TÉCNICO)";
    }

    @Override
    protected String getFooterText() {
	return "Plan de Gestión Integral del Recurso Hídrico en el municipio "
		+ ReportDAO.getAlternativeValueByColumnName("municipio",
			pkValue)
		+ "\n"
		+ "Informe de propuestas de la comunidad (A CUBRIR POR TÉCNICO)";
    }

    @Override
    protected void writeSpecificContent() {
	try {
	    ResultSet rs = ReportDAO.getAlternativeValues(pkValue);
	    rs.next();

	    ArrayList<ReportData> data = new ArrayList<ReportData>();

	    RtfReportStyles.writeHeading1(document,
		    "1. ALTERNATIVA PROPUESTA PARA EL ABASTECIMIENTO Y SANEAMIENTO DE AGUA: "
			    + pkValue);

	    Paragraph attribute = new Paragraph(
		    "Prioridad asignada a la alternativa: ",
		    RtfReportStyles.normalBoldStyle);
	    Paragraph value = new Paragraph("(A cubrir por técnico)",
		    RtfReportStyles.normalStyle);

	    document.add(Chunk.NEWLINE);
	    document.add(attribute);
	    document.add(value);

	    writeSection1_1(rs, data);
	    writeSection1_2(rs, data);
	    writeSection1_3(rs, data);
	    writeSection1_4(rs, data);

	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}

    }

    private void writeSection1_1(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles
		.writeHeading2(document, "1.1 COMUNIDADES BENEFICIARIAS");

	data.clear();
	data.add(new ReportData("Departamento: ", rs.getString("departamento"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Municipio: ", rs.getString("municipio"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Cantón: ", rs.getString("canton"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	ReportUtils.writeTable(document,
		FonsaguaConstants.COMUNIDADES_IMPLICADAS_COL_ALIAS,
		ReportDAO.getDataForAlternativeRelatedTable(
			FonsaguaConstants.COMUNIDADES_IMPLICADAS,
			FonsaguaConstants.COMUNIDADES_IMPLICADAS_COL_NAMES,
			pkValue));
	document.add(Chunk.NEWLINE);

	data.clear();
	data.add(new ReportData("Número total de población implicada: ", rs
		.getString("pobl_actual"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de centros educativos considerados: ",
		rs.getString("n_cent_educativos"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de centros de salud considerados: ", rs
		.getString("n_cent_salud"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de otros centros considerados: ", rs
		.getString("n_cent_otros"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
    }

    private void writeSection1_2(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document,
		"1.2 PROPUESTA DE ACTUACIÓN PARA EL ABASTECIMIENTO");

	data.clear();
	data.add(new ReportData("Tipo de actuación: ", rs
		.getString("tipo_alternativa"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Tipo de distribución: ", rs
		.getString("tipo_distribución"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Cantón: ", rs.getString("tipo_sistema"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph attribute = new Paragraph(
		"Descripción de la propuesta de actuación: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph(rs.getString("coment_alternativa"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);

    }

    private void writeSection1_3(ResultSet rs, ArrayList<ReportData> data) {
	// TODO Auto-generated method stub

    }

    private void writeSection1_4(ResultSet rs, ArrayList<ReportData> data) {
	// TODO Auto-generated method stub

    }

}
