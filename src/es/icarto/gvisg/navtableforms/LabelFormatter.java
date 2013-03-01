package es.icarto.gvisg.navtableforms;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

public class LabelFormatter {

    public void format(JLabel label) {

	Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>();
	attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);

	Font font = new Font(attributes);
	label.setForeground(new Color(100, 100, 100));
	label.setFont(font);
    }

}
