package es.icarto.gvsig.navtableforms;

import javax.swing.JTextArea;

public class TextAreaFormatter {

    public void format(JTextArea widget) {
	widget.setWrapStyleWord(true);
	widget.setLineWrap(true);
    }
}
