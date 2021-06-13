package userAccess.jSwing.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import jLife.Configuration;
import userAccess.OperationsView;

public class MainView extends JFrame implements OperationsView {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	
	private JMenu mnFile;
	private JMenuItem mntnSave;
	private JMenuItem mntnExit;
	
	private JMenu mnSimulations;
	private JMenuItem mntnSimulationNew;
	private JMenuItem mntnUpdateSimulation;
	private JMenuItem mntnDeleteSimulation;
	private JMenuItem mntnShowSimulationData;
	private JMenuItem mntnSimulationsDemo;
	
	private JMenu mnWorld;
	private JMenuItem mntnNewWorld;
	private JMenuItem mntnUpdateWorld;
	private JMenuItem mntnDeleteWorld;
	private JMenuItem mntnShowWorldsData;
	
	private JMenu mnUsers;
	private JMenuItem mntnNewUser;
	private JMenuItem mntnUpdateUser;
	private JMenuItem mntnDeleteUser;
	private JMenuItem mntnShowUsersData;
	
	private JMenu mnSessions;
	private JMenuItem mntnUpdateSession;
	private JMenuItem mntnDeleteSession;
	private JMenuItem mntnShowSessionsData;
	
	private JMenu mnHelp;
	private JMenuItem mntnAbot;
	private JDialog abotDialog;
	
	public MainView() {
		initMainView();
	}

	private void initMainView() {
		setTitle(Configuration.get().getProperty("aplication.title") + " GESTIÓN PRINCIPAL");
		//Permite controlar manualmente el cierre de la ventana
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		//Adapta tamaño preferido de ventana al 25% de la pantalla
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
		setPreferredSize(this.getSize());

		// Centra la ventana en la pantalla
		setLocation((screenSize.width - getSize().width) / 2,
				(screenSize.height - getSize().height) / 2);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu();
		mnFile.setHorizontalAlignment(SwingConstants.CENTER);
		mnFile.setToolTipText("Menú archivo.");
		mnFile.setText("Archivo");
		menuBar.add(mnFile);

		mntnSave = new JMenuItem();
		mntnSave.setText("Guardar");
		mnFile.add(mntnSave);

		mntnExit = new JMenuItem();

		mntnExit.setText("Salir");
		mnFile.add(mntnExit);

		mnSimulations = new JMenu();
		mnSimulations.setToolTipText("Menú simulaciones.");
		mnSimulations.setText("Simulaciones");
		menuBar.add(mnSimulations);

		mntnSimulationNew = new JMenuItem();
		mntnSimulationNew.setText("Crear nueva simulación");
		mnSimulations.add(mntnSimulationNew);

		mntnUpdateSimulation = new JMenuItem();
		mntnUpdateSimulation.setText("Modificar simulación");
		mnSimulations.add(mntnUpdateSimulation);

		mntnDeleteSimulation = new JMenuItem();
		mntnDeleteSimulation.setText("Eliminar simulación");
		mnSimulations.add(mntnDeleteSimulation);

		mntnShowSimulationData = new JMenuItem();
		mntnShowSimulationData.setText("Mostrar datos");
		mnSimulations.add(mntnShowSimulationData);

		mntnSimulationsDemo = new JMenuItem();
		mntnSimulationsDemo.setText("Demo JV");
		mnSimulations.add(mntnSimulationsDemo);

		mnWorld = new JMenu();
		mnWorld.setToolTipText("Menú mundos.");
		mnWorld.setText("Mundos");
		menuBar.add(mnWorld);

		mntnNewWorld = new JMenuItem();
		mntnNewWorld.setText("Crear nuevo mundo");
		mnWorld.add(mntnNewWorld);

		mntnUpdateWorld = new JMenuItem();
		mntnUpdateWorld.setText("Modificar mundo");
		mnWorld.add(mntnUpdateWorld);

		mntnDeleteWorld = new JMenuItem();
		mntnDeleteWorld.setText("Eliminar mundo");
		mnWorld.add(mntnDeleteWorld);

		mntnShowWorldsData = new JMenuItem();
		mntnShowWorldsData.setText("Mostrar datos");
		mnWorld.add(mntnShowWorldsData);

		mnUsers = new JMenu();
		mnUsers.setToolTipText("Menú usuarios.");
		mnUsers.setText("Usuarios");
		menuBar.add(mnUsers);

		mntnNewUser = new JMenuItem();
		mntnNewUser.setText("Crear nuevo usuario");
		mnUsers.add(mntnNewUser);

		mntnUpdateUser = new JMenuItem();
		mntnUpdateUser.setText("Modificar usuario");
		mnUsers.add(mntnUpdateUser);

		mntnDeleteUser = new JMenuItem();
		mntnDeleteUser.setText("Eliminar usuario");
		mnUsers.add(mntnDeleteUser);

		mntnShowUsersData = new JMenuItem();
		mntnShowUsersData.setText("Mostrar usuario");
		mnUsers.add(mntnShowUsersData);

		mnSessions = new JMenu();
		mnSessions.setToolTipText("Menú sesiones.");
		mnSessions.setText("Sesiones");
		menuBar.add(mnSessions);

		mntnUpdateSession = new JMenuItem();
		mntnUpdateSession.setText("Modificar sesión");
		mnSessions.add(mntnUpdateSession);

		mntnDeleteSession = new JMenuItem();
		mntnDeleteSession.setText("Eliminar sesión");
		mnSessions.add(mntnDeleteSession);

		mntnShowSessionsData = new JMenuItem();
		mntnShowSessionsData.setText("Mostrar datos");
		mnSessions.add(mntnShowSessionsData);

		mnHelp = new JMenu();
		mnHelp.setActionCommand("ayuda");
		mnHelp.setToolTipText("Menú ayuda.");
		mnHelp.setText("Ayuda");
		menuBar.add(mnHelp);

		mntnAbot = new JMenuItem();
		mntnAbot.setText("Acerca de...");
		mnHelp.add(mntnAbot);
	}

	public JMenuItem getMntnSave() {
		return mntnSave;
	}

	public JMenuItem getMntnExit() {
		return mntnExit;
	}

	public JMenuItem getMntnNewSimulation() {
		return mntnSimulationNew;
	}

	public JMenuItem getMntnUpdateSimulacion() {
		return mntnUpdateSimulation;
	}

	public JMenuItem getMntnDeleteSimulation() {
		return mntnDeleteSimulation;
	}

	public JMenuItem getMntnShowSimulationData() {
		return mntnShowSimulationData;
	}
	
	public JMenuItem getMntnSimulationDemo() {
		return mntnSimulationsDemo;
	}

	public JMenuItem getMntnNewWorld() {
		return mntnNewWorld;
	}

	public JMenuItem getMntnUpdateWorld() {
		return mntnUpdateWorld;
	}

	public JMenuItem getMntnDeleteWorld() {
		return mntnDeleteWorld;
	}

	public JMenuItem getMntnShowWorldsData() {
		return mntnShowWorldsData;
	}

	public JMenuItem getMntnNewUser() {
		return mntnNewUser;
	}

	public JMenuItem getMntnUpdateUser() {
		return mntnUpdateUser;
	}

	public JMenuItem getMntnDeleteUser() {
		return mntnDeleteUser;
	}

	public JMenuItem getMntnShowUsersData() {
		return mntnShowUsersData;
	}

	public JMenuItem getMntnUpdateSession() {
		return mntnUpdateSession;
	}

	public JMenuItem getMntnDeleteSession() {
		return mntnDeleteSession;
	}

	public JMenuItem getMntnShowSessionsData() {
		return mntnShowSessionsData;
	}

	public JMenuItem getMntnAbot() {
		return mntnAbot;
	}

	public void showAbout() {
		abotDialog = new JDialog(this) {
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g) {
				Graphics2D g2D = (Graphics2D) g;
				g2D.setColor(Color.CYAN);
				g2D.fillRect(0, 0, 300, 200);
				g2D.setColor(Color.BLACK);
				g2D.drawString(Configuration.get().getProperty("aplication.title"), 20, 70);
			}
		};
		abotDialog.setLocation(getX() + 100, getY() + 70);
		abotDialog.setTitle("Acerca De...");
		abotDialog.setBounds(460, 460, 200, 150);
		abotDialog.setResizable(false);
		abotDialog.setVisible(true);
	}

	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, 
				Configuration.get().getProperty("aplication.title"), JOptionPane.INFORMATION_MESSAGE);
	}
	
	public boolean confirmMessage(String message) {
		return JOptionPane.showConfirmDialog(null, message,
				Configuration.get().getProperty("aplication.title"), JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
	}

} 
