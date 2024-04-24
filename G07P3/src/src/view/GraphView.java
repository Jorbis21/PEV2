package src.view;

import java.awt.Color;

import org.math.plot.*;

public class GraphView {

	private static Plot2DPanel plot;

	public GraphView() {
		// define your data
		double[] numGeneraciones = new double[100];
		double[] mejorHistorico = new double[100];
		double[] mejorGeneracion = new double[100];
		double[] mediaFitness = new double[100];
		// create your PlotPanel (you can use it as a JPanel)
		plot = new Plot2DPanel();
		// define the legend position
		plot.addLegend("SOUTH");
		// add a line plot to the PlotPanel
		plot.addLinePlot("Mejor Absoluto", Color.BLUE, numGeneraciones, mejorHistorico);
		plot.addLinePlot("Mejor Generacion", Color.RED, numGeneraciones, mejorGeneracion);
		plot.addLinePlot("Media Fitness", Color.GREEN, numGeneraciones, mediaFitness);
	}

	public Plot2DPanel getPlot() {
		return plot;
	}

	public static void plotLines(double[] numGeneraciones, double[] mejorHistorico, double[] mejorGeneracion,
			double[] mediaFitness) {
		plot.removeAllPlots();
		plot.addLinePlot("Mejor Absoluto", Color.BLUE, numGeneraciones, mejorHistorico);
		plot.addLinePlot("Mejor Generacion", Color.RED, numGeneraciones, mejorGeneracion);
		plot.addLinePlot("Media Fitness", Color.GREEN, numGeneraciones, mediaFitness);
	}
}
