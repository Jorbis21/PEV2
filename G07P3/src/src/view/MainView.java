package src.view;


import javax.swing.JFrame;
import javax.swing.JPanel;

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
		final boolean shouldFill = true;

		JFrame frame = new JFrame("Grid");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1500, 800);

		JTabbedPane tabbedPane = new JTabbedPane();

		// First tab - Grid ---------------------------------------------------------------------------------
		JPanel mainPanel = new JPanel();
		/* 
	   * The right panel will have two Panels, one below the other and the left panel will only have one
		 * The right panel will have a text panel that will be placed under the button panel.
		 */


		mainPanel.setPreferredSize(new Dimension(1500, 800)); // Set preferred size of mainPanel
		tabbedPane.addTab("Grid", mainPanel);
		// Grid bag layout
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		// Left panel that be placed bottom left. will be 2 columns wide and 2 rows tall.
		GridView gridView = new GridView(8, 8);
		JPanel buttonPanel = gridView.getButtonPanel();
		c.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
		c.ipady = 0;	//reset to default 
		c.weightx = 2.0; // Fill the available horizontal space, 2 times wider
		c.weighty = 2.0; // Fill the available vertical space, 2 times taller
		c.gridwidth = 4; //2 columns wide, 2 times wider
		c.gridheight = 4; //2 rows tall, 2 times taller
		c.gridx = 0; //leftmost column
		c.gridy = 0; //top row
		mainPanel.add(buttonPanel, c);

		// Text panel placed at the bottom of the bottomPanel, 2 columns wide and 1 row tall
		JPanel bottomRightPanel = gridView.getTextPanel();
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
