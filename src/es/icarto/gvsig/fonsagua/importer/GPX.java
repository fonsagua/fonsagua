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
	    final int columnCount = driver.getFieldCount();
	    for (int i = 0; i < columnCount; i++) {
		String fieldName = driver.getFieldName(i);
		SimpleHeaderField sh = new SimpleHeaderField(fieldName, i);
		simpleHeader.add(sh);
	    }
	    simpleHeader.add(new SimpleHeaderField("x", columnCount));
	    simpleHeader.add(new SimpleHeaderField("y", columnCount + 1));
	} catch (ReadDriverException e) {
	    logger.error(e.getStackTrace(), e);
	    throw new RuntimeException("Error leyendo el fichero dbf", e);
	}
    }

    private void initValues() {
	values = new DefaultTableModel();

	try {
	    final long rowCount = driver.getRowCount();
	    final int columnCount = driver.getFieldCount();
	    for (int i=0; i< columnCount;i++) {
		values.addColumn(driver.getFieldName(i));
	    }
	    values.addColumn("x");
	    values.addColumn("y");
	    
	    for (int i = 0; i < rowCount; i++) {
		if (driver.getShapeType(i) != FShape.POINT) {
		    return;
		}
		Object rowData[] = new Object[columnCount + 2];
		for (int column = 0; column < columnCount; column++) {
		    rowData[column] = driver.getFieldValue(i, column).toString();		    
		}
		
		final IGeometry geom = driver.getShape(i);
		FPoint2D point = (FPoint2D) geom.getInternalShape();
		rowData[columnCount] = point.getX() + "";
		rowData[columnCount + 1] = point.getY() + "";
		values.addRow(rowData);
		
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
