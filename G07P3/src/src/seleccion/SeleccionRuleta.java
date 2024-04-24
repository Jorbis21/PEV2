package src.seleccion;

import java.util.ArrayList;
import java.util.Random;


import src.cromosoma.Cromosoma;
import src.utils.Utils;

public class SeleccionRuleta implements ISeleccion{

  @Override
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random random) {
    ArrayList<Integer> fitness = new ArrayList<Integer>();
	ArrayList<Cromosoma> selection = new ArrayList<Cromosoma>();
	double totalFit = 0;
		
	fitness = group(poblacion);

  for(int i = 0; i < fitness.size(); ++i){
    totalFit += fitness.get(i);
  }

  	fitness.set(0, 0);
	for(int i = 1; i < fitness.size(); ++i)
		fitness.set(i, (int)(fitness.get(i) / totalFit) + fitness.get(i - 1));

    for(int i = 0; i < poblacion.size(); ++i) { // procedemos a seleccionar
      double rand = random.nextDouble(); // generamos un numero aleatorio entre [0..1]
      int ind = Utils.findInterval(rand, fitness); // usamos busqueda binaria de forma que qi-1 < random < q
      selection.add(new Cromosoma(poblacion.get(ind))); // añadimos el Cromosoma a la seleccion
    }

    return selection;
  }

  @Override
  public String toString() {
    return "Seleccion por Ruleta";
  }
}