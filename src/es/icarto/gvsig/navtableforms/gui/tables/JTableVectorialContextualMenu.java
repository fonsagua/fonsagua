package es.icarto.gvsig.navtableforms.gui.tables;

import java.awt.event.MouseEvent;

import javax.swing.JTable;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.menu.JTableContextualMenu;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;

public class JTableVectorialContextualMenu extends JTableContextualMenu {

    private FLyrVect layer;
    private JTable table;
    private TableFormFactory factory;

    public JTableVectorialContextualMenu(String layerName,
	    TableFormFactory factory) {
	this.layer = new TOCLayerManager().getLayerByName(layerName);
	this.factory = factory;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	table = (JTable) e.getComponent();
	if (doubleOrRightClickOnARow(e)) {
	    BasicAbstractForm form = factory.createForm(layer);
	    form.init();
	    form.setPosition(table.convertRowIndexToModel(table
		    .getSelectedRow()));
	    PluginServices.getMDIManager().addWindow(form);
	}
    }

    private boolean doubleOrRightClickOnARow(MouseEvent e) {
	return (table.getSelectedRow() != NO_ROW_SELECTED)
		&& ((e.getClickCount() == 2) || (e.getButton() == BUTTON_RIGHT));
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

    @Override
    protected void initContextualMenu() {
	// We have no real Contextual Menu in this case
    }

}
