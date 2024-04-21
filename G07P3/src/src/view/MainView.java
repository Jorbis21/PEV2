package src.view;


import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;

import src.view.GraphView;
import src.view.GridView;

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
		
		GridView gridView = new GridView(8, 8);
		JPanel buttonPanel = gridView.getButtonPanel();
		mainPanel.add(buttonPanel);

		// Right panel
		JPanel rightPanel = gridView.getRightPanel();
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
