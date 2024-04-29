package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;

public interface IMutacion {
    public Individuo mutar(Individuo Individuo, Random rand, double probMut);
}
