package src.view;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainView {

	public MainView() {
		final boolean shouldFill = false;

		JFrame frame = new JFrame("Practica 1 G07");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width - 10, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height - 10));
		
		
	}
	
	public static void main(String args[]) {
		System.setProperty("org.graphstream.ui", "swing");
		new MainView();
	}
}
