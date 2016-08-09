package es.icarto.gvsig.fonsagua.importer;

import java.util.ArrayList;
import java.util.List;

import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.GFactory;
import es.icarto.gvsig.importer.ImportError;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class NoTarget extends JDBCTarget {

    public NoTarget() {
	super(DBSession.getCurrentSession().getJavaConnection());
	field = new Field("");
	field.setValue(this);
    }

    @Override
    public boolean matches(String value) {
	return false;
    }

    @Override
    public boolean process(String value, ImporterTM table, int i) {

	IGeometry geom = new GFactory().getGeometry(table, i);
	table.setGeom(geom, i);

	table.setTarget(field, i);
	table.setCode("", i);

	return true;
    }

    @Override
    public String calculateCode(ImporterTM table, int i) {
	return null;
    }

    @Override
    public List<ImportError> checkErrors(ImporterTM table, int row) {
	ArrayList<ImportError> l = new ArrayList<ImportError>();
	l.add(new ImportError("Código no válido", row));
	l.add(new ImportError("Capa destino no válida", row));
	table.setError(l, row);
	return l;
    }
    
    @Override
    public String getInsertSQL(String parentCode, String code, String geomAsWKT) {
	return null;
    }
}