package src.individuo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Individuo {
  static protected Random rand  = new Random();
  static protected int tamCromosoma;
  static protected boolean maximizacion;
  static protected ArrayList<Double> min, max;
  static protected double precision;

  protected ArrayList<Integer> tamGenes;
  protected int dimension;
	protected double fitness;
  
  abstract public double getFenotipo(int x);
  abstract public <T> ArrayList<T> getGenotipo();

  public Individuo(ArrayList<Double> min, ArrayList<Double> max, int dimension, boolean tipo, double precision) {
		Individuo.min = min;
    Individuo.max = max;
    Individuo.maximizacion = tipo;
    Individuo.precision = precision;

    this.tamGenes = new ArrayList<Integer>();
    int tamTotal = 0;
    this.dimension = dimension;
    
    for (int i = 0; i < dimension; i++) {
      int tamGen = tamGen(precision, min.get(i), max.get(i));
      this.tamGenes.add(tamGen);
      tamTotal += tamGen;
    }

    Individuo.tamCromosoma = tamTotal;
	}
  
  public Individuo(Individuo i){
    this.dimension = i.dimension;
		this.fitness = i.fitness;
  }

  public int tamGen(double precision, double min, double max) {
    return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
  }

  // Getters and Setters
  public double getFitness() {
    return fitness;
  }

  public int getTamCromosoma() {
    return tamCromosoma;
  }

  public abstract double calcularFitness();

}
