package es.udc.cartolab.gvsig.fonsagua.forms.factories;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.TableFormFactory;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.AbastecimientosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.BombeosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.CaptacionesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.DepDistribucionForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.DepIntermediosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.abastecimiento.TuberiasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AmenazasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.AreasPotencialesRiegoForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CentrosEducativosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.CentrosSaludForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.OtrosServiciosForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.PuntosViviendasForm;
import es.udc.cartolab.gvsig.fonsagua.forms.fuentes.FuentesForm;

public class FonsaguaTableFormFactory implements TableFormFactory {
    
    private static FonsaguaTableFormFactory instance = null;
    
    public static FonsaguaTableFormFactory getInstance() {
	if (instance == null) {
	    instance = new FonsaguaTableFormFactory();
	}
	return instance;
    }
    
    private FonsaguaTableFormFactory() {
    }

    @Override
    public BasicAbstractForm createForm(FLyrVect layer) {
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
	    }
	}
	return null;
    }

}