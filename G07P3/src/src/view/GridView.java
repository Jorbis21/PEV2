package src.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridView {

	JPanel buttonPanel;
	JPanel rightPanel;

	public GridView(int dim) {
		// Button panel ----------------------------------------------------
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(dim, dim));
		JButton[][] buttons = new JButton[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
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

		// Right panel ----------------------------------------------------
		rightPanel = new JPanel();
		JLabel label = new JLabel("DIOS ME CAGOOO");
		label.setPreferredSize(new Dimension(300, 400));

		JButton startButton = new JButton("Start");
		startButton.setSize(80, 25);
		startButton.addActionListener(e -> {
			label.setText("*sonido de pedo con grumos*");
		});

		rightPanel.add(label);
		rightPanel.add(startButton);
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}
}
