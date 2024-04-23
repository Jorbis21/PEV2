package src.mutacion;

import java.util.Random;

import src.arbol.Nodo;
import src.cromosoma.Cromosoma;
import src.utils.Pair;

public class MutacionTerminal implements IMutacion {

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut) {
        Cromosoma progMutado = new Cromosoma(programa);
        if(rand.nextDouble() < probMut) {
            String s = progMutado.getRandomTerminal();
            Nodo x = progMutado.getHojaRand();
            if(s == "CONSTANTE") {
            	x.setNumval(new Pair(rand.nextInt(8), rand.nextInt(Cromosoma.dimension)));
            }
            x.setValor(s);
        }
        return new Cromosoma(progMutado);
    }
    
}
