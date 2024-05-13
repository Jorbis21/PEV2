package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public class SeleccionEstocasticaUniversal implements ISeleccion{

  @Override
  public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random random) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'select'");
  }
  
  @Override
  public String toString() {
    return "Seleccion Estocastica Universal";
  }
}
