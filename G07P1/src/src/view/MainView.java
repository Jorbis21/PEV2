package src.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;

public class MainView {
	public MainView() {

		JFrame window = new JFrame("Practica 1 G07");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setLayout(new GridLayout(1, 1));

		JPanel windowPanel = new JPanel();
		windowPanel.setLayout(new GridLayout(1, 2, 10, 10));
		windowPanel.setSize(new Dimension(1600, 800));

		window.add(windowPanel);

		
		
		// Graph panel ---------------------------------------------------------------------------------
		JPanel graphPanel = new JPanel();
		graphPanel.setLayout(new GridLayout(1, 1));
		graphPanel.setSize(new Dimension(1600, 800));
		Plot2DPanel plot = new GraphView().getPlot();
		graphPanel.add(plot);
		windowPanel.add(graphPanel);




		// Option panel ---------------------------------------------------------------------------------
		
		JPanel optionsPanel = new OptionsView().getOptionsPanel();
		window.setSize(new Dimension(1600, 800));
		windowPanel.add(optionsPanel);

		window.setVisible(true);
	}
	
	public static void main(String args[]) {
		new MainView();
	}
}
