package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.Utils;

public class SeleccionTorneoDet implements ISeleccion{

  final private int tamTournament = 3;

  @Override
  public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random random) {
    ArrayList<Individuo> selected = new ArrayList<>();

    for (int i = 0; i < poblacion.size(); i++) {
      
    }

    return selected;
  }

  @Override
  public String toString() {
    return "Seleccion Torneo Determinista";
  }
  
}
