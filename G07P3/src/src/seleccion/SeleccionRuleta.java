package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;
import src.utils.Utils;

public class SeleccionRuleta implements ISeleccion{

  @Override
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random random, TableroGlobal tab) {
    ArrayList<Cromosoma> seleccionados = new ArrayList<>();
    double totalFitness = 0;
    
    // Calculate the total fitness of the population
    for (Cromosoma cromosoma : poblacion) {
      totalFitness += cromosoma.getFitness();
    }
    
    // Perform roulette selection
    while (seleccionados.size() < poblacion.size()) {
      double randomValue = random.nextDouble() * totalFitness;
      double cumulativeFitness = 0;
      
      for (Cromosoma cromosoma : poblacion) {
        cumulativeFitness += cromosoma.getFitness();
        
        if (cumulativeFitness >= randomValue) {
          seleccionados.add(cromosoma);
          break;
        }
      }
    }
    
    return seleccionados;
  }

  @Override
  public String toString() {
    return "Seleccion por Ruleta";
  }
}