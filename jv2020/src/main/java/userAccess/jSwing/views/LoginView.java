package userAccess.jSwing.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jLife.Configuration;
import userAccess.OperationsView;

@SuppressWarnings("serial")
public class LoginView extends JDialog implements OperationsView {

	private static String OK = "ok";
	private static String CANCEL = "Cancelar";

	private JPanel textPanel;
	private JPanel fieldsPanel;
	private JPanel labelPanel;
	private JPanel buttonsPanel;

	private JTextField userField;
	private JPasswordField passwordField;

	private JButton buttonOk;
	private JButton buttonCancel;
	private JPanel titlePanel;
	private JPanel workPanel;
	private JLabel lblTitle;
	private JLabel lblHelp;
	private JLabel lblSubtitle;

	public LoginView() {
		this.initLoginView();
	}

	private void initLoginView() {
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setModal(true);
		this.setResizable(false);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - getSize().width) / 2,
				(screenSize.height - getSize().height)/ 2);

		this.getContentPane().setLayout(new BorderLayout(0, 0));	
		this.titlePanel = new JPanel();
		this.getContentPane().add(this.titlePanel, BorderLayout.NORTH);
		this.titlePanel.setLayout(new BorderLayout(0, 0));

		this.lblSubtitle = new JLabel("                        ");
		this.titlePanel.add(this.lblSubtitle, BorderLayout.WEST);
		this.lblTitle = new JLabel(Configuration.get().getProperty("aplication.title")+  " Control de acceso");
		this.titlePanel.add(this.lblTitle, BorderLayout.CENTER);

		this.lblHelp = new JLabel("Ayuda ");
		this.lblHelp.setToolTipText("");
		this.lblHelp.setFont(new Font("Dialog", Font.PLAIN, 11));
		this.titlePanel.add(lblHelp, BorderLayout.EAST);
		this.workPanel = new JPanel();
		this.getContentPane().add(this.workPanel);

		this.createTextPanel();
		this.createButtonPanel();  
	}

	private void createTextPanel() {
		this.textPanel = new JPanel();
		this.workPanel.add(textPanel);
		this.textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		this.labelPanel = new JPanel();
		JLabel labelUser = new JLabel("Usuario:");
		JLabel labelPassword = new JLabel("Clave de acceso: ");
		labelUser.setLabelFor(this.userField);
		labelPassword.setLabelFor(passwordField);
		this.labelPanel.setLayout(new BorderLayout(0, 0));
		this.labelPanel.add(labelUser, BorderLayout.NORTH);
		this.labelPanel.add(labelPassword);
		this.textPanel.add(this.labelPanel);

		this.fieldsPanel = new JPanel();
		this.userField = new JTextField(10);
		this.passwordField = new JPasswordField(10);
		this.userField.setActionCommand(OK);
		this.passwordField.setActionCommand(OK);
		this.fieldsPanel.setLayout(new BorderLayout(0, 0));
		this.fieldsPanel.add(this.userField, BorderLayout.NORTH);
		this.fieldsPanel.add(this.passwordField);
		this.textPanel.add(this.fieldsPanel);
	}

	private void createButtonPanel() {      
		this.buttonsPanel = new JPanel(new GridLayout(0,1));
		this.workPanel.add(buttonsPanel);
		this.buttonOk = new JButton("OK");
		this.buttonOk.setToolTipText("Confirmar credenciales.");
		this.buttonCancel = new JButton("Cancelar");
		this.buttonCancel.setToolTipText("Abandonar y salir.");
		this.buttonOk.setActionCommand(OK);
		this.buttonCancel.setActionCommand(CANCEL);
		this.buttonsPanel.setLayout(new BorderLayout(0,0));
		this.buttonsPanel.add(this.buttonOk, BorderLayout.NORTH);
		this.buttonsPanel.add(this.buttonCancel, BorderLayout.SOUTH);	
	}

	public JButton getButtonOk() {
		return this.buttonOk;
	}

	public JLabel getLblHelp() {
		return this.lblHelp;
	}

	public JButton getButtonCancel() {
		return this.buttonCancel;
	}

	public JTextField getUserField() {
		return this.userField;
	}

	public JPasswordField getPasswordField() {
		return this.passwordField;
	}

	public void resetFocus() {
		this.userField.requestFocusInWindow();
	}

	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, Configuration.get().getProperty("aplication.title"), JOptionPane.INFORMATION_MESSAGE);
	}

} 
