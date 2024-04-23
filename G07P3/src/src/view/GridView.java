package src.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridView {
	final static Color darkGreen = new Color(72, 111, 56);
	final static Color lightGreen = new Color(144, 222, 112);
	final static Color darkgrey = new Color(31, 31, 31);

	// grid baglayout variables
	final boolean shouldFill = true;
	
	JPanel buttonPanel;
	JPanel rightPanel;
	JPanel textPanel;
	int dimensionY = 0;

	public GridView(int x, int y) {
		dimensionY = y;
		// Button panel ----------------------------------------------------
		buttonPanel = new JPanel();
		drawButtonGrid(x, y);

		// Text panel ----------------------------------------------------
		/*
		 * Creates a Label panel where the best program is shown
		 * This panel will be placed under the button panel and will have a label with the text 
		 * "Aqui es donde se muestra el mejor programa" this panel will span the entire width of the button panel
		 */
		textPanel = new JPanel();
		JLabel textLabel = new JLabel("Aqui es donde se muestra el mejor programa");
		textPanel.add(textLabel);
		// Right panel ----------------------------------------------------
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		// seleccion label and dropdown. This will be placed top right of the right panel
		JLabel seleccionLabel = new JLabel("Seleccion:");
		c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		c.ipady = 0;	//reset to default
		c.weightx = 0.0; // Don't fill the available horizontal space
		c.weighty = 0.0; // Don't fill the available vertical space
		c.gridwidth = 1; //1 column wide
		c.gridheight = 1; //1 row tall
		c.gridx = 0; //leftmost column
		c.gridy = 0; //top row
		rightPanel.add(seleccionLabel, c);

		JComboBox<String> seleccionDropdown = new JComboBox<>(new String[]{"Seleccion 1", "Seleccion 2", "Seleccion 3"});
		c.gridx = 1; //rightmost column
		c.gridy = 0; //top row
		rightPanel.add(seleccionDropdown, c);

		// mutacion label and dropdown. This will be placed top right of the right panel 
		JLabel mutacionLabel = new JLabel("Cruce:");
		c.gridx = 0; //leftmost column
		c.gridy = 1; //top row
		rightPanel.add(mutacionLabel, c);

		JComboBox<String> cruceDropdown = new JComboBox<>(new String[]{"Cruce 1", "Cruce 2", "Cruce 3"});
		c.gridx = 1; //rightmost column
		c.gridy = 1; //top row
		rightPanel.add(cruceDropdown, c);

		// cruce label and dropdown. This will be placed middle right of the right panel
		JLabel cruceLabel = new JLabel("Mutacion:");
		c.gridx = 0; //leftmost column
		c.gridy = 2; //second row
		rightPanel.add(cruceLabel, c);

		JComboBox<String> mutacionDropdown = new JComboBox<>(new String[]{"Mutacion 1", "Mutacion 2", "Mutacion 3", "Mutacion 4"});
		c.gridx = 1; //rightmost column
		c.gridy = 2; //second row
		rightPanel.add(mutacionDropdown, c);

		JLabel label = new JLabel("Dimension y: ");
		c.gridx = 0; //leftmost column
		c.gridy = 3; //third row
		rightPanel.add(label, c);
		
		JTextField dim = new JTextField();
		dim.setText("8");
		c.gridx = 1; //rightmost column
		c.gridy = 3; //fourth row
		rightPanel.add(dim, c);

		// esto no funciona ahora pero funcionara
		dim.addActionListener(e -> {
			int newDimensionY = Integer.parseInt(dim.getText());
			drawButtonGrid(x, newDimensionY);
		});

		JButton startButton = new JButton("Start");
		c.weightx = 1.0; // fill the available horizontal space
		c.gridwidth = 2; //2 columns wide
		c.gridx = 0; //first
		c.gridy = 4; //fith row
		rightPanel.add(startButton, c);

		startButton.addActionListener(e -> {
			textLabel.setText("La Seleccion es: " + seleccionDropdown.getSelectedItem() + 
			" \nEl cruce es: " + cruceDropdown.getSelectedItem() + 
			" \nLa mutacion es: " + mutacionDropdown.getSelectedItem());
		});

	}

	private void drawButtonGrid(int x, int y){
		buttonPanel.removeAll();

		buttonPanel.setLayout(new GridLayout(x, dimensionY));
		JButton[][] buttons = new JButton[x][dimensionY];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < dimensionY; j++) {
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
