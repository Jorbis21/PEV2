package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;
import src.utils.Utils;

public class SeleccionUniEstocastica implements ISeleccion{

  @Override
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random random, TableroGlobal tab) {
		ArrayList<Cromosoma> selection = new ArrayList<Cromosoma>();

		double totalFitness = 0.0;
		for (Cromosoma cromosoma : poblacion) {
			totalFitness += cromosoma.getFitness();
		}

		double averageFitness = totalFitness / poblacion.size();
		double pointer = random.nextDouble() * averageFitness;

		double cumulativeFitness = 0.0;
		for (Cromosoma cromosoma : poblacion) {
			cumulativeFitness += cromosoma.getFitness();
			if (cumulativeFitness >= pointer) {
				selection.add(cromosoma);
				pointer += averageFitness;
			}
		}

		return selection;
  }

	@Override
	public String toString() {
		return "Seleccion Universal Estocastica";
	}
}