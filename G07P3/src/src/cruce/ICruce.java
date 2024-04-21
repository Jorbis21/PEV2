package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;


public interface ICruce {
  public ArrayList<Cromosoma> cruzar(ArrayList<Cromosoma> padres, Random rand, double probCruce);
}
