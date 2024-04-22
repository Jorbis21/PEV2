package src.mutacion;

import java.util.Random;

import src.cromosoma.Cromosoma;

public class MutacionTerminal implements IMutacion {

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut) {
        Cromosoma progMutado = new Cromosoma(programa);
        if(rand.nextDouble() < probMut) {
            String s = progMutado.getRandomTerminal();
            progMutado.getHojaRand().setValor(s);
        }
        return new Cromosoma(progMutado);
    }
    
}
