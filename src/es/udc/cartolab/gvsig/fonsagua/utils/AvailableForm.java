package es.udc.cartolab.gvsig.fonsagua.utils;

import com.iver.cit.gvsig.fmap.layers.FLayer;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.fonsagua.OpenAlternativeExtension;

public abstract class AvailableForm {

    public static boolean forLayer(FLayer layer) {
	return FormFactory.hasMainFormRegistered(layer.getName())
		&& ifAltLayerConcreteAltIsOpened(layer.getName());
    }

    private static boolean ifAltLayerConcreteAltIsOpened(String layername) {

	if (FonsaguaFormFactory.getInstance().getAllAlternativasLayersNames()
		.contains(layername)) {
	    if (OpenAlternativeExtension.getCode() != null) {
		return true;
	    }
	    return false;
	}
	return true;
    }
}
