package es.udc.cartolab.gvsig.fonsagua.utils;

import com.iver.cit.gvsig.fmap.layers.FLayer;

import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.udc.cartolab.gvsig.fonsagua.OpenAlternativeExtension;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;

public class EnabilityConditions {

    private EnabilityConditions() {
	throw new AssertionError(
		"Suppress default constructor for noninstantiability");
    }

    public static boolean isFormOpenable(FLayer layer) {
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

    public static boolean isAllAlternativesMode() {
	return FormFactory.checkLayerLoadedRegistered(AlternativasForm.NAME)
		&& OpenAlternativeExtension.getCode() == null;
    }
}
