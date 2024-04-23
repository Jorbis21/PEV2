package src.bloating;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;

public class Tarpeian implements IBloating{

	@Override
	public void control(int profundidadMedia, int mediaFitness, ArrayList<Cromosoma> poblacion, Random rand, double probMuerte, int actGen, int numGen) {
		for(Cromosoma cr : poblacion) {
			if(cr.getGenotipo().getProfundidad() > profundidadMedia && rand.nextDouble() < probMuerte) {
				cr.setFitness((int) (mediaFitness + Math.sqrt(actGen/numGen)*mediaFitness));
			}
		}
	}
	
	public String toString() {
		return "Tarpeian";
	}

}