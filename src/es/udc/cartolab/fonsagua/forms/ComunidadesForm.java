package es.udc.cartolab.fonsagua.forms;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.udc.cartolab.gvsig.fonsagua.croquis.CroquisButtons;

@SuppressWarnings("serial")
public class ComunidadesForm extends BasicAbstractForm {

    public static final String NAME = "comunidades";

    public ComunidadesForm(FLyrVect layer) {
	super(layer);
	initWindow();
    }

    private void initWindow() {
	viewInfo.setHeight(750);
	viewInfo.setWidth(550);
	viewInfo.setTitle("Comunidades");
    }

    @Override
    protected void fillSpecificValues() {
	JPanel actionsToolBar = this.getActionsToolBar();
	int comunidadId = Integer.parseInt(((JTextField) getFormBody()
		.getComponentByName("id_comunidad")).getText());
	actionsToolBar.add(new CroquisButtons(comunidadId)
		.getAddCroquisButton());
	actionsToolBar.add(new CroquisButtons(comunidadId)
		.getShowCroquisButton());
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

}
