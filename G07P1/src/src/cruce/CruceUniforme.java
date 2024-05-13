package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public class CruceUniforme implements ICruce{

  @Override
  public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Random rand, double probCruce) {
    ArrayList<Individuo> hijos = new ArrayList<>();

    int numPadres = padres.size();
    for (int i = 0; i < numPadres; i += 2) {
      Individuo padre1 = padres.get(i);
      Individuo padre2 = padres.get(i + 1);

      if (rand.nextDouble() < probCruce) {
        ArrayList<Object> genotipoHijo1 = new ArrayList<>();
        ArrayList<Object> genotipoHijo2 = new ArrayList<>();
        for (int j = 0; j < padre1.getGenotipo().size(); j++) {
          if(rand.nextDouble() > 0.5){
            genotipoHijo1.add(padre1.getGenotipo().get(j));
            genotipoHijo2.add(padre2.getGenotipo().get(j));
          } else {
            genotipoHijo1.add(padre2.getGenotipo().get(j));
            genotipoHijo2.add(padre1.getGenotipo().get(j));
          }
        }

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
    return "Cruce Uniforme";
  }
  
}
