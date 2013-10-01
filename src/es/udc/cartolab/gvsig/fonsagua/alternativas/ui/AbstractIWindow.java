package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiFrame.MDIFrame;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;

@SuppressWarnings("serial")
public abstract class AbstractIWindow extends JPanel implements IWindow {

    private WindowInfo windowInfo;
    private String title = "";

    public AbstractIWindow(LayoutManager layout) {
	super(layout);
    }

    @Override
    public Object getWindowProfile() {
	return WindowInfo.DIALOG_PROFILE;
    }

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.MODALDIALOG);

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
}
