package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import javax.swing.JPanel;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialEditableNNRelTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialTableHandler;
import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.AddCroquisListener;
import es.udc.cartolab.gvsig.fonsagua.croquis.listeners.ShowCroquisListener;
import es.udc.cartolab.gvsig.fonsagua.croquis.ui.CroquisButtons;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;

@SuppressWarnings("serial")
public class ComunidadesForm extends BasicAbstractForm {

    public static final String NAME = "comunidades";
    public static final String PKFIELD = "cod_comunidad";
    public static String[] colNames = { "comunidad", "cod_comunidad" };
    public static String[] colAlias = { "Comunidad", "Código" };

    private CroquisButtons croquisButtons;

    public ComunidadesForm(FLyrVect layer) {
	super(layer);
	addTableHandler(new AlphanumericTableHandler(AdescosForm.NAME,
		getWidgetComponents(), PKFIELD, AdescosForm.colNames,
		AdescosForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(EntrevistadoresForm.NAME,
		getWidgetComponents(), PKFIELD, EntrevistadoresForm.colNames,
		EntrevistadoresForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(EntrevistadosForm.NAME,
		getWidgetComponents(), PKFIELD, EntrevistadosForm.colNames,
		EntrevistadosForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(SubcuencasForm.NAME,
		getWidgetComponents(), PKFIELD, SubcuencasForm.colNames,
		SubcuencasForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(CargosPublicosForm.NAME,
		getWidgetComponents(), PKFIELD, CargosPublicosForm.colNames,
		CargosPublicosForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(OngsForm.NAME,
		getWidgetComponents(), PKFIELD, OngsForm.colNames,
		OngsForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(
		OtrasOrganizacionesForm.NAME, getWidgetComponents(), PKFIELD,
		OtrasOrganizacionesForm.colNames,
		OtrasOrganizacionesForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(TiposCultivosForm.NAME,
		getWidgetComponents(), PKFIELD, TiposCultivosForm.colNames,
		TiposCultivosForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(
		ProduccionConsumoForm.NAME, getWidgetComponents(), PKFIELD,
		ProduccionConsumoForm.colNames, ProduccionConsumoForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(GanaderiaForm.NAME,
		getWidgetComponents(), PKFIELD, GanaderiaForm.colNames,
		GanaderiaForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(CooperativasForm.NAME,
		getWidgetComponents(), PKFIELD, CooperativasForm.colNames,
		CooperativasForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(
		CapacitacionesRiesgosForm.NAME, getWidgetComponents(), PKFIELD,
		CapacitacionesRiesgosForm.colNames,
		CapacitacionesRiesgosForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(
		ImplicacionComunidadForm.NAME, getWidgetComponents(), PKFIELD,
		ImplicacionComunidadForm.colNames,
		ImplicacionComunidadForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(
		ValoracionSistemaForm.NAME, getWidgetComponents(), PKFIELD,
		ValoracionSistemaForm.colNames, ValoracionSistemaForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(DatosConsumoForm.NAME,
		getWidgetComponents(), PKFIELD, DatosConsumoForm.colNames,
		DatosConsumoForm.colAlias));
	addTableHandler(new AlphanumericTableHandler(HabitosConsumoForm.NAME,
		getWidgetComponents(), PKFIELD, HabitosConsumoForm.colNames,
		HabitosConsumoForm.colAlias));
	addTableHandler(new VectorialTableHandler(
		FuentesContaminacionForm.NAME, getWidgetComponents(), PKFIELD,
		FuentesContaminacionForm.colNames,
		FuentesContaminacionForm.colAlias));
	addTableHandler(new VectorialTableHandler(PuntosViviendasForm.NAME,
		getWidgetComponents(), PKFIELD, PuntosViviendasForm.colNames,
		PuntosViviendasForm.colAlias));
	addTableHandler(new VectorialTableHandler(CentrosEducativosForm.NAME,
		getWidgetComponents(), PKFIELD, CentrosEducativosForm.colNames,
		CentrosEducativosForm.colAlias));
	addTableHandler(new VectorialTableHandler(CentrosSaludForm.NAME,
		getWidgetComponents(), PKFIELD, CentrosSaludForm.colNames,
		CentrosSaludForm.colAlias));
	addTableHandler(new VectorialTableHandler(
		AreasPotencialesRiegoForm.NAME, getWidgetComponents(), PKFIELD,
		AreasPotencialesRiegoForm.colNames,
		AreasPotencialesRiegoForm.colAlias));
	addTableHandler(new VectorialTableHandler(OtrosServiciosForm.NAME,
		getWidgetComponents(), PKFIELD, OtrosServiciosForm.colNames,
		OtrosServiciosForm.colAlias));
	addTableHandler(new VectorialTableHandler(AmenazasForm.NAME,
		getWidgetComponents(), PKFIELD, AmenazasForm.colNames,
		AmenazasForm.colAlias));
	addTableHandler(new VectorialEditableNNRelTableHandler(
		AbastecimientosForm.NAME, getWidgetComponents(),
		FonsaguaConstants.dataSchema, PKFIELD,
		"r_abastecimientos_comunidades", AbastecimientosForm.PKFIELD,
		AbastecimientosForm.colNames, AbastecimientosForm.colAlias));

    }

    private void addCroquisButtons() {
	final String comunidadId = getFormController().getValue(PKFIELD);
	JPanel actionsToolBar = this.getActionsToolBar();
	if (croquisButtons == null) {
	    croquisButtons = new CroquisButtons(comunidadId);
	    actionsToolBar.add(croquisButtons.getAddCroquisButton());
	    actionsToolBar.add(croquisButtons.getShowCroquisButton());
	} else {
	    croquisButtons.setAddlistener(new AddCroquisListener(comunidadId));
	    croquisButtons
		    .setShowlistener(new ShowCroquisListener(comunidadId));
	}

    }

    @Override
    protected void fillSpecificValues() {
	addCroquisButtons();
	super.fillSpecificValues();
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
