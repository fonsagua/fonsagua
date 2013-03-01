package es.icarto.gvisg.navtableforms;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class AbeilleUtils {

    private LabelFormatter lf;
    private TextAreaFormatter ta;

    public AbeilleUtils() {
	lf = new LabelFormatter();
	ta = new TextAreaFormatter();
    }

    public void formatTextArea(Container c) {
	int count = 0;
	while (c.getComponentCount() > count) {
	    Component comp = c.getComponent(count++);
	    if (comp instanceof JTextArea) {
		JTextArea widget = (JTextArea) comp;
		ta.format(widget);
	    }

	    if (comp instanceof Container) {
		formatTextArea((Container) comp);
	    }
	}
    }

    public void formatLabels(Container c) {
	int count = 0;
	while (c.getComponentCount() > count) {
	    Component comp = c.getComponent(count++);
	    if (comp instanceof JLabel) {
		JLabel widget = (JLabel) comp;
		lf.format(widget);
	    }

	    if (comp instanceof Container) {
		formatLabels((Container) comp);
	    }
	}
    }

}
