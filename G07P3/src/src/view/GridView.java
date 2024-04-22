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

		// mutacion label and dropdown. This will be placed top right of the right panel 
		JLabel mutacionLabel = new JLabel("Mutacion:");
		c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		c.ipady = 0;	//reset to default
		c.weightx = 0.0; // Don't fill the available horizontal space
		c.weighty = 0.0; // Don't fill the available vertical space
		c.gridwidth = 1; //1 column wide
		c.gridheight = 1; //1 row tall
		c.gridx = 0; //leftmost column
		c.gridy = 0; //top row
		rightPanel.add(mutacionLabel, c);

		JComboBox<Integer> mutacionDropdown = new JComboBox<>(new Integer[]{1, 2, 3});
		c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		c.ipady = 0;	//reset to default
		c.weightx = 0.0; // Don't fill the available horizontal space
		c.weighty = 0.0; // Don't fill the available vertical space
		c.gridwidth = 1; //1 column wide
		c.gridheight = 1; //1 row tall
		c.gridx = 1; //rightmost column
		c.gridy = 0; //top row
		rightPanel.add(mutacionDropdown, c);

		JLabel label = new JLabel("Dimension y: ");
		c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		c.ipady = 0;	//reset to default
		c.weightx = 0.0; // Don't fill the available horizontal space
		c.weighty = 0.0; // Don't fill the available vertical space
		c.gridwidth = 1; //1 columns wide
		c.gridheight = 1; //1 row tall
		c.gridx = 0; //leftmost column
		c.gridy = 1; //second row
		rightPanel.add(label, c);

		JTextField dim = new JTextField();
		dim.setText("8");
		c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		c.ipady = 0;	//reset to default
		c.weightx = 0.0; // Don't fill the available horizontal space
		c.weighty = 0.0; // Don't fill the available vertical space
		c.gridwidth = 1; //1 columns wide
		c.gridheight = 1; //1 row tall
		c.gridx = 1; //rightmost column
		c.gridy = 1; //second row
		rightPanel.add(dim, c);
		
		JButton startButton = new JButton("Start");
		c.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		c.ipady = 0;	//reset to default
		c.weightx = 1.0; // Don't fill the available horizontal space
		c.weighty = 0.0; // fill the available vertical space
		c.gridwidth = 2; //1 columns wide
		c.gridheight = 1; //1 row tall
		c.gridx = 0; //first
		c.gridy = 2; //second row
		rightPanel.add(startButton, c);

		startButton.addActionListener(e -> {
			textLabel.setText("Se ha cambiado la dimension y a " + dim.getText() + " y la mutacion a " + mutacionDropdown.getSelectedItem());
			dimensionY = Integer.parseInt(dim.getText());
			drawButtonGrid(x, y);
		});

	}

	private void drawButtonGrid(int x, int y){
		// remove all buttons from the button panel
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
