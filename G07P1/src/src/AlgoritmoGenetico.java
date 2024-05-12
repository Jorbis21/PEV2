package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import src.individuo.Individuo;
import src.cruce.ICruce;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;
import src.utils.Utils;

public class AlgoritmoGenetico {

  private ArrayList<Individuo> poblacion;
	private ArrayList<Individuo> elite;
  
  private int tamPob;
	private int numGen;
	private int actGen;
	private double probCruce;
	private double probMutacion;
	private double elitismo;
  private double precision;

  private ICruce cruce;
	private IMutacion mutacion;
	private ISeleccion seleccion;
  private Individuo funcion;

  private double mediaFitness;
	private Individuo mejorHistorico;
	private Individuo mejorGeneracion;

  private double[] mediaFitnessArray;
	private double[] mejorHistoricoArray;
	private double[] mejorGeneracionArray;

  private Random rand = new Random();

  public AlgoritmoGenetico(int tamPob, int numGen, double probCruce, double probMutacion, double elitismo, double precision, Individuo funcion, ICruce cruce, IMutacion mutacion, ISeleccion seleccion) {
    this.tamPob = tamPob;
    this.numGen = numGen;
    this.actGen = 0;
    this.probCruce = probCruce;
    this.probMutacion = probMutacion;
    this.elitismo = elitismo;
    this.precision = precision;

    this.cruce = cruce;
    this.mutacion = mutacion;
    this.seleccion = seleccion;
    this.funcion = funcion;

    this.mediaFitness = 0;
    this.mejorHistorico = null;
    this.mejorGeneracion = null;

    poblacion = new ArrayList<>();
    elite = new ArrayList<>();
    mediaFitnessArray = new double[numGen];
    mejorHistoricoArray = new double[numGen];
    mejorGeneracionArray = new double[numGen];
  }

  private void iniPoblacion() {
    for (int i = 0; i < tamPob; i++) {
      poblacion.add(funcion.generaPoblacion(this.precision));
    }
  }

  private void cogerDatos() {
		double sumaFit = 0;
		for (Individuo cr : poblacion) {
			sumaFit += cr.getFitness();
		}
		mediaFitness = sumaFit / tamPob;
		mediaFitnessArray[actGen] = mediaFitness;

		poblacion = Utils.sortSample(poblacion);
		mejorGeneracion = poblacion.get(0);
		
		if (mejorHistorico == null || mejorHistorico.getFitness() < mejorGeneracion.getFitness()) {
			mejorHistorico = mejorGeneracion;
		}
		mejorHistoricoArray[actGen] = mejorHistorico.getFitness();
		mejorGeneracionArray[actGen] = mejorGeneracion.getFitness();
	}

  private void extraerElite() {
		poblacion = Utils.sortSample(poblacion);
		elite.clear();
		for (int i = 0; i < (elitismo * tamPob); i++) {
			elite.add(poblacion.get(i));
		}
	}

	private void meterElite() {
		poblacion = Utils.sortSample(poblacion);
		int i = tamPob - 1, j = 0;
		while (j < elite.size()) {
			poblacion.set(i, elite.get(j));
			i--;
			j++;
		}
	}

  private void seleccion() {
		poblacion = seleccion.select(poblacion, rand);
	}

	private void cruce() {
		Collections.shuffle(poblacion);
		poblacion = cruce.cruzar(poblacion, rand, probCruce);
	}

	private void mutacion() {
		for (int i = 0; i < tamPob - (tamPob%2); i++) {
			poblacion.set(i, mutacion.mutar(poblacion.get(i), rand, probMutacion));
		}
	}


  public void run(){
    iniPoblacion();
	
		do {
			extraerElite();
			seleccion();
			cruce();
			mutacion();
			meterElite();
			cogerDatos();
			System.out.println("[Generacion]: " + actGen + " || [MejorGeneracion]: " + mejorGeneracion.getFitness()
					+ " || [MediaFitness]: " + mediaFitness);
			++actGen;
		} while (actGen < numGen);

  }

  // Getters & Setters
	public double[] getMediaFitnessArray() {
		return mediaFitnessArray;
	}

	public double[] getMejorHistoricoArray() {
		return mejorHistoricoArray;
	}

	public double[] getMejorGeneracionArray() {
		return mejorGeneracionArray;
	}

	public int getNumGen() {
		return numGen;
	}

	public Individuo getMejorHistorico() {
		return mejorHistorico;
	}
}
