package es.udc.cartolab.gvsig.fonsagua.utils;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.Point2D;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.cit.gvsig.exceptions.visitors.VisitorException;
import com.iver.cit.gvsig.fmap.MapControl;
import com.iver.cit.gvsig.fmap.layers.FBitSet;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.tools.BehaviorException;
import com.iver.cit.gvsig.fmap.tools.PointSelectionListener;
import com.iver.cit.gvsig.fmap.tools.Events.PointEvent;

import es.icarto.gvsig.navtableforms.AbstractForm;
import es.icarto.gvsig.navtableforms.utils.FormFactory;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;

public class FormPointListener extends PointSelectionListener {

    public FormPointListener(MapControl mc) {
	super(mc);
    }

    /**
     * The image to display when the cursor is active.
     */
    private final Image img = PluginServices.getIconTheme()
	    .get("cursor-query-information").getImage();

    /**
     * The cursor used to work with this tool listener.
     * 
     * @see #getCursor()
     */
    private Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(img,
	    new Point(16, 16), "");

    private IWindow lastOpenDialog;

    @Override
    public Cursor getCursor() {
	return cur;
    }

    @Override
    public void point(PointEvent event) throws BehaviorException {
	try {
	    PluginServices.getMDIManager().setWaitCursor();
	    FLyrVect[] actives = new TOCLayerManager().getActiveLayers();
	    for (FLyrVect layer : actives) {
		if (AvailableForm.forLayer(layer)) {
		    Point2D p = event.getPoint();
		    if (openForm(layer, p)) {
			break;
		    }
		}
	    }
	} catch (ReadDriverException e) {
	    throw new BehaviorException("No se pudo hacer la selección");
	} catch (VisitorException e) {
	    throw new BehaviorException("No se pudo hacer la selección");
	} finally {
	    PluginServices.getMDIManager().restoreCursor();
	}

    }

    /**
     * returns true if a form has been opened
     */
    private boolean openForm(FLyrVect lyrVect, Point2D p)
	    throws ReadDriverException, VisitorException {

	// Tolerancia de 3 pixels
	final double tol = mapCtrl.getViewPort().toMapDistance(3);
	final Point2D mapPoint = mapCtrl.getViewPort().toMapPoint(
		(int) p.getX(), (int) p.getY());
	final FBitSet newBitSet = lyrVect.queryByPoint(mapPoint, tol);
	lyrVect.getRecordset().setSelection(newBitSet);

	final int selectedPos = lyrVect.getRecordset().getSelection()
		.nextSetBit(0);
	if (selectedPos != -1) {
	    final AbstractForm dialog = FormFactory
		    .createFormRegistered(lyrVect);
	    if ((dialog != null) && (dialog.init())) {
		if (lastOpenDialog != null) {
		    PluginServices.getMDIManager().closeWindow(lastOpenDialog);
		}
		lastOpenDialog = dialog;
		dialog.setPosition(selectedPos);
		PluginServices.getMDIManager().addWindow(dialog);
		return true;
	    }
	}
	return false;
    }

    @Override
    public void pointDoubleClick(PointEvent event) throws BehaviorException {
    }

}
