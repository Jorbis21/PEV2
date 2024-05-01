package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.Utils;

public class SeleccionRuleta implements ISeleccion{

  @Override
  public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random random) {
    ArrayList<Individuo> seleccionados = new ArrayList<>();
    ArrayList<Double> fitness = new ArrayList<>();
    double sumaFitness = 0;
    
    for (Individuo individuo : poblacion) {
      fitness.add(individuo.getFitness());
      sumaFitness += individuo.getFitness();
    }

    for(int i = 0; i < poblacion.size(); ++i) {
      fitness.set(i, ((fitness.get(i) / sumaFitness) + (i == 0 ? 0 : fitness.get(i - 1))));
    }

    for(int i = 0; i < poblacion.size(); ++i) {
      double randomValue = random.nextDouble();
      int index = Utils.findInterval(randomValue, fitness);
      seleccionados.add(poblacion.get(index));
    }

    return seleccionados;
  }

  
  @Override
  public String toString() {
    return "Seleccion Ruleta";
  }
}
