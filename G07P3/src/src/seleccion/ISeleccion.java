package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;
import src.utils.Utils;

public interface ISeleccion {
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random rand);

  default public void sortSample(ArrayList<Cromosoma> poblacion){
    poblacion = Utils.sortSample(poblacion);
  }
}
