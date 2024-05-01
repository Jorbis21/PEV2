package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;

public class MutacionBoolean implements IMutacion{

    @Override
    public Individuo mutar(Individuo indv, Random rand, double probMut) {
        for(int i = 0; i < indv.getTamCromosoma(); i++){
            if(rand.nextDouble() < probMut){
                indv.getGenotipo().set(i, !(boolean)indv.getGenotipo().get(i));
            }
        }
        return indv;
    }
    
    @Override
    public String toString() {
        return "Mutacion Booleana";
    }


}
