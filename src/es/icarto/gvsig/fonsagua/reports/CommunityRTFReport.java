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
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.BombeosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.CaptacionesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.DepDistribucionForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.DepIntermediosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.JuntasAguaForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.TuberiasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AdescosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CentrosEducativosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CentrosSaludForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.OngsForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.OtrasOrganizacionesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.OtrosServiciosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ValoracionSistemaForm;

public class CommunityRTFReport extends RTFReport {

    public CommunityRTFReport(String fileName, String communityName) {
	super(fileName, communityName);
    }

    @Override
    protected String getTitle() {
	return "INFORME DE DIAGNÓSTICO COMUNITARIO";
    }

    @Override
    protected String getSubtitle() {
	return "(COMUNIDAD DE "
		+ ReportDAO.getCommunityValueByColumnName("comunidad", pkValue)
			.toUpperCase() + ")";
    }

    @Override
    protected String getHeaderText() {
	return "Informe de Diagnóstico Comunitario \n"
		+ "Fecha de levantamiento:";
    }

    @Override
    protected void writeFooter() {
	Paragraph footerText = new Paragraph(
		"Plan de Gestión Integral del Recurso Hídrico en el municipio "
			+ ReportDAO.getCommunityValueByColumnName("municipio",
				pkValue)
			+ "\n"
			+ "Informe de diagnóstico de la comunidad "
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
	    writeSection1_4(rs, data);
	    writeSection1_5(rs, data);
	    writeSection1_6(rs, data);
	    writeSection1_7(rs, data);

	    RtfReportStyles.writeHeading1(document,
		    "2. INFRAESTRUCTURAS DE AGUA");

	    writeSection2_1(rs, data);
	    writeSection2_2(rs, data);
	    writeSection2_3(rs, data);
	    writeSection2_4(rs, data);
	} catch (SQLException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}

    }

    private void writeSection1_1(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException {
	data.add(new ReportData("Nombre: ", rs.getString("comunidad"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Código de la comunidad: ", rs
		.getString("cod_comunidad"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading2(document,
		"1.1 UBICACIÓN Y CARACTERÍSTICAS BÁSICAS");

	RtfReportStyles.writeHeading3(document, "1.1.1 Ubicación");
	data.clear();
	data.add(new ReportData("Departamento: ", rs.getString("departamento"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Municipio: ", rs.getString("municipio"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Cantón: ", rs.getString("canton"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles
		.writeHeading3(document, "1.1.2 Características básicas");
	data.clear();
	data.add(new ReportData("Tipo de núcleo: ", rs.getString("tip_nucleo"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Año de establecimiento de comunidad: ", rs
		.getString("anho_establecimiento"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Tipo de población según origen: ", rs
		.getString("tip_origen"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
    }

    private void writeSection1_2(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document, "1.2 DATOS POBLACIONALES");
	data.clear();
	data.add(new ReportData("Número de familias: ", rs
		.getString("n_familias"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de viviendas: ", rs
		.getString("n_viviendas"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de habitantes: ", rs
		.getString("n_habitantes"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Niñas (<5 años): ", rs.getString("n_ninhas"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Niños (<5 años): ", rs.getString("n_ninhos"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Niñas (entre 5 y 18 años): ", rs
		.getString("n_muj_jovenes"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Niños (entre 5 y 18 años): ", rs
		.getString("n_hom_jovenes"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Mujeres (entre 18 y 60 años): ", rs
		.getString("n_mujeres"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Hombres (entre 18 y 60 años): ", rs
		.getString("n_hombres"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Ancianas (> 60 años): ", rs
		.getString("n_ancianas"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Ancianos (> 60 años): ", rs
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
    }

    private void writeSection1_3(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document, "1.3 ASPECTOS ORGANIZATIVOS");

	RtfReportStyles.writeHeading3(document, "1.3.1 Datos del patronato");
	data.clear();
	data.add(new ReportData("Existencia de ADESCOS: ", rs
		.getString("h_adescos"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de ADESCOS: ", rs
		.getString("n_adescos"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	ReportUtils.writeTable(document, AdescosForm.colAlias, ReportDAO
		.getDataForCommunityRelatedTable(AdescosForm.NAME,
			AdescosForm.colNames, pkValue));

	RtfReportStyles.writeHeading3(document, "1.3.2 ONGs");
	ReportUtils.writeTable(document, OngsForm.colAlias, ReportDAO
		.getDataForCommunityRelatedTable(OngsForm.NAME,
			OngsForm.colNames, pkValue));

	RtfReportStyles.writeHeading3(document, "1.3.3 Otras organizaciones");
	ReportUtils.writeTable(document, OtrasOrganizacionesForm.colAlias,
		ReportDAO.getDataForCommunityRelatedTable(
			OtrasOrganizacionesForm.NAME,
			OtrasOrganizacionesForm.colNames, pkValue));

	Paragraph attribute = new Paragraph(
		"Observaciones sobre aspectos organizativos: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph(rs.getString("coment_org"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);
    }

    private void writeSection1_4(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document, "1.4 ACTIVIDADES ECONÓMICAS");

	RtfReportStyles
		.writeHeading3(document,
			"1.4.1 Principales fuente de ingresos de las familias en porcentaje");
	data.clear();
	data.add(new ReportData("Sector primario (%): ", rs
		.getString("f_primario"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData(
		"Número de centros de actividad agropecuaria: ", rs
			.getString("n_agropecuario"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Sector secundario (%): ", rs
		.getString("f_secundario"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de industrias: ", rs
		.getString("n_industria"), RtfReportStyles.normalStyle));
	data.add(new ReportData(
		"Número de centros con actividad en la construcción: ", rs
			.getString("n_construccion"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Número de centros de maquila: ", rs
		.getString("n_maquila"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Sector terciario o servicios (%): ", rs
		.getString("f_terciario"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de comercios: ", rs
		.getString("n_comercio"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Número de otros centros: ", rs
		.getString("n_otros"), RtfReportStyles.normalStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document, "1.4.2 Sector primario");
	data.clear();
	data.add(new ReportData("Familias que cultivan por cuenta propia: ", rs
		.getString("f_c_propia"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Familias que cultivan por cuenta ajena: ", rs
		.getString("f_c_ajena"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles
		.writeHeading4(document,
			"1.4.2.1 Agricultura. Familias con titularidad del lote de cultivo");
	data.clear();
	data.add(new ReportData(
		"Familias propietarias de la tierra de cultivo: ", rs
			.getString("f_propietarias"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Mujeres: ", rs.getString("prop_mujeres"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Hombres: ", rs.getString("prop_hombres"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData(
		"Área Media cultivada por familia (manzanas): ", rs
			.getString("prop_area_cultivada"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading4(document,
		"1.4.2.2 Agricultura. Familias arrendatarias");
	data.clear();
	data.add(new ReportData("Familias que arriendan la tierra: ", rs
		.getString("f_arrendatarias"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Mujeres: ", rs.getString("arre_mujeres"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Hombres: ", rs.getString("arre_hombres"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData(
		"Área Media cultivada por familia (manzanas): ", rs
			.getString("arre_area_cultivada"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading4(document,
		"1.4.2.3 Agricultura. Familias que cultivan a medias");
	data.clear();
	data.add(new ReportData("Familias que arriendan la tierra a medias: ",
		rs.getString("f_medias"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Mujeres: ", rs.getString("med_mujeres"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Hombres: ", rs.getString("med_hombres"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData(
		"Área Media cultivada por familia (manzanas): ", rs
			.getString("med_area_cultivada"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document, "1.4.3 Sector secundario");
	data.clear();
	data.add(new ReportData("Familias que trabajan en la industria: ", rs
		.getString("f_industria"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Familias que trabajan en la construcción: ",
		rs.getString("f_construccion"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Familias que trabajan en maquila: ", rs
		.getString("f_maquila"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Familias que trabajan en otras actividades: ",
		rs.getString("f_otros_sec"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document, "1.4.4 Sector terciario");
	data.clear();
	data.add(new ReportData("Familias que trabajan en comercios: ", rs
		.getString("f_comercio"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Familias que trabajan en otras actividades: ",
		rs.getString("f_otros_ter"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph attribute = new Paragraph(
		"Resumen de las actividades económicas: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph(rs.getString("resum_econ"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);
    }

    private void writeSection1_5(ResultSet rs, ArrayList<ReportData> data) {
	try {
	    RtfReportStyles.writeHeading2(document, "1.5 MEDIO FÍSICO");

	    RtfReportStyles.writeHeading3(document, "1.5.1 Relieve (%)");
	    data.clear();
	    data.add(new ReportData("Llano: ", rs.getString("llano"),
		    RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Pendiente media: ", rs
		    .getString("pend_media"), RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Pendiente elevada: ", rs
		    .getString("pend_elevada"), RtfReportStyles.normalBoldStyle));
	    ReportUtils.writeDataList(document, data);

	    RtfReportStyles.writeHeading3(document, "1.5.2 Tipo de vegetación");

	    RtfReportStyles.writeHeading4(document, "1.5.2.1 Usos de suelo");
	    data.clear();
	    data.add(new ReportData("Residencial: ", rs
		    .getString("veg_residencial"),
		    RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Superficie (%): ", rs
		    .getString("veg_res_sup"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Industrial: ", rs
		    .getString("veg_industrial"),
		    RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Superficie (%): ", rs
		    .getString("veg_ind_sup"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Agrícola: ", rs.getString("veg_agricola"),
		    RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Tipo de vegetación: ", rs
		    .getString("veg_agr_tip"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Superficie (%): ", rs
		    .getString("veg_agr_sup"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Forestal: ", rs.getString("veg_forestal"),
		    RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Tipo de vegetación: ", rs
		    .getString("veg_for_tip"), RtfReportStyles.normalStyle));
	    data.add(new ReportData("Superficie (%): ", rs
		    .getString("veg_for_sup"), RtfReportStyles.normalStyle));
	    ReportUtils.writeDataList(document, data);

	    RtfReportStyles.writeHeading4(document, "1.5.2.2 Características");
	    data.clear();
	    data.add(new ReportData("Estado de deforestación: ", rs
		    .getString("deforestacion"),
		    RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData(
		    "Tendencia de avance de la frontera agrícola: ", rs
			    .getString("avance_fagricola"),
		    RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Riesgo de erosión", rs
		    .getString("riesgo_erosion"),
		    RtfReportStyles.normalBoldStyle));
	    data.add(new ReportData("Frecuencia de incendios forestales: ", rs
		    .getString("frec_incendios"),
		    RtfReportStyles.normalBoldStyle));
	    ReportUtils.writeDataList(document, data);

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private void writeSection1_6(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document, "1.6 SERVICIOS BÁSICOS");

	RtfReportStyles.writeHeading3(document, "1.6.1 Tipo de vivienda");
	data.clear();
	data.add(new ReportData(
		"Número de familias propietarias de la vivienda: ", rs
			.getString("f_viv_prop"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Mujeres: ", rs.getString("viv_prop_mujeres"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Hombres: ", rs.getString("viv_prop_hombres"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData(
		"Número de familias No propietarias de la vivienda: ", rs
			.getString("f_viv_noprop"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles
		.writeHeading4(document, "1.6.1.1 Vivienda por material");
	data.clear();
	data.add(new ReportData("Bahareque: ", rs.getString("viv_bahareque"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Adobe: ", rs.getString("viv_adobe"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Mixto: ", rs.getString("viv_mixto"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Otros tipos: ", rs.getString("viv_otros"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading4(document,
		"1.6.1.2 Vivienda por tipo de cocina");
	data.clear();
	data.add(new ReportData("De leña: ", rs.getString("coc_lenha"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("De leña mejorada: ", rs
		.getString("coc_lenha_mej"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document,
		"1.6.2 Electricidad y comunicaciones");
	data.clear();
	data.add(new ReportData("Existencia de electricidad: ", rs
		.getString("electricidad"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de viviendas con electricidad: ", rs
		.getString("viv_electricidad"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Tarifa ($): ", rs
		.getString("tarifa_electricidad"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Alumbrado Público: ", rs
		.getString("alumbrado"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Línea Telefónica fija: ", rs
		.getString("telf_fijo"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Cobertura de teléfono móvil: ", rs
		.getString("telf_movil"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	RtfReportStyles.writeHeading3(document, "1.6.3 Educación");
	data.clear();
	data.add(new ReportData("Existencia de centros educativos: ", rs
		.getString("cent_educativos"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Número de centros: ", rs
		.getString("n_cent_educativos"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
	ReportUtils.writeTable(document, CentrosEducativosForm.colAlias,
		ReportDAO.getDataForCommunityRelatedTable(
			CentrosEducativosForm.NAME,
			CentrosEducativosForm.colNames, pkValue));

	RtfReportStyles.writeHeading3(document, "1.6.4 Asistencia Sanitaria");
	data.clear();
	data.add(new ReportData(
		"Existe programa educativo de salud e higiene: ", rs
			.getString("h_prog_salud"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Tipo de asistencia sanitaria: ", null,
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Unidad de salud: ", rs
		.getString("as_unidad_salud"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Clínica comunal: ", rs
		.getString("as_clinica_comunal"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Promotor de salud: ", rs
		.getString("as_promotor"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Otros: ", rs.getString("as_otros"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Número de centros de salud: ", rs
		.getString("n_cent_salud"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Tiempo medio a Unidad de Salud (min): ", rs
		.getString("t_unidad_salud"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Tiempo medio a clínica comunal (min): ", rs
		.getString("t_clinica_comunal"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData(
		"Frecuencia de visita de médico a clínica (meses): ", rs
			.getString("frec_med_clinica"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData(
		"Frecuencia de visitas del promotor de salud (meses): ", rs
			.getString("frec_promotor"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Tipo de enfermedades más comunes en niños: ",
		rs.getString("enf_tip_ninhos"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData(
		"Tipo de enfermedades más comunes en adultos: ", rs
			.getString("enf_tip_adultos"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
	ReportUtils.writeTable(document, CentrosSaludForm.colAlias, ReportDAO
		.getDataForCommunityRelatedTable(CentrosSaludForm.NAME,
			CentrosSaludForm.colNames, pkValue));

	RtfReportStyles.writeHeading3(document,
		"1.6.5 Otros equipamientos y servicios");
	data.clear();
	data.add(new ReportData("Número de iglesias: ", rs
		.getString("n_iglesias"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
	ReportUtils.writeTable(document, OtrosServiciosForm.colAlias, ReportDAO
		.getDataForCommunityRelatedTable(OtrosServiciosForm.NAME,
			OtrosServiciosForm.colNames, pkValue));

	RtfReportStyles.writeHeading3(document,
		"1.6.6 Infraestructura viaria y transporte");
	data.clear();
	data.add(new ReportData("Tipo de acceso: ", rs.getString("tip_acceso"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Infraestructura de acceso: ", rs
		.getString("tip_sup_acceso"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Estado de la carretera en Verano: ", rs
		.getString("acceso_ver"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Estado de la carretera en Invierno: ", rs
		.getString("acceso_inv"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Se cuenta con transporte público: ", rs
		.getString("trans_publico"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Frecuencia (horas): ", rs
		.getString("frec_tpublico"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Tiempo a parada (min): ", rs
		.getString("t_parada"), RtfReportStyles.normalStyle));
	data.add(new ReportData(
		"Tiempo en bus hasta la cabecera municipal (min): ", rs
			.getString("t_cabmunicipal"),
		RtfReportStyles.normalStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph attribute = new Paragraph("Comentarios sobre Servicios: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph(rs.getString("coment_ser"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);
    }

    private void writeSection1_7(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document, "1.7 FACTORES DE RIESGO");

	data.clear();
	data.add(new ReportData(
		"Principales factores de riesgo de la comunidad: ", null,
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Deslizamientos: ",
		rs.getString("fr_deslizam"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Desbordamiento de río: ", rs
		.getString("fr_desbord"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Inundaciones: ", rs.getString("fr_inundac"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Puntos de asalto: ", rs
		.getString("fr_asaltos"), RtfReportStyles.normalStyle));
	data.add(new ReportData("Otros: ", rs.getString("fr_otros"),
		RtfReportStyles.normalStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph otros_detalle = new Paragraph(
		rs.getString("fr_otros_detalle"), RtfReportStyles.normalStyle);
	document.add(Chunk.NEWLINE);
	document.add(otros_detalle);

	data.clear();
	data.add(new ReportData("Existe comité de gestión de riesgos: ", rs
		.getString("comite_riesgos"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Está activo el comité: ", rs
		.getString("comite_activo"), RtfReportStyles.normalStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph attribute = new Paragraph(
		"Descripción y año del último fenómeno que causó daños: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph(rs.getString("ult_fenomeno"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);
    }

    private void writeSection2_1(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document,
		"2.1 RESUMEN DEL TIPO DE ABASTECIMIENTO");

	RtfReportStyles.writeHeading3(document,
		"2.1.1 Sistemas de abastecimiento");
	data.clear();
	data.add(new ReportData("Existe sitema de abastecimiento de agua: ", rs
		.getString("sist_abastecimiento"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Origen del agua: ", rs
		.getString("origen_aguas"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Nº de viviendas a las que da servicio: ", rs
		.getString("n_viv_abast"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData(
		"Nº de familias con servicio de agua en época seca: ", rs
			.getString("tot_s_abast"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData(
		"Nº de familias con servicio de agua en época de lluvias: ", rs
			.getString("tot_ll_abast"),
		RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph attribute = new Paragraph(
		"Resumen para el informe técnico: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph(rs.getString("resum_abast"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);

	RtfReportStyles.writeHeading4(document,
		"2.1.1.1 Población sin sistema de abastecimiento");
	data.clear();
	data.add(new ReportData(
		"Nº de familias con servicio de agua en época seca: ", rs
			.getString("tot_s_sin_abast"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData(
		"Nº de familias sin sistema de agua en época de lluvias: ", rs
			.getString("tot_ll_sin_abast"),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Tipo de fuentes: ", null,
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Río: ", rs.getString("f_rio"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Manantial: ", rs.getString("f_manantial"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Pozo: ", rs.getString("f_pozo"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Quebrada: ", rs.getString("f_quebrada"),
		RtfReportStyles.normalStyle));
	data.add(new ReportData("Compra: ", rs.getString("f_compra"),
		RtfReportStyles.normalStyle));
	ReportUtils.writeDataList(document, data);

	Paragraph attribute2 = new Paragraph(
		"Resumen de los hábitos del agua: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value2 = new Paragraph(rs.getString("res_hab_agua"),
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute2);
	document.add(value2);
    }

    private void writeSection2_2(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading2(document,
		"2.2 ANÁLISIS DE LOS SISTEMAS EXISTENTES");

	data.clear();
	data.add(new ReportData(
		"Número de sistemas de abastecimiento de la comunidad: ",
		String.valueOf(ReportDAO
			.getNumberOfElementsFromRelationshipTable(
				"r_abastecimientos_comunidades",
				"cod_comunidad", pkValue)),
		RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Consumo de la comunidad (l/hab día): ", rs
		.getString("tot_consumo"), RtfReportStyles.normalBoldStyle));
	data.add(new ReportData("Nº de viviendas a las que da servicio: ", rs
		.getString("n_viv_abast"), RtfReportStyles.normalBoldStyle));
	ReportUtils.writeDataList(document, data);
	ReportUtils.writeTable(document, AbastecimientosForm.colAlias,
		ReportDAO.getDataFromAbastecimientosTableByCommunity(pkValue));

	RtfReportStyles.writeHeading3(document, "2.2.1 Captaciones");
	String[] captacionesColAlias = ReportUtils
		.addCodAbastecimientoAliasToArray(CaptacionesForm.colAlias,
			"Cod. Abastecimiento");
	String[] captacionesColNames = ReportUtils
		.addCodAbastecimientoAliasToArray(CaptacionesForm.colNames,
			"t.cod_abastecimiento");
	ReportUtils.writeTable(document, captacionesColAlias, ReportDAO
		.getDataOfElementOfAbastecimientoByCommunity(
			CaptacionesForm.NAME, captacionesColNames, pkValue));

	RtfReportStyles.writeHeading3(document, "2.2.2 Depósitos intermedios");
	String[] depIntermediosColAlias = ReportUtils
		.addCodAbastecimientoAliasToArray(DepIntermediosForm.colAlias,
			"Cod. Abastecimiento");
	String[] depIntermediosColNames = ReportUtils
		.addCodAbastecimientoAliasToArray(DepIntermediosForm.colNames,
			"t.cod_abastecimiento");
	ReportUtils.writeTable(document, depIntermediosColAlias, ReportDAO
		.getDataOfElementOfAbastecimientoByCommunity(
			DepIntermediosForm.NAME, depIntermediosColNames,
			pkValue));

	RtfReportStyles.writeHeading3(document,
		"2.2.3 Depósitos de distribución");
	String[] depDistribucionColAlias = ReportUtils
		.addCodAbastecimientoAliasToArray(DepDistribucionForm.colAlias,
			"Cod. Abastecimiento");
	String[] depDistribucionColNames = ReportUtils
		.addCodAbastecimientoAliasToArray(DepDistribucionForm.colNames,
			"t.cod_abastecimiento");
	ReportUtils.writeTable(document, depDistribucionColAlias, ReportDAO
		.getDataOfElementOfAbastecimientoByCommunity(
			DepDistribucionForm.NAME, depDistribucionColNames,
			pkValue));

	RtfReportStyles.writeHeading3(document, "2.2.4 Tuberías");
	String[] tuberiasColAlias = ReportUtils
		.addCodAbastecimientoAliasToArray(TuberiasForm.colAlias,
			"Cod. Abastecimiento");
	String[] tuberiasColNames = ReportUtils
		.addCodAbastecimientoAliasToArray(TuberiasForm.colNames,
			"t.cod_abastecimiento");
	ReportUtils.writeTable(document, tuberiasColAlias, ReportDAO
		.getDataOfElementOfAbastecimientoByCommunity(TuberiasForm.NAME,
			tuberiasColNames, pkValue));

	RtfReportStyles.writeHeading3(document, "2.2.4 Bombas");
	String[] bombeosColAlias = ReportUtils
		.addCodAbastecimientoAliasToArray(BombeosForm.colAlias,
			"Cod. Abastecimiento");
	String[] bombeosColNames = ReportUtils
		.addCodAbastecimientoAliasToArray(BombeosForm.colNames,
			"t.cod_abastecimiento");
	ReportUtils.writeTable(document, bombeosColAlias, ReportDAO
		.getDataOfElementOfAbastecimientoByCommunity(BombeosForm.NAME,
			bombeosColNames, pkValue));
    }

    private void writeSection2_3(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading3(document, "2.3.1 Valoración");
	ValoracionSistemaForm.colNames[0] = "t.cod_comunidad";
	ValoracionSistemaForm.colNames[1] = "t.cod_abastecimiento";

	ReportUtils.writeTable(document, ValoracionSistemaForm.colAlias,
		ReportDAO.getDataOfElementOfAbastecimientoByCommunity(
			ValoracionSistemaForm.NAME,
			ValoracionSistemaForm.colNames, pkValue));

	Paragraph attribute = new Paragraph(
		"Comentarios sobre la calidad del agua: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph("(A CUBRIR POR EL USUARIO)",
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);
    }

    private void writeSection2_4(ResultSet rs, ArrayList<ReportData> data)
	    throws SQLException, DocumentException {
	RtfReportStyles.writeHeading3(document,
		"2.4.1 Datos de la Junta de Agua");
	String[] juntasColAlias = ReportUtils.addCodAbastecimientoAliasToArray(
		JuntasAguaForm.colAlias, "Cod. Abastecimiento");
	JuntasAguaForm.colNames[JuntasAguaForm.colNames.length - 1] = "t.n_mujeres";
	String[] juntasColNames = ReportUtils.addCodAbastecimientoAliasToArray(
		JuntasAguaForm.colNames, "t.cod_abastecimiento");
	ReportUtils.writeTable(document, juntasColAlias, ReportDAO
		.getDataOfElementOfAbastecimientoByCommunity(
			JuntasAguaForm.NAME, juntasColNames, pkValue));

	RtfReportStyles.writeHeading3(document, "2.4.2 Tarifa");
	Paragraph attribute = new Paragraph(
		"Comentarios sobre sistema tarifario: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value = new Paragraph("(A CUBRIR POR EL USUARIO)",
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute);
	document.add(value);

	Paragraph attribute2 = new Paragraph(
		"Comentarios sobre gestión del sistema: ",
		RtfReportStyles.normalBoldStyle);
	Paragraph value2 = new Paragraph("(A CUBRIR POR EL USUARIO)",
		RtfReportStyles.normalStyle);

	document.add(Chunk.NEWLINE);
	document.add(attribute2);
	document.add(value2);
    }
}
