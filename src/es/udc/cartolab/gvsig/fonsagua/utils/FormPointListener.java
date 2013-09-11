package es.udc.cartolab.gvsig.fonsagua.utils;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.SingletonDialogAlreadyShownException;
import com.iver.cit.gvsig.fmap.MapControl;
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

	super.point(event);

	try {
	    PluginServices.getMDIManager().setWaitCursor();
	    FLyrVect[] layers = new TOCLayerManager().getActiveLayers();
	    for (FLyrVect layer : layers) {
		int selectedPos = layer.getRecordset().getSelection()
			.nextSetBit(0);
		if (selectedPos != -1) {
		    AbstractForm dialog = FormFactory
			    .createFormRegistered(layer);
		    if ((dialog != null) && (dialog.init())) {
			if (lastOpenDialog != null) {
			    PluginServices.getMDIManager().closeWindow(
				    lastOpenDialog);
			}
			lastOpenDialog = dialog;
			dialog.setPosition(selectedPos);
			PluginServices.getMDIManager().addWindow(dialog);
		    }
		}
	    }

	} catch (ReadDriverException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (SingletonDialogAlreadyShownException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} finally {
	    PluginServices.getMDIManager().restoreCursor();
	}

    }

    @Override
    public void pointDoubleClick(PointEvent event) throws BehaviorException {
    }

}
