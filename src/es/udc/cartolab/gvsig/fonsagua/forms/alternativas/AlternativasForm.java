package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.udc.cartolab.gvsig.fonsagua.OpenAlternativeExtension;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.SelectComunitiesForAlternativeDialog;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.SelectElementForAlternativeDialog;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.SelectFuentesForAlternativeDialog;
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
    private JButton validBT;

    public AlternativasForm(FLyrVect layer) {
	super(layer);

	comImplicadas = (JTable) formBody
		.getComponentByName("comunidades_implicadas");
	comImplicadasListener = new MyListener(
		new SelectComunitiesForAlternativeDialog(2));
	comImplicadas.addMouseListener(comImplicadasListener);
	comImplicadas.getTableHeader().setReorderingAllowed(false);

	fuentesImplicadas = (JTable) formBody
		.getComponentByName("fuentes_implicadas");
	fuentesImplicadasListener = new MyListener(
		new SelectFuentesForAlternativeDialog(4));
	fuentesImplicadas.addMouseListener(fuentesImplicadasListener);
	fuentesImplicadas.getTableHeader().setReorderingAllowed(false);

	validBT = (JButton) formBody.getComponentByName("valid_button");
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
	try {
	    double caudalFuentes = Double.parseDouble(layerController.getValue(
		    "caudal_fuentes").replace(",", "."));
	    double demanda = Double.parseDouble(layerController.getValue(
		    "demanda").replace(",", "."));
	    if (caudalFuentes > 0 && demanda > 0 && caudalFuentes >= demanda) {
		validBT.setText("Válida");
		validBT.setBackground(new Color(0, 255, 0));
		OpenAlternativeExtension.setValidAlternative(true);
	    } else {
		validBT.setText("Inválida");
		validBT.setBackground(new Color(255, 0, 0));
		OpenAlternativeExtension.setValidAlternative(false);
	    }

	} catch (NumberFormatException e) {
	    validBT.setText("Inválida");
	    validBT.setBackground(new Color(255, 0, 0));
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
		dialog.setAutomaticValue(layerController, getWidgetComponents());
		setChangedValues();
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
