package es.icarto.gvsig.fonsagua.importer;

import java.sql.Connection;

import javax.swing.table.DefaultTableModel;

import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.commons.db.ConnectionWrapper;
import es.icarto.gvsig.fonsagua.importer.entities.Aldea;
import es.icarto.gvsig.importer.ImportError;
import es.icarto.gvsig.importer.ImporterTM;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class ErrorCheck {

    private final String name;

    public ErrorCheck(String name) {
	this.name = name;
    }

    public ImportError checkPointInCorrectAldea(ImporterTM table,
	    String tablename, String code, int row) {
	Geometry point = table.getGeom(row).toJTSGeometry();
	String pointStr = "ST_GeomFromText( '" + point.toText() + "' )";

	Aldea aldea = Aldea.f().thatIntersectsWith(pointStr);
	if (aldea == null) {
	    String errorMsg = String.format(
		    "No hay ninguna aldea en las coordenadas de '%s'", code);
	    return new ImportError(errorMsg, row);
	}


	String aldeaPK = bufferAndIntersects(pointStr, code.substring(0, 6));
	if (aldeaPK == null) {
	    String msg = "%s '%s' no está en la aldea que indica su código";
	    String errorMsg = String.format(msg, name, code);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    private String bufferAndIntersects(String point, String aldea) {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	ConnectionWrapper conW = new ConnectionWrapper(con);

	String query = String.format(
		"SELECT %s FROM %s WHERE %s = '%s' AND ST_Intersects(ST_Buffer(geom, 3000), %s)",
		Aldea.pkname,
		Aldea.tablename, Aldea.pkname, aldea, point);
	DefaultTableModel table = conW.execute(query);
	if ((table == null) || (table.getRowCount() < 1)) {
	    return null;
	}

	return table.getValueAt(0, 0).toString();
    }

}
