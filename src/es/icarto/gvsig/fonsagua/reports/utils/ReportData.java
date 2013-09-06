package es.icarto.gvsig.fonsagua.reports.utils;

import com.lowagie.text.rtf.style.RtfFont;

public class ReportData {

    private final String attribute;
    private final String value;
    private final RtfFont style;

    public ReportData(String attribute, String value, RtfFont style) {
	this.attribute = attribute;
	this.value = value;
	this.style = style;
    }

    public String getAttribute() {
	return attribute;
    }

    public String getValue() {
	return value;
    }

    public RtfFont getStyle() {
	return style;
    }

}
