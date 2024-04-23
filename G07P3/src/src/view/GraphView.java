package src.view;

import org.math.plot.*;

public class GraphView {

	private Plot2DPanel plot;
	
	public GraphView() {
		// define your data
		double[] x = { 0, 1, 2, 3, 4, 5, 6 };
		double[] y = { 1, 4, 14, 32, 36, 40, 42};
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


