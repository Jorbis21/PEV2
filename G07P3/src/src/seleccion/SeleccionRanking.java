package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.cromosoma.Cromosoma;
import src.utils.Utils;

public class SeleccionRanking implements ISeleccion{
    double BETA = 2.0;
    @Override
    public ArrayList<Cromosoma> select(ArrayList<Cromosoma> poblacion, Random rand) {
        ArrayList<Cromosoma> selection = new ArrayList<Cromosoma>();
        ArrayList<Cromosoma> copy = poblacion;

        selection = Utils.sortSample(selection);

        ArrayList<Integer> fit = new ArrayList<Integer>();

        fit = group(copy);
        rankingPunctuation(fit);

        for(int i = 0; i < copy.size(); i++){
            int index = rand.nextInt(fit.size());
            selection.add(new Cromosoma(copy.get(index)));
        }

        return selection;
    }

    private void rankingPunctuation(ArrayList<Integer> fit){
        int accPunc = 0;
        for(int i = 0; i < fit.size(); ++i){
            double probOfIth = (double) i / fit.size();
            probOfIth *= 2*(BETA-1);
            probOfIth = BETA - probOfIth;
            probOfIth = (double) probOfIth * ((double) 1 / fit.size());
            fit.set(i, accPunc);
            accPunc += probOfIth;
        }
    }
    @Override
    public String toString(){
        return "Seleccion por Ranking";
    }
}