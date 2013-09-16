package es.udc.cartolab.gvsig.fonsagua.utils;

import java.util.HashSet;
import java.util.Set;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.utils.DBConnectionBaseFormFactory;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.BombeosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.CaptacionesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.CoberturaForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.DepDistribucionForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.DepIntermediosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.EvaluacionForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.GestComercialForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.GestFinancieraForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.JuntasAguaForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.SingletonAbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.TuberiasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltBombeosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltConexionesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltDepositosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltEmbalsesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltFuentesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltTuberiasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltValvulasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.PreferenciasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.PresupuestoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AdescosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AmenazasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AreasPotencialesRiegoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CapacitacionesRiesgosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CargosPublicosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CentrosEducativosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CentrosSaludForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CooperativasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.DatosConsumoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.EntrevistadoresForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.EntrevistadosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.FuentesContaminacionForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.GanaderiaForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.HabitosConsumoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ImplicacionComunidadForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.OngsForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.OtrasOrganizacionesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.OtrosServiciosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ProduccionConsumoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.PuntosViviendasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.SingletonComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.SubcuencasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.TiposCultivosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ValoracionSistemaForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.AforosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.AnaliticasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.FuentesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.SingletonFuentesForm;

public class FonsaguaFormFactory extends DBConnectionBaseFormFactory {

    private static FonsaguaFormFactory instance = null;

    private static final Set<String> mainFormNames = new HashSet<String>();
    private static final Set<String> alternativasFormNames = new HashSet<String>();

    static {
	mainFormNames.add(ComunidadesForm.NAME);
	mainFormNames.add(AmenazasForm.NAME);
	mainFormNames.add(PuntosViviendasForm.NAME);
	mainFormNames.add(AreasPotencialesRiegoForm.NAME);
	mainFormNames.add(CentrosSaludForm.NAME);
	mainFormNames.add(OtrosServiciosForm.NAME);
	mainFormNames.add(CentrosEducativosForm.NAME);
	mainFormNames.add(AbastecimientosForm.NAME);
	mainFormNames.add(BombeosForm.NAME);
	mainFormNames.add(CaptacionesForm.NAME);
	mainFormNames.add(DepIntermediosForm.NAME);
	mainFormNames.add(DepDistribucionForm.NAME);
	mainFormNames.add(TuberiasForm.NAME);
	mainFormNames.add(FuentesForm.NAME);
	mainFormNames.add(FuentesContaminacionForm.NAME);
	// System elements in design mode
	alternativasFormNames.add(AltBombeosForm.NAME);
	alternativasFormNames.add(AltConexionesForm.NAME);
	alternativasFormNames.add(AltDepositosForm.NAME);
	alternativasFormNames.add(AltFuentesForm.NAME);
	alternativasFormNames.add(AltTuberiasForm.NAME);
	alternativasFormNames.add(AltValvulasForm.NAME);
	alternativasFormNames.add(AltEmbalsesForm.NAME);
	mainFormNames.addAll(alternativasFormNames);

	instance = new FonsaguaFormFactory();
    }

    public static void registerFormFactory() {
	FormFactory.registerFormFactory(instance);
    }

    public static FonsaguaFormFactory getInstance() {
	return instance;
    }

    private FonsaguaFormFactory() {
    }

    @Override
    public AbstractForm createForm(FLyrVect layer) {
	if (layer != null) {
	    if (layer.getName().equals(ComunidadesForm.NAME)) {
		return new ComunidadesForm(layer);
	    } else if (layer.getName().equals(AmenazasForm.NAME)) {
		return new AmenazasForm(layer);
	    } else if (layer.getName().equals(PuntosViviendasForm.NAME)) {
		return new PuntosViviendasForm(layer);
	    } else if (layer.getName().equals(AreasPotencialesRiegoForm.NAME)) {
		return new AreasPotencialesRiegoForm(layer);
	    } else if (layer.getName().equals(CentrosSaludForm.NAME)) {
		return new CentrosSaludForm(layer);
	    } else if (layer.getName().equals(OtrosServiciosForm.NAME)) {
		return new OtrosServiciosForm(layer);
	    } else if (layer.getName().equals(CentrosEducativosForm.NAME)) {
		return new CentrosEducativosForm(layer);
	    } else if (layer.getName().equals(AbastecimientosForm.NAME)) {
		return new AbastecimientosForm(layer);
	    } else if (layer.getName().equals(BombeosForm.NAME)) {
		return new BombeosForm(layer);
	    } else if (layer.getName().equals(CaptacionesForm.NAME)) {
		return new CaptacionesForm(layer);
	    } else if (layer.getName().equals(DepIntermediosForm.NAME)) {
		return new DepIntermediosForm(layer);
	    } else if (layer.getName().equals(DepDistribucionForm.NAME)) {
		return new DepDistribucionForm(layer);
	    } else if (layer.getName().equals(TuberiasForm.NAME)) {
		return new TuberiasForm(layer);
	    } else if (layer.getName().equals(FuentesForm.NAME)) {
		return new FuentesForm(layer);
	    } else if (layer.getName().equals(FuentesContaminacionForm.NAME)) {
		return new FuentesContaminacionForm(layer);
	    } else if (layer.getName().equals(AltBombeosForm.NAME)) {
		return new AltBombeosForm(layer);
	    } else if (layer.getName().equals(AltConexionesForm.NAME)) {
		return new AltConexionesForm(layer);
	    } else if (layer.getName().equals(AltDepositosForm.NAME)) {
		return new AltDepositosForm(layer);
	    } else if (layer.getName().equals(AltFuentesForm.NAME)) {
		return new AltFuentesForm(layer);
	    } else if (layer.getName().equals(AltTuberiasForm.NAME)) {
		return new AltTuberiasForm(layer);
	    } else if (layer.getName().equals(AltValvulasForm.NAME)) {
		return new AltValvulasForm(layer);
	    } else if (layer.getName().equals(AltEmbalsesForm.NAME)) {
		return new AltEmbalsesForm(layer);
	    }

	}
	return null;
    }

    @Override
    public AbstractSubForm createSubForm(String tableName) {
	if (tableName != null) {
	    if (tableName.equals(CoberturaForm.NAME)) {
		return new CoberturaForm();
	    } else if (tableName.equals(EvaluacionForm.NAME)) {
		return new EvaluacionForm();
	    } else if (tableName.equals(GestComercialForm.NAME)) {
		return new GestComercialForm();
	    } else if (tableName.equals(GestFinancieraForm.NAME)) {
		return new GestFinancieraForm();
	    } else if (tableName.equals(JuntasAguaForm.NAME)) {
		return new JuntasAguaForm();
	    } else if (tableName.equals(AdescosForm.NAME)) {
		return new AdescosForm();
	    } else if (tableName.equals(CapacitacionesRiesgosForm.NAME)) {
		return new CapacitacionesRiesgosForm();
	    } else if (tableName.equals(CargosPublicosForm.NAME)) {
		return new CargosPublicosForm();
	    } else if (tableName.equals(CooperativasForm.NAME)) {
		return new CooperativasForm();
	    } else if (tableName.equals(DatosConsumoForm.NAME)) {
		return new DatosConsumoForm();
	    } else if (tableName.equals(EntrevistadoresForm.NAME)) {
		return new EntrevistadoresForm();
	    } else if (tableName.equals(EntrevistadosForm.NAME)) {
		return new EntrevistadosForm();
	    } else if (tableName.equals(GanaderiaForm.NAME)) {
		return new GanaderiaForm();
	    } else if (tableName.equals(HabitosConsumoForm.NAME)) {
		return new HabitosConsumoForm();
	    } else if (tableName.equals(ImplicacionComunidadForm.NAME)) {
		return new ImplicacionComunidadForm();
	    } else if (tableName.equals(OngsForm.NAME)) {
		return new OngsForm();
	    } else if (tableName.equals(OtrasOrganizacionesForm.NAME)) {
		return new OtrasOrganizacionesForm();
	    } else if (tableName.equals(ProduccionConsumoForm.NAME)) {
		return new ProduccionConsumoForm();
	    } else if (tableName.equals(SubcuencasForm.NAME)) {
		return new SubcuencasForm();
	    } else if (tableName.equals(TiposCultivosForm.NAME)) {
		return new TiposCultivosForm();
	    } else if (tableName.equals(ValoracionSistemaForm.NAME)) {
		return new ValoracionSistemaForm();
	    } else if (tableName.equals(AforosForm.NAME)) {
		return new AforosForm();
	    } else if (tableName.equals(AnaliticasForm.NAME)) {
		return new AnaliticasForm();
	    } else if (tableName.equals(PreferenciasForm.NAME)) {
		return new PreferenciasForm();
	    } else if (tableName.equals(PresupuestoForm.NAME)) {
		return new PresupuestoForm();
	    }
	}
	return null;
    }

    @Override
    public AbstractForm createSingletonForm(FLyrVect layer) {
	if (layer != null) {
	    if (layer.getName().equals(ComunidadesForm.NAME)) {
		return new SingletonComunidadesForm(layer);
	    } else if (layer.getName().equals(FuentesForm.NAME)) {
		return new SingletonFuentesForm(layer);
	    } else if (layer.getName().equals(AbastecimientosForm.NAME)) {
		return new SingletonAbastecimientosForm(layer);
	    }
	}
	return null;
    }

    @Override
    public AbstractForm createForm(String layerName) {
	FLyrVect layer = new TOCLayerManager().getLayerByName(layerName);
	return createForm(layer);
    }

    @Override
    public AbstractForm createSingletonForm(String layerName) {
	FLyrVect layer = new TOCLayerManager().getLayerByName(layerName);
	return createSingletonForm(layer);
    }

    @Override
    public boolean hasMainForm(String layerName) {
	return mainFormNames.contains(layerName);
    }

    @Override
    public boolean allLayersLoaded() {
	for (String layer : mainFormNames) {
	    if (new TOCLayerManager().getLayerByName(layer) == null) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public void loadLayer(String layerName) {
	loadLayer(layerName, FonsaguaConstants.dataSchema);

    }

    @Override
    public void loadTable(String tableName) {
	loadTable(tableName, FonsaguaConstants.dataSchema);

    }

    public boolean allAlternativasLayersLoaded() {
	for (String layer : alternativasFormNames) {
	    if (new TOCLayerManager().getLayerByName(layer) == null) {
		return false;
	    }
	}
	return true;
    }

    public Set<String> getAllAlternativasLayersNames() {
	return alternativasFormNames;
    }

}
