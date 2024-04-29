package src.arbol;

import java.util.ArrayList;
import java.util.Random;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;
import src.utils.Pair;

public class Arbol {
	public static int max_prof;

	private Nodo raiz;
	private int profundidad = 0;
	Random rand = new Random();
	private TableroGlobal tab;
	ArrayList<Nodo> middleNodes = new ArrayList<Nodo>();

	public Arbol(int tipoCreacion, TableroGlobal tab) {
		this.tab = tab;
		raiz = new Nodo();
		switch (tipoCreacion) {
			case 0:
				inicializacionCreciente(0, null, raiz);
				break;
			case 1:
				inicializacionCompleta(0, null, raiz);
				break;
			case 2:
				if (rand.nextDouble() > 0.5)
					inicializacionCreciente(0, null, raiz);
				else
					inicializacionCompleta(0, null, raiz);
				break;
		}
		calcProf(raiz, 0);
	}

	public Arbol(int tipoCreacion, Nodo act, TableroGlobal tab) {
		this.tab = tab;
		raiz = new Nodo();
		switch (tipoCreacion) {
			case 0:
				inicializacionCreciente(0, act, raiz);
				break;
			case 1:
				inicializacionCompleta(0, act, raiz);
				break;
			case 2:
				if (rand.nextDouble() > 0.5)
					inicializacionCreciente(0, act, raiz);
				else
					inicializacionCompleta(0, act, raiz);
				break;
		}
		calcProf(raiz, 0);
	}
	
	public Arbol(Nodo raiz, TableroGlobal tab) {
		this.tab = tab;
		this.raiz = raiz;
		inicializacionCompleta(0, raiz, raiz.getAnt());
		calcProf(this.raiz, 0);
	}

	public void calcProf(Nodo act, int prof) {
		if (act.esHoja()) {
			if (prof > profundidad) {
				setProfundidad(prof);
			}
		} else {
			if (prof > profundidad) {
				setProfundidad(prof);
			}
			if (act.getNumhijos() == 1) {
				calcProf(act.getIzq(), prof + 1);
			} else {
				calcProf(act.getIzq(), prof + 1);
				calcProf(act.getDer(), prof + 1);
			}
			if(!act.esRaiz() && (act.getNumhijos() == 1 || act.getNumhijos() == 2)) {
				middleNodes.add(act);
			}
		}
	}

	public Nodo getHojaRand(Nodo act) {
		if (act.esHoja()) {
			return act;
		} else {
			if (act.getNumhijos() == 1) {
				return getHojaRand(act.getIzq());
			} else {
				if (rand.nextDouble() > 0.5) {
					return getHojaRand(act.getIzq());
				} else {
					return getHojaRand(act.getDer());
				}
			}
		}
	}

	public Nodo getNodoRand(Nodo act) {
		if(act.esRaiz()) {
			if(act.getDer() != null && !act.getDer().esHoja() && rand.nextDouble() > 0.5) {
				return getNodoRand(act.getDer());
			}
			else if(!act.getIzq().esHoja() ) {
				return getNodoRand(act.getIzq());
			}
			else {
				return act;
			}
		}
		else {
			if(rand.nextDouble() > 0.5) {
				return act;
			}
			else {
				if(act.getDer() != null && !act.getDer().esHoja() && rand.nextDouble() > 0.5) {
					return getNodoRand(act.getDer());
				}
				else if(!act.getIzq().esHoja() ) {
					return getNodoRand(act.getIzq());
				}
				else {
					return act;
				}
			}
		}
    }

	public String toString(Nodo act) {
		if (act.esHoja()) {
			if (act.getValor() == "CONSTANTE") {
				return act.getNumval().toString();
			} else {
				return "(" + act.getValor() + ")";
			}
		} else {
			if (act.getNumhijos() == 1) {
				return "(" + act.getValor() + toString(act.getIzq()) + ")";
			} else {
				String hijoIzq, hijoDer;
				hijoIzq = toString(act.getIzq());
				hijoDer = toString(act.getDer());
				return "(" + act.getValor() + hijoIzq + hijoDer + ")";
			}
		}
	}

	private void iniFuncCr(int profundidad, Nodo act, Nodo ant) {
		act.setAnt(ant);
		act.setValor(Cromosoma.funciones[rand.nextInt(Cromosoma.funciones.length)]);
		if (act.getValor() == "SUMA" || act.getValor() == "PROGN")
			act.setNumhijos(2);
		else
			act.setNumhijos(1);
		if (act.getNumhijos() == 2) {
			act.setIzq(inicializacionCreciente(profundidad + 1, act, new Nodo()));
			act.setDer(inicializacionCreciente(profundidad + 1, act, new Nodo()));
		} else {
			act.setIzq(inicializacionCreciente(profundidad + 1, act, new Nodo()));
		}
		if (act.esRaiz()) {
			this.raiz = act;
		}
	}

	private void iniFuncC(int profundidad, Nodo act, Nodo ant) {
		act.setAnt(ant);
		act.setValor(Cromosoma.funciones[rand.nextInt(Cromosoma.funciones.length)]);
		if (act.getValor() == "SUMA" || act.getValor() == "PROGN")
			act.setNumhijos(2);
		else
			act.setNumhijos(1);
		if (act.getNumhijos() == 2) {
			act.setIzq(inicializacionCompleta(profundidad + 1, act, new Nodo()));
			act.setDer(inicializacionCompleta(profundidad + 1, act, new Nodo()));
		} else {
			act.setIzq(inicializacionCompleta(profundidad + 1, act, new Nodo()));
		}
		if (act.esRaiz()) {
			this.raiz = act;
		}
	}

	private void iniTerm(int profundidad, Nodo act, Nodo ant) {
		setProfundidad(profundidad);
		act.setValor(Cromosoma.terminales[rand.nextInt(Cromosoma.terminales.length)]);
		if (act.getValor() == "CONSTANTE") {
			act.setNumval(new Pair(rand.nextInt(8), rand.nextInt(tab.getDim())));
		}
		act.setAnt(ant);
	}

	public Nodo inicializacionCompleta(int profundidad, Nodo ant, Nodo act) {
		if (profundidad <= max_prof) {
			iniFuncC(profundidad, act, ant);
		} else {
			iniTerm(profundidad, act, ant);
		}
		return act;
	}

	public Nodo inicializacionCreciente(int profundidad, Nodo ant, Nodo act) {
		if (profundidad <= max_prof) {
			if (profundidad == 0 || profundidad == 1) {
				iniFuncCr(profundidad, act, ant);
			} else {
				if (rand.nextDouble() > 0.5) {
					iniFuncCr(profundidad, act, ant);
				} else {
					iniTerm(profundidad, act, ant);
				}
			}
		} else {
			iniTerm(profundidad, act, ant);
		}
		return act;
	}

	public void swapSubtrees(Nodo nodo1, Nodo nodo2) {
		Nodo aux = new Nodo(nodo1);
		nodo1.setValor(nodo2.getValor());
		nodo1.setNumhijos(nodo2.getNumhijos());
		nodo1.setNumval(nodo2.getNumval());
		nodo1.setIzq(nodo2.getIzq());
		nodo1.setDer(nodo2.getDer());
		nodo1.setAnt(nodo2.getAnt());

		nodo2.setValor(aux.getValor());
		nodo2.setNumhijos(aux.getNumhijos());
		nodo2.setNumval(aux.getNumval());
		nodo2.setIzq(aux.getIzq());
		nodo2.setDer(aux.getDer());
		nodo2.setAnt(aux.getAnt());
	}

	public Graph getTreeView() {
		Graph result = new SingleGraph("Arbol");

		Nodo raiz = new Nodo(this.getRaiz());
		
		// add Nodes from Arbol to graph
		addNodes(result, raiz);

		// add Edges from Arbol to graph
		addEdges(result, raiz);

		return result;
	}

	public void addNodes(Graph result, Nodo raiz){
		Nodo nodoRaiz = new Nodo(raiz);
		if(raiz.esHoja()){
			result.addNode(raiz.getValor());
		}else{
			Nodo nodoIzq = new Nodo(raiz.getIzq());
			result.addNode(raiz.getValor());

			addNodes(result, nodoIzq);
			if(raiz.getNumhijos() == 2){
				Nodo nodoDer = new Nodo(raiz.getDer());
				addNodes(result, nodoDer);
			}
		}
	}

	public void addEdges(Graph result, Nodo raiz){
		if(raiz.esHoja()){
			return;
		}else{
			String valorRaiz = new String(raiz.getValor());
			String valorIzq = new String(raiz.getIzq().getValor());
			result.addEdge(valorRaiz + "-" + valorIzq, valorRaiz, valorIzq);
			String valorDer = (raiz.getDer().getValor());
			if(raiz.getNumhijos() == 2){
				// add edge from raiz to der
				result.addEdge(valorRaiz + "-" + valorDer, valorRaiz, valorDer);
				addEdges(result, raiz.getDer());
			}

		}
	}

	public void setMax_prof(int max_prof) {
		this.max_prof = max_prof;
	}

	/// Getters & Setters ---------------------------------------------------------

	public Nodo getRaiz() {
		return raiz;
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

	public ArrayList<Nodo> getMiddleNodes() {
		return middleNodes;
	}

}
