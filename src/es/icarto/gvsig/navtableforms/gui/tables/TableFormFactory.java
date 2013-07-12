package es.icarto.gvsig.navtableforms.gui.tables;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.AbstractForm;

public interface TableFormFactory {
    
    public abstract AbstractForm createForm(FLyrVect layer);

    public abstract AbstractForm createSingletonForm(FLyrVect layer);

    public abstract boolean hasMainForm(String layerName);

    public abstract boolean allLayersLoaded();

    public abstract void checkLayerLoaded(String layerName);
    
}
