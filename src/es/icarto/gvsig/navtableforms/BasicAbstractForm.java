package es.icarto.gvsig.navtableforms;

import java.io.InputStream;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.project.documents.table.gui.Table;
import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

import es.icarto.gvsig.navtableforms.gui.tables.AlphanumericTableLoader;
import es.icarto.gvsig.navtableforms.utils.TOCTableManager;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;

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

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
    	    super.getWindowInfo();
    	    windowInfo.setTitle(PluginServices.getText(this, getBasicName()));
	}
	return windowInfo;
    }
    
    protected abstract String getBasicName();

    protected Table loadTable(String name) {
	TOCTableManager tocTable = new TOCTableManager();
	Table table = tocTable.getTableByName(name);
	try {
	    if (table == null) {
		AlphanumericTableLoader.loadTable(FonsaguaConstants.dataSchema,
			name);
		tocTable = new TOCTableManager();
		table = tocTable.getTableByName(name);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return table;
    }

}
