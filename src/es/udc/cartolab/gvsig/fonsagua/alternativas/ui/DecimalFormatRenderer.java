package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class DecimalFormatRenderer extends DefaultTableCellRenderer {
    private static final Locale locale = Locale.getDefault();
    private static final NumberFormat formatter;

    static {
	formatter = NumberFormat.getInstance(locale);
	formatter.setMaximumFractionDigits(2);
	formatter.setMinimumFractionDigits(2);
	formatter.setMinimumIntegerDigits(1);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
	    boolean isSelected, boolean hasFocus, int row, int column) {

	if (value instanceof String) {
	    value = ((String) value).replace(".", ",");
	} else if (value instanceof Number) {
	    value = formatter.format((Number) value);
	}
	return super.getTableCellRendererComponent(table, value, isSelected,
		hasFocus, row, column);
    }
}