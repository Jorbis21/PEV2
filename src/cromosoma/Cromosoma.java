package src.cromosoma;

import src.arbol.Arbol;

public class Cromosoma{
    // aqui el profe nos pone puntos suspensivos

    public static String terminales[];
    public static String terminales6[] = {"A0", "A1", "D0", "D1", "D2", "D3"};
    public static String funciones[] = {"AND", "OR", "NOT", "IF"}; 

    private Arbol arbol;
    private double fitness;
    private double fitnessBruto; // aptitud antes de transformarla
    private double punt;
    private double puntAcum;
    private String fenotipo;

    public Cromosoma(int profundidad, int tipoCreacion, boolean useIf, int tipoMultiplexor){
        arbol = new Arbol(profundidad, useIf);
        switch(tipoCreacion){
            case 0:
                arbol.inicializacionCreciente(0);
                break;
            case 1:
                arbol.inicializacionCompleta(0, 0);
                break;
            case 2:
                int ini = new Random().nextInt(2);
                if(ini == 0)
                    arbol.inicializacionCreciente(0);
                else
                    arbol.inicializacionCompleta(0, 0);
                break;
        }
    }


    // Getters & Setters ----------------------------------------------

    public int getFuncionesLength(){
        return funciones.length;
    }

}
