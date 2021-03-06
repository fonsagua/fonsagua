package es.icarto.gvsig.fonsagua.reports;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.headerfooter.RtfHeaderFooter;
import com.lowagie.text.rtf.table.RtfCell;

import es.icarto.gvsig.fonsagua.reports.utils.RtfReportStyles;

public abstract class RTFReport {

    protected Document document;
    protected String pkValue;

    public RTFReport(String fileName, String pkValue) {
	this.pkValue = pkValue;
	try {
	    document = new Document(PageSize.A4);
	    document.setMargins(85.05f, 85.05f, 42.525f, 42.525f); // 28.35f=1cm
	    RtfWriter2.getInstance(document, new FileOutputStream(fileName));
	    writeHeader();
	    writeFooter();
	    document.open();
	    writeReport();
	    document.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    private void writeReport() {
	RtfReportStyles.writeTitle(document, getTitle());
	RtfReportStyles.writeSubtitle(document, getSubtitle());
	writeSpecificContent();
    }

    protected abstract String getTitle();

    protected abstract String getSubtitle();

    protected abstract String getHeaderText();

    protected abstract String getFooterText();

    protected abstract void writeSpecificContent();

    protected void writeHeader() {
	try {
	    Table tableHeader = new Table(4);
	    tableHeader.setWidth(100f);
	    float[] cellWidts = { 125f, 50f, 165f, 85f };// TotalSize=425f
	    tableHeader.setWidths(cellWidts);
	    tableHeader.setBorder(Rectangle.NO_BORDER);

	    RtfCell leftImageCell1 = new RtfCell(getLeftHeaderImage1());
	    leftImageCell1.setHorizontalAlignment(Chunk.ALIGN_LEFT);
	    RtfCell leftImageCell2 = new RtfCell(getLeftHeaderImage2());
	    leftImageCell2.setHorizontalAlignment(Chunk.ALIGN_LEFT);
	    RtfCell centerCell = new RtfCell(new Paragraph(getHeaderText(),
		    RtfReportStyles.headerFooterTextStyle));
	    centerCell.setHorizontalAlignment(Chunk.ALIGN_CENTER);
	    RtfCell rightImageCell = new RtfCell(getRightHeaderImage());
	    rightImageCell.setHorizontalAlignment(Chunk.ALIGN_RIGHT);

	    tableHeader.addCell(leftImageCell1);
	    tableHeader.addCell(leftImageCell2);
	    tableHeader.addCell(centerCell);
	    tableHeader.addCell(rightImageCell);
	    tableHeader.addCell(new RtfCell());
	    tableHeader.setBorder(Rectangle.NO_BORDER);

	    RtfHeaderFooter header = new RtfHeaderFooter(tableHeader);

	    document.setHeader(header);

	} catch (BadElementException e) {
	    e.printStackTrace();
	}
    }

    protected void writeFooter() {
	Paragraph footerText = new Paragraph(getFooterText(),
		RtfReportStyles.headerFooterTextStyle);
	RtfHeaderFooter footer = new RtfHeaderFooter(footerText);
	document.setFooter(footer);
    }

    protected Image getRightHeaderImage() {
	Image image = null;
	try {
	    image = Image.getInstance(getClass().getClassLoader()
		    .getResource("images/logo_esf_galicia.jpg").getPath());
	    image.scalePercent((float) 30.00);
	    image.setAlignment(Chunk.ALIGN_RIGHT);
	} catch (BadElementException e) {
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return image;
    }

    protected Image getLeftHeaderImage1() {
	Image image = null;
	try {
	    image = Image.getInstance(getClass().getClassLoader()
		    .getResource("images/logo_nasmar.jpg").getPath());
	    image.scalePercent((float) 10.00);
	    image.setAlignment(Chunk.ALIGN_LEFT);
	} catch (BadElementException e) {
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return image;
    }

    protected Image getLeftHeaderImage2() {
	Image image = null;
	try {
	    image = Image.getInstance(getClass().getClassLoader()
		    .getResource("images/logo_ahjasa.jpg").getPath());
	    image.scalePercent((float) 50.00);
	    image.setAlignment(Chunk.ALIGN_LEFT);
	} catch (BadElementException e) {
	    e.printStackTrace();
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return image;
    }

}
