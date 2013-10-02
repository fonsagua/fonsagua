package es.icarto.gvsig.fonsagua.reports.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.iver.andami.PluginServices;

@SuppressWarnings("serial")
public class AcceptCancelPanel extends JPanel {

    private JButton btnOk = null;
    private JButton btnCancel = null;

    public AcceptCancelPanel(ActionListener okAction,
	    ActionListener cancelAction) {
	super(new FlowLayout());

	add(getBtnOk(okAction));
	add(getCancelButton(cancelAction));

    }

    public AcceptCancelPanel() {
	this(null, null);

    }

    /**
     * This method initializes btnOk
     * 
     * @return javax.swing.JButton
     */
    private JButton getBtnOk(ActionListener okAction) {
	if (btnOk == null) {
	    btnOk = new JButton();
	    btnOk.setText(PluginServices.getText(this, "ok"));
	    btnOk.setActionCommand("OK");
	    if (okAction != null) {
		btnOk.addActionListener(okAction);
	    }
	}
	return btnOk;
    }

    /**
     * This method initializes btnCancel
     * 
     * @return javax.swing.JButton
     */
    private JButton getCancelButton(ActionListener cancelAction) {
	if (btnCancel == null) {
	    btnCancel = new JButton();
	    btnCancel.setText(PluginServices.getText(this, "cancel"));
	    btnCancel.setActionCommand("CANCEL");
	    if (cancelAction != null) {
		btnCancel.addActionListener(cancelAction);
	    }
	}
	return btnCancel;
    }

    /**
     * Adds an ActionListener to the <b>cancel</b> button.
     * 
     * @param l
     */
    public void addCancelButtonActionListener(ActionListener l) {
	btnCancel.addActionListener(l);
    }

    /**
     * Sets the ActionListener to the <b>OK</b> button removing any other
     * previous one.
     * 
     * @param l
     */
    public void setOkButtonActionListener(ActionListener l) {
	ActionListener[] listeners = btnOk.getActionListeners();
	for (int i = 0; i < listeners.length; i++) {
	    btnOk.removeActionListener(listeners[i]);
	}
	btnOk.addActionListener(l);
    }

    /**
     * Sets the ActionListener to the <b>cancel</b> button removing any other
     * previous one.
     * 
     * @param l
     */
    public void setCancelButtonActionListener(ActionListener l) {
	ActionListener[] listeners = btnCancel.getActionListeners();
	for (int i = 0; i < listeners.length; i++) {
	    btnCancel.removeActionListener(listeners[i]);
	}
	btnCancel.addActionListener(l);
    }

    /**
     * Adds an ActionListener to the <b>OK</b> button.
     * 
     * @param l
     */
    public void addOkButtonActionListener(ActionListener l) {
	btnOk.addActionListener(l);
    }

    /**
     * Returns the ok button contained by this panel since resizing issues
     * should be automatically handled by the layout manager. The use of this
     * method is discouraged, it is keeped only for compatibility issues. Try
     * using specific button properties access methods contained by this class
     * instead.
     * 
     * @return the Ok button
     * @deprecated
     */
    @Deprecated
    public JButton getOkButton() {
	return btnOk;
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
