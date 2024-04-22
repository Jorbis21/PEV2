package src.cromosoma;

import java.util.Random;

import src.arbol.Arbol;
import src.arbol.Nodo;
import src.utils.Pair;

public class Cromosoma{

    public static String terminales[] = {"IZQUIERDA", "AVANZA", "CONSTANTE"};
    public static String funciones[] = {"SUMA", "SALTA", "PROGN"};
    public static int tablero[][] = iniTablero();
    public static double probObs = 0.9;
    public static int dimension = 8;
    
    private static String direcciones[] = {"Arriba", "Izquierda", "Abajo", "Derecha"};
   
    private Random rand = new Random();
    private Arbol arbol;
    private Pair posicion = new Pair(4, 4);
    private int posDir = 0;
    private String dir = direcciones[posDir];
    private int fitness;
    private String fenotipo;
    private int numIz = 0;
    private int numOp = 0;
    
	// Constructor copia
	public Cromosoma(Cromosoma cromosoma) {
		this.arbol = cromosoma.getGenotipo();
		posicion = new Pair(4,4);
		posDir = 0;
		dir = direcciones[posDir];
		numIz = 0;
		numOp = 0;
		calcFit(arbol.getRaiz());
		fenotipo = arbol.toString(arbol.getRaiz());
	}

	public Cromosoma(int profundidad, int tipoCreacion){
        arbol = new Arbol(profundidad);
        //puede haber lio con lo de la dimension
        switch(tipoCreacion){
            case 0:
                arbol.inicializacionCreciente(0, null);
                break;
            case 1:
                arbol.inicializacionCompleta(0, null);
                break;
            case 2:
                Random rand = new Random();
                if(rand.nextDouble() >= 0.5)
                    arbol.inicializacionCreciente(0, null);
                else
                    arbol.inicializacionCompleta(0, null);
                break;
        }
        calcFit(arbol.getRaiz());
        fenotipo = arbol.toString(arbol.getRaiz());
    }
    
    static int[][] iniTablero() {
    	int[][] aux = new int[8][dimension];
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < dimension; j++ ) {
    			aux[i][j] = 0;
    		}
    	}
    	return aux;
    }
    private void avanza() {
    	numOp++;
    	switch(dir) {
		case "Arriba":
			posicion = posicion.suma(new Pair(0,1), dimension);
		break;
		case "Izquierda":
			posicion = posicion.suma(new Pair(-1,0), dimension);
		break;
		case "Abajo":
			posicion = posicion.suma(new Pair(0,-1), dimension);
		break;
		case "Derecha":
			posicion = posicion.suma(new Pair(1,0), dimension);
		break;
    	}
    }
    private void explosion() {
    	tablero[posicion.getFirst() - 1][posicion.getSecond() - 1] = 1;
		tablero[posicion.getFirst() - 1][posicion.getSecond()] = 1;
		tablero[posicion.getFirst() - 1][posicion.getSecond() + 1] = 1;
		tablero[posicion.getFirst()][posicion.getSecond() - 1] = 1;
		tablero[posicion.getFirst()][posicion.getSecond()] = 1;
		tablero[posicion.getFirst()][posicion.getSecond() + 1] = 1;
		tablero[posicion.getFirst() + 1][posicion.getSecond() - 1] = 1;
		tablero[posicion.getFirst() + 1][posicion.getSecond()] = 1;
		tablero[posicion.getFirst() + 1][posicion.getSecond() + 1] = 1;
		fitness += 9;
    }
    private void ejecIzq() {
    	numIz++;
    	posDir++;
		if(posDir == direcciones.length)
			posDir = 0;
		dir = direcciones[posDir];
    }
    private void ejecAvanza() {
    	avanza();
		if(tablero[posicion.getFirst()][posicion.getSecond()] == 0) {
			tablero[posicion.getFirst()][posicion.getSecond()] = 1;
			fitness++;
		}
		else if (tablero[posicion.getFirst()][posicion.getSecond()] == 3){
			while(tablero[posicion.getFirst()][posicion.getSecond()] == 3) {
				if(rand.nextDouble() < probObs) {
					if(fitness > 0) {
						fitness--;
					}
					avanza();
				}
				else {
					explosion();
				}
			}
			
		}
    }
    private Pair calcSum(Nodo act) {
    	Pair izqVal, derVal;
		izqVal = calcFit(act.getIzq());
		derVal = calcFit(act.getDer());
		return izqVal.suma(derVal, dimension);
    }
    private Pair calcSalta(Nodo act) {
    	numOp++;
    	Pair newPos = calcFit(act.getIzq());
		posicion = posicion.suma(newPos, dimension);
		if(tablero[posicion.getFirst()][posicion.getSecond()] == 0) {
			tablero[posicion.getFirst()][posicion.getSecond()] = 1;
			fitness++;
		}
		else if (tablero[posicion.getFirst()][posicion.getSecond()] == 3){
			while(tablero[posicion.getFirst()][posicion.getSecond()] == 3) {
				if(rand.nextDouble() < probObs) {
					if(fitness > 0) {
						fitness--;
					}
					avanza();
				}
				else {
					explosion();
				}
			}
		}
		return newPos;
    }
    private Pair calcFit(Nodo act) {
    	if(numOp < 100 && numIz < 100) {
    		String val = act.getValor();
        	if(act.esHoja()) {
        		if(val == "IZQUIERDA") {
        			ejecIzq();
        		}
        		else if(val == "AVANZA") {
        			ejecAvanza();
        		}
        		return act.getNumval();
        	}
        	else {
        		if(val == "SUMA") {
        			return calcSum(act);
        		}
        		else if(val == "SALTA") {
        			return calcSalta(act);
        		}
        		else if(val == "PROGN") {
        			calcFit(act.getIzq());
        			return calcFit(act.getDer());
        		}
        	}
    	}
    	return new Pair();
    }

	// Getters & Setters ----------------------------------------------
	public Nodo getHojaRand(){
		return arbol.getHojaRand(arbol.getRaiz());
	}

	public Nodo getNodoRand(){
		return arbol.getNodoRand(arbol.getRaiz(), 0);
	}
    
    public String getFenotipo() {
    	return this.fenotipo;
    }

    public Arbol getGenotipo() {
    	return this.arbol;
    }

    public int getFitness() {
    	return this.fitness;
    }
   
	public int getProfundidadArbol() {
		return this.arbol.getProfundidad();
	}

	public String getRandomTerminal() {
		return terminales[rand.nextInt(terminales.length)];
	}
	//posiblemente lo acabe quitando
	public void setArbol(Arbol arbol) {
		this.arbol = arbol;
		posicion = new Pair(4,4);
		posDir = 0;
		dir = direcciones[posDir];
		numIz = 0;
		numOp = 0;
		calcFit(arbol.getRaiz());
        fenotipo = arbol.toString(arbol.getRaiz());
	}
}
