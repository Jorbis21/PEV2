package src.mutacion;

import java.util.Random;

import src.cromosoma.Cromosoma;
import src.TableroGlobal;
import src.arbol.Arbol;

public class MutacionHoist implements IMutacion{

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut, TableroGlobal tab) {
        Cromosoma progMutado = new Cromosoma(programa, tab);
        if(rand.nextDouble() < probMut) {
        
        }
        return new Cromosoma(progMutado, tab);
    }
    
    @Override
    public String toString() {
        return "Mutacion Hoist";
    }
}


/* OPCION 1
 * 
 * Se coge un subarbol aleatorio y ese pasa a ser el nuevo cromosoma
 * 
 * Cromosoma progMutado = new Cromosoma(programa);
 * if(rand.nextDouble() < probMut) {
 *     Arbol subarbol = new Arbol(progMutado.getNodoRand());
 *     progMutado.setArbol(subarbol);
 * }
 * return progMutado;
 */

/* OPCION 2
 * 
 * Se coge un subarbol aleatorio y ese arbol se elimina. Aqui hay que chequar que se cambie la raiz 
 * del arbol eliminado por un nodo terminal
 * 
 * Cromosoma progMutado = new Cromosoma(programa);
 * if(rand.nextDouble() < probMut) {
 *      String s = progMutado.getRandomTerminal();
 *      Nodo nodo = progMutado.getNodoRand();
 *      nodo.terminate(s);
 * }
 */