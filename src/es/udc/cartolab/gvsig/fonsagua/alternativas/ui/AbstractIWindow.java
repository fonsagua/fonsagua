package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;


import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiFrame.MDIFrame;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;

import es.icarto.gvsig.fonsagua.reports.ui.AcceptCancelPanel;

@SuppressWarnings("serial")
public abstract class AbstractIWindow extends JPanel implements IWindow {

    private WindowInfo windowInfo;
    private String title = "";
    private int windowInfoProperties = WindowInfo.MODALDIALOG;

    public AbstractIWindow() {
	super(new MigLayout("insets 10"));
    }

    @Override
    public Object getWindowProfile() {
	return WindowInfo.DIALOG_PROFILE;
    }

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(windowInfoProperties);

	    windowInfo.setTitle(title);
	    Dimension dim = getPreferredSize();
	    // To calculate the maximum size of a form we take the size of the
	    // main frame minus a "magic number" for the menus, toolbar, state
	    // bar
	    // Take into account that in edition mode there is less available
	    // space
	    MDIFrame a = (MDIFrame) PluginServices.getMainFrame();
	    final int MENU_TOOL_STATE_BAR = 205;
	    int maxHeight = a.getHeight() - MENU_TOOL_STATE_BAR;
	    int maxWidth = a.getWidth() - 15;

	    int width, heigth = 0;
	    if (dim.getHeight() > maxHeight) {
		heigth = maxHeight;
	    } else {
		heigth = new Double(dim.getHeight()).intValue();
	    }
	    if (dim.getWidth() > maxWidth) {
		width = maxWidth;
	    } else {
		width = new Double(dim.getWidth()).intValue();
	    }

	    // getPreferredSize doesn't take into account the borders and other
	    // stuff
	    // introduced by Andami, neither scroll bars so we must increase the
	    // "preferred"
	    // dimensions
	    windowInfo.setWidth(width + 25);
	    windowInfo.setHeight(heigth + 15);
	}
	return windowInfo;
    }

    protected void setWindowTitle(String title) {
	this.title = title;
    }
    

    protected void setWindowInfoProperties(int properties) {
	this.windowInfoProperties = properties;
    }

    protected void addAcceptCancelPanel(ActionListener accept,
	    ActionListener cancel) {
	AcceptCancelPanel acceptCancelPanel = new AcceptCancelPanel(accept,
		cancel);
	add(acceptCancelPanel, "dock south");
    }
}
