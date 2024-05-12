package src.individuo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Individuo {
  static protected Random rand  = new Random();
  static protected int tamCromosoma;
  static protected boolean maximizacion;
  static protected ArrayList<Double> min, max;
  static protected double precision;
  static protected int dimension;

  protected ArrayList<Integer> tamGenes;
	protected double fitness;
  
  abstract public double getFenotipo(int x);
  abstract public <T> ArrayList<T> getGenotipo();
  abstract public double calcularFitness();
  abstract public <T> Individuo build(Individuo i, ArrayList<T> valores);
  abstract public Individuo generaPoblacion(double precision);
  abstract public String showable();

  public Individuo(ArrayList<Double> min, ArrayList<Double> max, int dimension, boolean tipo, double precision) {
		Individuo.min = min;
    Individuo.max = max;
    Individuo.maximizacion = tipo;
    Individuo.precision = precision;
    Individuo.dimension = dimension;

    this.tamGenes = new ArrayList<Integer>();
    int tamTotal = 0;
    
    for (int i = 0; i < dimension; i++) {
      int tamGen = tamGen(precision, min.get(i), max.get(i));
      this.tamGenes.add(tamGen);
      tamTotal += tamGen;
    }
    Individuo.tamCromosoma = tamTotal;
	}
  
  public Individuo(Individuo i){
    this.tamGenes = new ArrayList<Integer>();
    int tamTotal = 0;

    for (int j = 0; j < dimension; j++) {
      int tamGen = tamGen(Individuo.precision, Individuo.min.get(j), Individuo.max.get(j));
      this.tamGenes.add(tamGen);
      tamTotal += tamGen;
    }

    Individuo.tamCromosoma = tamTotal;
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

  public static boolean isMaximizacion() {
    return maximizacion;
  }
  

}
