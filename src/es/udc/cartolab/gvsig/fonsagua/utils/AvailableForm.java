package es.udc.cartolab.gvsig.fonsagua.utils;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.fonsagua.OpenAlternativeExtension;

public final class AvailableForm {

    public final static boolean forLayer(FLyrVect layer) {
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