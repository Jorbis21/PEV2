package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;

public class SeleccionRestos implements ISeleccion{

    @Override
    public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random rand, TableroGlobal tab) {
        ArrayList<Cromosoma> seleccionados = new ArrayList<>();

        final int tamOriginal = poblacion.size();
        int sel = 0;
    
        int fitnessTotal = 0;
        double[] probs = new double[poblacion.size()];
    
        for (int i = 0; i < poblacion.size(); ++i) {
          probs[i] = poblacion.get(i).getFitness();
          fitnessTotal += probs[i];
        }
    
        for (int i = 0; i < poblacion.size(); ++i) {
          probs[i] = probs[i] / fitnessTotal;
        }
    
        for (int i = 0; i < poblacion.size(); ++i) {
          int vecesSeleccionado = (int) Math.floor(probs[i] * tamOriginal);
    
          if (vecesSeleccionado > 0) {
            sel++;
            seleccionados.add(new Cromosoma(poblacion.get(i), tab));
            poblacion.remove(i);
          }
        }
    
        ISeleccion aux = new SeleccionRuleta();
    
        ArrayList<Cromosoma> ultimos = aux.select(poblacion, rand, tab);
    
        for (Cromosoma i : ultimos) {
          seleccionados.add(new Cromosoma(i, tab));
        }
    
        return seleccionados;
    }

    public String toString(){
        return "Seleccion por Restos";
    }
}