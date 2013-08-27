package es.udc.cartolab.gvsig.fonsagua;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class OpenAlternativeExtension extends OpenAbstractExtension {

    private static String code;
    private static boolean validAlternative;

    @Override
    public void initialize() {
	id = "open_alternative";
	OpenAlternativeExtension.validAlternative = false;
	super.initialize();
    }

    @Override
    public void execute(String actionCommand) {
	try {
	    String[] fields = new String[2];
	    String[] orderBy = new String[1];
	    Map<String, Map<String, Map<String, List<String>>>> divsCodes = new HashMap<String, Map<String, Map<String, List<String>>>>();
	    fields[0] = AlternativasForm.PKFIELD;
	    fields[1] = AlternativasForm.CANTONFK;
	    orderBy[0] = AlternativasForm.PKFIELD;
	    String[][] alternativas = DBSession.getCurrentSession().getTable(
		    AlternativasForm.NAME, FonsaguaConstants.dataSchema,
		    fields, "", orderBy, false);
	    if (alternativas.length < 1) {
		JOptionPane.showMessageDialog(new TOCLayerManager().getView(),
			PluginServices.getText(this,
				"no_alternatives_to_open_msg"),
			PluginServices.getText(this,
				"no_alternatives_to_open_title"),
			JOptionPane.INFORMATION_MESSAGE);
		return;
	    }

	    Map<String, String> departNames = new HashMap<String, String>();
	    fields[0] = FonsaguaConstants.departamentosPK;
	    fields[1] = FonsaguaConstants.departamentosName;
	    orderBy[0] = FonsaguaConstants.departamentosPK;
	    String[][] departamentos = DBSession.getCurrentSession().getTable(
		    FonsaguaConstants.departamentosTable,
		    FonsaguaConstants.baseSchema, fields, "", orderBy, false);
	    for (String[] depart : departamentos) {
		departNames.put(depart[0], depart[1]);
	    }

	    Map<String, String> municNames = new HashMap<String, String>();
	    fields[0] = FonsaguaConstants.municipiosPK;
	    fields[1] = FonsaguaConstants.municipiosName;
	    orderBy[0] = FonsaguaConstants.municipiosPK;
	    String[][] municipios = DBSession.getCurrentSession().getTable(
		    FonsaguaConstants.municipiosTable,
		    FonsaguaConstants.baseSchema, fields, "", orderBy, false);
	    String departCode;
	    for (String[] munic : municipios) {
		if ((munic[0] != null) && (munic[0].length() >= 2)) {
		    municNames.put(munic[0], munic[1]);
		}

	    }

	    Map<String, String> cantonNames = new HashMap<String, String>();
	    fields[0] = FonsaguaConstants.cantonesPK;
	    fields[1] = FonsaguaConstants.cantonesName;
	    orderBy[0] = FonsaguaConstants.cantonesPK;
	    String[][] cantones = DBSession.getCurrentSession().getTable(
		    FonsaguaConstants.cantonesTable,
		    FonsaguaConstants.baseSchema, fields, "", orderBy, false);
	    String municCode;
	    for (String[] canton : cantones) {
		if ((canton[0] != null) && (canton[0].length() >= 4)) {
		    cantonNames.put(canton[0], canton[1]);
		}
	    }

	    for (String[] alternativa : alternativas) {
		if ((alternativa[0] != null) && (alternativa[1] != null)
			&& (alternativa[1].length() >= 4)) {
		    departCode = alternativa[1].substring(0, 2);
		    municCode = alternativa[1].substring(0, 4);
		    if (!divsCodes.containsKey(departCode)) {
			divsCodes
				.put(departCode,
					new HashMap<String, Map<String, List<String>>>());
		    }
		    if (!divsCodes.get(departCode).containsKey(municCode)) {
			divsCodes.get(departCode).put(municCode,
				new HashMap<String, List<String>>());
		    }
		    if (!divsCodes.get(departCode).get(municCode)
			    .containsKey(alternativa[1])) {
			divsCodes.get(departCode).get(municCode)
				.put(alternativa[1], new ArrayList<String>());
		    }
		    divsCodes.get(departCode).get(municCode)
			    .get(alternativa[1]).add(alternativa[0]);
		}
	    }
	    OpenAlternativeDialog dialog = new OpenAlternativeDialog(divsCodes,
		    departNames, municNames, cantonNames);
	    PluginServices.getMDIManager().addWindow(dialog);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public static void openAlternative(String code) {
	OpenAlternativeExtension.code = code;
	FonsaguaAlternative.code = code;
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
	} catch (Exception e) {
	    // TODO: catch error
	    e.printStackTrace();
	}
    }

    public static String getCode() {
	return code;
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
