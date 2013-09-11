package es.icarto.gvsig.fonsagua.reports.utils;

import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.table.RtfCell;

public class ReportUtils {

    private final static Locale loc = new Locale("es");

    public static void writeDataList(Document document,
	    ArrayList<ReportData> data) {
	Paragraph listParagrpah = new Paragraph();
	List list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", RtfReportStyles.normalBoldStyle));

	for (ReportData reportData : data) {
	    Phrase phrase = new Phrase();
	    phrase.add(new Chunk(reportData.getAttribute(), reportData
		    .getStyle()));
	    phrase.add(new Chunk(getValueFormatted(reportData.getValue()),
		    RtfReportStyles.normalStyle));
	    list.add(new ListItem(phrase));
	}
	try {
	    listParagrpah.add(list);
	    document.add(listParagrpah);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static void writeTable(Document document, String[] headerFieldNames,
	    String[][] data) {
	try {
	    Table table = new Table(headerFieldNames.length, data.length);

	    for (String headerFieldName : headerFieldNames) {
		RtfCell headerCell = new RtfCell(new Phrase(headerFieldName,
			RtfReportStyles.normalBoldStyle));
		headerCell.setBackgroundColor(Color.GRAY);
		headerCell.setHorizontalAlignment(Chunk.ALIGN_CENTER);
		table.addCell(headerCell);
	    }

	    for (String[] row : data) {
		for (String cell : row) {
		    RtfCell valueCell = new RtfCell(new Phrase(
			    getValueFormatted(cell),
			    RtfReportStyles.normalStyle));
		    valueCell.setHorizontalAlignment(Chunk.ALIGN_CENTER);
		    table.addCell(valueCell);
		}
	    }
	    document.add(table);
	} catch (BadElementException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static String getValueFormatted(String value) {
	if (value == null) {
	    return "";
	} else if (value.equalsIgnoreCase("t")) {
	    return "S�";
	} else if (value.equalsIgnoreCase("f")) {
	    return "No";
	} else if (value.contains("-")) {
	    return getDateFormatted(value);
	} else {
	    return value;
	}
    }

    private static String getDateFormatted(String dateString) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String dateFormatted = null;
	try {
	    Date date = formatter.parse(dateString);
	    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, loc);
	    dateFormatted = df.format(date);
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	return dateFormatted;
    }

    public static String[] addAliasToArray(String[] colAlias, String newAlias) {
	String[] newColAlias = new String[colAlias.length + 1];
	for (int i = 0; i < colAlias.length; i++) {
	    newColAlias[i] = colAlias[i];
	}
	newColAlias[newColAlias.length - 1] = newAlias;
	return newColAlias;
    }
}
