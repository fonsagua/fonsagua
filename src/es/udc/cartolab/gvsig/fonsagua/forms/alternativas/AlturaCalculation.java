package es.udc.cartolab.gvsig.fonsagua.forms.alternativas;

import java.math.BigDecimal;

import javax.swing.JComboBox;

import es.icarto.gvsig.navtableforms.IValidatableForm;
import es.icarto.gvsig.navtableforms.calculation.Calculation;

public class AlturaCalculation extends Calculation {

    public AlturaCalculation(IValidatableForm form) {
	super(form);
    }

    @Override
    protected String resultName() {
	return "altura";
    }

    @Override
    protected String[] operandNames() {
	return new String[] {"tipo_fuente_alternativa", "cota", "profundidad_pozo", "margen_fondo"};
    }

    @Override
    protected String calculate() {
	
	JComboBox comboBox = form.getFormPanel().getComboBox("tipo_fuente_alternativa");
	Object tipoFuente = comboBox.getSelectedItem();
	BigDecimal altura = operandValue("cota"); 
	if ((tipoFuente != null) && tipoFuente.toString().equalsIgnoreCase("pozo")) {
		BigDecimal profundidadPozo = operandValue("profundidad_pozo");
		BigDecimal margenFondo = operandValue("margen_fondo");
		altura = altura.subtract(profundidadPozo).add(margenFondo);
	}
	
	return formatter.format(altura);
    }

}
