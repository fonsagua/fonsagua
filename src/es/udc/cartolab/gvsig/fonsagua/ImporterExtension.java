package es.udc.cartolab.gvsig.fonsagua;

import java.net.URL;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import com.iver.andami.PluginServices;

import es.icarto.gvsig.commons.AbstractExtension;
import es.icarto.gvsig.fonsagua.importer.FonsaguaHeader;
import es.icarto.gvsig.fonsagua.importer.FonsaguaOutput;
import es.icarto.gvsig.fonsagua.importer.FonsaguaRuler;
import es.icarto.gvsig.fonsagua.importer.GPX;
import es.icarto.gvsig.importer.FileToImport;
import es.icarto.gvsig.importer.Header;
import es.icarto.gvsig.importer.ImportManager;
import es.icarto.gvsig.importer.Output;
import es.icarto.gvsig.importer.Ruler;
import es.icarto.gvsig.importer.reader.DBF;
import es.icarto.gvsig.importer.reader.Reader;
import es.icarto.gvsig.importer.reader.XLS;
import es.udc.cartolab.gvsig.users.utils.DBSession;

// Autodetectar cuando son UTM y cuando GEO
// Mejorar como se muestran errores y warnings
// Separar errores de warnings
public class ImporterExtension extends AbstractExtension {

    private FileToImport dialog;

    @Override
    public void initialize() {
	String iconName = "importerextension";
	URL iconUrl = this.getClass().getClassLoader()
		.getResource("images/" + iconName.toLowerCase() + ".png");
	PluginServices.getIconTheme().registerDefault(iconName, iconUrl);
	XLS xls = new XLS();
	xls.setHeaderLine(XLS.FIRST_NOT_EMPTY);
	List<Reader> readers = Arrays.asList(new DBF(), new GPX(), xls);
	dialog = new FileToImport(null);
	dialog.setReaders(readers);
    }

    @Override
    public void execute(String actionCommand) {

	dialog.openDialog();

	Reader reader = dialog.getInitializedReader();
	if (reader == null) {
	    return;
	}
	Header header = new FonsaguaHeader().getHeader();
	Output output = new FonsaguaOutput();
	Ruler ruler = new FonsaguaRuler();

	Connection con = DBSession.getCurrentSession().getJavaConnection();
	ImportManager importManager = new ImportManager(reader, header, output,
		ruler, con);

	importManager.readHeader();
	importManager.processFile();
    }

    @Override
    public boolean isEnabled() {
	return DBSession.isActive() && (getView() != null);
    }

}
