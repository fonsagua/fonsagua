package es.udc.cartolab.fonsagua.forms;

import com.iver.cit.gvsig.fmap.layers.FLyrVect;

@SuppressWarnings("serial")
public class CentrosEducativosForms extends BasicAbstractForm {

    public static final String NAME = "centros_educativos";

    public CentrosEducativosForms(FLyrVect layer) {
	super(layer);
	initWindow();
	// TODO Auto-generated constructor stub
    }

    private void initWindow() {
	viewInfo.setHeight(550);
	viewInfo.setWidth(350);
	viewInfo.setTitle("Centros Educativos");
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected void fillSpecificValues() {
    }

}
