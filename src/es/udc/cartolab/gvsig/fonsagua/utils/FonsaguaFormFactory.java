package es.udc.cartolab.gvsig.fonsagua.utils;

import java.beans.PropertyVetoException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JInternalFrame;

import com.hardcode.driverManager.DriverLoadException;
import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.data.DataSource;
import com.hardcode.gdbms.engine.data.DataSourceFactory;
import com.hardcode.gdbms.engine.data.NoSuchTableException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.ProjectExtension;
import com.iver.cit.gvsig.fmap.edition.EditableAdapter;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.LayerFactory;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;
import com.iver.cit.gvsig.project.ProjectFactory;
import com.iver.cit.gvsig.project.documents.table.ProjectTable;
import com.iver.cit.gvsig.project.documents.table.gui.Table;
import com.iver.cit.gvsig.project.documents.view.gui.BaseView;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.AbstractSubForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.icarto.gvsig.navtableforms.utils.TOCTableManager;
import es.udc.cartolab.gvsig.fonsagua.FonsaguaConstants;
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
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class FonsaguaFormFactory extends FormFactory {

    private static FonsaguaFormFactory instance = null;

    private static final Set<String> mainFormNames = new HashSet<String>();

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
	    }
	}
	return null;
    }

    @Override
    public AbstractSubForm createSubForm(String tableName) {
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
    public void checkLayerLoaded(String layerName) {
	// We check whether the secondary layer is loaded
	if (new TOCLayerManager().getLayerByName(layerName) == null) {
	    // If it isn't, we retrieve the current view,
	    // load the layer and add it
	    IWindow[] windows = PluginServices.getMDIManager()
		    .getOrderedWindows();
	    BaseView view = null;
	    for (IWindow w : windows) {
		if (w instanceof BaseView) {
		    view = (BaseView) w;
		    break;
		}
	    }
	    try {
		view.getMapControl()
			.getMapContext()
			.getLayers()
			.addLayer(
				DBSession.getCurrentSession().getLayer(
					layerName, layerName,
					FonsaguaConstants.dataSchema, null,
					view.getProjection()));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @Override
    public void checkTableLoaded(String tableName) {
	try {
	    TOCTableManager toc = new TOCTableManager();
	    if (toc.getTableByName(tableName) == null) {
		loadTable(FonsaguaConstants.dataSchema,
			tableName);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void loadTable(String schema, String tableName)
	    throws DriverLoadException, NoSuchTableException,
	    ReadDriverException, PropertyVetoException {

	IWindow[] ws = PluginServices.getMDIManager().getAllWindows();
	IWindow baseView = null;
	for (IWindow w : ws) {
	    if (w instanceof BaseView) {
		baseView = w;
	    }
	}
	DBSession session = DBSession.getCurrentSession();

	String completeTableName = schema + "." + tableName;

	LayerFactory.getDataSourceFactory().addDBDataSourceByTable(tableName,
		session.getServer(), session.getPort(), session.getUserName(),
		session.getPassword(), session.getDatabase(),
		completeTableName, "PostgreSQL Alphanumeric");

	DataSource dataSource;
	dataSource = LayerFactory.getDataSourceFactory()
		.createRandomDataSource(tableName,
			DataSourceFactory.AUTOMATIC_OPENING);
	SelectableDataSource sds = new SelectableDataSource(dataSource);
	EditableAdapter auxea = new EditableAdapter();

	auxea.setOriginalDataSource(sds);

	ProjectTable projectTable = ProjectFactory
		.createTable(tableName, auxea);
	Table t = new Table();
	t.setModel(projectTable);

	PluginServices.getMDIManager().addWindow(t);
	JInternalFrame frame = (JInternalFrame) t.getRootPane().getParent();

	ProjectExtension ext = (ProjectExtension) PluginServices
		.getExtension(ProjectExtension.class);
	ext.getProject().addDocument(projectTable);
	frame.toBack();
	frame.setSelected(false);
	JInternalFrame frameBaseView = (JInternalFrame) ((BaseView) baseView)
		.getRootPane().getParent();
	frameBaseView.setSelected(true);
    }

}
