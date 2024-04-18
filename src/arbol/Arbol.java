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

    // Develeve el arbol en forma de array
    public ArrayList<String> toArray(){
        ArrayList<String> array = new ArrayList<String>();
        toArrayAux(array, this);
        return array;
    }
    
    private void toArrayAux(ArrayList<String> array, Arbol a){
        array.add(a.valor);
        for(int i = 0; i < a.hijos.size(); i++){
            toArrayAux(array, a.hijos.get(i));
        }
    }


    // Insertar un arbol en otro arbol.
	public void insert(Arbol a, int index){
		if(index == -1){
			hijos.add(a);
			numHijos = hijos.size();
		}
		else
			hijos.set(index, a);
	}

    public Nodo inicializacionCompleta(int profundidad, Nodo ant){
    	int numHijos = 0, aleat;
    	String valor; Nodo aux = new Nodo();
        if(profundidad < max_prof){
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
        else if(profundidad == (max_prof - 1)) {
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

