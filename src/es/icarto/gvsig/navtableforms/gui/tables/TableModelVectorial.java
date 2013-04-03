package es.icarto.gvsig.navtableforms.gui.tables;

import javax.swing.table.AbstractTableModel;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class TableModelVectorial extends AbstractTableModel {

    private int rowCount;
    private FLyrVect layer;

    private int colCount;
    private String[] colNames;
    private String[] colAliases;

    public TableModelVectorial(FLyrVect layer, String[] colNames,
	    String[] colAliases) throws ReadDriverException {
	this.layer = layer;
	this.colNames = colNames;
	this.colAliases = colAliases;
	initMetadata();
    }

    private void initMetadata() throws ReadDriverException {
	rowCount = (int) layer.getRecordset().getRowCount();
	colCount = colNames.length;
    }

    @Override
    public int getRowCount() {
	return rowCount;
    }

    @Override
    public int getColumnCount() {
	return colCount;
    }

    @Override
    public String getColumnName(int column) {
	return colAliases[column];
    }

    public String getLayerColumnName(int column) {
	return colNames[column];
    }

    @Override
    public boolean isCellEditable(int arg0, int arg1) {
	return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	try {
	    return layer.getRecordset().getFieldValue(rowIndex,
		    layer.getRecordset().getFieldIndexByName(colNames[columnIndex]));
	} catch (ReadDriverException e) {
	    e.printStackTrace();
	    return null;
	}
    }

}
