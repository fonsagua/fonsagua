package es.icarto.gvsig.fonsagua.reports;

import org.junit.BeforeClass;
import org.junit.Test;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.icarto.gvsig.navtableforms.DataBaseProperties;
import es.icarto.gvsig.navtableforms.Drivers;
import es.icarto.gvsig.navtableforms.TestProperties;
import es.udc.cartolab.gvsig.users.utils.DBSessionPostGIS;

public class TestReports {

    @BeforeClass
    public static void doSetupBeforeClass() {
	Drivers.initgvSIGDrivers(TestProperties.driversPath);
	try {
	    DBSessionPostGIS.createConnection(DataBaseProperties.server,
		    DataBaseProperties.port, DataBaseProperties.dbname, null,
		    "postgres", "postgres");
	} catch (DBException e) {
	    e.printStackTrace();
	}

    }

    @Test
    public void testReport() {
	String fileName = "/tmp/fooReport.rtf";
	new CommunityRTFReport(fileName, "01010101");
    }

}
