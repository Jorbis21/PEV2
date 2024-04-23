package src.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import src.AlgoritmoGenetico;
import src.bloating.Fundamentado;
import src.bloating.IBloating;
import src.bloating.Tarpeian;
import src.cruce.CruceArboreo;
import src.mutacion.IMutacion;
import src.mutacion.MutacionContraccion;
import src.mutacion.MutacionHoist;
import src.mutacion.MutacionPermutacion;
import src.mutacion.MutacionTerminal;
import src.seleccion.ISeleccion;
import src.seleccion.SeleccionRanking;
import src.seleccion.SeleccionRestos;
import src.seleccion.SeleccionRuleta;
import src.seleccion.SeleccionTorneoDet;
import src.seleccion.SeleccionTorneoPro;
import src.seleccion.SeleccionTruncamiento;
import src.seleccion.SeleccionUniEstocastica;

public class GridView {
	final static Color darkGreen = new Color(72, 111, 56);
	final static Color lightGreen = new Color(144, 222, 112);
	final static Color darkgrey = new Color(31, 31, 31);

	// grid baglayout variables
	final boolean shouldFill = false;
	
	JPanel buttonPanel;
	JPanel rightPanel;
	JPanel textPanel;

	ArrayList<Integer> creacionList = new ArrayList<>();
	String[] creacionNames;
	ArrayList<IBloating> bloatingList = new ArrayList<>();
	String[] bloatingNames;
	ArrayList<ISeleccion> seleccionList = new ArrayList<>();
	String[] seleccionNames;
	ArrayList<IMutacion> mutacionList = new ArrayList<>();
	String[] mutacionNames;

	int dimensionY = 8;
	AlgoritmoGenetico ag;
	int[][] grid;
	JButton[][] buttons;

	public GridView(int x, int y) {

		initArrayLists();
		
		dimensionY = y;
		// Button panel ----------------------------------------------------
		buttonPanel = new JPanel();
		drawButtonGrid(x, y);

		// Text panel ----------------------------------------------------
		textPanel = new JPanel();
		JLabel textLabel = new JLabel("Aqui es donde se muestra el mejor programa");
		textPanel.add(textLabel);
		// Right panel ----------------------------------------------------
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		JLabel tamPobLabel = new JLabel("Tam Poblacion:");
		c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		c.ipady = 10; // padding 
		c.ipadx = 10;
		c.weightx = 0.0; // Don't fill the available horizontal space
		c.weighty = 0.0; // Don't fill the available vertical space
		c.gridwidth = 1; //1 column wide
		c.gridheight = 1; //1 row tall
		c.gridx = 0; //leftmost column
		c.gridy = 0; //top row
		rightPanel.add(tamPobLabel, c);

		JTextField tamPobText = new JTextField();
		tamPobText.setText("100");
		c.gridx = 1; //rightmost column
		c.gridy = 0; //top row
		rightPanel.add(tamPobText, c);

		JLabel numGenLabel = new JLabel("Num Generaciones:");
		c.gridx = 0; //leftmost column
		c.gridy = 1; //second row
		rightPanel.add(numGenLabel, c);

		JTextField numGenText = new JTextField();
		numGenText.setText("100");
		c.gridx = 1; //rightmost column
		c.gridy = 1; //second row
		rightPanel.add(numGenText, c);

		JLabel tipoCreacionLabel = new JLabel("Tipo Creacion:");
		c.gridx = 0; //leftmost column
		c.gridy = 2; //third row
		rightPanel.add(tipoCreacionLabel, c);

		// Crea un dropdown con creacionNames y selecciona el primer elemento
		JComboBox<String> tipoCreacionDropdown = new JComboBox<>(creacionNames);
		tipoCreacionDropdown.setSelectedIndex(0);
		c.gridx = 1; //rightmost column
		c.gridy = 2; //third row
		rightPanel.add(tipoCreacionDropdown, c);

		JLabel profundidadMaxLabel = new JLabel("Profundidad Maxima:");
		c.gridx = 0; //leftmost column
		c.gridy = 3; //fourth row
		rightPanel.add(profundidadMaxLabel, c);

		JSpinner profundidadMaxSpinner = new JSpinner(new SpinnerNumberModel(2, 2, 5, 1));
		profundidadMaxSpinner.setValue(profundidadMaxSpinner.getValue());
		c.gridx = 1; //rightmost column
		c.gridy = 3; //fourth row
		rightPanel.add(profundidadMaxSpinner, c);

		JLabel controlBloatingLabel = new JLabel("Control Bloating:");
		c.gridx = 0; //leftmost column
		c.gridy = 4; //fith row
		rightPanel.add(controlBloatingLabel, c);

		JComboBox<String> controlBloatingDropdown = new JComboBox<>(bloatingNames);
		controlBloatingDropdown.setSelectedIndex(0);
		c.gridx = 1; //rightmost column
		c.gridy = 4; //fith row
		rightPanel.add(controlBloatingDropdown, c);

		JLabel probMuerteLabel = new JLabel("Probabilidad Muerte:");
		c.gridx = 0; //leftmost column
		c.gridy = 5; //sixth row
		rightPanel.add(probMuerteLabel, c);

		JTextField probMuerteText = new JTextField(); 
		probMuerteText.setText("1");
		c.gridx = 1; //rightmost column
		c.gridy = 5; //sixth row
		rightPanel.add(probMuerteText, c);

		JLabel elitismoLabel = new JLabel("Elitismo:");
		c.gridx = 0; //leftmost column
		c.gridy = 6; //seventh row
		rightPanel.add(elitismoLabel, c);

		JTextField elitismoText = new JTextField();
		elitismoText.setText("0");
		c.gridx = 1; //rightmost column
		c.gridy = 6; //seventh row
		rightPanel.add(elitismoText, c);

		JLabel seleccionLabel = new JLabel("Seleccion:");
		c.gridx = 0; //rightmost column
		c.gridy = 7; //eighth row
		rightPanel.add(seleccionLabel, c);

		JComboBox<String> seleccionDropdown = new JComboBox<>(seleccionNames);
		seleccionDropdown.setSelectedIndex(0);
		c.gridx = 1; //rightmost column
		c.gridy = 7; //eighth row
		rightPanel.add(seleccionDropdown, c);

		JLabel probCruceLabel = new JLabel("Probabilidad Cruce:");
		c.gridx = 0; //leftmost column
		c.gridy = 8; //ninth row
		rightPanel.add(probCruceLabel, c);

		JTextField probCruceText = new JTextField();
		probCruceText.setText("70");
		c.gridx = 1; //rightmost column
		c.gridy = 8; //ninth row
		rightPanel.add(probCruceText, c);

		JLabel mutacionLabel = new JLabel("Mutacion:");
		c.gridx = 0; //leftmost column
		c.gridy = 9; //tenth row
		rightPanel.add(mutacionLabel, c);

		JComboBox<String> mutacionDropdown = new JComboBox<>(mutacionNames);
		mutacionDropdown.setSelectedIndex(0);
		c.gridx = 1; //rightmost column
		c.gridy = 9; //tenth row
		rightPanel.add(mutacionDropdown, c);
		
		JLabel probMutacionLabel = new JLabel("Probabilidad Mutacion:");
		c.gridx = 0; //leftmost column
		c.gridy = 10; //eleventh row
		rightPanel.add(probMutacionLabel, c);

		JTextField probMutacionText = new JTextField();
		probMutacionText.setText("20");
		c.gridx = 1; //rightmost column
		c.gridy = 10; //eleventh row
		rightPanel.add(probMutacionText, c);

		JLabel label = new JLabel("Dimension y: ");
		c.gridx = 0; //leftmost column
		c.gridy = 11; //twelfth row
		rightPanel.add(label, c);
		
		JTextField dim = new JTextField();
		dim.setText("8");
		c.gridx = 1; //rightmost column
		c.gridy = 11; //twelfth row
		rightPanel.add(dim, c);

		JButton dimButton = new JButton("Apply");
		c.gridx = 2; //rightmost column
		c.gridy = 11; //twelfth row
		rightPanel.add(dimButton, c);

		dimButton.addActionListener(e -> {
			dimensionY = Integer.parseInt(dim.getText());
			drawButtonGrid(x, dimensionY);
		});

		JButton startButton = new JButton("Start");
		c.weightx = 1.0; // fill the available horizontal space
		c.gridwidth = 2; //2 columns wide
		c.gridx = 0; //first
		c.gridy = 12; //thirteenth row
		rightPanel.add(startButton, c);

		// puede  que aqui este un error bien tocho pero creo que esta en el orden correcto
		startButton.addActionListener(e -> { 
			ag = new AlgoritmoGenetico(Integer.parseInt(tamPobText.getText()), 
										Integer.parseInt(numGenText.getText()), 
										creacionList.get(tipoCreacionDropdown.getSelectedIndex()),
										(int) profundidadMaxSpinner.getValue(),
										(Double.parseDouble(probCruceText.getText())/100),
										(Double.parseDouble(probMutacionText.getText())/100),
										(Double.parseDouble(probMuerteText.getText())/100),
										(Double.parseDouble(elitismoText.getText())/100),
										new CruceArboreo(),
										mutacionList.get(mutacionDropdown.getSelectedIndex()),
										seleccionList.get(seleccionDropdown.getSelectedIndex()),
										bloatingList.get(controlBloatingDropdown.getSelectedIndex()));
			ag.run();
		});

	}

	private void drawButtonGrid(int x, int y){
		buttonPanel.removeAll();

		buttonPanel.setLayout(new GridLayout(x, y));
		buttons = new JButton[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				buttons[i][j] = new JButton();
				// set the background color of the button to dark green
				buttons[i][j].setBackground(darkGreen);
				buttons[i][j].addActionListener(e -> {
					JButton button = (JButton) e.getSource();
					if (button.getBackground() == darkGreen) {
						button.setBackground(darkgrey);
					} else {
						button.setBackground(darkGreen);
					}
				});
				buttonPanel.add(buttons[i][j]);
			}
		}
		buttonPanel.revalidate();
	}

	private void finalButtonGrid(int x, int y, int[][] grid){
		buttonPanel.removeAll();
		buttonPanel.setLayout(new GridLayout(x, y));
		buttons = new JButton[x][y];
		gridToButtons(x, y);
		buttonPanel.revalidate();
	}

	private void buttonsToGrid(int x, int y, JButton[][] buttons){
		grid = new int[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (buttons[i][j].getBackground() == darkGreen) 
					grid[i][j] = 0;
				else if (buttons[i][j].getBackground() == lightGreen)
					grid[i][j] = 1;
				else
					grid[i][j] = 2;
			}
		}
	}

	private void gridToButtons(int x, int y){
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				switch (grid[i][j]) {
					case 0:
						buttons[i][j].setBackground(darkGreen);
						break;
					case 1:
						buttons[i][j].setBackground(lightGreen);
						break;
					default:
						buttons[i][j].setBackground(darkgrey);
						break;
				}
			}
		}
	}


	private void initArrayLists(){

		for(int i = 0; i < 3; i++) {
			creacionList.add(i);
		}
		
		creacionNames = new String[creacionList.size()];
		creacionNames[0] = "Creciente";
		creacionNames[1] = "Completa";
		creacionNames[2] = "Ramped and Half";
		

		bloatingList.add(new Fundamentado());
		bloatingList.add(new Tarpeian());

		bloatingNames = new String[bloatingList.size()];
		for(IBloating bloating : bloatingList) {
			bloatingNames[bloatingList.indexOf(bloating)] = bloating.toString();
		}

		seleccionList.add(new SeleccionRuleta());
		seleccionList.add(new SeleccionTorneoDet());
		seleccionList.add(new SeleccionTorneoPro());
		seleccionList.add(new SeleccionRestos());
		seleccionList.add(new SeleccionTruncamiento());
		seleccionList.add(new SeleccionUniEstocastica());
		seleccionList.add(new SeleccionRanking());
		
		seleccionNames = new String[seleccionList.size()];
		for(ISeleccion seleccion : seleccionList) {
			seleccionNames[seleccionList.indexOf(seleccion)] = seleccion.toString();
		}

		mutacionList.add(new MutacionTerminal());
		mutacionList.add(new MutacionContraccion());
		mutacionList.add(new MutacionPermutacion());
		mutacionList.add(new MutacionHoist());
		
		mutacionNames = new String[mutacionList.size()];
		for(IMutacion mutacion : mutacionList) {
			mutacionNames[mutacionList.indexOf(mutacion)] = mutacion.toString();
		}

	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public JPanel getTextPanel() {
		return textPanel;
	}
}
