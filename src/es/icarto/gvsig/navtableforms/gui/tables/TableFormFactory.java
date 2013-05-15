package es.icarto.gvsig.navtableforms.gui.tables;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;

public interface TableFormFactory {
    
    public abstract BasicAbstractForm createForm(FLyrVect layer);

    public abstract BasicAbstractForm createSingletonForm(FLyrVect layer);

    public abstract boolean hasMainForm(String layerName);

    public abstract boolean allLayersLoaded();
    
}
