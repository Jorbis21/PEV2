package src.arbol;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;

public class Arbol {
    private String valor;
    private ArrayList<Arbol> hijos;
    private int numHijos;
    private int max_prof;
    private int profundidad;

    public Arbol(){
        hijos = new ArrayList<Arbol>();
        numHijos = 0;
        numNodos = 0;
        valor = "";
    }

    public Arbol(String v){
        valor = v;
        hijos = new ArrayList<Arbol>();
        numHijos = 0;
    }

    public Arbol(int profundidad){
        hijos = new ArrayList<Arbol>();
        numHijos = 0;
        numNodos = 0;
        valor = "";
        this.profundidad = profundidad;
        esHoja = false;
        esRaiz = false;
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

    // Insertar un valor en el arbol (nodo simple)
    public Arbol insert(String v, int index){
		Arbol a = new Arbol(v);
		if(index == -1){
			hijos.add(a);
			numHijos = hijos.size();
		}
		else
			hijos.set(index, a);
		return a;
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
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ArrayList<Arbol> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Arbol> hijos) {
        this.hijos = hijos;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
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

    public void setMax_prof(int max_prof) {
        this.max_prof = max_prof;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public boolean isEsHoja() {
        return esHoja;
    }

    public void setEsHoja(boolean esHoja) {
        this.esHoja = esHoja;
    }

    public boolean isEsRaiz() {
        return esRaiz;
    }

    public void setEsRaiz(boolean esRaiz) {
        this.esRaiz = esRaiz;
    }
    private class Nodo{
    	String valor;
    	boolean esHijo;
    	boolean esRaiz;
    	boolean esNodo;
    	
    }
}

