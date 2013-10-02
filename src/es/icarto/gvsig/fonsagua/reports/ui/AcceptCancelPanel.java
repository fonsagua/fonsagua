package es.icarto.gvsig.fonsagua.reports.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.iver.andami.PluginServices;

@SuppressWarnings("serial")
public class AcceptCancelPanel extends JPanel {

    public static final String CANCEL_ACTION_COMMAND = "CANCEL";
    public static String OK_ACTION_COMMAND = "OK";
    private JButton btnOk = null;
    private JButton btnCancel = null;

    public AcceptCancelPanel(ActionListener okAction,
	    ActionListener cancelAction) {
	super(new FlowLayout());
	addOkButton(okAction);
	addCancelButton(cancelAction);
    }

    public AcceptCancelPanel() {
	this(null, null);
    }

    private void addOkButton(ActionListener okAction) {
	btnOk = new JButton();
	btnOk.setText(PluginServices.getText(this, "ok"));
	btnOk.setActionCommand(OK_ACTION_COMMAND);
	if (okAction != null) {
	    btnOk.addActionListener(okAction);
	}
	add(btnOk);
    }

    private void addCancelButton(ActionListener cancelAction) {
	btnCancel = new JButton();
	btnCancel.setText(PluginServices.getText(this, "cancel"));
	btnCancel.setActionCommand(CANCEL_ACTION_COMMAND);
	if (cancelAction != null) {
	    btnCancel.addActionListener(cancelAction);
	}
	add(btnCancel);
    }

    public void addCancelButtonActionListener(ActionListener l) {
	btnCancel.addActionListener(l);
    }

    public void setOkButtonActionListener(ActionListener l) {
	ActionListener[] listeners = btnOk.getActionListeners();
	for (int i = 0; i < listeners.length; i++) {
	    btnOk.removeActionListener(listeners[i]);
	}
	btnOk.addActionListener(l);
    }

    public void setCancelButtonActionListener(ActionListener l) {
	ActionListener[] listeners = btnCancel.getActionListeners();
	for (int i = 0; i < listeners.length; i++) {
	    btnCancel.removeActionListener(listeners[i]);
	}
	btnCancel.addActionListener(l);
    }

    public void addOkButtonActionListener(ActionListener l) {
	btnOk.addActionListener(l);
    }

    public boolean isOkButtonEnabled() {
	return btnOk.isEnabled();
    }

    public boolean isCancelButtonEnabled() {
	return btnCancel.isEnabled();
    }

    public void setOkButtonEnabled(boolean b) {
	btnOk.setEnabled(b);
    }

    public void setCancelButtonEnabled(boolean b) {
	btnCancel.setEnabled(b);
    }
}
