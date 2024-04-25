package src.mutacion;

import java.util.Random;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;
import src.utils.Pair;

public class MutacionContraccion implements IMutacion{

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut, TableroGlobal tab) {
        Cromosoma progMutado = new Cromosoma(programa, tab);
        if(rand.nextDouble() < probMut) {
            String s = progMutado.getRandomTerminal();
            if(s == "CONSTANTE") {
                Pair valor = new Pair(rand.nextInt(8), rand.nextInt(tab.getDim()));
                progMutado.getNodoRand().contraeConNum(s, valor);
            }else{
                progMutado.getNodoRand().contrae(s);
            }
            
        }
        return new Cromosoma(progMutado, tab);
    }

    @Override
    public String toString() {
        return "Mutacion Contraccion";
    }
}
