package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.Utils;

public class SeleccionTorneoPro implements ISeleccion {

  final private int tamTournament = 3;

  @Override
  public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random random) {
    ArrayList<Individuo> selected = new ArrayList<>();
    ArrayList<Individuo> sortedTournament = new ArrayList<>();

    for (int i = 0; i < poblacion.size(); i++) {
      // cogemos 3 individuos aleatorios
      ArrayList<Individuo> tournament = new ArrayList<>();
      for (int j = 0; j < tamTournament; j++) {
        int randomIndex = random.nextInt(poblacion.size());
        tournament.add(poblacion.get(randomIndex));
      }

      // Ordenamos y cogemos el mejor
      Individuo winner = null;
      sortedTournament = Utils.sortSample(tournament);
      if (random.nextDouble() < 0.75)
        winner = sortedTournament.get(0);
      else
        winner = sortedTournament.get(tamTournament - 1);
        
      selected.add(winner);
    }
    return selected;
  }

  @Override
  public String toString() {
    return "Seleccion Torneo Probabilista";
  }

}
