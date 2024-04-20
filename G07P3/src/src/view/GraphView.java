package src.view;

import org.math.plot.*;

public class GraphView {

	private Plot2DPanel plot;
	
	public GraphView() {
		// define your data
		double[] x = { 1, 2, 3, 4, 5, 6 };
		double[] y = { 45, 89, 6, 32, 63, 12 };
		// create your PlotPanel (you can use it as a JPanel)
		plot = new Plot2DPanel();
		// define the legend position
		plot.addLegend("SOUTH");
		// add a line plot to the PlotPanel
		plot.addLinePlot("my plot", x, y);
	}	


	public Plot2DPanel getPlot() {
		return plot;
	}
}


