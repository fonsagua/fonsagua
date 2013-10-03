package es.udc.cartolab.gvsig.fonsagua.croquis.listeners;

import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public abstract class BaseCroquisListener implements ActionListener {

    protected final String comunidadId;

    public BaseCroquisListener(String comunidadId) {
	this.comunidadId = comunidadId;
    }

    protected InputStream readCroquis() throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	return session.getBinaryStream(FonsaguaConstants.CROQUIS_TABLENAME,
		FonsaguaConstants.dataSchema,
		FonsaguaConstants.CROQUIS_FIELDNAME, "WHERE "
			+ FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
			+ " = '" + comunidadId + "'");
    }

}
