package es.icarto.gvsig.fonsagua.importer.entities;

import java.sql.Connection;

import es.icarto.gvsig.importer.Entity;
import es.icarto.gvsig.importer.EntityFactory;
import es.udc.cartolab.gvsig.users.utils.DBSession;

// Un caserío es una comunidad pero que proviene de la capa de cartografía base
public class Caserio extends Entity {

    public final static String tablename = "caserios_comunidades";
    public final static String pkname = "cod_caseri";
    public final static String descname = "caserio";

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

    public static EntityFactory<Caserio> f() {
	Connection con = DBSession.getCurrentSession().getJavaConnection();
	return new EntityFactory<Caserio>(con, Caserio.class);
    }
}
