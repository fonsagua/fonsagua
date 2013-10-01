package es.udc.cartolab.gvsig.fonsagua.utils;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.icarto.gvsig.navtableforms.DataBaseProperties;
import es.icarto.gvsig.navtableforms.Drivers;
import es.icarto.gvsig.navtableforms.TestProperties;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;
import es.udc.cartolab.gvsig.users.utils.DBSession;
import es.udc.cartolab.gvsig.users.utils.DBSessionPostGIS;

public class DatabaseDirectAccesQueriesTest {

    @BeforeClass
    public static void doSetupBeforeClass() {
	Drivers.initgvSIGDrivers(TestProperties.driversPath);
	try {
	    DBSessionPostGIS.createConnection(DataBaseProperties.server,
		    DataBaseProperties.port, DataBaseProperties.dbname, null,
		    DataBaseProperties.user, DataBaseProperties.userpwd);
	} catch (DBException e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void insertDefaultPreferences() throws SQLException {

	final String codAltValue = "TEST-INSERT-DEFAULT-PREFERENCES";

	DBSession.getCurrentSession().insertRow(
		FonsaguaConstants.dataSchema,
		AlternativasForm.NAME,
		new String[] { "cod_alternativa", "departamento", "municipio",
			"canton" },
		new String[] { codAltValue, "dep", "mun", "canton" });

	final String whereClause = "where cod_alternativa='" + codAltValue
		+ "'";
	String[][] table = DBSession.getCurrentSession().getTable(
		AlternativesPreferences.ALTERNATIVAS_PREFERENCES_TABLE,
		FonsaguaConstants.dataSchema,
		new String[] { "tasa_crecimiento", "ano_horiz_sistema" },
		whereClause, null, true);
	final double tasaCrecimiento = 2;
	final int anoHorizSistema = 20;
	assertEquals(tasaCrecimiento, Double.parseDouble(table[0][0]), 0.01);
	assertEquals(anoHorizSistema, Integer.parseInt(table[0][1]));

	DBSession.getCurrentSession().deleteRows(FonsaguaConstants.dataSchema,
		AlternativesPreferences.ALTERNATIVAS_PREFERENCES_TABLE,
		whereClause);
	DBSession.getCurrentSession().deleteRows(FonsaguaConstants.dataSchema,
		AlternativasForm.NAME, whereClause);
    }
}
