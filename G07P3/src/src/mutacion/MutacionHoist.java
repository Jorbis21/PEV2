package src.mutacion;

import java.util.Random;

import src.cromosoma.Cromosoma;
import src.arbol.Arbol;

public class MutacionHoist implements IMutacion{

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut) {
        Cromosoma progMutado = new Cromosoma(programa);
        if(rand.nextDouble() < probMut) {
        
        }
        return progMutado;
    }   
}


/* OPCION 1
 * 
 * Se coge un subarbol aleatorio y ese pasa a ser el nuevo cromosoma
 */

/* OPCION 2
 * 
 * Se coge un subarbol aleatorio y ese arbol se elimina. Aqui hay que chequar que se cambie la raiz 
 * del arbol eliminado por un nodo terminal
 */