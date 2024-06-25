package src.individuo;

import java.util.ArrayList;
import java.util.Arrays;

public class IndividuoFuncion2 extends IndividuoBinario{
  
  private static final ArrayList<Double> MINIMUM = new ArrayList<>(Arrays.asList(-10.0, -6.5));
  private static final ArrayList<Double> MAXIMUM = new ArrayList<>(Arrays.asList(0.0, 0.0));
  private static final int DIM = 2;
  private static final boolean MAXIMICIZACION = false;

  public IndividuoFuncion2(double precision) {
    super(MINIMUM, MAXIMUM, DIM, MAXIMICIZACION, precision);
    this.fitness = calcularFitness();
  }

  public IndividuoFuncion2(IndividuoFuncion2 i) {
    super(i);
    this.fitness = calcularFitness();
  }

  public <T> IndividuoFuncion2(IndividuoFuncion2 i, ArrayList<Boolean> cromosoma) {
    super(i, cromosoma);
    this.fitness = calcularFitness();
  }

  @Override
  public ArrayList<Boolean> getGenotipo() {
    return cromosoma;
  }

  @Override
  public double calcularFitness() {
    double x1 = getFenotipo(0);
    double x2 = getFenotipo(1);
    return Math.sin(x2) * Math.exp(Math.pow(1 - Math.cos(x1), 2)) + Math.cos(x1) * Math.exp(Math.pow(1 - Math.sin(x2), 2)) + Math.pow(x1 - x2, 2);
  }

  @Override
  public String showable() {
    double x1 = getFenotipo(0);
    double x2 = getFenotipo(1);
    return "X1: " + x1 + ", X2: " + x2;
  }


  @Override
  @SuppressWarnings("unchecked")
  public <T> Individuo build(Individuo i, ArrayList<T> valores){
    return new IndividuoFuncion2((IndividuoFuncion2)i , (ArrayList<Boolean>) valores);
  }

  @Override
  public Individuo generaPoblacion(double precision) {
    return new IndividuoFuncion2(precision);
  }

  @Override
  public String toString() {
    return "Funcion 2";
  }
}
