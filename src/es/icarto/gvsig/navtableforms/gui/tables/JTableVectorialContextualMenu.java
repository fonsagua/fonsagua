package es.icarto.gvsig.navtableforms.gui.tables;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.navtableforms.AbstractForm;

public class JTableVectorialContextualMenu implements MouseListener {

    private AbstractForm form;
    private JTable table;

    public JTableVectorialContextualMenu(AbstractForm form) {
	this.form = form;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	table = (JTable) e.getComponent();
	if (doubleOrRigthClickOnARow(e)) {
	    if (form.init()) {
		form.setPosition(0);
		PluginServices.getMDIManager().addWindow(form);
	    }
	}
    }

    private boolean doubleOrRigthClickOnARow(MouseEvent e) {
	return (table.getSelectedRow() > -1)
		&& ((e.getClickCount() == 2) || (e.getButton() == MouseEvent.BUTTON3));

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
