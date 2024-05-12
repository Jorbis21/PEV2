package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.Utils;

public class SeleccionRuleta implements ISeleccion{

  @Override
  public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random random) {

    double totalFitness = 0;
    ArrayList<Individuo> selected = new ArrayList<>();
    
    ArrayList<Individuo> sortedPob = new ArrayList<>();
    sortedPob.addAll(poblacion);
    Utils.sortSample(sortedPob);

    ArrayList<Double> fitness = new ArrayList<>();
    for(int i = 0; i < sortedPob.size(); i++)
      fitness.add(sortedPob.get(i).getFitness());

    for(int i = 0; i < fitness.size(); i++)
			totalFitness += fitness.get(i);

    for(int i = 0; i < sortedPob.size(); i++)
			fitness.set(i, (fitness.get(i) / totalFitness) + (i > 0 ? fitness.get(i-1) : 0));
    
    for(int i = 0; i < sortedPob.size(); i++){
      double r = random.nextDouble();
      int index = Utils.findInterval(r, fitness);
      Individuo ind = sortedPob.get(index);
      selected.add(ind.build(ind, ind.getGenotipo()));
    }

    return selected;
  }

  
  @Override
  public String toString() {
    return "Seleccion Ruleta";
  }
}
