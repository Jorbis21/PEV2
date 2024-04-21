package src.mutacion;

import java.util.Random;

import src.arbol.Nodo;
import src.cromosoma.Cromosoma;

public class MutacionPermutacion implements IMutacion{

    @Override
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut) {
        //Cromosoma progMutado = new Cromosoma(programa); //El constructor de copia de Cromosoma no está implementado 

        if (rand.nextDouble() < probMut) {
            //Nodo nodo = progMutado.getNodoNoHojaAleatorio(rand);
            //progMutado.permutacion(nodo);
        }
        return null; //progMutado;   
    }
}
