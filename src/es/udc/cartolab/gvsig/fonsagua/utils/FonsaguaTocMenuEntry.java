/*
 * This file is part of NavTable
 * Copyright (C) 2009 - 2010  Cartolab (Universidade da Coruña)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * Authors:
 *   Juan Ignacio Varela García <nachouve (at) gmail (dot) com>
 *   Pablo Sanxiao Roca <psanxiao (at) gmail (dot) com>
 *   Javier Estévez Valiñas <valdaris (at) gmail (dot) com>
 *   Jorge Lopez Fernandez <jlopez (at) cartolab (dot) es>
 */
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
	    if (layer instanceof FLyrVect) {
		AbstractForm dialog = FormFactory
			.createFormRegistered((FLyrVect) layer);

		if ((dialog != null) && (dialog.init())) {
		    PluginServices.getMDIManager().addWindow(dialog);
		}
	    }
	}
    }

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
	boolean availableLayerWithForm = false;
	for (FLayer layer : selectedItems) {
	    availableLayerWithForm |= ((layer instanceof FLyrVect)
		    && (FormFactory.hasMainFormRegistered(layer.getName())) && (layer
		    .isAvailable()));
	}
	return availableLayerWithForm;
    }

    @Override
    public boolean isVisible(ITocItem item, FLayer[] selectedItems) {
	if (isTocItemBranch(item)
		&& !(selectedItems == null || selectedItems.length <= 0)) {
	    boolean layerWithForm = false;
	    for (FLayer layer : selectedItems) {
		layerWithForm |= ((layer instanceof FLyrVect) && (FormFactory
			.hasMainFormRegistered(layer.getName())));
	    }
	    return layerWithForm;
	}
	return false;
    }

}
