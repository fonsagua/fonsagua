package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.math.BigDecimal;

import javax.swing.JComboBox;

import es.icarto.gvsig.navtableforms.IValidatableForm;
import es.icarto.gvsig.navtableforms.calculation.Calculation;

public class NivelDinamicoCalculation extends Calculation {

    public NivelDinamicoCalculation(IValidatableForm form) {
	super(form);
    }

    @Override
    protected String resultName() {
	return "nivel_dinamico";
    }

    @Override
    protected String[] operandNames() {
	return new String[] {"tipo_fuente_alternativa", "cota", "margen_fondo"};
    }

    @Override
    protected String calculate() {
	JComboBox comboBox = form.getFormPanel().getComboBox("tipo_fuente_alternativa");
	Object tipoFuente = comboBox.getSelectedItem();
	BigDecimal nivel_dinamico = operandValue("cota"); 
	if ((tipoFuente != null) && tipoFuente.toString().equalsIgnoreCase("pozo")) {
	    BigDecimal margenFondo = operandValue("margen_fondo");
	    nivel_dinamico = nivel_dinamico.subtract(margenFondo);
	}
	
	return formatter.format(nivel_dinamico);
    }

}
