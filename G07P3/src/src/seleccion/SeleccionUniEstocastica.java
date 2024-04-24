package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;
import src.utils.Utils;

public class SeleccionUniEstocastica implements ISeleccion{

  @Override
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random random) {
	  	ArrayList<Double> probability = new ArrayList<Double>();
		ArrayList<Cromosoma> selection = new ArrayList<Cromosoma>();
		double totalFit = 0;
		int tamPob = poblacion.size();
		
		probability = group(poblacion);
		
		for(double i : probability) // Calculamos la suma total de los fitness
			totalFit += i;
		
		for(int i = 0; i < probability.size(); ++i) // Sumamos la probabilidad acumulada, al primero se le suma 0 y al resto se acumula
			probability.set(i, (probability.get(i) / totalFit) + (i == 0 ? 0 : probability.get(i-1)));
		
		double distPtr = 1.0 / tamPob; // calculamos la distancias entre las marcas.
		double rand = random.nextDouble(0, distPtr); // pos de primera marca se obtiene a partir de rand entre [0, distPtr]
		
		for(int i = 0; i < tamPob; ++i) { // procedemos a seleccionar
			int ind = Utils.findInterval(rand, probability); // usamos busqueda binaria de forma que qi-1 < random < q
			selection.add(new Cromosoma(poblacion.get(ind))); // añadimos nuestro Cromosoma a la seleccion
			//rand = ((distPtr + i - 1) / tamPob);
			rand += distPtr % 1.0;
		}
		return selection;
  }

	@Override
	public String toString() {
		return "Seleccion Universal Estocastica";
	}
}