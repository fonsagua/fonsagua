package es.udc.cartolab.gvsig.fonsagua.config;

import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.values.Value;
import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.CADExtension;
import com.iver.cit.gvsig.EditionManager;
import com.iver.cit.gvsig.exceptions.visitors.StopWriterVisitorException;
import com.iver.cit.gvsig.fmap.core.DefaultFeature;
import com.iver.cit.gvsig.fmap.core.IFeature;
import com.iver.cit.gvsig.fmap.edition.IRowEdited;
import com.iver.cit.gvsig.fmap.layers.FLayer;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.ReadableVectorial;
import com.iver.cit.gvsig.layers.VectorialLayerEdited;
import com.iver.cit.gvsig.listeners.EndGeometryListener;
import com.iver.cit.gvsig.project.documents.view.gui.View;
import com.vividsolutions.jts.geom.Geometry;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.OpenAlternativeExtension;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltFuentesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.FuentesForm;
import es.udc.cartolab.gvsig.navtable.ToggleEditing;
import es.udc.cartolab.gvsig.navtable.format.ValueFactoryNT;

public class FonsaguaEndGeometryListener implements EndGeometryListener {
    
    private static final Logger logger = Logger
	    .getLogger(FonsaguaEndGeometryListener.class);

    @Override
    public void endGeometry(FLayer layer, String cadToolKey) {

	if (cadToolKey.startsWith("_epanet_cadtool_")) {
	    View vista = (View) PluginServices.getMDIManager()
		    .getActiveWindow();
	    EditionManager edMan = CADExtension.getEditionManager();
	    vista.getMapControl().getCanceldraw().setCanceled(true);

	    FLyrVect lv = (FLyrVect) layer;
	    VectorialLayerEdited lyrEd = (VectorialLayerEdited) edMan
		    .getActiveLayerEdited();
	    boolean flag = false;
	    try {
		ArrayList<IRowEdited> selectedRow = lyrEd.getSelectedRow();
		IRowEdited iRowEdited = selectedRow.get(0);
		Value[] attributes = iRowEdited.getAttributes();
		int idx = lv.getRecordset().getFieldIndexByName(
			AlternativasForm.PKFIELD);
		attributes[idx] = ValueFactoryNT.createValueByType(OpenAlternativeExtension.getCode(), Types.VARCHAR);
		iRowEdited.setAttributes(attributes);
		
		
		// El listener debería ser creado por cada herramienta, o las herramientas
		// deberían tener la opción de añadir nuevos comportamientos al listener, registrando eventos o
		// comportamientos propios
		
		if (layer.getName().equals(AltFuentesForm.NAME)) {
		    flag = processAltFuentes(vista, iRowEdited, lv);
		}
		

		lv.getRecordset().removeSelectionListener(lyrEd);
	    } catch (ReadDriverException e) {
		logger.error(e.getStackTrace(), e);
	    } catch (ParseException e) {
		logger.error(e.getStackTrace(), e);
	    }
	    try {
		new ToggleEditing().stopEditing(layer, flag);
	    } catch (StopWriterVisitorException e) {
		logger.error(e.getMessage(), e);
		try {
		    new ToggleEditing().stopEditing(layer, true);
		} catch (StopWriterVisitorException e1) {

		}
		throw new RuntimeException(e);
	    }

	    lv.removeLayerListener(edMan);

	    vista.getMapControl().setTool("zoomIn");
	    vista.hideConsole();
	    // vista.repaintMap();
	    CADExtension.clearView();
	    PluginServices.getMainFrame().enableControls();
	}
    }

    private boolean processAltFuentes(View vista, IRowEdited iRowEdited, FLyrVect lv) throws ReadDriverException, ParseException {
	FLyrVect fuentes = new TOCLayerManager(vista.getMapControl()).getLayerByName(FuentesForm.NAME);
	DefaultFeature feat = (DefaultFeature) iRowEdited.getLinkedRow();
	Geometry geom = feat.getGeometry().toJTSGeometry();
	int idxAltFuentes = lv.getRecordset().getFieldIndexByName("cod_fuente");
	ReadableVectorial altFuenteSrc = lv.getSource();
	List<String>altFuenteCodes = new ArrayList<String>();
	for (int i = 0, end = altFuenteSrc.getShapeCount(); i < end; i++) {
	    altFuenteCodes.add(altFuenteSrc.getFeature(i).getAttribute(idxAltFuentes).toString());
	}
	
	final ReadableVectorial fuentesSrc = fuentes.getSource();
	String codFuente = null;
	for (int i = 0, end = fuentesSrc.getShapeCount(); i < end; i++) {
	    IFeature fuenteFeat = fuentesSrc.getFeature(i);
	    Geometry fuenteGeom = fuenteFeat.getGeometry().toJTSGeometry();
	    if ( geom.intersects(fuenteGeom) ) {
		int idx = fuentes.getRecordset().getFieldIndexByName(FuentesForm.PKFIELD);
		codFuente = fuenteFeat.getAttribute(idx).toString();
		if (altFuenteCodes.contains(codFuente)) {
		    JOptionPane.showMessageDialog(null, "Esta poniendo una nueva fuente sobre una fuente existente y el código de la fuente existente ya se está usando. El punto no va a ser añadido");
		    return true;
		}
		break;
	    }
	}
	if (codFuente == null) {
	    
	    for (int i=0; i < 100; i++) {
		String newCodFuente = String.format("%sfu%02d", OpenAlternativeExtension.getCode(), i);
		if (!altFuenteCodes.contains(newCodFuente)) {
		    codFuente = newCodFuente;
		    break;
		}
	    }
	}
	Value[] attributes = iRowEdited.getAttributes();
	attributes[idxAltFuentes] = ValueFactoryNT.createValueByType(codFuente, Types.VARCHAR);
	iRowEdited.setAttributes(attributes);
	return false;
    }
}