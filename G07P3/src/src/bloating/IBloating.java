package src.bloating;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;

public interface IBloating {
	public void control(int profundidadMedia, int mediaFitness, ArrayList<Cromosoma> poblacion, Random rand, double probMuerte, int actGen, int numGen);
}
