package es.icarto.gvsig.navtableforms.gui.tables;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

import com.hardcode.driverManager.DriverLoadException;
import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.data.DataSource;
import com.hardcode.gdbms.engine.data.DataSourceFactory;
import com.hardcode.gdbms.engine.data.NoSuchTableException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.fmap.edition.EditableAdapter;
import com.iver.cit.gvsig.fmap.layers.LayerFactory;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.ProjectFactory;
import com.iver.cit.gvsig.project.documents.table.ProjectTable;
import com.iver.cit.gvsig.project.documents.table.gui.Table;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

import es.udc.cartolab.gvsig.users.utils.DBSession;

public class AlphanumericTableLoader {

    public static void loadTable(String schema, String tableName)
	    throws DriverLoadException, NoSuchTableException,
	    ReadDriverException, PropertyVetoException {

	IWindow[] ws = PluginServices.getMDIManager().getAllWindows();
	IWindow baseView = null;
	for (IWindow w : ws) {
	    if (w instanceof BaseView) {
		baseView = w;
	    }
	}
	DBSession session = DBSession.getCurrentSession();

	String completeTableName = schema + "." + tableName;

	LayerFactory.getDataSourceFactory().addDBDataSourceByTable(tableName,
		session.getServer(), session.getPort(), session.getUserName(),
		session.getPassword(), session.getDatabase(),
		completeTableName, "PostgreSQL Alphanumeric");

	DataSource dataSource;
	dataSource = LayerFactory.getDataSourceFactory()
		.createRandomDataSource(tableName,
			DataSourceFactory.AUTOMATIC_OPENING);
	SelectableDataSource sds = new SelectableDataSource(dataSource);
	EditableAdapter auxea = new EditableAdapter();

	auxea.setOriginalDataSource(sds);

	ProjectTable projectTable = ProjectFactory
		.createTable(tableName, auxea);
	Table t = new Table();
	t.setModel(projectTable);

	PluginServices.getMDIManager().addWindow(t);
	JInternalFrame frame = (JInternalFrame) t.getRootPane().getParent();

	ProjectExtension ext = (ProjectExtension) PluginServices
		.getExtension(ProjectExtension.class);
	ext.getProject().addDocument(projectTable);
	frame.toBack();
	frame.setSelected(false);
	JInternalFrame frameBaseView = (JInternalFrame) ((BaseView) baseView)
		.getRootPane().getParent();
	frameBaseView.setSelected(true);
    }

}
