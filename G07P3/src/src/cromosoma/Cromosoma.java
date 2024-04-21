package src.cromosoma;

import java.util.Random;

import src.arbol.Arbol;
import src.utils.Pair;

public class Cromosoma{
    // aqui el profe nos pone puntos suspensivos

    public static String terminales[] = {"IZQUIERDA", "AVANZA", "CONSTANTE"};
    public static String funciones[] = {"SUMA", "SALTO", "PROGN"};
    public static int dimension = 8;
    
    private static int tablero[][];

    private Arbol arbol;
    private Pair posicion = new Pair(4, 4);
    private String dir;
    private double fitness;
    private double fitnessBruto; // aptitud antes de transformarla
    private double punt;
    private double puntAcum;
    private String fenotipo;

    public Cromosoma(int profundidad, int tipoCreacion){
        arbol = new Arbol(profundidad);
        tablero = new int[8][dimension];
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
        //Calculo del fitness
    }
    
    private Pair calcFit() {
    	arbol.getRaiz().getValor();
    	
    	
    	return new Pair();
    }


    // Getters & Setters ----------------------------------------------

    public int getFuncionesLength(){
        return funciones.length;
    }

}
