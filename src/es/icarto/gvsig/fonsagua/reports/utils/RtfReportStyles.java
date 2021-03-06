package es.icarto.gvsig.fonsagua.reports.utils;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.style.RtfFont;

public class RtfReportStyles {

    public static final RtfFont headerFooterTextStyle = new RtfFont("Arial", 10);
    public static final RtfFont reportTitleStyle = new RtfFont("Arial", 28,
	    Font.BOLD);
    public static final RtfFont reportSubtitleStyle = new RtfFont("Arial", 18,
	    Font.BOLD);
    public static final RtfFont heading1Style = new RtfFont("Arial", 12,
	    Font.BOLD);
    public static final RtfFont heading2Style = new RtfFont("Arial", 12,
	    Font.BOLD);
    public static final RtfFont heading3Style = new RtfFont("Arial", 12,
	    Font.BOLD);
    public static final RtfFont heading4Style = new RtfFont("Arial", 11,
	    Font.BOLD);
    public static final RtfFont normalBoldStyle = new RtfFont("Arial", 11,
	    Font.BOLD);
    public static final RtfFont normalStyle = new RtfFont("Arial", 11);

    public static final RtfFont tableBoldStyle = new RtfFont("Arial", 10,
	    Font.BOLD);

    public static final RtfFont tableStyle = new RtfFont("Arial", 10);

    public static void writeTitle(Document document, String text) {
	try {
	    for (int i = 0; i < 17; i++) {
		document.add(Chunk.NEWLINE);
	    }
	    Paragraph title = new Paragraph(text, reportTitleStyle);
	    title.setAlignment(Chunk.ALIGN_CENTER);
	    document.add(title);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static void writeSubtitle(Document document, String text) {
	try {
	    document.add(Chunk.NEWLINE);
	    Paragraph subTitle = new Paragraph(text, reportSubtitleStyle);
	    subTitle.setAlignment(Chunk.ALIGN_CENTER);
	    document.add(subTitle);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static void writeHeading1(Document document, String text) {
	try {
	    document.add(Chunk.NEXTPAGE);
	    heading1Style.setColor(54, 95, 145);
	    Paragraph heading1 = new Paragraph(text, heading1Style);
	    document.add(heading1);
	    document.add(Chunk.NEWLINE);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static void writeHeading2(Document document, String text) {
	try {
	    document.add(Chunk.NEWLINE);
	    Paragraph heading2 = new Paragraph(text, heading2Style);
	    document.add(heading2);
	    document.add(Chunk.NEWLINE);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static void writeHeading3(Document document, String text) {
	try {
	    document.add(Chunk.NEWLINE);
	    heading3Style.setColor(54, 95, 145);
	    Paragraph heading3 = new Paragraph(text, heading3Style);
	    document.add(heading3);
	    document.add(Chunk.NEWLINE);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static void writeHeading4(Document document, String text) {
	try {
	    document.add(Chunk.NEWLINE);
	    Paragraph heading2 = new Paragraph(text, heading4Style);
	    document.add(heading2);
	    document.add(Chunk.NEWLINE);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }
}
