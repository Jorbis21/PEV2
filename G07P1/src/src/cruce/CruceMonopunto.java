package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public class CruceMonopunto implements ICruce {

  @Override
  public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Random rand, double probCruce) {
    ArrayList<Individuo> hijos = new ArrayList<>();

    int numPadres = padres.size();
    for (int i = 0; i < numPadres; i += 2) {
      Individuo padre1 = padres.get(i);
      Individuo padre2 = padres.get(i + 1);

      if (rand.nextDouble() < probCruce) {
        int puntoCruce = rand.nextInt(padre1.getGenotipo().size());

        ArrayList<Object> genotipoHijo1 = new ArrayList<>(padre1.getGenotipo().subList(0, puntoCruce));
        genotipoHijo1.addAll(padre2.getGenotipo().subList(puntoCruce, padre2.getGenotipo().size()));

        ArrayList<Object> genotipoHijo2 = new ArrayList<>(padre2.getGenotipo().subList(0, puntoCruce));
        genotipoHijo2.addAll(padre1.getGenotipo().subList(puntoCruce, padre1.getGenotipo().size()));

        Individuo hijo1 = padre1.build(padre1, genotipoHijo1);
        Individuo hijo2 = padre2.build(padre2, genotipoHijo2);

        hijos.add(hijo1);
        hijos.add(hijo2);
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
