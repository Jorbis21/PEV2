package src.mutacion;

import java.util.Random;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;

public class MutacionFuncional implements IMutacion {

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut, TableroGlobal tab) {
        Cromosoma progMutado = new Cromosoma(programa, tab);
        if(rand.nextDouble() < probMut) {
            String s = progMutado.getRandomFuncion();
            progMutado.getNodoRand().setValor(s);
        }
        return new Cromosoma(progMutado, tab);
    }
    

    @Override
    public String toString() {
        return "Mutacion Funcional";
    }
}
