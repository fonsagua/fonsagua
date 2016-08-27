package es.icarto.gvsig.fonsagua.importer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import es.icarto.gvsig.commons.utils.Field;
import es.icarto.gvsig.importer.ImporterTM;
import es.icarto.gvsig.importer.Ruler;
import es.icarto.gvsig.importer.Target;

public class FonsaguaRuler implements Ruler {

    private final List<Target> targets;
    private final NoTarget noTarget;

    public FonsaguaRuler() {
	targets = new ArrayList<Target>();
	targets.add(new ComunidadTarget());

	Pattern vivPattern = Pattern.compile("^(\\d{8})VI\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target vivTarget = new CentroTarget("puntos_viviendas", "cod_vivienda",
		vivPattern, "VI", "%02d", "La vivienda");
	targets.add(vivTarget);

	Pattern eduPattern = Pattern.compile("^(\\d{8})CE\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target eduTarget = new CentroTarget("centros_educativos", "cod_c_educativo",
		eduPattern, "CE", "%02d", "El colegio");
	targets.add(eduTarget);

	Pattern reuPattern = Pattern.compile("^(\\d{8})OS\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target reuTarget = new CentroTarget("otros_servicios", "cod_servicio",
		reuPattern, "OS", "%02d", "El centro");
	targets.add(reuTarget);

	Pattern saludPattern = Pattern.compile("^(\\d{8})CS\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target saludTarget = new CentroTarget("centros_salud", "cod_c_salud",
		saludPattern, "CS", "%02d", "El centro de salud");
	targets.add(saludTarget);

	Pattern amenazasPattern = Pattern.compile("^(\\d{8})RI\\d{2}$",
		Pattern.CASE_INSENSITIVE);
	Target amenazasTarget = new CentroTarget("amenazas", "cod_amenaza",
		amenazasPattern, "RI", "%02d", "El riesgo");
	targets.add(amenazasTarget);

	targets.add(new FuenteTarget());

	noTarget = new NoTarget();
    }

    @Override
    public void processValue(String value, ImporterTM table, int i) {

	boolean anyMatch = false;
	for (Target target : targets) {
	    boolean match = target.process(value, table, i);
	    if (match) {
		target.checkErrors(table, i);
	    }
	    anyMatch = anyMatch || match;
	}

	if (!anyMatch) {
	    noTarget.process(value, table, i);
	    noTarget.checkErrors(table, i);
	}

    }

    @Override
    public List<Field> getFields() {
	List<Field> fields = new ArrayList<Field>();
	fields.add(noTarget.getField());
	for (Target t : targets) {
	    fields.add(t.getField());
	}
	return fields;
    }
}
