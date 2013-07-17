package es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericNotEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialTableHandler;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AdescosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.DatosConsumoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ImplicacionComunidadForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ValoracionSistemaForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.FuentesForm;

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

}
