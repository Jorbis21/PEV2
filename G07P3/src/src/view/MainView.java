package src.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.math.plot.Plot2DPanel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JTabbedPane;

import src.view.GraphView;
import src.view.GridView;

public class MainView{
	public MainView() {
		// grid baglayout variables
		final boolean shouldFill = false;

		JFrame frame = new JFrame("Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - 10, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 10));

		JTabbedPane tabbedPane = new JTabbedPane();

		// First tab - Grid ---------------------------------------------------------------------------------
		JPanel mainPanel = new JPanel();

		mainPanel.setSize(new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - 10, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 10));
		tabbedPane.addTab("Grid", mainPanel);
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill)
			c.fill = GridBagConstraints.HORIZONTAL;

		GridView gridView = new GridView(8, 8);
		JPanel buttonPanel = gridView.getButtonPanel();
		c.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
		c.ipady = 0; // reset to default
		c.weightx = 1.0 / 3.0; // Fill one-third of the available horizontal space
		c.weighty = 1.0 / 3.0; // Fill one-third of the available vertical space
		c.gridwidth = 4; // 2 columns wide, 2 times wider
		c.gridheight = 4; // 2 rows tall, 2 times taller
		c.gridx = 0; // leftmost column
		c.gridy = 0; // top row
		mainPanel.add(buttonPanel, c);

		// Text panel placed at the bottom of the bottomPanel, 2 columns wide and 1 row tall
		JScrollPane bottomRightPanel = gridView.getTextPanel();
		c.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
		c.ipady = 0;	//reset to default
		c.weightx = 2.0; // Fill the available horizontal space, 2 times wider
		c.weighty = 1.0; // Fill the available vertical space
		c.gridwidth = 4; //2 columns wide, 2 times wider
		c.gridheight = 2; //1 row tall
		c.gridx = 0; //leftmost column
		c.gridy = 4; //bottom row
		mainPanel.add(bottomRightPanel, c);

		// Right panel placed to the right of the left panel, 1 column wide and 3 rows tall
		JPanel rightPanel = gridView.getRightPanel();
		c.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
		c.ipady = 40;	//make this component tall
		c.weightx = 1.0; //Fill the available horizontal space
		c.gridwidth = 2; //1 column wide
		c.gridheight = 3; //3 rows tall
		c.gridx = 4; //rightmost column
		c.gridy = 0; //top row
		mainPanel.add(rightPanel, c);
		
		// Second tab - Graph --------------------------------------------------------------------------------
		JPanel graphPanel = new JPanel();
		graphPanel.setPreferredSize(new Dimension(1600, 800)); // Set preferred size of graphPanel
		graphPanel.setLayout(new GridLayout(1,1));
		
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
