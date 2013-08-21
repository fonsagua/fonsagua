package es.udc.cartolab.gvsig.fonsagua.utils;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.icarto.gvsig.navtableforms.DataBaseProperties;
import es.icarto.gvsig.navtableforms.Drivers;
import es.icarto.gvsig.navtableforms.TestProperties;
import es.udc.cartolab.gvsig.fonsagua.alternativas.ui.SelectComunitiesForAlternativeDialog;
import es.udc.cartolab.gvsig.users.utils.DBSessionPostGIS;

public class ComunidadesImplicadas {

    public static void main(String[] args) {

	setUp();

	JFrame ventana = new JFrame("Contenido base de datos");
	SelectComunitiesForAlternativeDialog selectComunitiesForAlternativeDialog = new SelectComunitiesForAlternativeDialog(
		2);

	ventana.getContentPane().add(selectComunitiesForAlternativeDialog);
	ventana.pack();
	ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	ventana.setVisible(true);
	selectComunitiesForAlternativeDialog.update("aaaaa");

    }

    private static void setUp() {
	Drivers.initgvSIGDrivers(TestProperties.driversPath);
	try {
	    DBSessionPostGIS.createConnection(DataBaseProperties.server,
		    DataBaseProperties.port, DataBaseProperties.dbname, null,
		    DataBaseProperties.user, DataBaseProperties.userpwd);
	} catch (DBException e) {
	    e.printStackTrace();
	}
    }

}
