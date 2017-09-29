package es.icarto.gvsig.fonsagua.importer;

import java.awt.image.ImageObserver;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.exceptions.layers.ReloadLayerException;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.commons.gui.IWindowClosed;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;
import es.icarto.gvsig.importer.Output;
import es.icarto.gvsig.importer.Ruler;
import es.icarto.gvsig.importer.TableInfo;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class FonsaguaOutput implements Output, IWindowClosed {

    private static final Logger logger = Logger.getLogger(FonsaguaOutput.class);
    private TableInfo dialog = null;

    @Override
    public void process(ImporterTM table, Ruler ruler) {

	reorder(table);
	dialog = new TableInfo(table, ruler);
	dialog.setContextMenu(new MyMouseListener(ruler));
	dialog.setWindowClosed(this);
	dialog.openDialog();
    }

    public void reorder(ImporterTM table) {
	putComunidadesFirst(table);
	sortByCode(table, "comunidades");
	putInformacionGeneralSecond(table);
	sortByCode(table, "informacion_general");
	sortOthersByCode(table);
    }

    private void putComunidadesFirst(ImporterTM table) {
	for (int j = 0; j < table.getRowCount() - 1; j++) {
	    for (int i = 0; i < table.getRowCount() - 1; i++) {
		String tablename = table.getTarget(i).toString();
		if (!tablename.equals("comunidades")) {
		    table.moveRow(i, i, i + 1);
		}
	    }
	}
    }

    private void putInformacionGeneralSecond(ImporterTM table) {
	int first = -1;
	for (int i = 0; i < table.getRowCount() - 1; i++) {
	    String tablename = table.getTarget(i).toString();
	    if (!tablename.equals("comunidades")) {
		first = i;
		break;
	    }
	}
	if (first == -1) {
	    return;
	}
	for (int j = 0; j < table.getRowCount() - 1; j++) {
	    for (int i = first; i < table.getRowCount() - 1; i++) {
		String tablename = table.getTarget(i).toString();
		if (!tablename.equals("informacion_general")) {
		    table.moveRow(i, i, i + 1);
		}
	    }
	}
    }

    private void sortByCode(ImporterTM table, String key) {
	int length = table.getRowCount();
	int first = -1;
	for (int i = 0; i < length; i++) {
	    String tablename = table.getTarget(i).toString();
	    if (tablename.equals(key)) {
		first = i;
		break;
	    }
	}
	if ((first != -1) && (first != length)) {
	    boolean moved = true;
	    while (moved) {
		moved = false;
		for (int i = first; i < length - 1; i++) {
		    String tablename1 = table.getTarget(i).toString();
		    String tablename2 = table.getTarget(i + 1).toString();
		    if (!tablename1.equals(key)) {
			break;
		    }
		    if (tablename2.equals(key)) {
			String code1 = table.getCode(i);
			String code2 = table.getCode(i + 1);
			// System.out.println(String.format(
			// "code1: %s, code2: %s", code1, code2));
			if (code1.compareTo(code2) > 0) {
			    moved = true;
			    // System.out.println(String.format(
			    // "row1: %s, row2: %s", i, i + 1));
			    table.moveRow(i, i, i + 1);
			}
		    }
		}
	    }
	}
    }

    private void sortOthersByCode(ImporterTM table) {
	int length = table.getRowCount();
	int first = -1;
	for (int i = 0; i < length; i++) {
	    String tablename = table.getTarget(i).toString();
	    if (!tablename.equals("comunidades")
		    && !tablename.equals("informacion_general")) {
		first = i;
		break;
	    }
	}
	if ((first != -1) && (first != length)) {
	    boolean moved = true;
	    while (moved) {
		moved = false;
		for (int i = first; i < length - 1; i++) {
		    String code1 = table.getCode(i);
		    String code2 = table.getCode(i + 1);
		    // System.out.println(String.format("code1: %s, code2: %s",
		    // code1, code2));
		    if (code1.compareTo(code2) > 0) {
			moved = true;
			// System.out.println(String.format("row1: %s, row2: %s",
			// i, i + 1));
			table.moveRow(i, i, i + 1);
		    }
		}
	    }
	}
    }

    @Override
    public void windowClosed(IWindow window) {
	if (!dialog.isGood()) {
	    return;
	}
	ImporterTM table = (ImporterTM) dialog.getJTable().getModel();
	reorder(table);

	Connection con = DBSession.getCurrentSession().getJavaConnection();
	Statement statement = null;
	boolean autoCommit;
	try {
	    autoCommit = con.getAutoCommit();
	    con.setAutoCommit(false);
	    statement = con.createStatement();

	    for (int i = 0; i < table.getRowCount(); i++) {
		JDBCTarget target = (JDBCTarget) table.getTarget(i).getValue();

		IGeometry geom = table.getGeom(i);
		String geomAsWKT = geom.toJTSGeometry().toText();

		String id = table.getCode(i);
		String codCom = id.substring(0, 8);

		String sql = target.getInsertSQL(codCom, id, geomAsWKT);
		statement.addBatch(sql);
	    }

	    statement.executeBatch();

	    con.commit();
	    con.setAutoCommit(autoCommit);

	    FLyrVect[] layers = new TOCLayerManager().getAllLayers();
	    for (FLyrVect l : layers) {
		try {
		    l.reload();
		} catch (ReloadLayerException e) {
		    logger.error(e.getStackTrace(), e);
		}
	    }
	    JOptionPane.showMessageDialog(null, "Añadidos correctamete");
	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	    logger.error(e.getCause().getMessage());
	    try {
		statement.clearBatch();
		con.rollback();
	    } catch (SQLException e1) {
		logger.error(e1.getStackTrace(), e1);
	    }
	    JOptionPane.showMessageDialog(null,
		    "Ha habido un error añadiendo los datos", "Error",
		    ImageObserver.ERROR);
	}
    }
}
