package src.arbol;

import src.utils.Pair;

public class Nodo{
	private String valor;
	private Pair numVal;
	private Nodo ant, izq, der;
	private int numHijos;
	
	public Nodo(){
		this.valor = null;
		numVal = new Pair();
		this.ant = null;
		this.izq = null;
		this.der = null;
		this.numHijos = 0;
	}

	public Nodo(Nodo nodo){
		this.valor = nodo.valor;
		this.numVal = nodo.numVal;
		this.ant = nodo.ant;
		this.izq = nodo.izq;
		this.der = nodo.der;
		this.numHijos = nodo.numHijos;
	}
	
	public Nodo(String valor, Nodo ant, Nodo izq, Nodo der, int numHijos){
		this.valor = null;
		numVal = new Pair();
		this.ant = ant;
		this.izq = izq;
		this.der = der;
		this.numHijos = numHijos;
	}

	public void permutaHijos(){
		if(numHijos == 2) {
			Nodo aux = this.izq;
			this.izq = this.der;
			this.der = aux;
		}
	}

	public void contrae(String valor){
		this.valor = valor;
		this.izq = null;
		this.der = null;
		this.numHijos = 0;
	}
	
	public void terminate(String valor){
		this.valor = valor;
		this.izq = null;
		this.der = null;
		this.numHijos = 0;
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
	
	public void setValorNum(String valor, Pair numVal) {
		this.valor = valor;
		this.numVal = numVal;
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
