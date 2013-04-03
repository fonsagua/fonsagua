package es.udc.cartolab.gvsig.fonsagua;

import com.iver.andami.PluginServices;
import com.iver.andami.plugins.Extension;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.fmap.layers.FLayers;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableFormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AmenazasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AreasPotencialesRiegoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CentrosEducativosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CentrosSaludForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.OtrosServiciosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.PuntosViviendasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.factories.FonsaguaTableFormFactory;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.FuentesForm;

public class FormsExtension extends Extension {

    private FLyrVect layer;
    private final TableFormFactory factory = FonsaguaTableFormFactory
	    .getInstance();

    @Override
    public void execute(String actionCommand) {

	BasicAbstractForm dialog = null;
	dialog = factory.createForm(layer);

	if ((dialog != null) && (dialog.init())) {
	    PluginServices.getMDIManager().addWindow(dialog);
	}
    }

    protected void registerIcons() {
	PluginServices.getIconTheme().registerDefault(
		"example1-ntforms",
		this.getClass().getClassLoader()
			.getResource("images/example1.png"));
    }

    @Override
    public void initialize() {
	registerIcons();
    }

    @Override
    public boolean isEnabled() {
	if (isExampleDataSetLoaded()) {
	    return true;
	}
	return false;
    }

    private boolean isExampleDataSetLoaded() {
	IWindow iWindow = PluginServices.getMDIManager().getActiveWindow();
	if (iWindow instanceof View) {
	    View view = (View) iWindow;
	    FLayers layers = view.getMapControl().getMapContext().getLayers();
	    if (layers != null) {
		if (layers.getActives().length > 0) {
		    TOCLayerManager toc = new TOCLayerManager();
		    layer = toc.getActiveLayer();
		    String layerName = layer.getName();
		    if (layerName.equals(ComunidadesForm.NAME)
			    || layerName.equals(PuntosViviendasForm.NAME)
			    || layerName.equals(AreasPotencialesRiegoForm.NAME)
			    || layerName.equals(AmenazasForm.NAME)
			    || layerName.equals(CentrosSaludForm.NAME)
			    || layerName.equals(OtrosServiciosForm.NAME)
			    || layerName.equals(CentrosEducativosForm.NAME)
			    || layerName.equals(FuentesForm.NAME)
			    || layerName.equals(AbastecimientosForm.NAME)) {

			return true;
		    }

		}
	    }

	}

	return false;

    }

    @Override
    public boolean isVisible() {
	return true;
    }

}
