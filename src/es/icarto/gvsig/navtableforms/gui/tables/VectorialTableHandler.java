package es.icarto.gvsig.navtableforms.gui.tables;

import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JTable;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.AbstractForm;

public class VectorialTableHandler {

    private FLyrVect layer;
    private JTable jtable;
    private String foreingKeyId;
    private String[] colNames;
    private String[] colAliases;
    private MouseListener listener;
    private AbstractForm form;

    public VectorialTableHandler(FLyrVect layer,
	    HashMap<String, JComponent> widgets, String foreingKeyId,
	    String[] colNames, String[] colAliases) {
	this.layer = layer;
	jtable = (JTable) widgets.get(layer.getName());
	jtable.setAutoCreateRowSorter(true);

	this.foreingKeyId = foreingKeyId;
	this.colNames = colNames;
	this.colAliases = colAliases;
    }

    public void reload(AbstractForm form) {
	this.form = form;
	listener = new JTableVectorialContextualMenu(form);
	jtable.addMouseListener(listener);
	jtable.setFillsViewportHeight(true);

    }

    public void removeListeners() {
	jtable.removeMouseListener(listener);
    }

    public void fillValues(String value) {
	try {
	    TableModelVectorial model = new TableModelVectorial(layer,
		    colNames, colAliases);
	    jtable.setModel(model);
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	}

    }

}
