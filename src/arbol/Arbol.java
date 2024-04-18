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

    public Arbol at(int index){
		return at(this, 0, index);
	}
	
	private Arbol at(Arbol a, int pos, int index){
		Arbol s = null;
		if(pos >= index) s = a;
		else if(a.getNumHijos() > 0)
		{
			for(int i = 0; i < a.getNumHijos(); i++)
				if(s == null) s = at(a.getHijos().get(i), pos+i+1, index);
		}
		return s;
	}

    public int inicializacionCompleta(Cromosoma cr, int profundidad, int nodos){
        int n = nodos;
        int nHijos = 2;
        if(profundidad < max_prof){
            setProfundidad(profundidad);
            Random rnd = new Random();

            this.valor = Cromosoma.funciones[func];
            this.setEsRaiz(true);
        }
        
        return n;
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
    	
    	public void getNumval(Pair numVal) {
    		this.numVal = numVal;
    	}
    	
    	public void getNumhijos(int numHijos) {
    		this.numHijos = numHijos;
    	}
    	
    	public void getAnt(Nodo ant) {
    		this.ant = ant;
    	}
    	
    	public void getIzq(Nodo izq) {
    		this.izq = izq;
    	}
    	
    	public void getDer(Nodo der) {
    		this.der = der;
    	}
    }
}

