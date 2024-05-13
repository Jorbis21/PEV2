package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.Utils;

public class SeleccionRestos implements ISeleccion {

  @Override
  public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand) {
    SeleccionRuleta ruleta = new SeleccionRuleta();
    ArrayList<Individuo> sortedPob = new ArrayList<>();
    ArrayList<Individuo> selection = new ArrayList<>();
    ArrayList<Double> fitness = new ArrayList<>();
    
    double totalFit = 0;
    double totalNum = poblacion.size();

    sortedPob = Utils.sortSample(poblacion);

    for(Individuo ind : sortedPob)
      fitness.add(ind.getFitness());

    for (double i : fitness) // Calculamos la suma total de los fitness
      totalFit += i;

    for (int i = 0; i < fitness.size(); ++i) { // Calculamos la probabilidadd
      fitness.set(i, (fitness.get(i) / totalFit));
    }

    for (int i = 0; i < totalNum; ++i) { // Seleccionamos la primera tanda de individuos
      for (int j = 0; j < Math.floor(fitness.get(i) * totalNum); ++j)
        selection.add(sortedPob.get(i));
    }

    ArrayList<Individuo> remaining = ruleta.select(poblacion, rand); // hacemos la segunda seleccion de los que quedan
    for (int i = 0; i < remaining.size() && selection.size() < totalNum; ++i)
      selection.add(remaining.get(i));

    return selection;
  }

  @Override
  public String toString() {
    return "Seleccion Restos";
  }
}
