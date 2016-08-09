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
import es.icarto.gvsig.fonsagua.importer.entities.Caserio;
import es.icarto.gvsig.importer.GFactory;
import es.icarto.gvsig.importer.ImportError;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.JDBCTarget;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class ComunidadTarget extends JDBCTarget {

    private static final Logger logger = Logger
	    .getLogger(ComunidadTarget.class);

    private final Pattern pattern;
    private final String tablename;
    private final String pkname;
    private final String idDiff;

    public ComunidadTarget() {
	super(DBSession.getCurrentSession().getJavaConnection());
	this.tablename = "comunidades";
	field = new Field("comunidades");
	field.setValue(this);
	this.pattern = Pattern.compile("^\\d{8}$", Pattern.CASE_INSENSITIVE);
	this.pkname = "cod_comunidad";
	this.idDiff = "";
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
    
    private final static double MIN_DISTANCE_TO_PARENT = 2000;

    /**
     * Si hay un caserio en la aldea donde está el nuevo punto a menos de 1km se
     * coge ese caserio como el código de la comunidad. En caso contrario se
     * coge el código de máximo valor entre los que ya existan (para esa aldea)
     * en la tabla o en comunidades o en caserios y se le suma 1
     */
    public String doCalculateCode(ImporterTM table, int i) {
	Geometry point = table.getGeom(i).toJTSGeometry();
	String pointStr = "ST_GeomFromText( '" + point.toText() + "' )";

	Aldea aldea = Aldea.f().thatIntersectsWith(pointStr);
	Caserio caserio = Caserio.f().closestTo(pointStr, aldea);
	if (caserio != null) {
	    double d = caserio.distanceTo(point);
	    if (d < MIN_DISTANCE_TO_PARENT) {
		return caserio.getPK();
	    }
	}

	DefaultTableModel results2 = maxCode(Caserio.tablename, Caserio.pkname,
		6, aldea.getPK());
	DefaultTableModel results3 = maxCode(tablename, pkname, 6,
		aldea.getPK());

	String maxCodeInDB = results2.getValueAt(0, 0).toString();
	String maxCodeInData = results3.getValueAt(0, 0).toString();
	String maxCodeInTable = table.maxCodeValueForTarget(this.field, i,
		aldea.getPK());

	String maxCode = maxCodeInTable;
	if (maxCode.compareTo(maxCodeInDB) < 0) {
	    maxCode = maxCodeInDB;
	}
	if (maxCode.compareTo(maxCodeInData) < 0) {
	    maxCode = maxCodeInData;
	}
	int code = Integer.parseInt(maxCode) + 1;

	return code + "";
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

	error = checkDistanceToCaserio(table, code, table.getGeom(row), row);
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

    // "Comunidad %s duplicada en el fichero de entrada"
    private ImportError checkTableUnique(ImporterTM table, String tablename,
	    String code, int row) {

	// TODO. Ver como personalizar el msg para el elemento. Cada target
	// debería tener un nombre "Comunidad", "Vivienda" con el que referirse
	// a él, y no el nombre de la tabla (tablename") Y también debería
	// definir el género duplicado/duplicada
	if (existsInProcessed(table, tablename, code, row)) {
	    String errorMsg = String.format("La comunidad '%s' está duplicada en el fichero de entrada", code);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    // "La comunidad %s ya existe en la base de datos"
    private ImportError checkDBUnique(String tablename, String pkName,
	    String code, int row) {
	if (existsInDB(tablename, pkName, code)) {
	    String errorMsg = String.format("La comunidad '%s' ya existe en la base de datos", code);
	    return new ImportError(errorMsg, row);
	}

	return null;
    }

    private ImportError checkPointInCorrectAldea(ImporterTM table,
	    String tablename, String code, int row) {
	Geometry point = table.getGeom(row).toJTSGeometry();
	String pointStr = "ST_GeomFromText( '" + point.toText() + "' )";
	Aldea aldea = Aldea.f().thatIntersectsWith(pointStr);
	if (!code.startsWith(aldea.getPK())) {
	    String errorMsg = String.format(
		    "La comunidad '%s' no está en la aldea que indica su código",
		    tablename, code);
	    return new ImportError(errorMsg, row);
	}
	return null;
    }

    private ImportError checkDistanceToCaserio(ImporterTM table, String code,
	    IGeometry geom, int row) {
	Caserio caserio = Caserio.f().fromDB(code);
	if (caserio != null) {
	    double distance = caserio.distanceTo(geom);
	    if (distance > MIN_DISTANCE_TO_PARENT) {
		return new ImportError(
			"Existe un caserío con ese código a más de 2km de esta comunidad",
			row);
	    }

	}

	return null;
    }
    
    @Override
    public String getInsertSQL(String parentCode, String code, String geomAsWKT) {
	String codDepartamento = code.substring(0, 2);
	String codMunicipio = code.substring(2, 4);
	String codCanton = code.substring(4, 6);
	String codCaserio = code;
	return String
		.format("INSERT INTO %s (%s, cod_departamento, cod_municipio, cod_canton, cod_caserio, geom) VALUES ('%s', '%s', '%s', '%s', '%s', ST_GeomFromText('%s', 32616))",
			tablename, pkname, code, codDepartamento, codMunicipio, codCanton, codCaserio, geomAsWKT);
    }

}
