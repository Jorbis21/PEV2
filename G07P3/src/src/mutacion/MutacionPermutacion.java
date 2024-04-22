package src.mutacion;

import java.util.Random;

import src.arbol.Nodo;
import src.cromosoma.Cromosoma;

public class MutacionPermutacion implements IMutacion{

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut) {
        Cromosoma progMutado = new Cromosoma(programa);

        if (rand.nextDouble() < probMut) {
            Nodo nodo = progMutado.getNodoRand();
            nodo.permutaHijos();
        }
        return new Cromosoma(progMutado);  
    }
}
