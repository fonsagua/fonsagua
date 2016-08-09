package es.icarto.gvsig.fonsagua.importer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.exceptions.layers.ReloadLayerException;
import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;
import es.icarto.gvsig.importer.Output;
import es.icarto.gvsig.importer.Ruler;
import es.icarto.gvsig.importer.TableInfo;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class FonsaguaOutput implements Output {

    private static final Logger logger = Logger.getLogger(FonsaguaOutput.class);

    public void process(ImporterTM table, Ruler ruler) {

	reorder(table);
	TableInfo dialog = new TableInfo(table, ruler);
	dialog.setContextMenu(new MyMouseListener(ruler));
	dialog.openDialog();
	if (!dialog.isGood()) {
	    return;
	}

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
	    try {
		statement.clearBatch();
		con.rollback();
	    } catch (SQLException e1) {
		logger.error(e1.getStackTrace(), e1);
	    }
	}
    }

    protected void reorder(ImporterTM table) {
	int lastComunidadesRow = -1;
	for (int i = 0; i < table.getRowCount(); i++) {
	    String tablename = table.getTarget(i).toString();
	    if (tablename.equals("comunidades")
		    && (lastComunidadesRow + 1) != i) {
		lastComunidadesRow += 1;
		table.moveRow(i, i, lastComunidadesRow);
	    }
	}

	int lastViviendaRow = lastComunidadesRow + 1;
	for (int i = lastViviendaRow; i < table.getRowCount(); i++) {
	    String tablename = table.getTarget(i).toString();
	    if (tablename.equals("informacion_general")
		    && (lastViviendaRow + 1) != i) {
		lastViviendaRow += 1;
		table.moveRow(i, i, lastViviendaRow);
	    }
	}
    }
}
