package src.cruce;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public interface ICruce {
  public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Random rand, double probCruce);
}
