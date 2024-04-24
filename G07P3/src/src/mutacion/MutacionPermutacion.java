package src.mutacion;

import java.util.Random;

import src.TableroGlobal;
import src.arbol.Nodo;
import src.cromosoma.Cromosoma;

public class MutacionPermutacion implements IMutacion{

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut, TableroGlobal tab) {
        Cromosoma progMutado = new Cromosoma(programa, tab);

        if (rand.nextDouble() < probMut) {
            Nodo nodo = progMutado.getNodoRand();
            nodo.permutaHijos();
        }
        return new Cromosoma(progMutado, tab);  
    }

    @Override
    public String toString() {
        return "Mutacion Permutacion";
    }
}
