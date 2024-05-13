package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.Utils;

public class SeleccionTruncamiento implements ISeleccion{

  final private Double trunc = 0.5; // entre 0.5 o 0.1 tambien podemos hacer que se seleccione aleatoriamente pero asi no podemos optimizarlo tanto

  @Override
  public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random random) {
    
    ArrayList<Individuo> selection = new ArrayList<>();
    ArrayList<Individuo> sortedPob = new ArrayList<>();

    sortedPob = Utils.sortSample(poblacion);

    int truncSize = (int) (poblacion.size() * trunc);

    int rep = (int) (1/trunc); // el numero de veces que tenemos que coger cada individuo

    for (int i = 0; i < truncSize; i++) {
      for (int j = 0; j < rep; j++) {
        selection.add(sortedPob.get(i));
      }
    }
    return selection;

  }

  @Override
  public String toString() {
    return "Seleccion Truncamiento";
  }
  
}
