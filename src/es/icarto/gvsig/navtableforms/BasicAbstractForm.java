package es.icarto.gvsig.navtableforms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.RowSorter.SortKey;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

import es.icarto.gvsig.commons.gui.OkCancelPanel;
import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.navtableforms.gui.i18n.I18nResourceManager;
import es.icarto.gvsig.navtableforms.gui.i18n.resource.I18nResource;
import es.icarto.gvsig.navtableforms.gui.i18n.resource.JavaBundleI18nResource;
import es.udc.cartolab.gvsig.fonsagua.AlternativeBudgetReportExtension;
import es.udc.cartolab.gvsig.fonsagua.utils.Utils;
import es.udc.cartolab.gvsig.navtable.contextualmenu.ChooseSortFieldDialog;

@SuppressWarnings("serial")
public abstract class BasicAbstractForm extends AbstractForm {

    private static final Logger logger = Logger
	    .getLogger(AlternativeBudgetReportExtension.class);

    public BasicAbstractForm(FLyrVect layer) {
	super(layer);
	addSorterButton();
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
		.getResource("rules/" + getBasicName() + ".xml").getPath();
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

    protected void addSorterButton() {
	java.net.URL imgURL = getClass().getClassLoader().getResource(
		"sort.png");
	JButton jButton = new JButton(new ImageIcon(imgURL));
	jButton.setToolTipText("Ordenar registros");

	jButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		List<String> columns = Utils.getFields(getSchema(), getBasicName());
		List<Field> fields = new ArrayList<Field>();

		I18nResourceManager translator = getI18nHandler().getResourceManager();
		for (String column : columns) {
		    fields.add(new Field(column, translator.getString(column)));
		}

		ChooseSortFieldDialog dialog = new ChooseSortFieldDialog(fields);

		if (dialog.open().equals(OkCancelPanel.OK_ACTION_COMMAND)) {
		    List<Field> sortedFields = dialog.getFields();
		    List<SortKey> sortKeys = new ArrayList<SortKey>();
		    SelectableDataSource sds = getRecordset();
		    for (Field field : sortedFields) {
			try {
			    int fieldIdx = sds.getFieldIndexByName(field
				    .getKey());
			    sortKeys.add(new SortKey(fieldIdx, field
				    .getSortOrder()));
			} catch (ReadDriverException e1) {
			    logger.error(e1.getStackTrace(), e1);
			}
		    }
		    setSortKeys(sortKeys);
		}
	    }
	});
	getActionsToolBar().add(jButton);
    }

    protected String getSchema() {
	return "public";
    }
    
    protected abstract String getBasicName();

}
