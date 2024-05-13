package src.individuo;

import java.util.ArrayList;

public abstract class IndividuoBinario extends Individuo {

  protected ArrayList<Boolean> cromosoma;

  public IndividuoBinario(ArrayList<Double> min, ArrayList<Double> max, int dimension, boolean tipo, double precision) {
    super(min, max, dimension, tipo, precision);

    this.cromosoma = new ArrayList<Boolean>();
    for (int i = 0; i < Individuo.tamCromosoma; i++) {
      cromosoma.add(Individuo.rand.nextBoolean());
    }
  }

  public IndividuoBinario(IndividuoBinario i) {
    super(i);
    this.cromosoma = new ArrayList<Boolean>();
    for (boolean alelo : i.cromosoma)
      this.cromosoma.add(alelo);
  }

  public IndividuoBinario(IndividuoBinario i, ArrayList<Boolean> cromosoma) {
    super(i);
    this.cromosoma = new ArrayList<>();
    for (boolean alelo : cromosoma)
      this.cromosoma.add(alelo);
  }

  // Siguiendo lo que pone el la practica fenotipo(x) = min + bin2dec(x) * (max - min) / (2^tamCromosoma - 1)
  // donde dim es la la dimension en la que estamos trabajando
  public double getFenotipo(int x) {
    return (Individuo.min.get(x)
        + bin2Dec(x) * (Individuo.max.get(x) - Individuo.min.get(x)) / (Math.pow(2, Individuo.tamCromosoma) - 1));
  }

  @SuppressWarnings("unchecked")
  public ArrayList<Boolean> getGenotipo() {
    return cromosoma;
  }

  public double bin2Dec(int x) {
    double dec = 0;

    for (int i = 0; i < tamGenes.get(x); i++) {
      dec += cromosoma.get(i + tamGenes.get(x) * x) ? Math.pow(2, tamGenes.get(x) - 1 - i) : 0;
    }

    return dec;
  }
}
