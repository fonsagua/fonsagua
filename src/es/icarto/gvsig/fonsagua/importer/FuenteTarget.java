package es.icarto.gvsig.fonsagua.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.fmap.core.IGeometry;
import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.fonsagua.importer.entities.Aldea;
import es.icarto.gvsig.importer.Entity;
import es.icarto.gvsig.importer.GFactory;
import es.icarto.gvsig.importer.ImportError;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class FuenteTarget extends JDBCTarget {

    private static final Logger logger = Logger
	    .getLogger(FuenteTarget.class);

    private final Pattern pattern;
    private final String tablename;
    private final String pkname;
    private final String idDiff;
    
    public FuenteTarget() {
	super(DBSession.getCurrentSession().getJavaConnection());
	this.tablename = "fuentes";
	field = new Field("fuentes");
	field.setValue(this);
	this.pattern = Pattern.compile("^(\\d{6})FU\\d{2}$", Pattern.CASE_INSENSITIVE);
	this.pkname = "cod_fuente";
	this.idDiff = "FU";
	// geomBuild = new XYPointBuilder("", "");
    }

    @Override
    public boolean matches(String value) {
	Matcher matcher = pattern.matcher(value);
	return matcher.matches();
    }

    @Override
    public boolean process(String value, ImporterTM table, int i) {
	Matcher matcher = pattern.matcher(value);
	if (!matcher.matches()) {
	    return false;
	}
	final String code = matcher.group();

	IGeometry geom = new GFactory().getGeometry(table, i);
	table.setGeom(geom, i);
	table.setTarget(field, i);
	table.setCode(code, i);

	return true;

    }

    @Override
    public String calculateCode(ImporterTM table, int i) {
	String code = null;
	try {
	    code = doCalculateCode(table, i);
	} catch (Exception e) {
	    logger.error(e.getMessage(), e);
	}
	return code == null ? "" : code;
    }

    public String doCalculateCode(ImporterTM table, int i) {
	Geometry point = table.getGeom(i).toJTSGeometry();
	String pointStr = "ST_GeomFromText( '" + point.toText() + "' )";

	Aldea aldea = Aldea.f().thatIntersectsWith(pointStr);
	if (aldea == null) {
	    return null;
	}

	DefaultTableModel results3 = maxCode(tablename, pkname, 6,
		aldea.getPK());

	String maxCodeInData = results3.getValueAt(0, 0).toString();
	String maxCodeInTable = table.maxCodeValueForTarget(this.field, i,
		aldea.getPK());
	
	String code = codeIt(aldea, maxCodeInData, maxCodeInTable);
	return code;
    }
    
    private String codeIt(Entity parent, String maxCodeInData, String maxCodeInTable) {
	String maxCode = "000000" + idDiff + "00";
	if (maxCode.compareTo(maxCodeInTable) < 0) {
	    maxCode = maxCodeInTable;
	}

	if (maxCode.compareTo(maxCodeInData) < 0) {
	    maxCode = maxCodeInData;
	}
	int parseInt = Integer.parseInt(maxCode.substring(8)) + 1;
	String code = parent.getPK() + idDiff
		+ String.format("%02d", parseInt);
	return code;
	
    }

    @Override
    public List<ImportError> checkErrors(ImporterTM table, int row) {
	List<ImportError> l = new ArrayList<ImportError>();
	ImportError error = null;

	String code = table.getCode(row);

	error = checkCode(table, code, row);
	if (error != null) {
	    l.add(error);
	    table.setError(l, row);
	    return l;
	}

	error = checkTableUnique(table, tablename, code, row);
	if (error != null) {
	    l.add(error);
	}
	error = checkDBUnique(tablename, pkname, code, row);
	if (error != null) {
	    l.add(error);
	}

	error = checkPointInCorrectAldea(table, tablename, code, row);
	if (error != null) {
	    l.add(error);
	}

	table.setError(l, row);
	return l;
    }

    private ImportError checkCode(ImporterTM table, String code, int row) {
	Matcher matcher = pattern.matcher(code);
	if (!matcher.matches()) {
	    return new ImportError("Código no válido", row);
	}
	return null;
    }

    private ImportError checkTableUnique(ImporterTM table, String tablename,
	    String code, int row) {
	if (existsInProcessed(table, tablename, code, row)) {
	    String errorMsg = String.format("Fuente '%s' duplicada en el fichero de entrada", code);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    private ImportError checkDBUnique(String tablename, String pkName,
	    String code, int row) {
	if (existsInDB(tablename, pkName, code)) {
	    String errorMsg = String.format("La fuente '%s' ya existe en la base de datos", code);
	    return new ImportError(errorMsg, row);
	}

	return null;
    }

    private ImportError checkPointInCorrectAldea(ImporterTM table,
	    String tablename, String code, int row) {
	Geometry point = table.getGeom(row).toJTSGeometry();
	String pointStr = "ST_GeomFromText( '" + point.toText() + "' )";
	Aldea aldea = Aldea.f().thatIntersectsWith(pointStr);
	if (aldea == null) {
	    String errorMsg = String
		    .format("No hay ninguna aldea en las coordenadas de '%s'",
			    code);
	    return new ImportError(errorMsg, row);
	}
	if (!code.startsWith(aldea.getPK())) {
	    String errorMsg = String.format("La fuente '%s' no está en la aldea que indica su código",
		    code);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }
    
    @Override
    public String getInsertSQL(String parentCode, String code, String geomAsWKT) {

	return String
		.format("INSERT INTO %s (%s, tipo_fuente, geom) VALUES ('%s', ' ', ST_GeomFromText('%s', 32616))",
			tablename, pkname, code, geomAsWKT);
    }

}
