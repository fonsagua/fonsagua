package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;

@SuppressWarnings("serial")
public class AlternativasForm extends BasicAbstractForm {

    public static final String NAME = "alternativas";
    public static final String PKFIELD = "cod_alternativa";
    public static final String DEPARTFK = "departamento";
    public static final String MUNICFK = "municipio";
    public static final String CANTONFK = "canton";
    private JTable comImplicadas;

    public AlternativasForm(FLyrVect layer) {
	super(layer);

	comImplicadas = (JTable) formBody
		.getComponentByName("comunidades_implicadas");
	comImplicadas.addMouseListener(new MyListener());
    }

    @Override
    protected String getBasicName() {
	return NAME;
    }

    @Override
    protected String getPrimaryKeyValue() {
	return getFormController().getValue(PKFIELD);
    }

    @Override
    protected void fillSpecificValues() {
	super.fillSpecificValues();
	try {
	    comImplicadas.setModel(DatabaseDirectAccessQueries
		    .getComunidadesImplicadasTable(getPrimaryKeyValue()));
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private class MyListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {

	    SelectComunitiesForAlternativeDialog dialog = new SelectComunitiesForAlternativeDialog();
	    dialog.update(getPrimaryKeyValue());
	    PluginServices.getMDIManager().addCentredWindow(dialog);

	    final TableModel filteredTableModel = dialog
		    .getFilteredTableModel();
	    if (filteredTableModel != null) {
		comImplicadas.setModel(filteredTableModel);
	    }
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

    }
}
