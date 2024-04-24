package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import src.arbol.Arbol;
import src.bloating.IBloating;
import src.cromosoma.Cromosoma;
import src.cruce.ICruce;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;
import src.utils.Utils;

public class AlgoritmoGenetico {

	private ArrayList<Cromosoma> poblacion;
	private ArrayList<Cromosoma> elite;

	private int tamPob;
	private int numGen;
	private int actGen;
	private int tipoCreacion;
	private double probCruce;
	private double probMutacion;
	private double probMuerte;
	private double elitismo;

	private ICruce cruce;
	private IMutacion mutacion;
	private ISeleccion seleccion;
	private IBloating bloating;

	private double[] mediaFitnessArray;
	private double[] mejorHistoricoArray;
	private double[] mejorGeneracionArray;

	private double mediaFitness;
	private Cromosoma mejorHistorico;
	private Cromosoma mejorGeneracion;

	private Random rand = new Random();

	public AlgoritmoGenetico(int tamPob, int numGen, int tipoCreacion, int profundidad, double probCruce,
			double probMutacion, double probMuerte, double elitismo, ICruce cruce, IMutacion mutacion, ISeleccion seleccion,
			IBloating bloating) {
		this.tamPob = tamPob;
		this.numGen = numGen;
		this.tipoCreacion = tipoCreacion;
		Arbol.max_prof = profundidad;
		this.probCruce = probCruce;
		this.probMutacion = probMutacion;
		this.probMuerte = probMuerte;
		this.elitismo = elitismo;
		this.cruce = cruce;
		this.mutacion = mutacion;
		this.seleccion = seleccion;
		this.bloating = bloating;
		this.poblacion = new ArrayList<Cromosoma>();
		this.elite = new ArrayList<Cromosoma>();
		this.mediaFitnessArray = new double[numGen];
		this.mejorHistoricoArray = new double[numGen];
		this.mejorGeneracionArray = new double[numGen];
		this.actGen = 0;
		this.mediaFitness = 0;
		this.mejorHistorico = null;
		this.mejorGeneracion = null;
	}

	private void iniPoblacion() {
		for (int i = 0; i < tamPob; i++) {
			poblacion.add(new Cromosoma(tipoCreacion));
		}
		bloating();
		Utils.sortSample(poblacion);
		mejorHistorico = poblacion.get(0);
		cogerDatos();
	}

	private void bloating() {
		int sumProf = 0;
		for (Cromosoma cr : poblacion) {
			sumProf += cr.getGenotipo().getProfundidad();
		}
		bloating.control(sumProf / tamPob, mediaFitness, poblacion, rand, probMuerte, actGen, numGen);
	}

	private void cogerDatos() {
		double sumaFit = 0;
		for (Cromosoma cr : poblacion) {
			sumaFit += cr.getFitness();
		}
		mediaFitness = sumaFit / tamPob;
		mediaFitnessArray[actGen] = mediaFitness;

		poblacion = Utils.sortSample(poblacion);
		mejorGeneracion = poblacion.get(0);
		Cromosoma aux = new Cromosoma(poblacion.get(0));
		if (aux.getFitness() > mejorGeneracion.getFitness()) {
			mejorHistorico = aux;
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
		for(Cromosoma cr : poblacion) 
			cr = this.mutacion.mutar(cr, rand, probMutacion);
	}

	public void run() {
		iniPoblacion();

		do {
			extraerElite();
			seleccion();
			cruce();
			mutacion();
			bloating();
			meterElite();
			cogerDatos();
			System.out.println("[Generacion]: " + actGen + " || [MejorGeneracion]: " + mejorGeneracion.getFitness()
					+ " || [MediaFitness]: " + mediaFitness);
			++actGen;
		} while (actGen < numGen);
	}

	// Faltan Getters y setters
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

	public Cromosoma getMejorHistorico() {
		return mejorHistorico;
	}
}
