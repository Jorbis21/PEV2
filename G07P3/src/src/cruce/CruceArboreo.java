package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.TableroGlobal;
import src.arbol.Nodo;
import src.cromosoma.Cromosoma;

public class CruceArboreo implements ICruce {

  @Override
  public ArrayList<Cromosoma> cruzar(ArrayList<Cromosoma> padres, Random rand, double probCruce, TableroGlobal tab) {
    ArrayList<Cromosoma> nuevaGeneracion = new ArrayList<Cromosoma>();

    for(int i = 0; i < padres.size() - padres.size()%2; i+=2) {
    	Cromosoma padre1 = padres.get(i);
        Cromosoma padre2 = padres.get(i+1);

        if (rand.nextDouble() < probCruce) {
          Cromosoma hijo1 = new Cromosoma(padre1, tab);
          Cromosoma hijo2 = new Cromosoma(padre2, tab);

          Nodo nodoHijo1 = hijo1.getNodoRand();
          Nodo nodoHijo2 = hijo2.getNodoRand();

          hijo1.swapSubtrees(nodoHijo1, nodoHijo2);
          
          nuevaGeneracion.add(new Cromosoma(hijo1, tab));
          nuevaGeneracion.add(new Cromosoma(hijo2, tab));
        }
        else {
        	 nuevaGeneracion.add(new Cromosoma(padre1, tab));
             nuevaGeneracion.add(new Cromosoma(padre2, tab));
        }
    }
    return nuevaGeneracion;
  }

}
