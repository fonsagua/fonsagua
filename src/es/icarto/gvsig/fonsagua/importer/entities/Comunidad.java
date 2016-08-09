package es.icarto.gvsig.fonsagua.importer.entities;

import java.sql.Connection;

import es.icarto.gvsig.importer.Entity;
import es.icarto.gvsig.importer.EntityFactory;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public class Comunidad extends Entity {

    public final static String tablename = "comunidades";
    public final static String pkname = "cod_comunidad";
    public final static String descname = "comunidad";

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

    public static EntityFactory<Comunidad> f() {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	return new EntityFactory<Comunidad>(con, Comunidad.class);
    }
}
