package src.individuo;

import java.util.ArrayList;
import java.util.Random;

public abstract class Individuo<T> {
  static protected Random rand  = new Random();
  static protected int tamCromosoma;
  static protected boolean maximizacion;
  static protected ArrayList<Double> min, max;
  static protected double precision;

  protected ArrayList<T> cromosoma;
  protected ArrayList<Integer> tamGenes;
  protected int dimension;
	protected double fitness;
  
  abstract public double getFenotipo(int x);
  abstract public ArrayList<T> getGenotipo();

  public Individuo(ArrayList<Double> min, ArrayList<Double> max, int dimension, boolean tipo, double precision) {
		Individuo.min = min;
    Individuo.max = max;
    Individuo.maximizacion = tipo;
    Individuo.precision = precision;

    this.tamGenes = new ArrayList<Integer>();
    int tamTotal = 0;
    this.dimension = dimension;
    for(int i = 0; i < dimension; i++){
      int aux = tamGen(precision, min.get(i), max.get(i));
      tamGenes.add(aux);
      tamTotal += aux;
    }
    Individuo.tamCromosoma = tamTotal;

    this.fitness = calcularFitness();
	}
  
  public Individuo(Individuo<T> i){
    this.cromosoma = new ArrayList<T>();
    this.dimension = i.dimension;
		this.fitness = i.fitness;

    for (int index = 0; index < i.cromosoma.size(); index++) {
      this.cromosoma.add(i.cromosoma.get(index));
    }
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
