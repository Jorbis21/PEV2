package src.view;

import src.cromosoma.Cromosoma;

import org.graphstream.graph.*;

public class TreeView {
    
    private Cromosoma cromosoma;

    public void showTree(Cromosoma crom){
    	Graph graph = crom.getArbol().getTreeView();

		graph.display();
    }

    
}
