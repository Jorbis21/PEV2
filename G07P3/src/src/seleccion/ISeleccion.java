package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;
import src.utils.Utils;

public interface ISeleccion {
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random rand);

  default public ArrayList<Integer> group(ArrayList<Cromosoma> poblacion){
		ArrayList<Integer> groupedFitness = new ArrayList<Integer>();
		
		for(Cromosoma i : poblacion){
			groupedFitness.add(i.getFitness());
		}

		double max = 0;
		for(int i = 1; i < groupedFitness.size(); i++){
			if(groupedFitness.get(i) > max)
				max = groupedFitness.get(i);
		}

		for(int i = 0; i < groupedFitness.size(); ++i){
			groupedFitness.set(i, (int)(1.05*max) - groupedFitness.get(i));
		}

		poblacion = Utils.sortSample(poblacion);
		return groupedFitness;
	}
}
