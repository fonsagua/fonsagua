package es.icarto.gvsig.fonsagua.reports.utils;

import java.util.ArrayList;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Phrase;

public class ReportUtils {

    public static void writeDataList(Document document,
	    ArrayList<ReportData> data) {
	List list = new List();
	list.setIndentationLeft(20);
	list.setSymbolIndent(15);
	list.setListSymbol(new Chunk("\u2022", RtfReportStyles.normalBoldStyle));

	for (ReportData reportData : data) {
	    Phrase phrase = new Phrase();
	    phrase.add(new Chunk(reportData.getAttribute(), reportData
		    .getStyle()));
	    phrase.add(new Chunk(reportData.getValue(),
		    RtfReportStyles.normalStyle));
	    list.add(new ListItem(phrase));
	}
	try {
	    document.add(list);
	} catch (DocumentException e) {
	    e.printStackTrace();
	}
    }

}
