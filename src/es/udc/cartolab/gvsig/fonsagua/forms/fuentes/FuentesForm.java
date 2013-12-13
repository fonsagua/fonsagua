package es.udc.cartolab.gvsig.fonsagua.forms.fuentes;

import com.iver.cit.gvsig.exceptions.visitors.StopWriterVisitorException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.project.documents.table.gui.Table;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.utils.TOCTableManager;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

@SuppressWarnings("serial")
public class FuentesForm extends BasicAbstractForm {

    public static final String NAME = "fuentes";
    public static final String PKFIELD = "cod_fuente";
    public static String[] colNames = { "fuente", "cod_fuente" };
    public static String[] colAlias = { "Fuente", "Código" };

    public FuentesForm(FLyrVect layer) {
	super(layer);
	addTableHandler(new AlphanumericTableHandler(AforosForm.NAME,
		getWidgetComponents(), PKFIELD, AforosForm.colNames,
		AforosForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(NivelesFreaticosForm.NAME,
		getWidgetComponents(), PKFIELD, NivelesFreaticosForm.colNames,
		NivelesFreaticosForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(AnaliticasForm.NAME,
		getWidgetComponents(), PKFIELD, AnaliticasForm.colNames,
		AnaliticasForm.colAlias));
	addTableHandler(new VectorialEditableNNRelTableHandler(
		AbastecimientosForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_fuentes", AbastecimientosForm.PKFIELD,
		AbastecimientosForm.colNames, AbastecimientosForm.colAlias));
    }

    @Override
    protected String getPrimaryKeyValue() {
	return getFormController().getValue(PKFIELD);
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    public void deleteRecord() throws StopWriterVisitorException {
	super.deleteRecord();
	TOCTableManager tocTableManager = new TOCTableManager();
	String[] tablesToReload = new String[] { AforosForm.NAME,
		AnaliticasForm.NAME, "r_abastecimientos_fuentes" };
	for (String tableName : tablesToReload) {
	    final Table table = tocTableManager.getTableByName(tableName);
	    if (table != null) {
		table.refresh();
	    }
	}
    }

}
