package es.icarto.gvsig.navtableforms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    public static String driversPath;

    static {
	Properties p = new Properties();
	FileInputStream in;
	try {
	    in = new FileInputStream("config/test.properties");
	    p.load(in);
	    in.close();
	    driversPath = p.getProperty("driversPath");
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
