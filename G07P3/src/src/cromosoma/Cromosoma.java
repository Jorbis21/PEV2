package src.cromosoma;

import java.util.Random;

import src.arbol.Arbol;
import src.arbol.Nodo;
import src.utils.Pair;

public class Cromosoma{
    // aqui el profe nos pone puntos suspensivos

    public static String terminales[] = {"IZQUIERDA", "AVANZA", "CONSTANTE"};
    public static String funciones[] = {"SUMA", "SALTA", "PROGN"};
    public static int dimension = 8;
    
    private static String direcciones[] = {"Arriba", "Izquierda", "Abajo", "Derecha"};
    private static int tablero[][];

    private Arbol arbol;
    private Pair posicion = new Pair(4, 4);
    private int posDir = 0;
    private String dir = direcciones[posDir];
    private int fitness;
    private String fenotipo;

    public Cromosoma(int profundidad, int tipoCreacion){
        arbol = new Arbol(profundidad);
        //puede haber lio con lo de la dimension
        tablero = new int[8][dimension];
        iniTablero();
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
    }
    
    private void iniTablero() {
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < dimension; j++ ) {
    			tablero[i][j] = 0;
    		}
    	}
    }
    
    private Pair calcFit(Nodo act) {
    	String val = act.getValor();
    	if(act.esHoja()) {
    		if(val == "IZQUIERDA") {
    			posDir++;
    			if(posDir == direcciones.length)
    				posDir = 0;
    			dir = direcciones[posDir];
    		}
    		else if(val == "AVANZA") {
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
    			if(tablero[posicion.getFirst()][posicion.getSecond()] == 0) {
    				tablero[posicion.getFirst()][posicion.getSecond()] = 1;
    				fitness++;
    			}
    		}
    		return act.getNumval();
    	}
    	else {
    		if(val == "SUMA") {
    			Pair izqVal, derVal;
    			izqVal = calcFit(act.getIzq());
    			derVal = calcFit(act.getDer());
    			return izqVal.suma(derVal, dimension);
    		}
    		else if(val == "SALTA") {
    			Pair newPos = calcFit(act.getIzq());
    			posicion = posicion.suma(newPos, dimension);
    			if(tablero[posicion.getFirst()][posicion.getSecond()] == 0) {
    				tablero[posicion.getFirst()][posicion.getSecond()] = 1;
    				fitness++;
    			}
    			return newPos;
    		}
    		else if(val == "PROGN") {
    			calcFit(act.getIzq());
    			return calcFit(act.getDer());
    		}
    	}
    	return new Pair();
    }


    // Getters & Setters ----------------------------------------------
    public Arbol getGenotipo() {
    	return this.arbol;
    }
    public int getFitness() {
    	return this.fitness;
    }
   
}
