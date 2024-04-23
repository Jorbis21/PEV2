package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;

public class SeleccionTruncamiento implements ISeleccion{

	@Override
	public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random rand) {
		double trunc = 0.5;
		ArrayList<Cromosoma> selection = new ArrayList<Cromosoma>();
		
		int numSeleccionados = (int) Math.round(trunc * poblacion.size());
		int vecesReproducido = (int) Math.round(1 / trunc);
		
		for(int i = 0; i < numSeleccionados; ++i) {
			for(int j = 0; j < vecesReproducido; ++j) {
				selection.add(new Cromosoma(poblacion.get(i)));
			}
		}
		return selection;
	}
	@Override
	public String toString() {
		return "Seleccion por Truncamiento";
	}
}