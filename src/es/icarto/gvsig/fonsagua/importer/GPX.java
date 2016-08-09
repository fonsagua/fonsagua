package es.icarto.gvsig.fonsagua.importer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.gvsig.gpx.driver.GPXVectorialDriver;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.cit.gvsig.fmap.core.FPoint2D;
import com.iver.cit.gvsig.fmap.core.FShape;
import com.iver.cit.gvsig.fmap.core.IGeometry;

import es.icarto.gvsig.importer.SimpleHeaderField;
import es.icarto.gvsig.importer.reader.Reader;

public class GPX implements Reader {

    private static final Logger logger = Logger.getLogger(GPX.class);

    private GPXVectorialDriver driver;
    private List<SimpleHeaderField> simpleHeader;
    private DefaultTableModel values;

    @Override
    public void initReader(File file) {
	driver = new GPXVectorialDriver();
	try {
	    driver.open(file);
	    driver.initialize();
	    initSimpleHeader();
	    initValues();
	    driver.close();
	} catch (ReadDriverException e) {
	    logger.error(e.getStackTrace(), e);
	    throw new RuntimeException("Error leyendo el fichero gpx", e);
	}
    }

    private void initSimpleHeader() {
	simpleHeader = new ArrayList<SimpleHeaderField>();
	try {
	    for (int i = 0; i < driver.getFieldCount(); i++) {
		String fieldName = driver.getFieldName(i);
		SimpleHeaderField sh = new SimpleHeaderField(fieldName, i);
		simpleHeader.add(sh);
	    }
	} catch (ReadDriverException e) {
	    logger.error(e.getStackTrace(), e);
	    throw new RuntimeException("Error leyendo el fichero dbf", e);
	}
    }

    private void initValues() {
	values = new DefaultTableModel();

	// TODO
	// Probablemente crear mi propio table model es lo que tiene más
	// sentido. O una clase genérica propia que encapsule un table model

	// Hay que gestionar la reproyección de los puntos

	// Tiene sentido mostrar la x/y lat/lng originales y un campo adicional
	// con los valores proyectados ya sacados de la geometría final

	// Añadir regla para vértices de parcelas y como generar el polígono a
	// partir de los vértices
	values.addColumn("id");
	values.addColumn("x");
	values.addColumn("y");
	// values.addColumn("orggeom");

	try {
	    for (int i = 0; i < driver.getRowCount(); i++) {
		Object rowData[] = new Object[4];
		rowData[0] = driver.getFieldValue(i, 0).toString();
		if (driver.getShapeType(i) == FShape.POINT) {
		    final IGeometry geom = driver.getShape(i);
		    FPoint2D point = (FPoint2D) geom.getInternalShape();
		    rowData[1] = point.getX() + "";
		    rowData[2] = point.getY() + "";
		    // rowData[3] = geom;
		    values.addRow(rowData);
		}
	    }
	} catch (ReadDriverException e) {
	    logger.error(e.getStackTrace(), e);
	    throw new RuntimeException("Error leyendo el fichero gpx", e);
	}
    }

    @Override
    public List<SimpleHeaderField> getSimpleHeader() {
	return simpleHeader;
    }

    @Override
    public DefaultTableModel getValues() {
	return values;
    }

    @Override
    public FileFilter getFileFilter() {
	String description = "Ficheros GPX";
	String extensions = "gpx";
	return new FileNameExtensionFilter(description, extensions);
    }

}
