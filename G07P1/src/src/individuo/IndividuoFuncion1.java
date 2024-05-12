package src.individuo;

import java.util.ArrayList;
import java.util.Arrays;

public class IndividuoFuncion1 extends IndividuoBinario {

  private static final ArrayList<Double> MINIMUM = new ArrayList<>(Arrays.asList(-10.0, -10.0));
  private static final ArrayList<Double> MAXIMUM = new ArrayList<>(Arrays.asList(10.0, 10.0));
  private static final int DIM = 2;
  private static final boolean MAXIMICIZACION = true;

  public IndividuoFuncion1(double precision) {
    super(MINIMUM, MAXIMUM, DIM, MAXIMICIZACION, precision);
    this.fitness = calcularFitness();
  }

  public IndividuoFuncion1(IndividuoFuncion1 i) {
    super(i);
    this.fitness = calcularFitness();
  }

  public <T> IndividuoFuncion1(IndividuoFuncion1 i, ArrayList<Boolean> cromosoma) {
    super(i, cromosoma);
    this.fitness = calcularFitness();
  }

  public Individuo build(IndividuoFuncion1 i, ArrayList<Boolean> cromosoma) {
    return new IndividuoFuncion1(i, cromosoma);
  }

  @Override
  public ArrayList<Boolean> getGenotipo() {
    return cromosoma;
  }

  @Override
  public double calcularFitness() {
    double x1 = getFenotipo(0);
    double x2 = getFenotipo(1);
    return Math.pow(x1, 2) + (2 * Math.pow(x2, 2));
  }

  @Override
  public String showable() {
    double x1 = getFenotipo(0);
    double x2 = getFenotipo(1);
    return "X1: " + x1 + " X2: " + x2;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> Individuo build(Individuo i, ArrayList<T> valores){
    return new IndividuoFuncion1((IndividuoFuncion1)i , (ArrayList<Boolean>) valores);
  }

  @Override
  public Individuo generaPoblacion(double precision) {
    return new IndividuoFuncion1(precision);
  }

  @Override
  public String toString() {
    return "Funcion 1";
  }
}