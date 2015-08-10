package es.icarto.gvsig.navtableforms;

import java.net.MalformedURLException;

import es.icarto.gvsig.navtableforms.gui.i18n.resource.I18nResource;
import es.icarto.gvsig.navtableforms.gui.i18n.resource.JavaBundleI18nResource;
import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;

@SuppressWarnings("serial")
public abstract class BasicAbstractSubForm extends AbstractSubForm {

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

}
