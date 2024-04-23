package src.seleccion;

import java.util.ArrayList;
import java.util.Random;
import src.cromosoma.Cromosoma;
import src.utils.Utils;

public class SeleccionTorneoPro implements ISeleccion {

  final private int tamTournament = 3;

  @Override
  public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random random) {
    ArrayList<Cromosoma> selection = new ArrayList<Cromosoma>();

    while (selection.size() < poblacion.size()) {
      ArrayList<Cromosoma> tournament = new ArrayList<Cromosoma>();
      for (int i = 0; i < tamTournament; ++i)
        tournament.add(poblacion.get(random.nextInt(poblacion.size()))); 
      tournament = Utils.sortSample(tournament);
      // Hacemos la parte probabilista
      selection.add(random.nextDouble() > 0.3 ? new Cromosoma(tournament.get(0)) : new Cromosoma(tournament.get(tournament.size() - 1)));
    }

    return selection;
  }

  @Override
  public String toString() {
    return "Seleccion por Torneo Probabilista";
  }
}