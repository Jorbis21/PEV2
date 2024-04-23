package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.arbol.Nodo;
import src.cromosoma.Cromosoma;

public class CruceArboreo implements ICruce {

  @Override
  public ArrayList<Cromosoma> cruzar(ArrayList<Cromosoma> padres, Random rand, double probCruce) {
    ArrayList<Cromosoma> nuevaGeneracion = new ArrayList<Cromosoma>();

    Cromosoma padre1 = padres.get(0);
    Cromosoma padre2 = padres.get(1);

    if (rand.nextDouble() < probCruce) {
      Cromosoma hijo1 = new Cromosoma(padre1);
      Cromosoma hijo2 = new Cromosoma(padre2);

      Nodo nodoHijo1 = hijo1.getNodoRand();
      Nodo nodoHijo2 = hijo2.getNodoRand();

      hijo1.swapSubtrees(nodoHijo1, nodoHijo2);
      
      nuevaGeneracion.add(hijo1);
      nuevaGeneracion.add(hijo2);
    } else {
      nuevaGeneracion.add(new Cromosoma(padre1));
      nuevaGeneracion.add(new Cromosoma(padre2));
    }

    return nuevaGeneracion;
  }

}
