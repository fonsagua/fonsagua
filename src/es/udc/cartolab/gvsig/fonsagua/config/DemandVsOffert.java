package es.udc.cartolab.gvsig.fonsagua.config;

import java.util.Map;

import com.hardcode.gdbms.driver.exceptions.ReadDriverException;
import com.hardcode.gdbms.engine.values.NumericValue;
import com.hardcode.gdbms.engine.values.Value;
import com.iver.cit.gvsig.fmap.layers.FLyrVect;
import com.iver.cit.gvsig.fmap.layers.SelectableDataSource;

import es.icarto.gvsig.navtableforms.utils.TOCLayerManager;
import es.udc.cartolab.gvsig.epanet.exceptions.ExternalError;
import es.udc.cartolab.gvsig.epanet.structures.JunctionWrapper;
import es.udc.cartolab.gvsig.epanet.structures.NodeWrapper;
import es.udc.cartolab.gvsig.epanet.structures.ReservoirWrapper;
import es.udc.cartolab.gvsig.epanet.structures.validations.NodesChecker;
import es.udc.cartolab.gvsig.epanet.structures.validations.Warning;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AltConexionesForm;
import es.udc.cartolab.gvsig.fonsagua.forms.alternativas.AlternativasForm;

public class DemandVsOffert implements NodesChecker {

    private int habConecIdx = -1;
    private int qExtraIdx = -1;
    private int demandaIdx = -1;
    private int poblActualIdx = -1;
    private double demanda;
    private double demEcon;
    private double demCentros;
    private double poblActual;
    private String msg;;

    @Override
    public Warning check(Map<String, NodeWrapper> nodes) {

	init();
	msg = "";

	double offert = 0;
	double habConectados = 0;
	double qExtra = 0;

	for (NodeWrapper node : nodes.values()) {

	    final double nodeDemand = node.getDemand();
	    if (node instanceof ReservoirWrapper) {
		offert += Math.abs(nodeDemand);
	    } else if (node instanceof JunctionWrapper) {
		if (nodeDemand < 0) {
		    // TODO: Not a really good form to check if the node is a
		    // Source
		    offert += Math.abs(nodeDemand);
		} else {
		    Value[] row = node.getFeature().getAttributes();
		    habConectados += value2double(row, habConecIdx);
		    qExtra += value2double(row, qExtraIdx);

		}
	    }
	}

	if (Double.compare(qExtra, demEcon + demCentros) != 0) {
	    msg += "Aviso: Las demandas extras (económica más centros) del planteamiento es distinta a la demanda extra en las conexiones\n";
	}

	if (poblActual != habConectados) {
	    msg += "Aviso: La población introducida en el planteamiento es distinta a los habitantes conectados introducidos en las conexiones\n";
	}

	if (demanda > offert) {
	    msg += "Aviso de cálculo hidráulico: La demanda de agua del sistema supera el caudal de entrada\n";
	}

	if (msg.length() > 0) {
	    return new Warning(msg);
	}
	return null;
    }

    private void init() {

	final TOCLayerManager tocLayerManager = new TOCLayerManager();
	FLyrVect altConexiones = tocLayerManager
		.getLayerByName(AltConexionesForm.NAME);
	FLyrVect alternativas = tocLayerManager
		.getLayerByName(AlternativasForm.NAME);

	try {
	    final SelectableDataSource conRecordset = altConexiones
		    .getRecordset();
	    habConecIdx = conRecordset.getFieldIndexByName("hab_conectados");
	    qExtraIdx = conRecordset.getFieldIndexByName("q_extra");
	    final SelectableDataSource altRecordset = alternativas
		    .getRecordset();
	    demandaIdx = altRecordset.getFieldIndexByName("demanda");
	    poblActualIdx = altRecordset.getFieldIndexByName("pobl_actual");

	    Value[] row = altRecordset.getRow(0);
	    demanda = value2double(row, demandaIdx);
	    poblActual = value2double(row, poblActualIdx);

	    double n_cent_educativos = value2double(row,
		    altRecordset.getFieldIndexByName("n_cent_educativos"));
	    double n_cent_salud = value2double(row,
		    altRecordset.getFieldIndexByName("n_cent_salud"));
	    double n_cent_otros = value2double(row,
		    altRecordset.getFieldIndexByName("n_cent_otros"));

	    double dot_cent_educativos = value2double(row,
		    altRecordset.getFieldIndexByName("dot_cent_educativos"));
	    double dot_cent_salud = value2double(row,
		    altRecordset.getFieldIndexByName("dot_cent_salud"));
	    double dot_cent_otros = value2double(row,
		    altRecordset.getFieldIndexByName("dot_cent_otros"));

	    demCentros = (n_cent_educativos * dot_cent_educativos
		    + n_cent_salud * dot_cent_salud + n_cent_otros
		    * dot_cent_otros);

	    double dot_sec_primario = value2double(row,
		    altRecordset.getFieldIndexByName("dot_sec_primario"));
	    double dot_sec_secundario = value2double(row,
		    altRecordset.getFieldIndexByName("dot_sec_secundario"));
	    double dot_sec_terciario = value2double(row,
		    altRecordset.getFieldIndexByName("dot_sec_terciario"));

	    demEcon = (dot_sec_primario + dot_sec_secundario + dot_sec_terciario);

	} catch (ReadDriverException e) {
	    throw new ExternalError(e);
	}

    }

    private double value2double(Value[] attr, int i) {
	double r = 0;
	Value value = attr[i];
	if (value instanceof NumericValue) {
	    r = ((NumericValue) value).doubleValue();
	}
	return r;
    }

}
