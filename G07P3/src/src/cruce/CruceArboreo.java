package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;

public class CruceArboreo implements ICruce {

  @Override
  public ArrayList<Cromosoma> cruzar(ArrayList<Cromosoma> padres, Random rand, double probCruce) {
    ArrayList<Cromosoma> nuevaGeneracion = new ArrayList<Cromosoma>();

    Cromosoma padre1 = padres.get(0);
    Cromosoma padre2 = padres.get(1);

    if (rand.nextDouble() < probCruce) {
      // get random nodes from both parents
      Cromosoma hijo1 = new Cromosoma(padre1.copia());
      Cromosoma hijo2 = new Cromosoma(padre2.copia());

      Nodo nodo1 = hijo1.getArbol().getRandomNode();
      Nodo nodo2 = hijo2.getArbol().getRandomNode();

      /*
       * swap nodes -- no se muy bien como hacerlo sin romper la encapsulacion que
       * tenemos. Podriamos implementar un metodo en Arbol
       * que haga el swap
       */

      // despues de hacer el swap
      nuevaGeneracion.add(hijo1);
      nuevaGeneracion.add(hijo2);
    } else {
      nuevaGeneracion.add(padre1);
      nuevaGeneracion.add(padre2);
    }

    return nuevaGeneracion;
  }

}
