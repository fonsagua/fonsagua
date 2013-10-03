package es.udc.cartolab.gvsig.fonsagua.forms.comunidades;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iver.cit.gvsig.fmap.drivers.DBException;

import es.icarto.gvsig.navtableforms.DataBaseProperties;
import es.icarto.gvsig.navtableforms.Drivers;
import es.icarto.gvsig.navtableforms.TestProperties;
import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;
import es.udc.cartolab.gvsig.users.utils.DBSessionPostGIS;

public class TestComunidadesCroquis {

    private static DBSession session;

    static boolean bufferedImageEquals(BufferedImage b1, BufferedImage b2) {
	if (b1 == b2) {
	    return true;
	} // true if both are null
	if (b1 == null || b2 == null) {
	    return false;
	}
	if (b1.getWidth() != b2.getWidth()) {
	    return false;
	}
	if (b1.getHeight() != b2.getHeight()) {
	    return false;
	}
	for (int i = 0; i < b1.getWidth(); i++) {
	    for (int j = 0; j < b1.getHeight(); j++) {
		if (b1.getRGB(i, j) != b2.getRGB(i, j)) {
		    return false;
		}
	    }
	}
	return true;
    }

    @BeforeClass
    public static void doSetupCroquis() throws DBException, SQLException {
	Drivers.initgvSIGDrivers(TestProperties.driversPath);
	DBSessionPostGIS.createConnection(DataBaseProperties.server,
		DataBaseProperties.port, DataBaseProperties.dbname, null,
		DataBaseProperties.user, DataBaseProperties.userpwd);

	String[] columns = { "cod_comunidad", "cod_departamento",
		"cod_municipio", "cod_canton", "cod_caserio" };
	Object[] values = { 1, 1, 1, 1, 1 };
	session = DBSession.getCurrentSession();
	session.insertRow("fonsagua", "comunidades", columns, values);
    }

    @AfterClass
    public static void doCloseCroquis() throws DBException, SQLException {
	session.deleteRows("fonsagua", "comunidades",
		" WHERE cod_comunidad = '1'");
	session.close();
    }

    @After
    public void deleteCroquis() throws DBException, SQLException {
	session.deleteRows("fonsagua", "comunidades_croquis",
		" WHERE cod_comunidad = '1'");
    }

    @Test
    public void testingInsertAndReadCroquis() throws Exception {

	// TODO: Change by example croquis
	File image = new File("data-test/test.jpg");
	session.deleteRows("fonsagua", "comunidades_croquis",
		" WHERE cod_comunidad = '1'");

	InputStream is1 = new FileInputStream(image);
	Object[] values = { "1" };
	String[] columns = { FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME };
	session.insertWithBinaryStream(FonsaguaConstants.CROQUIS_TABLENAME,
		FonsaguaConstants.dataSchema,
		    FonsaguaConstants.CROQUIS_FIELDNAME, is1, (int) image.length(), columns,
		    values);

	InputStream is2 = session.getBinaryStream(
		FonsaguaConstants.CROQUIS_TABLENAME,
		FonsaguaConstants.dataSchema,
		    FonsaguaConstants.CROQUIS_FIELDNAME, "WHERE "
			    + FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
			    + " = '1'");

	BufferedImage image1 = ImageIO.read(new FileInputStream(image));
	BufferedImage image2 = ImageIO.read(is2);
	assertTrue(bufferedImageEquals(image1, image2));
    }

    @Test
    public void testingUpdateAndReadCroquis() throws Exception {

	// TODO: Change by example croquis
	File image = new File("data-test/test.jpg");

	InputStream is1 = new FileInputStream(image);
	Object[] values = { "1" };
	String[] columns = { FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME };
	session.insertWithBinaryStream(FonsaguaConstants.CROQUIS_TABLENAME,
		FonsaguaConstants.dataSchema,
		    FonsaguaConstants.CROQUIS_FIELDNAME, is1, (int) image.length(), columns,
		    values);
	File imageToUpdate = new File("data-test/test2.jpg");
	is1 = new FileInputStream(imageToUpdate);
	session.updateWithBinaryStream(FonsaguaConstants.CROQUIS_TABLENAME,
		FonsaguaConstants.dataSchema,
		    FonsaguaConstants.CROQUIS_FIELDNAME, is1,
		    (int) image.length(), new String[0], new Object[0],
		    "WHERE " + FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
			    + " = '1'");

	InputStream is2 = session.getBinaryStream(
		FonsaguaConstants.CROQUIS_TABLENAME,
		    FonsaguaConstants.dataSchema,
		    FonsaguaConstants.CROQUIS_FIELDNAME, "WHERE "
			    + FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
			    + " = '1'");

	BufferedImage image1 = ImageIO.read(new FileInputStream(imageToUpdate));
	BufferedImage image2 = ImageIO.read(is2);
	assertTrue(bufferedImageEquals(image1, image2));
    }

}
