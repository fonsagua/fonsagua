package es.icarto.gvsig.fonsagua.reports.utils;

import java.awt.Color;
import java.util.Collection;

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

import es.udc.cartolab.gvsig.fonsagua.utils.FormatUtils;

public class ReportUtils {

    public static void writeDataList(Document document,
	    Collection<ReportListItem> data) {
	Paragraph listParagrpah = new Paragraph();
	List list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", RtfReportStyles.normalBoldStyle));

	for (ReportListItem reportData : data) {
	    Phrase phrase = new Phrase();
	    phrase.add(new Chunk(reportData.getAttribute(), reportData
		    .getStyle()));
	    phrase.add(new Chunk(FormatUtils.getValueFormatted(reportData.getValue()),
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
	    table.setWidth(100);
	    float[] columnWidths = new float[headerFieldNames.length];
	    for (float column : columnWidths) {
		column = 425f / columnWidths.length;
	    }
	    table.setWidths(columnWidths);

	    for (String headerFieldName : headerFieldNames) {
		RtfCell headerCell = new RtfCell(new Phrase(headerFieldName,
			RtfReportStyles.tableBoldStyle));
		headerCell.setBackgroundColor(Color.GRAY);
		headerCell.setHorizontalAlignment(Chunk.ALIGN_CENTER);
		table.addCell(headerCell);
	    }

	    for (String[] row : data) {
		for (String cell : row) {
		    RtfCell valueCell = new RtfCell(
			    new Phrase(FormatUtils.getValueFormatted(cell),
				    RtfReportStyles.tableStyle));
		    valueCell.setHorizontalAlignment(Chunk.ALIGN_CENTER);
		    table.addCell(valueCell);
		}
	    }
	    document.add(table);
	    document.add(Chunk.NEWLINE);
	} catch (BadElementException e) {
	    e.printStackTrace();
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

    public static String[] addAliasToArray(String[] colAlias, String newAlias) {
	String[] newColAlias = new String[colAlias.length + 1];
	for (int i = 0; i < colAlias.length; i++) {
	    newColAlias[i] = colAlias[i];
	}
	newColAlias[newColAlias.length - 1] = newAlias;
	return newColAlias;
    }

    public static String[] cloneArray(String[] colNames) {
	String[] cloned = new String[colNames.length];
	for (int i=0; i<colNames.length; i++) {
	    cloned[i] = new String(colNames[i]);
	}
	return cloned;
    }
}
