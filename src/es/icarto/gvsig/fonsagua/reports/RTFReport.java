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
import com.lowagie.text.rtf.RtfWriter2;

import es.icarto.gvsig.fonsagua.reports.utils.RtfReportStyles;

public abstract class RTFReport {

    protected Document document;

    public RTFReport(String fileName) {
	try {
	    document = new Document(PageSize.A4);
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

    protected abstract void writeHeader();

    protected abstract void writeFooter();

    protected abstract void writeSpecificContent();

    protected Image getRightHeaderImage() {
	Image image = null;
	try {
	    image = Image.getInstance("images/logo_esf.jpg");
	    image.scalePercent((float) 35.00);
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

    protected Image getLeftHeaderImage() {
	Image image = null;
	try {
	    image = Image.getInstance("images/logo_anda.jpg");
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
