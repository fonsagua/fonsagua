package es.icarto.gvsig.fonsagua.reports;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;
import com.lowagie.text.rtf.table.RtfCell;

import es.icarto.gvsig.fonsagua.reports.utils.ReportDAO;
import es.icarto.gvsig.fonsagua.reports.utils.RtfReportStyles;

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
		+ ReportDAO.getCommunityName(pkValue).toUpperCase() + ")";
    }

    @Override
    protected String getHeaderText() {
	return "Informe de Diagnóstico Comunitario \n"
		+ "Fecha de levantamiento:";
    }

    @Override
    protected void writeHeader() {
	try {
	    Table tableHeader = new Table(3);
	    tableHeader.setBorder(Rectangle.NO_BORDER);

	    RtfCell leftImageCell = new RtfCell(getLeftHeaderImage());
	    RtfCell centerCell = new RtfCell(new Paragraph(getHeaderText(),
		    RtfReportStyles.headerFooterTextStyle));
	    centerCell.setHorizontalAlignment(Chunk.ALIGN_CENTER);
	    RtfCell rightImageCell = new RtfCell(getRightHeaderImage());

	    tableHeader.addCell(leftImageCell);
	    tableHeader.addCell(centerCell);
	    tableHeader.addCell(rightImageCell);

	    RtfHeaderFooter header = new RtfHeaderFooter(tableHeader);

	    document.setHeader(header);

	} catch (BadElementException e) {
	    e.printStackTrace();
	}
    }

    @Override
    protected void writeFooter() {
	Paragraph footerText = new Paragraph(
		"Plan de Gestión Integral del Recurso Hídrico en el municipio "
			+ ReportDAO.getCommunityMunicipality(pkValue) + "\n"
			+ "Informe de diagnóstico de la comunidad "
			+ ReportDAO.getCommunityName(pkValue),
		RtfReportStyles.headerFooterTextStyle);

	RtfHeaderFooter footer = new RtfHeaderFooter(footerText);

	document.setFooter(footer);
    }

    @Override
    protected void writeSpecificContent() {
	// TODO Auto-generated method stub

    }

}
