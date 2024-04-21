package src.arbol;

import java.util.Random;

import src.cromosoma.Cromosoma;
import src.utils.Pair;

public class Arbol {
	public static int max_prof;
	
	private Nodo raiz;
    private int numNodos;
    private int profundidad;
    Random rand = new Random();
    
    public Arbol(){
        numNodos = 0;
    }


    public Arbol(int profundidad){
        numNodos = 0;
        this.profundidad = profundidad;
    }
    
    public String toString(Nodo act) {
    	if(act.esHoja()) {
    		
    	}
    	return "";
    }
    
    private void iniFunc(int numHijos, int aleat, int profundidad, String valor, Nodo aux, Nodo ant) {
    	setProfundidad(profundidad);
        numHijos = 0; aleat = rand.nextInt(Cromosoma.funciones.length);
        valor = Cromosoma.funciones[aleat];
        if(valor == "SUMA" || valor == "PROGN")
        	numHijos = 2;
        else
        	numHijos = 1;
        aux = new Nodo(valor, ant, null, null, numHijos);
        if(numHijos == 2) {
        	aux.izq = inicializacionCompleta(profundidad+1, aux);
        	aux.der = inicializacionCompleta(profundidad+1, aux);
        }
        else {
        	aux.izq = inicializacionCompleta(profundidad+1, aux);
        }
        if(aux.esRaiz()) {
        	this.raiz = aux;
        }
    }
    
    private void iniTerm(int numHijos, int aleat, int profundidad, String valor, Nodo aux, Nodo ant) {
    	setProfundidad(profundidad);
        numHijos = 0; aleat = rand.nextInt(Cromosoma.terminales.length);
        valor = Cromosoma.terminales[aleat];
        Pair numVal = new Pair();
        aux = new Nodo(valor, ant, null, null, numHijos);
        if(valor == "CONSTANTE") {
        	numVal = new Pair(rand.nextInt(Cromosoma.dimension), rand.nextInt(Cromosoma.dimension));
        }
        aux.setNumval(numVal);
    }
    public Nodo inicializacionCompleta(int profundidad, Nodo ant){
    	int numHijos = 0, aleat = -1;
    	String valor = null; Nodo aux = new Nodo();
        if(profundidad < max_prof){
        	iniFunc(numHijos, aleat, profundidad, valor, aux, ant);
        }
        else if(profundidad == (max_prof - 1)) {
        	iniTerm(numHijos, aleat, profundidad, valor, aux, ant);
        }
        return aux;
    }
    
  
    public Nodo inicializacionCreciente(int profundidad, Nodo ant){
    	int numHijos = 0, aleat = -1;
    	String valor = null; Nodo aux = new Nodo();
        if(profundidad < max_prof){
            if(profundidad == 0) {
            	iniFunc(numHijos, aleat, profundidad, valor, aux, ant);
            }
            else {
            	if(rand.nextDouble() >= 0.5) {
            		iniFunc(numHijos, aleat, profundidad, valor, aux, ant);
            	}
            	else {
            		iniTerm(numHijos, aleat, profundidad, valor, aux, ant);
            	}
            }
        }
        else {
        	iniTerm(numHijos, aleat, profundidad, valor, aux, ant);
        }
        return aux;
    }
    /// Getters & Setters ---------------------------------------------------------
    
    public Nodo getRaiz() {
    	return raiz;
    }
    
    public int getNumNodos() {
        return numNodos;
    }

    public void setNumNodos(int numNodos) {
        this.numNodos = numNodos;
    }

    public int getMax_prof() {
        return max_prof;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }
}

