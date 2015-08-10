package es.icarto.gvsig.navtableforms;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

import es.icarto.gvsig.navtableforms.gui.i18n.resource.I18nResource;
import es.icarto.gvsig.navtableforms.gui.i18n.resource.JavaBundleI18nResource;

@SuppressWarnings("serial")
public abstract class BasicAbstractForm extends AbstractForm {

    public BasicAbstractForm(FLyrVect layer) {
	super(layer);
	deleteMessageKey = "confirm_delete_register_cascade";
    }

    @Override
    public FormPanel getFormBody() {
	if (formBody == null) {
	    InputStream stream = getClass().getClassLoader()
		    .getResourceAsStream("forms/" + getBasicName() + ".xml");
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

    @Override
    public I18nResource[] getI18nResources() {
	try {
	    return new I18nResource[] {
		    new JavaBundleI18nResource(getBasicName(), this.getClass().getClassLoader()
			.getResource("i18n/").getPath())
	    };
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
    	    super.getWindowInfo();
    	    windowInfo.setTitle(PluginServices.getText(this, getBasicName()));
	}
	return windowInfo;
    }
    
    protected abstract String getBasicName();

}
