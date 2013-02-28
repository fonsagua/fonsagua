package es.udc.cartolab.fonsagua.forms;

import java.io.InputStream;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

import es.icarto.gvsig.navtableforms.AbstractForm;

@SuppressWarnings("serial")
public abstract class BasicAbstractForm extends AbstractForm {

    public BasicAbstractForm(FLyrVect layer) {
	super(layer);
    }

    @Override
    public FormPanel getFormBody() {
	if (formBody == null) {
	    InputStream stream = getClass().getClassLoader()
		    .getResourceAsStream("ui/" + getBasicName() + ".xml");
	    try {
		formBody = new FormPanel(stream);
	    } catch (FormException e) {
		e.printStackTrace();
	    }
	}
	return formBody;
    }

    @Override
    public String getXMLPath() {
	return this.getClass().getClassLoader()
		.getResource("metadata/" + getBasicName() + ".xml").getPath();
    }

    protected abstract String getBasicName();

    @Override
    @Deprecated
    public Logger getLoggerName() {
	return Logger.getLogger(this.getClass());
    }

}
