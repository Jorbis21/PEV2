package src.cromosoma;

import java.util.Random;

import src.TableroGlobal;
import src.arbol.Arbol;
import src.arbol.Nodo;
import src.utils.Pair;

public class Cromosoma {

	public static String terminales[] = { "IZQUIERDA", "AVANZA", "CONSTANTE" };
	public static String funciones[] = { "SUMA", "SALTA", "PROGN" };
	
	private static String direcciones[] = { "Arriba", "Izquierda", "Abajo", "Derecha" };

	private int[][] tablero;
	private Random rand = new Random();
	private Arbol arbol;
	private Pair posicion;
	private int posDir;
	private String dir;
	private double fitness = 0.0;
	private String fenotipo;
	private int numIz;
	private int numOp;
	private TableroGlobal tab;

	// Constructor copia
	public Cromosoma(Cromosoma cromosoma, TableroGlobal tab) {
		this.tab = tab;
		this.tablero = new int[8][tab.getDim()];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < tab.getDim(); j++) {
				this.tablero[i][j] = tab.getTab()[i][j];
			}
		}
		this.arbol = cromosoma.getGenotipo();
		posicion = new Pair(4, 4);
		posDir = 0;
		dir = direcciones[posDir];
		numIz = 0;
		numOp = 0;
		arbol.calcProf(arbol.getRaiz(), 0);
		calcFit(arbol.getRaiz());
		fenotipo = arbol.toString(arbol.getRaiz());
	}

	public Cromosoma(int tipoCreacion, TableroGlobal tab) {
		this.tab = tab;
		arbol = new Arbol(tipoCreacion, tab);
		this.tablero = new int[8][tab.getDim()];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < tab.getDim(); j++) {
				tablero[i][j] = tab.getTab()[i][j];
			}
		}
		posicion = new Pair(4, 4);
		posDir = 0;
		dir = direcciones[posDir];
		numIz = 0;
		numOp = 0;
		arbol.calcProf(arbol.getRaiz(), 0);
		calcFit(arbol.getRaiz());
		fenotipo = arbol.toString(arbol.getRaiz());
	}
	public Cromosoma(Arbol arbol, TableroGlobal tab) {
		this.tab = tab;
		this.arbol = arbol;
		this.tablero = new int[8][tab.getDim()];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < tab.getDim(); j++) {
				tablero[i][j] = tab.getTab()[i][j];
			}
		}
		posicion = new Pair(4, 4);
		posDir = 0;
		dir = direcciones[posDir];
		numIz = 0;
		numOp = 0;
		arbol.calcProf(arbol.getRaiz(), 0);
		calcFit(arbol.getRaiz());
		fenotipo = arbol.toString(arbol.getRaiz());
	}

	private void avanza() {
		numOp++;
		switch (dir) {
			case "Arriba":
				posicion = posicion.suma(new Pair(-1, 0), tab.getDim());
				break;
			case "Izquierda":
				posicion = posicion.suma(new Pair(0, -1), tab.getDim());
				break;
			case "Abajo":
				posicion = posicion.suma(new Pair(1, 0), tab.getDim());
				break;
			case "Derecha":
				posicion = posicion.suma(new Pair(0, 1), tab.getDim());
				break;
		}
	}
	
	private void ejecIzq() {
		numIz++;
		posDir++;
		if (posDir == direcciones.length)
			posDir = 0;
		dir = direcciones[posDir];
	}

	private void ejecAvanza() {
		avanza();
		if (tablero[posicion.getFirst()][posicion.getSecond()] == 0) {
			tablero[posicion.getFirst()][posicion.getSecond()] = 1;
			fitness+=1.0;
		} else if (tablero[posicion.getFirst()][posicion.getSecond()] == 2) {
			while (tablero[posicion.getFirst()][posicion.getSecond()] == 2) {
				if (fitness > 0) {
					fitness-=1.0;
				}
				avanza();
			}

		}
	}

	private Pair calcSum(Nodo act) {
		Pair izqVal, derVal;
		izqVal = calcFit(act.getIzq());
		derVal = calcFit(act.getDer());
		return izqVal.suma(derVal, tab.getDim());
	}

	private Pair calcSalta(Nodo act) {
		numOp++;
		Pair newPos = calcFit(act.getIzq());
		posicion = posicion.suma(newPos, tab.getDim());
		if (tablero[posicion.getFirst()][posicion.getSecond()] == 0) {
			tablero[posicion.getFirst()][posicion.getSecond()] = 1;
			fitness+=1.0;
		} else if (tablero[posicion.getFirst()][posicion.getSecond()] == 2) {
			while (tablero[posicion.getFirst()][posicion.getSecond()] == 2) {
				if (fitness > 0) {
					fitness-=1.0;
				}
				avanza();
			}
		}
		return newPos;
	}

	private Pair calcFit(Nodo act) {
		if (numOp < 100 && numIz < 100) {
			String val = act.getValor();
			if (act.esHoja()) {
				if (val == "IZQUIERDA") {
					ejecIzq();
				} else if (val == "AVANZA") {
					ejecAvanza();
				}
				return act.getNumval();
			} else {
				if (val == "SUMA") {
					return calcSum(act);
				} else if (val == "SALTA") {
					return calcSalta(act);
				} else if (val == "PROGN") {
					calcFit(act.getIzq());
					return calcFit(act.getDer());
				}
			}
		}
		return new Pair();
	}

	public void swapSubtrees(Nodo nodo1, Nodo nodo2) {
		arbol.swapSubtrees(nodo1, nodo2);
	}

	// Getters & Setters ----------------------------------------------

	public Nodo getHojaRand() {
		return arbol.getHojaRand(arbol.getRaiz());
	}

	public Nodo getNodoRand() {
		return arbol.getNodoRand(arbol.getRaiz());
	}

	public String getFenotipo() {
		return this.fenotipo;
	}

	public Arbol getGenotipo() {
		return this.arbol;
	}

	public double getFitness() {
		return this.fitness;
	}

	public int getProfundidadArbol() {
		return this.arbol.getProfundidad();
	}

	public String getRandomTerminal() {
		return terminales[rand.nextInt(terminales.length)];
	}
	public void setFitness(double fit) {
		this.fitness = fit;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public int[][] getTablero() {
		return this.tablero;
	}

	public Arbol getArbol(){
		return this.arbol;
	}
}
