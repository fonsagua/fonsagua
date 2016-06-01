package es.udc.cartolab.gvsig.fonsagua.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.icarto.gvsig.commons.format.Format;

public class FormatUtils extends Format{
    private final static Locale loc = new Locale("es");
    
    public static String getValueFormatted(String value) {
	if (value == null) {
	    return "";
	} else if (value.equalsIgnoreCase("t")) {
	    return "Sí";
	} else if (value.equalsIgnoreCase("f")) {
	    return "No";
	} else if (value.contains("-")) {
	    return getDateFormatted(value);
	} else if (value.contains(".")) {
	    return getDoubleFormatted(value);
	} else {
	    return value;
	}
    }

    private static String getDoubleFormatted(String doubleString) {
	NumberFormat nf_in = NumberFormat.getNumberInstance(Locale.UK);
	NumberFormat nf_out = NumberFormat.getNumberInstance(loc);
	double doubleNumber;
	String doubleFormatted = null;
	try {
	    doubleNumber = nf_in.parse(doubleString).doubleValue();
	    nf_out.setMinimumFractionDigits(2);
	    nf_out.setMaximumFractionDigits(2);
	    doubleFormatted = nf_out.format(doubleNumber);
	} catch (ParseException e) {
	    return doubleString;
	}
	return doubleFormatted;

    }

    private static String getDateFormatted(String dateString) {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String dateFormatted = null;
	try {
	    Date date = formatter.parse(dateString);
	    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, loc);
	    dateFormatted = df.format(date);
	} catch (ParseException e) {
	    return dateString;
	}
	return dateFormatted;
    }

}
