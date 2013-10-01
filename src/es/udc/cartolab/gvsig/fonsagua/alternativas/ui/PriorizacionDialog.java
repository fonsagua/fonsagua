package es.udc.cartolab.gvsig.fonsagua.alternativas.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import com.iver.andami.PluginServices;
import com.iver.andami.ui.mdiManager.IWindow;
import com.iver.andami.ui.mdiManager.WindowInfo;
import com.jeta.forms.components.panel.FormPanel;
import com.jeta.forms.gui.common.FormException;

import es.icarto.gvsig.navtableforms.gui.tables.handler.AlphanumericUpdateTableHandler;
import es.icarto.gvsig.navtableforms.gui.tables.handler.BaseTableHandler;
import es.icarto.gvsig.navtableforms.utils.AbeilleParser;
import es.icarto.gvsig.navtableforms.utils.TOCTableManager;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltPriorizacionForm;
import es.udc.cartolab.gvsig.fonsagua.forms.comunidades.ComunidadesForm;

@SuppressWarnings("serial")
public class PriorizacionDialog extends JPanel implements ActionListener,
	IWindow {

    public static final String NAME = "priorizacion";
    public static final String COD_WIDGET = "cod_comunidad";
    public static final String NAME_WIDGET = "nombre";

    /*
     * The next properties are the ones related to the interface itself, so they
     * don't need a real explanation
     */

    private WindowInfo windowInfo = null;
    private HashMap<String, JComponent> widgets;
    private BaseTableHandler handler;
    private JButton closeButton;

    public PriorizacionDialog(String communityCode, String communityName) {
	FormPanel formBody;
	InputStream stream = getClass().getClassLoader().getResourceAsStream(
		"ui/" + NAME + ".xml");
	try {
	    formBody = new FormPanel(stream);
	    formBody.setFocusCycleRoot(true);
	    widgets = AbeilleParser.getWidgetsFromContainer(formBody);
	    ((JTextField) widgets.get(COD_WIDGET)).setText(communityCode);
	    ((JTextField) widgets.get(NAME_WIDGET)).setText(communityName);
	    // We make sure the table is closed, so it's reloaded
	    new TOCTableManager().closeTableByName(AltPriorizacionForm.NAME);
	    handler = new AlphanumericUpdateTableHandler(
		    AltPriorizacionForm.NAME,
		    widgets, ComunidadesForm.PKFIELD,
		    AltPriorizacionForm.colNames, AltPriorizacionForm.colAlias);
	    JScrollPane scrollPane = new JScrollPane(formBody);
	    handler.fillValues(communityCode);
	    handler.reload();
	    MigLayout thisLayout = new MigLayout("inset 0, align center",
		    "[grow]", "[][grow][]");
	    this.setLayout(thisLayout);
	    this.add(scrollPane, "shrink, growx, growy, wrap");
	    JPanel buttons = new JPanel(new FlowLayout(FlowLayout.TRAILING));
	    closeButton = new JButton(PluginServices.getText(this, "close"));
	    closeButton.setPreferredSize(new Dimension(80, 30));
	    buttons.add(closeButton);
	    this.add(buttons, "shrink, growx, h 40!, wrap");
	    setListeners();
	    super.repaint();
	    super.setVisible(true);
	} catch (FormException e) {
	    e.printStackTrace();
	}

    }

    private void setListeners() {
	closeButton.addActionListener(this);
    }

    @Override
    public WindowInfo getWindowInfo() {
	if (windowInfo == null) {
	    windowInfo = new WindowInfo(WindowInfo.PALETTE
		    | WindowInfo.RESIZABLE);
	    windowInfo.setTitle(PluginServices.getText(this, "priorizacion"));
	    windowInfo.setWidth(750);
	    windowInfo.setHeight(400);
	}
	return windowInfo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(closeButton)) {
	    PluginServices.getMDIManager().closeWindow(this);
	}

    }

    @Override
    public Object getWindowProfile() {
	return null;
    }

}
