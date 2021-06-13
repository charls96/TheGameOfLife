package userAccess.jSwing.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

import jLife.Configuration;
import userAccess.OperationsView;
import userAccess.jSwing.controllers.SimulationRunController;

public class SimulationRunView extends JFrame implements OperationsView {

	private static final long serialVersionUID = 1L;

	private JPanel controlPanel;
	private JPanel simulationPanel;
	private JTextArea textViewingArea;
	private JToolBar toolBar;
	private JPanel statePanel;
	private JScrollPane textViewingPanel;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public SimulationRunView() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Ejecución Simulación JV");
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.controlPanel = new JPanel();
		this.getContentPane().add(controlPanel, BorderLayout.NORTH);
		
		
		this.simulationPanel = new JPanel();
		this.getContentPane().add(simulationPanel, BorderLayout.CENTER);
		this.simulationPanel.setLayout(new BorderLayout(0, 0));	
		
		this.textViewingArea = new JTextArea();
		this.textViewingArea.setEditable(false);
		this.textViewingArea.setRows(50);
		this.textViewingArea.setColumns(50);
		this.textViewingArea.setTabSize(4);
		this.textViewingArea.setFont(new Font("Courier New", Font.PLAIN, 16));
		this.textViewingArea.setBackground(Color.WHITE);
		
		this.textViewingPanel = new JScrollPane(textViewingArea);
		this.textViewingPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.simulationPanel.add(textViewingPanel, BorderLayout.NORTH);
		
		this.toolBar = new JToolBar();
		this.simulationPanel.add(toolBar, BorderLayout.SOUTH);
		
		this.statePanel = new JPanel();
		this.getContentPane().add(statePanel, BorderLayout.SOUTH);
	}

	public JTextArea getTextViewingArea() {
		return this.textViewingArea;
	}
	
	public void showSimulation(SimulationRunController controller) {
		byte[][] grid = controller.getWorld().getGrid();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				mostrarSimple((grid[i][j] == 1) ? "|o" : "| ");
			}
			this.textViewingArea.append("|\n");
		}
	}
	
	private void mostrarSimple(String mensaje) {
		this.textViewingArea.append(mensaje);
	}
	
	@Override
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message, Configuration.get().getProperty("aplication.title"), JOptionPane.INFORMATION_MESSAGE);
	}

} 
