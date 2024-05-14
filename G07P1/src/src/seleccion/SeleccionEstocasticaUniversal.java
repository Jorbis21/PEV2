package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public class SeleccionEstocasticaUniversal implements ISeleccion{

  @Override
  public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random random) {
    // Calculate the total fitness of the population
    double totalFitness = 0.0;
    for (Individuo individuo : poblacion) {
      totalFitness += individuo.getFitness();
    }

    // Calculate the average fitness
    double averageFitness = totalFitness / poblacion.size();

    // Calculate the selection threshold
    double selectionThreshold = random.nextDouble() * averageFitness;

    // Perform stochastic selection
    ArrayList<Individuo> selectedPoblacion = new ArrayList<>();
    double cumulativeFitness = 0.0;
    for (Individuo individuo : poblacion) {
      cumulativeFitness += individuo.getFitness();
      if (cumulativeFitness >= selectionThreshold) {
        selectedPoblacion.add(individuo);
        if (selectedPoblacion.size() == poblacion.size()) {
          break; // Stop selection if the selected population is full
        }
        selectionThreshold += averageFitness;
      }
    }

    return selectedPoblacion;
  }
  
  @Override
  public String toString() {
    return "Seleccion Estocastica Universal";
  }
}
