package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.messages.NotificationManager;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;

public class AlternativasFormExtension extends AbstractExtension {

    @Override
    public void initialize() {
	id = "alternativas_form";
	super.initialize();
    }

    @Override
    public boolean isEnabled() {
	return OpenAlternativeExtension.getCode() != null;
    }

    @Override
    public void execute(String actionCommand) {
	final FLyrVect layer = new TOCLayerManager()
		.getLayerByName(AlternativasForm.NAME);
	if (layer == null) {
	    NotificationManager.addWarning("Capa " + AlternativasForm.NAME
		    + " no está cargada en el TOC");
	    return;
	}
	AlternativasForm dialog = new AlternativasForm(layer);
	if ((dialog != null) && (dialog.init())) {
	    PluginServices.getMDIManager().addWindow(dialog);
	}
    }

}
