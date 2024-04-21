package src.mutacion;

import java.util.Random;

import src.arbol.Nodo;
import src.cromosoma.Cromosoma;

public class MutacionTerminal implements IMutacion {

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut) {
        //Cromosoma progMutado = new Cromosoma(programa); //El constructor de copia de Cromosoma no está implementado 
        if(rand.nextDouble() < probMut) {
            //String s = getTerminalAleatorio(programa, rand); // Esta funcion no está implementada
            //Nodo nodo = progMutado.getNodoTerminalAleatorio(rand);  // Esta funcion no está implementada
            //nodo.setValor(s);
        }
            
        return null;// progMutado;
    }
    
}
