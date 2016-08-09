package es.icarto.gvsig.fonsagua.importer.entities;

import java.sql.Connection;

import es.icarto.gvsig.importer.Entity;
import es.icarto.gvsig.importer.EntityFactory;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class Aldea extends Entity {

    public final static String tablename = "cantones";
    public final static String pkname = "cod_canton";
    public final static String descname = "canton";

    @Override
    public String tablename() {
	return tablename;
    }

    @Override
    public String pkname() {
	return pkname;
    }

    @Override
    public String descname() {
	return descname;
    }

    public static EntityFactory<Aldea> f() {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	return new EntityFactory<Aldea>(con, Aldea.class);
    }
}
