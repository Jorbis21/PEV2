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

  @Override
  public String toString() {
    return "Funcion 1";
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
}