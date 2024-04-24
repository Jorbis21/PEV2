package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;

public class SeleccionRestos implements ISeleccion{

//REVISAR>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random rand) {
        SeleccionRuleta ruleta = new SeleccionRuleta();
        ArrayList<Double> prob = new ArrayList<Double>();
		ArrayList<Cromosoma> selection = new ArrayList<Cromosoma>();
        int totalFit = 0;
        int totalNum = poblacion.size();

        prob = group(poblacion);

        for(double i : prob)
            totalFit += i;

        for(int i = 0; i < prob.size(); ++i) {
            prob.set(i, (prob.get(i) / totalFit));
        }

        for(int i = 0; i < totalNum; ++i) {
            for(int j = 0; j < Math.floor(prob.get(i) * totalNum); ++j) 
                selection.add(new Cromosoma(poblacion.get(i)));
        }

        ArrayList<Cromosoma> remaining = ruleta.select(poblacion, rand);
        for(int i = 0; i < remaining.size()	&& selection.size() < totalNum; ++i)
            selection.add(new Cromosoma(remaining.get(i)));

		return selection;
    }

    public String toString(){
        return "Seleccion por Restos";
    }
}