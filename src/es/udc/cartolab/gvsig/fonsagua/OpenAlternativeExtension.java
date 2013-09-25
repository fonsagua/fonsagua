package es.udc.cartolab.gvsig.fonsagua;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.iver.andami.PluginServices;
import com.iver.cit.gvsig.project.documents.view.gui.View;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.elle.utils.ELLEMap;
import es.udc.cartolab.gvsig.elle.utils.LoadLegend;
import es.udc.cartolab.gvsig.elle.utils.MapDAO;
import es.udc.cartolab.gvsig.epanet.JunctionExtension;
import es.udc.cartolab.gvsig.epanet.PipeExtension;
import es.udc.cartolab.gvsig.epanet.PumpExtension;
import es.udc.cartolab.gvsig.epanet.ReservoirExtension;
import es.udc.cartolab.gvsig.epanet.RunExtension;
import es.udc.cartolab.gvsig.epanet.SourceExtension;
import es.udc.cartolab.gvsig.epanet.TankExtension;
import es.udc.cartolab.gvsig.epanet.ValveExtension;
import es.udc.cartolab.gvsig.epanet.config.FonsaguaAlternative;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.OpenAlternativeDialog;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.fonsagua.utils.AlternativesPreferences;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaFilterFields;

public class OpenAlternativeExtension extends OpenAbstractExtension {

    private static String code;
    private static boolean validAlternative;

    @Override
    public void initialize() {
	id = "open_alternative";
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	try {
	    Map<String, String> departNames = FonsaguaFilterFields
		    .getDepartments();
	    Map<String, String> municNames = FonsaguaFilterFields
		    .getMuniccipalities();
	    Map<String, String> cantonNames = FonsaguaFilterFields
		    .getCantones();

	    String[][] alternativas = FonsaguaFilterFields.getAlternatives();
	    if (alternativas.length < 1) {
		JOptionPane.showMessageDialog(new TOCLayerManager().getView(),
			PluginServices.getText(this,
				"no_alternatives_to_open_msg"),
			PluginServices.getText(this,
				"no_alternatives_to_open_title"),
			JOptionPane.INFORMATION_MESSAGE);
		return;
	    }

	    Map<String, Map<String, Map<String, List<String>>>> divsCodes = FonsaguaFilterFields
		    .getDivCodes(alternativas);

	    OpenAlternativeDialog dialog = new OpenAlternativeDialog(divsCodes,
		    departNames, municNames, cantonNames);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public static void openAlternative(String code) {
	OpenAlternativeExtension.code = code;

	MapDAO mapDAO = MapDAO.getInstance();
	try {
	    final View view = createViewIfNeeded("Alternativas");
	    ELLEMap map = mapDAO.getMap(view,
		    FonsaguaConstants.AlternativesMap, LoadLegend.DB_LEGEND,
		    FonsaguaConstants.AlternativesMap);

	    // TODO: This strings should not be hard coded
	    final String sqlWhere = "where " + AlternativasForm.PKFIELD
		    + " = '" + code + "'";
	    map.getLayer("alt_bombeos").setWhere(sqlWhere);
	    map.getLayer("alt_conexiones").setWhere(sqlWhere);
	    map.getLayer("alt_depositos").setWhere(sqlWhere);
	    map.getLayer("alt_fuentes").setWhere(sqlWhere);
	    map.getLayer("alt_tuberias").setWhere(sqlWhere);
	    map.getLayer("alt_valvulas").setWhere(sqlWhere);
	    map.getLayer("alt_embalses").setWhere(sqlWhere);
	    map.getLayer("alternativas").setWhere(sqlWhere);
	    map.load(view.getProjection());
	    zoomToLayer(view, AlternativasForm.NAME);
	} catch (Exception e) {
	    // TODO: catch error
	    e.printStackTrace();
	}
	AlternativesPreferences.loadPreferences(code);
	FonsaguaAlternative.code = code;
	FonsaguaAlternative.f_var_hor = AlternativesPreferences.getInstance()
		.getfVarHoraria();
    }

    public static String getCode() {
	return code;
    }

    public static void setCode(String code) {
	OpenAlternativeExtension.code = code;
    }

    public static void setValidAlternative(boolean validAlternative) {
	OpenAlternativeExtension.validAlternative = validAlternative;
	JunctionExtension.setExternalEnability(validAlternative);
	PipeExtension.setExternalEnability(validAlternative);
	PumpExtension.setExternalEnability(validAlternative);
	ReservoirExtension.setExternalEnability(validAlternative);
	RunExtension.setExternalEnability(validAlternative);
	TankExtension.setExternalEnability(validAlternative);
	ValveExtension.setExternalEnability(validAlternative);

	SourceExtension.setExternalEnability(true);
    }

    public static boolean getValidAlternative() {
	return OpenAlternativeExtension.validAlternative;
    }

}
