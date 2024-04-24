package src.mutacion;

import java.util.Random;

import src.TableroGlobal;
import src.cromosoma.Cromosoma;

public interface IMutacion {
    public Cromosoma mutar(Cromosoma programa, Random rand, double probMut, TableroGlobal tab);
}
