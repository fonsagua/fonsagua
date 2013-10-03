package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.exceptions.layers.ReloadLayerException;
import com.iver.cit.gvsig.exceptions.visitors.StopWriterVisitorException;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;

import es.icarto.gvsig.navtableforms.BasicAbstractForm;
import es.icarto.gvsig.navtableforms.gui.tables.handler.VectorialTableHandler;
import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.fonsagua.OpenAlternativeExtension;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.SelectComunitiesForAlternativeDialog;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.SelectElementForAlternativeDialog;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.SelectFuentesForAlternativeDialog;
import es.udc.cartolab.gvsig.fonsagua.utils.DatabaseDirectAccessQueries;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFormFactory;

@SuppressWarnings("serial")
public class AlternativasForm extends BasicAbstractForm {

    public static final String NAME = "alternativas";
    public static final String PKFIELD = "cod_alternativa";
    public static final String DEPARTFK = "cod_departamento";
    public static final String DEPART_NAME = "departamento";
    public static final String MUNICFK = "cod_municipio";
    public static final String MUNIC_NAME = "municipio";
    public static final String CANTONFK = "cod_canton";
    public static final String CANTON_NAME = "canton";

    public static final String TIPODISTRIBUCIONFIELD = "tipo_distribucion";
    public static final String POBLACIONACTUALFIELD = "pobl_actual";
    private JTable comImplicadas;
    private MyListener comImplicadasListener;
    private JTable fuentesImplicadas;
    private MyListener fuentesImplicadasListener;
    private JButton validBT;

    public AlternativasForm(FLyrVect layer) {
	super(layer);

	comImplicadas = (JTable) formBody
		.getComponentByName("comunidades_implicadas");
	int[] delColumns = { 4, 1 };
	comImplicadasListener = new MyListener(
		new SelectComunitiesForAlternativeDialog(3), delColumns);
	comImplicadas.addMouseListener(comImplicadasListener);
	comImplicadas.getTableHeader().setReorderingAllowed(false);

	fuentesImplicadas = (JTable) formBody
		.getComponentByName("fuentes_implicadas");
	fuentesImplicadasListener = new MyListener(
		new SelectFuentesForAlternativeDialog(4));
	fuentesImplicadas.addMouseListener(fuentesImplicadasListener);
	fuentesImplicadas.getTableHeader().setReorderingAllowed(false);

	validBT = (JButton) formBody.getComponentByName("valid_button");

	addHandlersForTables();
    }

    private void addHandlersForTables() {
	addTableHandler(new VectorialTableHandler(AltEmbalsesForm.NAME,
		getWidgetComponents(), PKFIELD, AltEmbalsesForm.colNames,
		AltEmbalsesForm.colAlias));

	addTableHandler(new VectorialTableHandler(AltFuentesForm.NAME,
		getWidgetComponents(), PKFIELD, AltFuentesForm.colNames,
		AltFuentesForm.colAlias));

	addTableHandler(new VectorialTableHandler(AltDepositosForm.NAME,
		getWidgetComponents(), PKFIELD, AltDepositosForm.colNames,
		AltDepositosForm.colAlias));

	addTableHandler(new VectorialTableHandler(AltTuberiasForm.NAME,
		getWidgetComponents(), PKFIELD, AltTuberiasForm.colNames,
		AltTuberiasForm.colAlias));

	addTableHandler(new VectorialTableHandler(AltBombeosForm.NAME,
		getWidgetComponents(), PKFIELD, AltBombeosForm.colNames,
		AltBombeosForm.colAlias));

	addTableHandler(new VectorialTableHandler(AltConexionesForm.NAME,
		getWidgetComponents(), PKFIELD, AltConexionesForm.colNames,
		AltConexionesForm.colAlias));
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
	    comImplicadas.getColumnModel().removeColumn(
		    comImplicadas.getColumnModel().getColumn(1));

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

    @Override
    public void deleteRecord() throws StopWriterVisitorException {
	super.deleteRecord();
	Set<String> altLayersNames = FonsaguaFormFactory.getInstance()
		.getAllAlternativasLayersNames();
	TOCLayerManager tocManager = new TOCLayerManager();
	for (String name : altLayersNames) {
	    try {
		tocManager.getLayerByName(name).reload();
	    } catch (ReloadLayerException e) {
		e.printStackTrace();
	    }
	}
    }

    private class MyListener implements MouseListener {

	private SelectElementForAlternativeDialog dialog;
	private int[] delColumns;

	public MyListener(SelectElementForAlternativeDialog dialog,
		int[] delColumns) {
	    this.dialog = dialog;
	    this.delColumns = delColumns;
	}

	public MyListener(SelectElementForAlternativeDialog dialog) {
	    this.dialog = dialog;
	    this.delColumns = new int[0];
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	    dialog.update(getPrimaryKeyValue());
	    PluginServices.getMDIManager().addCentredWindow(dialog);

	    final DefaultTableModel filteredTableModel = dialog
		    .getFilteredTableModel();
	    if (filteredTableModel != null) {
		JTable table = ((JTable) e.getSource());
		table.setModel(addRowIfEmpty(filteredTableModel));
		dialog.setAutomaticValue(layerController, getWidgetComponents());
		for (int i : delColumns) {
		    table.getColumnModel().removeColumn(
			    table.getColumnModel().getColumn(i));
		}
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
