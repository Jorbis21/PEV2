package src.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.math.plot.Plot2DPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;

import  src.view.GraphView;

public class MainView{
	public MainView() {
		JFrame frame = new JFrame("Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1600, 800); // Set frame size to 1000 x 1000


		JTabbedPane tabbedPane = new JTabbedPane();

		// First tab - Grid ---------------------------------------------------------------------------------
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1, 2));
		mainPanel.setPreferredSize(new Dimension(1600, 800)); // Set preferred size of mainPanel
		tabbedPane.addTab("Grid", mainPanel);

		// Left panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(8, 8));
		JButton[][] buttons = new JButton[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
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

		mainPanel.add(buttonPanel);

		// Right panel
		JPanel rightPanel = new JPanel();

		JLabel label = new JLabel("DIOS ME CAGOOO");
		label.setPreferredSize(new Dimension(300, 400));


		JButton startButton = new JButton("Start");
		startButton.setSize(80, 25);
		startButton.addActionListener(e -> {
			label.setText("*sonido de pedo con grumos*");
		});

		rightPanel.add(label);
		rightPanel.add(startButton);
		mainPanel.add(rightPanel);
		
		// Second tab - Graph --------------------------------------------------------------------------------
		JPanel graphPanel = new JPanel();
		graphPanel.setLayout(new GridLayout(1, 1));
		graphPanel.setPreferredSize(new Dimension(1600, 800)); // Set preferred size of graphPanel
		
		tabbedPane.addTab("Graph", graphPanel);
		frame.add(tabbedPane);
		frame.pack();
		frame.setVisible(true);

		Plot2DPanel plot = new GraphView().getPlot();
		graphPanel.add(plot);


	}
	
	public static void main(String args[]) {
		new MainView();
	}
}
