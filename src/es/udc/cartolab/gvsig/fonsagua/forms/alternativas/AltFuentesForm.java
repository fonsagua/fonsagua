package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;


import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.iver.cit.gvsig.exceptions.visitors.StopWriterVisitorException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;

@SuppressWarnings("serial")
public class AltFuentesForm extends BasicAbstractForm {

    private static final Logger logger = Logger.getLogger(AltFuentesForm.class);
    public static final String NAME = "alt_fuentes";
    public static String[] colNames = {
	"fuente", "tipo_fuente_alternativa", "existencia_elemento", "q_usar"
    };
    public static String[] colAlias = {
	"Nombre", "Tipo de fuente", "Estado", "Caudal a usar (l/s)"
    };
    public AltFuentesForm(FLyrVect layer) {
	super(layer);
	addCalculation(new AlturaCalculation(this));
	addCalculation(new NivelDinamicoCalculation(this));
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    public boolean saveRecord() throws StopWriterVisitorException {
	String codFuente = getFormController().getValue("cod_fuente");
	String gid = getFormController().getValue("gid");
	String codAlt = getFormController().getValue("cod_alternativa");
	boolean exists = false;
	try {
	    exists = DatabaseDirectAccessQueries.codeExistsInAltFuentes(codAlt,
		    codFuente, gid);

	} catch (SQLException e) {
	    logger.error(e.getStackTrace(), e);
	}
	if (!exists) {
	    return super.saveRecord();
	}
	JOptionPane.showMessageDialog(null,
		"Este código de fuente ya existe, debe escoger otro");
	return true;
    }
}
