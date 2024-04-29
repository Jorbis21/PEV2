package src.mutacion;

import java.util.Random;

import src.TableroGlobal;
import src.arbol.Arbol;
import src.cromosoma.Cromosoma;
import src.utils.Pair;


public class MutacionExpansion implements IMutacion {

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut, TableroGlobal tab) {
        Cromosoma progMutado = new Cromosoma(programa, tab);
        if(rand.nextDouble() < probMut) {
            Arbol expansion = new Arbol(1, tab);
            progMutado.getHojaRand().expande(expansion);
        }
        return new Cromosoma(progMutado, tab);
    }
    @Override
    public String toString() {
        return "Mutacion Expansion";
    }
}
