package src.seleccion;

import java.util.ArrayList;
import java.util.Random;
import src.cromosoma.Cromosoma;
import src.utils.Utils;


public class SeleccionTorneoDet implements ISeleccion{
  private int tamTournament = 3;

  @Override
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random random) {
    ArrayList<Cromosoma> selection = new ArrayList<Cromosoma>();

    while(selection.size() < poblacion.size()) {
			ArrayList<Cromosoma> tournament = new ArrayList<Cromosoma>();
			for(int i = 0; i < tamTournament; ++i)
				tournament.add(poblacion.get(random.nextInt(poblacion.size()))); // cogemos k Cromosomas aleatorios de la poblacion

			tournament = Utils.sortSample(tournament);
			
			selection.add(tournament.get(0));
		}
		return selection;
  }

	@Override
	public String toString() {
		return "Seleccion por Torneo Determinista";
	}
}