package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;

public class SeleccionTruncamiento implements ISeleccion{

	@Override
	public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random rand, TableroGlobal tab) {
		double trunc = 0.5;

		int numSeleccionados = (int) (poblacion.size() * trunc);
		int vecesReproducido = (int) Math.round(1 / trunc);
		ArrayList<Cromosoma> seleccionados = new ArrayList<>();

		// Sort the population in descending order based on fitness
		poblacion.sort((c1, c2) -> Double.compare(c2.getFitness(), c1.getFitness()));

		for(int i = 0; i < numSeleccionados; ++i){
			for (int j = 0; j < vecesReproducido; ++j) {
				seleccionados.add(poblacion.get(i));
			}
		}
		
		return seleccionados;
	}
	@Override
	public String toString() {
		return "Seleccion por Truncamiento";
	}
}