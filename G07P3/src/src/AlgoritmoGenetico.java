package src;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;
import src.cruce.ICruce;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;

public class AlgoritmoGenetico {
	
	private ArrayList<Cromosoma> poblacion;
	private ArrayList<Cromosoma> elite;
	
	private int tamPob;
	private int numGen;
	private int actGen;
	private int tipoCreacion;
	private int profundidad = 2;
	private double probCruce;
	private double probMutacion;
	private double elitismo;
	
	private ICruce cruce;
	private IMutacion mutacion;
	private ISeleccion seleccion;
	
	private int[] mediaFitnessArray;
	private int[] mejorHistoricoArray;
	private int[] mejorGeneracionArray;

	private int mediaFitness;
	private Cromosoma mejorHistorico;
	private Cromosoma mejorGeneracion;
	
	private boolean pause = false;
	private Random rand = new Random();
	
	public AlgoritmoGenetico() {
		
	}
	
	private void iniPoblacion() {
		for(int i = 0; i < tamPob; i++) {
			poblacion.add(new Cromosoma(profundidad, tipoCreacion));
		}
		//aqui meter bloating
		cogerDatos();
	}
	private void cogerDatos() {
		int sumaFit = 0;
		for(Cromosoma cr : poblacion) {
			sumaFit += cr.getFitness();
		}
		mediaFitness = sumaFit / tamPob;
		poblacion.sort((a, b) -> Integer.compare(b.getFitness(), a.getFitness()));
		mejorGeneracion = poblacion.get(0);
		if(mejorHistorico == null || mejorHistorico.getFitness() < mejorGeneracion.getFitness()) {
			mejorHistorico = mejorGeneracion;
		}
		mediaFitnessArray[actGen] = mediaFitness;
		mejorHistoricoArray[actGen] = mejorHistorico.getFitness();
		mejorGeneracionArray[actGen] = mejorGeneracion.getFitness();
	}
	
	private void extraerElite() {
		poblacion.sort((a, b) -> Integer.compare(b.getFitness(), a.getFitness()));
		elite.clear();
		for(int i = 0; i < (elitismo * tamPob); i++) {
			elite.add(poblacion.get(i));
		}
	}
	private void meterElite() {
		poblacion.sort((a, b) -> Integer.compare(b.getFitness(), a.getFitness()));
		int i = tamPob - 1, j = 0;
		while(j < elite.size()) {
			poblacion.set(i, elite.get(j));
		}
	}
}
