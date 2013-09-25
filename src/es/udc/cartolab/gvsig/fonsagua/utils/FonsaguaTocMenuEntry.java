package es.udc.cartolab.gvsig.fonsagua.utils;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.project.documents.view.toc.AbstractTocContextMenuAction;
import com.iver.cit.gvsig.project.documents.view.toc.ITocItem;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;

public class FonsaguaTocMenuEntry extends AbstractTocContextMenuAction {

    @Override
    public void execute(ITocItem item, FLayer[] selectedItems) {
	for (FLayer layer : selectedItems) {
	    if (AvailableForm.forLayer(layer)) {
		AbstractForm dialog = FormFactory
			.createFormRegistered((FLyrVect) layer);
		if ((dialog != null) && (dialog.init())) {
		    PluginServices.getMDIManager().addWindow(dialog);
		}
	    }
	}
    }

    @Override
    public String getText() {
	return PluginServices.getText(this, "open_form");
    }

    @Override
    public String getGroup() {
	return "fonsagua";
    }

    @Override
    public int getGroupOrder() {
	return 150;
    }

    @Override
    public int getOrder() {
	return 1;
    }

    @Override
    public boolean isEnabled(ITocItem item, FLayer[] selectedItems) {
	return true;
    }

    @Override
    public boolean isVisible(ITocItem item, FLayer[] selectedItems) {
	if (isTocItemBranch(item) && !(selectedItems == null)) {
	    for (FLayer layer : selectedItems) {
		if (AvailableForm.forLayer(layer)) {
		    return true;
		}
	    }
	}
	return false;
    }
}
