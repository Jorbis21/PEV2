package src.arbol;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;
import src.utils.Pair;

public class Arbol {
	static int max_prof;
	
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
    
    
    public void insert() {
    	
    }
    
    public void toArray() {
    	
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
        	aux.setNumval(numVal);
        }
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

    public int getNumNodos() {
        return numNodos;
    }

    public void setNumNodos(int numNodos) {
        this.numNodos = numNodos;
    }

    public int getMax_prof() {
        return max_prof;
    }

    public void setMax_prof(int max_prof) {
        this.max_prof = max_prof;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    private class Nodo{
    	String valor;
    	Pair numVal;
    	Nodo ant, izq, der;
    	int numHijos;
    	
    	public Nodo(){
    		this.valor = null;
    		numVal = new Pair();
    		this.ant = null;
    		this.izq = null;
    		this.der = null;
    		this.numHijos = 0;
    	}
    	
    	public Nodo(String valor, Nodo ant, Nodo izq, Nodo der, int numHijos){
    		this.valor = null;
    		numVal = new Pair();
    		this.ant = ant;
    		this.izq = izq;
    		this.der = der;
    		this.numHijos = numHijos;
    	}
    	
    	public boolean esRaiz() {
    		return this.ant == null;
    	}
    	
    	public boolean esHoja() {
    		return this.izq == null && this.der == null;
    	}
    	
    	public String getValor() {
    		return this.valor;
    	}
    	
    	public Pair getNumval() {
    		return this.numVal;
    	}
    	
    	public int getNumhijos() {
    		return this.numHijos;
    	}
    	
    	public Nodo getAnt() {
    		return this.ant;
    	}
    	
    	public Nodo getIzq() {
    		return this.izq;
    	}
    	
    	public Nodo getDer() {
    		return this.der;
    	}
    	
    	
    	public void setValor(String valor) {
    		this.valor = valor;
    	}
    	
    	public void setNumval(Pair numVal) {
    		this.numVal = numVal;
    	}
    	
    	public void setNumhijos(int numHijos) {
    		this.numHijos = numHijos;
    	}
    	
    	public void setAnt(Nodo ant) {
    		this.ant = ant;
    	}
    	
    	public void setIzq(Nodo izq) {
    		this.izq = izq;
    	}
    	
    	public void setDer(Nodo der) {
    		this.der = der;
    	}
    }
}

