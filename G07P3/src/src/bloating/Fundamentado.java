package src.bloating;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;

public class Fundamentado implements IBloating{

	@Override
	public void control(int profundidadMedia, int mediaFitness, ArrayList<Cromosoma> poblacion, Random rand,
			double probMuerte, int actGen, int numGen) {
		double coeficiente;
		double[] fitness = new double[poblacion.size()];
		double[] profundidad = new double[poblacion.size()];
		
		for(int i = 0; i < poblacion.size(); i++) {
			fitness[i] = poblacion.get(i).getFitness();
			profundidad[i] = poblacion.get(i).getGenotipo().getProfundidad();
		}
		
		coeficiente = Math.max(Covarianza(fitness, profundidad), 0) / Varianza(fitness);
		double extra = Math.sqrt(actGen/numGen);
		extra *= coeficiente;
		coeficiente += extra * 0.9;
		
		for(Cromosoma cr : poblacion) {
			cr.setFitness((int)(cr.getFitness() + coeficiente * cr.getGenotipo().getProfundidad()));
		}
	}
	
	private static double Varianza(double[] valores) {
		int n = valores.length;
		double media = 0.0;
		double sumatoria = 0.0;
		
		// Calcular la media
		for (int i = 0; i < n; i++) {
			media += valores[i];
		}
		media /= n;
		
		// Calcular la sumatoria
		for (int i = 0; i < n; i++) {
			sumatoria += Math.pow((valores[i] - media), 2);
		}
		
		// Calcular la varianza
		double varianza = sumatoria / (n - 1);
		return varianza;
	}

	private static double Covarianza(double[] valores1, double[] valores2) {
		int n = valores1.length;
		double media1 = 0.0;
		double media2 = 0.0;
		double sumatoria = 0.0;
		
		// Calcular las medias
		for (int i = 0; i < n; i++) {
			media1 += valores1[i];
			media2 += valores2[i];
		}
		media1 /= n;
		media2 /= n;
		
		// Calcular la sumatoria
		for (int i = 0; i < n; i++) {
			sumatoria += (valores1[i] - media1) * (valores2[i] - media2);
		}
		
		// Calcular la covarianza
		double covarianza = sumatoria / (n - 1);
		return covarianza;
	}
	public String toString() {
		return "Fundamentado";
	}
}
