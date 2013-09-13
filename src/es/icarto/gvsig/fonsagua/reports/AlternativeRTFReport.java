package es.icarto.gvsig.fonsagua.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;

import es.icarto.gvsig.fonsagua.reports.utils.ReportDAO;
import es.icarto.gvsig.fonsagua.reports.utils.ReportListItem;
import es.icarto.gvsig.fonsagua.reports.utils.ReportUtils;
import es.icarto.gvsig.fonsagua.reports.utils.RtfReportStyles;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltBombeosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltConexionesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltDepositosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltEmbalsesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltFuentesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltTuberiasForm;
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
	return "(COMUNIDAD DE (A CUBRIR POR T�CNICO))";
    }

    @Override
    protected String getHeaderText() {
	return "Informe de Propuestas Comunitario \n"
		+ "Fecha de levantamiento: (A CUBRIR POR T�CNICO)";
    }

    @Override
    protected String getFooterText() {
	return "\n"
		+ "Plan de Gesti�n Integral del Recurso H�drico en el municipio "
		+ ReportDAO.getAlternativeValueByColumnName("municipio",
			pkValue)
		+ "\n"
		+ "Informe de propuestas de la comunidad (A CUBRIR POR T�CNICO)";
    }

    @Override
    protected void writeSpecificContent() {
	try {
	    ResultSet rs = ReportDAO.getAlternativeValues(pkValue);
	    rs.next();

	    ArrayList<ReportListItem> data = new ArrayList<ReportListItem>();

	    RtfReportStyles.writeHeading1(document,
		    "1. ALTERNATIVA PROPUESTA PARA EL ABASTECIMIENTO Y SANEAMIENTO DE AGUA: "
			    + pkValue);

	    Paragraph attribute = new Paragraph(
		    "Prioridad asignada a la alternativa: ",
		    RtfReportStyles.normalBoldStyle);
	    Paragraph value = new Paragraph("(A cubrir por t�cnico)",
		    RtfReportStyles.normalStyle);

	    document.add(Chunk.NEWLINE);
	    document.add(attribute);
	    document.add(value);

	    writeSection1_1(rs, data);
	    writeSection1_2(rs, data);
	    writeSection1_3(rs, data);
	    writeSection1_4(data);

	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}

    }

    private void writeSection1_1(ResultSet rs, ArrayList<ReportListItem> data)
	    throws SQLException, DocumentException {
	RtfReportStyles
		.writeHeading2(document, "1.1 COMUNIDADES BENEFICIARIAS");

	data.clear();
	data.add(new ReportListItem("Departamento: ", rs.getString("departamento"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Municipio: ", rs.getString("municipio"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Cant�n: ", rs.getString("canton"),
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
	data.add(new ReportListItem("N�mero total de poblaci�n implicada: ", rs
		.getString("pobl_actual"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("N�mero de centros educativos considerados: ",
		rs.getString("n_cent_educativos"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("N�mero de centros de salud considerados: ", rs
		.getString("n_cent_salud"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("N�mero de otros centros considerados: ", rs
		.getString("n_cent_otros"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
    }

    private void writeSection1_2(ResultSet rs, ArrayList<ReportListItem> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document,
		"1.2 PROPUESTA DE ACTUACI�N PARA EL ABASTECIMIENTO");

	data.clear();
	data.add(new ReportListItem("Tipo de actuaci�n: ", rs
		.getString("tipo_alternativa"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Tipo de distribuci�n: ", rs
		.getString("tipo_distribucion"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Cant�n: ", rs.getString("tipo_sistema"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph attribute = new Paragraph(
		"Descripci�n de la propuesta de actuaci�n: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph(rs.getString("coment_alternativa"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);

	RtfReportStyles.writeHeading3(document, "1.2.1 C�lculos t�cnicos");
	data.clear();
	data.add(new ReportListItem("Dotaci�n prevista (l/p*d�a): ", ReportDAO
		.getDotacion(pkValue), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Demanda de poblaci�n (l/s): ", rs
		.getString("dem_poblacion"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Demanda de centros (l/s): ", rs
		.getString("dem_centros"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Demanda de actividades ecn�micas (l/s): ", rs
		.getString("dem_econ"), RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Demanda total (l/s): ", rs
		.getString("demanda"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading4(document, "1.2.1.1 Embalses");
	ReportUtils.writeTable(document, AltEmbalsesForm.colAlias, ReportDAO
		.getDataForAlternativeRelatedTable(AltEmbalsesForm.NAME,
			AltEmbalsesForm.colNames, pkValue));

	RtfReportStyles.writeHeading4(document, "1.2.1.2 Fuentes");
	ReportUtils.writeTable(document, AltFuentesForm.colAlias, ReportDAO
		.getDataForAlternativeRelatedTable(AltFuentesForm.NAME,
			AltFuentesForm.colNames, pkValue));

	RtfReportStyles.writeHeading4(document, "1.2.1.3 Dep�sitos");
	ReportUtils.writeTable(document, AltDepositosForm.colAlias, ReportDAO
		.getDataForAlternativeRelatedTable(AltDepositosForm.NAME,
			AltDepositosForm.colNames, pkValue));

	Paragraph attribute2 = new Paragraph(
		"Posible ubicaci�n para los dep�sitos: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value2 = new Paragraph("(A CUBRIR POR T�CNICO)",
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute2);
	document.add(value2);

	RtfReportStyles.writeHeading4(document, "1.2.1.4 Tuber�as");
	ReportUtils.writeTable(document, AltTuberiasForm.colAlias, ReportDAO
		.getDataForAlternativeRelatedTable(AltTuberiasForm.NAME,
			AltTuberiasForm.colNames, pkValue));

	RtfReportStyles.writeHeading4(document, "1.2.1.5 Bombeos");
	ReportUtils.writeTable(document, AltBombeosForm.colAlias, ReportDAO
		.getDataForAlternativeRelatedTable(AltBombeosForm.NAME,
			AltBombeosForm.colNames, pkValue));

	RtfReportStyles.writeHeading4(document, "1.2.1.6 Conexiones");
	ReportUtils.writeTable(document, AltConexionesForm.colAlias, ReportDAO
		.getDataForAlternativeRelatedTable(AltConexionesForm.NAME,
			AltConexionesForm.colNames, pkValue));

	RtfReportStyles.writeHeading3(document,
		"1.2.2 Protecci�n de la microcuenca y de las fuentes");
	Paragraph value3 = new Paragraph("(A CUBRIR POR T�CNICO)",
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(value3);
    }

    private void writeSection1_3(ResultSet rs, ArrayList<ReportListItem> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading3(document,
		"1.3 PROPUESTA ACTUACI�N DE SANEAMIENTO DE AGUAS RESIDUALES");
	data.clear();
	data.add(new ReportListItem("Tipo de saneamiento propuesto: ", rs
		.getString("tipo_saneamiento"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document,
		"1.3.1 Sistema de alcantarillado");
	data.clear();
	data.add(new ReportListItem("N� de trampas de agua: ", rs
		.getString("trat_trampa"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("N� biofiltros: ", rs
		.getString("trat_biofiltros"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("N� de otros tratamientos: ", rs
		.getString("trat_otros"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Otros (Tipo): ", rs.getString("coment_otros"),
		RtfReportStyles.normalStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document, "1.3.2 Letrinas");
	data.clear();
	data.add(new ReportListItem("N� de letrinas de hoyo: ", rs
		.getString("let_hoyo"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("N� de fosas s�pticas: ", rs
		.getString("let_septica"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("N� letrinas de cierre hidr�ulico: ", rs
		.getString("let_hidraulica"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("N� de letrinas aboneras: ", rs
		.getString("let_abonera"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph attribute = new Paragraph("Comentarios sobre saneamiento: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph(rs.getString("coment_saneamiento"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);
    }

    private void writeSection1_4(ArrayList<ReportListItem> data)
	    throws SQLException, DocumentException {
	ResultSet rs = ReportDAO.getPresupuestoValues(pkValue);
	rs.next();

	RtfReportStyles.writeHeading2(document,
		"1.4 PRESUPUESTOS ESTIMADOS Y CUOTAS");

	RtfReportStyles.writeHeading3(document, "1.4.1 Abastecimiento");
	data.clear();
	data.add(new ReportListItem(
		"Dise�o de sistemas y previos a ejecuci�n ($): ", rs
			.getString("total_disenho"),
		RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Elementos captaci�n-distribuci�n ($): ", rs
		.getString("total_conduccion"), RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Distribuci�n ($): ", rs
		.getString("total_distribucion"), RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Pilas ($): ", rs.getString("total_pilas"),
		RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Protecci�n y conservaci�n ($): ", rs
		.getString("total_proteccion"), RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Personal ($): ", rs
		.getString("total_personal"), RtfReportStyles.normalStyle));
	data.add(new ReportListItem(
		"Implementaci�n del sistema y acompa�amiento comunitario ($): ",
		rs.getString("total_implementacion"),
		RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Coste total del abastecimiento ($): ", rs
		.getString("total_abastecimiento"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document, "1.4.2 Saneamiento");
	data.clear();
	data.add(new ReportListItem("Coste total del saneamiento ($): ", rs
		.getString("total_saneamiento"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document, "1.4.3 Costes totales");
	data.clear();
	data.add(new ReportListItem("COSTE TOTAL ALTERNATIVA ($): ", rs
		.getString("total"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("COSTE POR PERSONA ($): ", rs
		.getString("total_persona"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document, "1.4.4 Cuota");
	Paragraph attribute = new Paragraph(
		"C�lculo estimado de la cuota a pagar por la poblaci�n actual ($/persona/mes): ",
		RtfReportStyles.normalBoldStyle);

	document.add(attribute);

	data.clear();
	data.add(new ReportListItem("Bomba: ", null,
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Amortizaci�n ($/mes): ", rs
		.getString("amortizacion_bomba"), RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Consumo ($/mes): ", rs
		.getString("consumo_bomba"), RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Mantenimiento preventivo ($/mes): ", rs
		.getString("mantenimiento_bomba"), RtfReportStyles.normalStyle));
	data.add(new ReportListItem("Amortizaci�n de tuber�as ($/mes): ", rs
		.getString("amortizacion_tuberias"),
		RtfReportStyles.normalStyle));
	data.add(new ReportListItem("An�lisis de agua ($/mes): ", rs
		.getString("analisis_agua"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Cloro ($/mes): ", rs.getString("cloro"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Fungible ($/mes): ", rs.getString("fungible"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Reparaciones y herramientas ($/mes): ", rs
		.getString("reparaciones"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Fontanero ($/mes): ", rs
		.getString("fontanero"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("Asistencia t�cnica ($/mes): ", rs
		.getString("asistencia"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
	document.add(Chunk.NEWLINE);
	data.clear();
	data.add(new ReportListItem("CUOTA ($/mes): ", rs.getString("cuota"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportListItem("CUOTA POR PERSONA ($/P*mes): ", rs
		.getString("cuota_persona"), RtfReportStyles.normalStyle));
	ReportUtils.writeDataList(document, data);

    }

}
