package es.udc.cartolab.gvsig.fonsagua.croquis.listeners;

import java.awt.event.ActionListener;
import java.sql.SQLException;

import es.udc.cartolab.gvsig.fonsagua.utils.FonsaguaConstants;
import es.udc.cartolab.gvsig.users.utils.DBSession;

public abstract class BaseCroquisListener implements ActionListener {

    protected final String comunidadId;

    public BaseCroquisListener(String comunidadId) {
	this.comunidadId = comunidadId;
    }

    protected byte[] readCroquis() throws SQLException {
	DBSession session = DBSession.getCurrentSession();
	String[] columns = { FonsaguaConstants.CROQUIS_FIELDNAME };
	Object[][] values = session.getTableAsObjects(
		FonsaguaConstants.CROQUIS_TABLENAME,
		FonsaguaConstants.dataSchema, columns, "WHERE "
			+ FonsaguaConstants.CROQUIS_COMUNIDAD_FK_FIELDNAME
			+ " = '" + comunidadId + "'", new String[0], false);
	if ((values.length > 0) && (values[0].length > 0)) {
	    return (byte[]) values[0][0];
	}
	return null;
    }

}
