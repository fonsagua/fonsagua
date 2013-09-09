package es.icarto.gvsig.fonsagua.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;

import es.icarto.gvsig.fonsagua.reports.utils.ReportDAO;
import es.icarto.gvsig.fonsagua.reports.utils.ReportData;
import es.icarto.gvsig.fonsagua.reports.utils.ReportUtils;
import es.icarto.gvsig.fonsagua.reports.utils.RtfReportStyles;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AdescosForm;

public class CommunityRTFReport extends RTFReport {

    public CommunityRTFReport(String fileName, String communityName) {
	super(fileName, communityName);
    }

    @Override
    protected String getTitle() {
	return "INFORME DE DIAGN�STICO COMUNITARIO";
    }

    @Override
    protected String getSubtitle() {
	return "(COMUNIDAD DE "
		+ ReportDAO.getCommunityValueByColumnName("comunidad", pkValue)
			.toUpperCase() + ")";
    }

    @Override
    protected String getHeaderText() {
	return "Informe de Diagn�stico Comunitario \n"
		+ "Fecha de levantamiento:";
    }

    @Override
    protected void writeFooter() {
	Paragraph footerText = new Paragraph(
		"Plan de Gesti�n Integral del Recurso H�drico en el municipio "
			+ ReportDAO.getCommunityValueByColumnName("municipio",
				pkValue)
			+ "\n"
			+ "Informe de diagn�stico de la comunidad "
			+ ReportDAO.getCommunityValueByColumnName("comunidad",
				pkValue), RtfReportStyles.headerFooterTextStyle);

	RtfHeaderFooter footer = new RtfHeaderFooter(footerText);

	document.setFooter(footer);
    }

    @Override
    protected void writeSpecificContent() {
	try {
	    ResultSet rs = ReportDAO.getCommunityValues(pkValue);
	    rs.next();

	    ArrayList<ReportData> data = new ArrayList<ReportData>();

	    RtfReportStyles.writeHeading1(document,
		    "1. DATOS GENERALES DE LA COMUNIDAD");

	    writeSection1_1(rs, data);
	    writeSection1_2(rs, data);
	    writeSection1_3(rs, data);

	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }

    private void writeSection1_1(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException {
	data.add(new ReportData("Nombre: ", rs.getString("comunidad"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("C�digo de la comunidad: ", rs
		.getString("cod_comunidad"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading2(document,
		"1.1 UBICACI�N Y CARACTER�STICAS B�SICAS");
	RtfReportStyles.writeHeading3(document, "1.1.1 Ubicaci�n");
	data.clear();
	data.add(new ReportData("Departamento: ", rs.getString("departamento"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Municipio: ", rs.getString("municipio"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Cant�n: ", rs.getString("canton"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles
		.writeHeading3(document, "1.1.2 Caracter�sticas b�sicas");
	data.clear();
	data.add(new ReportData("Tipo de n�cleo: ", rs.getString("tip_nucleo"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("A�o de establecimiento de comunidad: ", rs
		.getString("anho_establecimiento"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Tipo de poblaci�n seg�n origen: ", rs
		.getString("tip_origen"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
    }

    private void writeSection1_2(ResultSet rs, ArrayList<ReportData> data) {
	try {
	    RtfReportStyles.writeHeading2(document, "1.2 DATOS POBLACIONALES");
	    data.clear();
	    data.add(new ReportData("N�mero de familias: ", rs
		    .getString("n_familias"), RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("N�mero de viviendas: ", rs
		    .getString("n_viviendas"), RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("N�mero de habitantes: ", rs
		    .getString("n_habitantes"), RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Ni�as (<5 a�os): ", rs
		    .getString("n_ninhas"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Ni�os (<5 a�os): ", rs
		    .getString("n_ninhos"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Ni�as (entre 5 y 18 a�os): ", rs
		    .getString("n_muj_jovenes"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Ni�os (entre 5 y 18 a�os): ", rs
		    .getString("n_hom_jovenes"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Mujeres (entre 18 y 60 a�os): ", rs
		    .getString("n_mujeres"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Hombres (entre 18 y 60 a�os): ", rs
		    .getString("n_hombres"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Ancianas (> 60 a�os): ", rs
		    .getString("n_ancianas"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Ancianos (> 60 a�os): ", rs
		    .getString("n_ancianos"), RtfReportStyles.normalStyle));
	    ReportUtils.writeDataList(document, data);

	    Paragraph attribute = new Paragraph(
		    "Resumen de los datos poblacionales: ",
		    RtfReportStyles.normalBoldStyle);
	    Paragraph value = new Paragraph(rs.getString("resum_censo"),
		    RtfReportStyles.normalStyle);

	    document.add(Chunk.NEWLINE);
	    document.add(attribute);
	    document.add(value);
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    private void writeSection1_3(ResultSet rs, ArrayList<ReportData> data) {
	try {
	    RtfReportStyles.writeHeading2(document,
		    "1.3 ASPECTOS ORGANIZATIVOS");
	    RtfReportStyles
		    .writeHeading3(document, "1.3.1 Datos del patronato");
	    data.clear();
	    data.add(new ReportData("Existencia de ADESCOS: ", rs
		    .getString("h_adescos"), RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("N�mero de ADESCOS: ", rs
		    .getString("n_adescos"), RtfReportStyles.normalBoldStyle));
	    ReportUtils.writeDataList(document, data);

	    ReportUtils.writeTable(document, AdescosForm.colAlias,
		    AdescosForm.colNames, AdescosForm.NAME, pkValue);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
