package src.mutacion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public class MutacionBoolean implements IMutacion{

    @Override
    public Individuo mutar(Individuo indv, Random rand, double probMut) {
        ArrayList<Boolean> genes = indv.getGenotipo();
        for (int i = 0; i < genes.size(); i++) {
            if (rand.nextDouble() < probMut) {
                genes.set(i, !genes.get(i));
            }
        }
        return indv.build(indv, genes);
    }
    
    @Override
    public String toString() {
        return "Mutacion Booleana";
    }


}
