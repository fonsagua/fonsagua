package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    private MyListener comImplicadasListener;
    private JTable fuentesImplicadas;
    private MyListener fuentesImplicadasListener;

    public AlternativasForm(FLyrVect layer) {
	super(layer);

	comImplicadas = (JTable) formBody
		.getComponentByName("comunidades_implicadas");
	comImplicadasListener = new MyListener(
		new SelectComunitiesForAlternativeDialog());
	comImplicadas.addMouseListener(comImplicadasListener);

	fuentesImplicadas = (JTable) formBody
		.getComponentByName("fuentes_implicadas");
	fuentesImplicadasListener = new MyListener(
		new SelectFuentesForAlternativeDialog());
	fuentesImplicadas.addMouseListener(fuentesImplicadasListener);
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

	    comImplicadas.setModel(addRowIfEmpty(DatabaseDirectAccessQueries
		    .getComunidadesImplicadasTable(getPrimaryKeyValue())));

	    fuentesImplicadas
		    .setModel(addRowIfEmpty(DatabaseDirectAccessQueries
			    .getFuentesImplicadasTable(getPrimaryKeyValue())));
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    private DefaultTableModel addRowIfEmpty(DefaultTableModel tableModel) {
	if (tableModel.getRowCount() == 0) {
	    tableModel.addRow(new Vector<Object>());
	}
	return tableModel;

    }

    @Override
    protected void removeListeners() {
	super.removeListeners();
	comImplicadas.removeMouseListener(comImplicadasListener);
	fuentesImplicadas.removeMouseListener(fuentesImplicadasListener);
    }

    private class MyListener implements MouseListener {

	private SelectElementForAlternativeDialog dialog;

	public MyListener(SelectElementForAlternativeDialog dialog) {
	    this.dialog = dialog;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	    dialog.update(getPrimaryKeyValue());
	    PluginServices.getMDIManager().addCentredWindow(dialog);

	    final DefaultTableModel filteredTableModel = dialog
		    .getFilteredTableModel();
	    if (filteredTableModel != null) {
		((JTable) e.getSource())
			.setModel(addRowIfEmpty(filteredTableModel));
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
