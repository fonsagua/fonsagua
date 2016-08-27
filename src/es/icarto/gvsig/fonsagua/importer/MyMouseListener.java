package es.icarto.gvsig.fonsagua.importer;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.Ruler;

public class MyMouseListener implements MouseListener {

    public static final String CONTEXT_MENU = MyMouseListener.class.getName();
    private final Ruler ruler;

    public MyMouseListener(Ruler ruler) {
	this.ruler = ruler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	if (e.getButton() != MouseEvent.BUTTON3) {
	    return;
	}
	final JTable table = (JTable) e.getSource();
	Point point = e.getPoint();
	final int row = table.rowAtPoint(point);
	if (row < 0) {
	    return;
	}

	JPopupMenu popup = new JPopupMenu();

	if (table.getSelectedRowCount() < 2) {
	    ListSelectionModel model = table.getSelectionModel();
	    model.setSelectionInterval(row, row);
	    JMenuItem createComunidad = createComunidad(table, row);
	    popup.add(createComunidad);
	}

	JMenuItem removeRow = removeRow(table);
	popup.add(removeRow);
	if (popup.getComponents().length != 0) {
	    popup.show(table, e.getX(), e.getY());
	}

    }

    private JMenuItem createComunidad(final JTable table, final int row) {
	JMenuItem menu = new JMenuItem("Crear Comunidad");
	menu.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		ImporterTM model = (ImporterTM) table.getModel();

		Object[] data = new Object[model.getColumnCount()];
		int tablenameIdx = -1;
		for (int i = 0; i < model.getColumnCount(); i++) {
		    final Object o = model.getValueAt(row, i);
		    if (o instanceof Field) {
			tablenameIdx = i;
		    }
		    data[i] = o;
		}
		model.insertRow(row, data);

		Field comunidad = null;
		for (Field f : ruler.getFields()) {
		    if (f.getKey().equals("comunidades")) {
			comunidad = f;
		    }
		}

		model.setValueAt(comunidad, row, tablenameIdx);
		model.reCheckErrors();
	    }
	});
	return menu;
    }

    private JMenuItem removeRow(final JTable table) {


	int numRows = table.getSelectedRows().length;
	String msg = numRows > 1 ? "Eliminar filas" : "Eliminar fila";
	JMenuItem menu = new JMenuItem(msg);
	menu.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		int numRows = table.getSelectedRows().length;
		ImporterTM model = (ImporterTM) table.getModel();
		for (int i = 0; i < numRows; i++) {
		    model.removeRow(table.getSelectedRow());
		}
		model.reCheckErrors();
	    }
	});
	return menu;
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
