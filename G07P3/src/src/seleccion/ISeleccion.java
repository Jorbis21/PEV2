package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;

public interface ISeleccion {
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random rand, TableroGlobal tab);

  default public ArrayList<Double> group(ArrayList<Cromosoma> poblacion){
		ArrayList<Double> groupedFitness = new ArrayList<Double>();
		
		for(Cromosoma i : poblacion){
			groupedFitness.add(i.getFitness());
		}

		double max = groupedFitness.get(0);
		for(int i = 1; i < groupedFitness.size(); i++){
			if(groupedFitness.get(i) > max)
				max = groupedFitness.get(i);
		}

		for(int i = 0; i < groupedFitness.size(); ++i){
			groupedFitness.set(i, (1.05*max) - groupedFitness.get(i));
		}

		return groupedFitness;
	}
}
