package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public class CruceMonopunto implements ICruce {

  @Override
  public ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Random rand, double probCruce) {
    ArrayList<Individuo> hijos = new ArrayList<>();

    // TODO comprobar si esta quita uno de los individuos de la generacion
    for (int i = 0; i < padres.size() - (padres.size() % 2); i += 2) {
      Individuo padre1 = padres.get(i);
      Individuo padre2 = padres.get(i + 1);


      if (rand.nextDouble() < probCruce) {
        int puntoCruce = rand.nextInt(padre1.getTamCromosoma());


      } else {
        hijos.add(padre1);
        hijos.add(padre2);
      }
    }

    return hijos;
  }

  @Override
  public String toString() {
    return "Cruce Monopunto";
  }
}
