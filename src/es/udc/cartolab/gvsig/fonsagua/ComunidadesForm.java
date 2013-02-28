package es.udc.cartolab.gvsig.fonsagua;

import java.io.File;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.jeta.forms.components.panel.FormPanel;

import es.icarto.gvsig.navtableforms.AbstractForm;

@SuppressWarnings("serial")
public class ComunidadesForm extends AbstractForm {

    public static final String ABEILLE_FILENAME = "comunidadesForm.xml";
    public static final String DATA_FILEPATH = "gvSIG" + File.separator
    	    + "extensiones" + File.separator
    	    + "es.udc.cartolab.gvsig.fonsagua" + File.separator + "comunidades.xml";
    private FormPanel form;

    public ComunidadesForm(FLyrVect layer) {
	super(layer);
	initWindow();
    }

    private void initWindow() {
	viewInfo.setHeight(750);
	viewInfo.setWidth(550);
	viewInfo.setTitle("Comunidades");
    }

    @Override
    public FormPanel getFormBody() {
	if (form == null) {
	    form = new FormPanel(ComunidadesForm.ABEILLE_FILENAME);
	}
	return form;
    }

    @Override
    public String getXMLPath() {
	return DATA_FILEPATH;
    }

    @Override
    public Logger getLoggerName() {
	return Logger.getLogger(this.getClass().getName());
    }

    @Override
    protected void fillSpecificValues() {
    }

}
