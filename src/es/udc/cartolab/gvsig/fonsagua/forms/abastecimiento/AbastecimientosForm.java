package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.exceptions.layers.ReloadLayerException;
import com.iver.cit.gvsig.exceptions.visitors.StopWriterVisitorException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.project.documents.table.gui.Table;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericNotEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialTableHandler;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.icarto.gvsig.navtableforms.utils.TOCTableManager;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AdescosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.DatosConsumoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ImplicacionComunidadForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ValoracionSistemaForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.FuentesForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

@SuppressWarnings("serial")
public class AbastecimientosForm extends BasicAbstractForm {

    public static final String NAME = "abastecimientos";
    public static final String PKFIELD = "cod_abastecimiento";
    public static String[] colNames = { "abastecimiento", "cod_abastecimiento" };
    public static String[] colAlias = { "Abastecimiento", "Código" };

    public AbastecimientosForm(FLyrVect layer) {
	super(layer);

	addTableHandler(new AlphanumericTableHandler(JuntasAguaForm.NAME,
		getWidgetComponents(), PKFIELD, JuntasAguaForm.colNames,
		JuntasAguaForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(CoberturaForm.NAME,
		getWidgetComponents(), PKFIELD, CoberturaForm.colNames,
		CoberturaForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(GestComercialForm.NAME,
		getWidgetComponents(), PKFIELD, GestComercialForm.colNames,
		GestComercialForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(GestFinancieraForm.NAME,
		getWidgetComponents(), PKFIELD, GestFinancieraForm.colNames,
		GestFinancieraForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(EvaluacionForm.NAME,
		getWidgetComponents(), PKFIELD, EvaluacionForm.colNames,
		EvaluacionForm.colAlias));
	addTableHandler(new VectorialTableHandler(CaptacionesForm.NAME,
		getWidgetComponents(), PKFIELD, CaptacionesForm.colNames,
		CaptacionesForm.colAlias));
	addTableHandler(new VectorialTableHandler(DepIntermediosForm.NAME,
		getWidgetComponents(), PKFIELD, DepIntermediosForm.colNames,
		DepIntermediosForm.colAlias));
	addTableHandler(new VectorialTableHandler(DepDistribucionForm.NAME,
		getWidgetComponents(), PKFIELD, DepDistribucionForm.colNames,
		DepDistribucionForm.colAlias));
	addTableHandler(new VectorialTableHandler(TuberiasForm.NAME,
		getWidgetComponents(), PKFIELD, TuberiasForm.colNames,
		TuberiasForm.colAlias));
	addTableHandler(new VectorialTableHandler(BombeosForm.NAME,
		getWidgetComponents(), PKFIELD, BombeosForm.colNames,
		BombeosForm.colAlias));
	addTableHandler(new VectorialEditableNNRelTableHandler(
		ComunidadesForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		ComunidadesForm.colNames, ComunidadesForm.colAlias));
	addTableHandler(new VectorialEditableNNRelTableHandler(
		FuentesForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_fuentes", FuentesForm.PKFIELD,
		FuentesForm.colNames, FuentesForm.colAlias));
	addTableHandler(new AlphanumericNotEditableNNRelTableHandler(
		AdescosForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		AdescosForm.colNames, AdescosForm.colAlias));
	addTableHandler(new AlphanumericNotEditableNNRelTableHandler(
		ImplicacionComunidadForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		ImplicacionComunidadForm.colNames,
		ImplicacionComunidadForm.colAlias));
	addTableHandler(new AlphanumericNotEditableNNRelTableHandler(
		DatosConsumoForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		DatosConsumoForm.colNames, DatosConsumoForm.colAlias));
	addTableHandler(new AlphanumericNotEditableNNRelTableHandler(
		ValoracionSistemaForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", ComunidadesForm.PKFIELD,
		ValoracionSistemaForm.colNames, ValoracionSistemaForm.colAlias));
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
	String[] tablesToReload = new String[] { JuntasAguaForm.NAME,
		CoberturaForm.NAME, GestComercialForm.NAME,
		GestFinancieraForm.NAME, EvaluacionForm.NAME,
		ValoracionSistemaForm.NAME, DatosConsumoForm.NAME,
		"r_abastecimientos_comunidades", "r_abastecimientos_fuentes" };
	for (String tableName : tablesToReload) {
	    final Table table = tocTableManager.getTableByName(tableName);
	    if (table != null) {
		table.refresh();
	    }
	}

	String[] layersToReload = new String[] { BombeosForm.NAME,
		CaptacionesForm.NAME, DepIntermediosForm.NAME,
		DepDistribucionForm.NAME, TuberiasForm.NAME };
	TOCLayerManager tocManager = new TOCLayerManager();
	for (String layerName : layersToReload) {
	    try {
		final FLyrVect l = tocManager.getLayerByName(layerName);
		if (l != null) {
		    l.reload();
		}
	    } catch (ReloadLayerException e) {
		e.printStackTrace();
	    }
	}

    }
}
