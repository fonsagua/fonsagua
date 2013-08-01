package es.icarto.gvsig.navtableforms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseProperties {

    public static String server;
    public static int port;
    public static String superuser;
    public static String user;
    public static String userpwd;
    public static String template;
    public static String dbname;

    static {
	Properties p = new Properties();
	FileInputStream in;
	try {
	    in = new FileInputStream("db/db_config_devel");
	    p.load(in);
	    in.close();
	    server = p.getProperty("server").replace("\"", "");
	    port = Integer.parseInt(p.getProperty("port").replace("\"", ""));
	    superuser = p.getProperty("superuser").replace("\"", "");
	    user = p.getProperty("user").replace("\"", "");
	    userpwd = p.getProperty("userpwd").replace("\"", "");
	    template = p.getProperty("template").replace("\"", "");
	    dbname = p.getProperty("dbname").replace("\"", "");

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
