package src.individuo;

import java.util.ArrayList;

public abstract class IndividuoBinario extends Individuo<Boolean>{

  public IndividuoBinario(ArrayList<Double> min, ArrayList<Double> max, int dimension, boolean tipo, double precision) {
    super(min, max, dimension, tipo, precision);

    this.cromosoma = new ArrayList<Boolean>();
    for(int i = 0; i < Individuo.tamCromosoma; i++){
      cromosoma.add(Individuo.rand.nextBoolean());
    }
  }

  public IndividuoBinario(IndividuoBinario i){
    super(i);
  }
  
  
}
