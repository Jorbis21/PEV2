package src.mutacion;

import java.util.Random;

import src.TableroGlobal;
import src.arbol.Nodo;
import src.cromosoma.Cromosoma;
import src.utils.Pair;

public class MutacionTerminal implements IMutacion {

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut, TableroGlobal tab) {
        Cromosoma progMutado = new Cromosoma(programa, tab);
        if(rand.nextDouble() < probMut) {
            String s = progMutado.getRandomTerminal();
            if(s == "CONSTANTE") {
            	progMutado.getHojaRand().setValorNum(s,(new Pair(rand.nextInt(8), rand.nextInt(tab.getDim()))));
            }
            progMutado.getHojaRand().setValorNum(s,new Pair());
        }
        return new Cromosoma(progMutado, tab);
    }
    

    @Override
    public String toString() {
        return "Mutacion Terminal";
    }
}
