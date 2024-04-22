package src.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridView {
	// grid baglayout variables
	final boolean shouldFill = true;
	JPanel buttonPanel;
	JPanel rightPanel;
	JPanel textPanel;

	public GridView(int x, int y) {
		// Button panel ----------------------------------------------------
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(x, y));
		JButton[][] buttons = new JButton[x][y];
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(e -> {
					JButton button = (JButton) e.getSource();
					if (button.getBackground().equals(java.awt.Color.YELLOW)) {
						button.setBackground(null);
					} else {
						button.setBackground(java.awt.Color.YELLOW);
					}
				});
				buttonPanel.add(buttons[i][j]);
			}
		}

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

		JLabel label = new JLabel("El programa todavia no se ha ejecutado");
		label.setPreferredSize(new Dimension(300, 400));

		JButton startButton = new JButton("Start");
		startButton.setSize(80, 25);
		startButton.addActionListener(e -> {
			label.setText("Programa ejecutado");
			textLabel.setText("El mejor programa es: PIPOOOPIIII");
		});

		JLabel mutacionLabel = new JLabel("Mutacion:");
		JComboBox<Integer> mutacionDropdown = new JComboBox<>(new Integer[]{1, 2, 3});

	
		rightPanel.add(mutacionLabel);
		rightPanel.add(mutacionDropdown);

		rightPanel.add(label);
		rightPanel.add(startButton);
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
