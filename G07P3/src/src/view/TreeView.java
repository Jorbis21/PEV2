package src.view;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import src.TableroGlobal;
import src.arbol.Arbol;
import src.cromosoma.Cromosoma;

public class TreeView {
    
    private Cromosoma cromosoma;
    private Arbol arbol;
   
    /*
    public TreeView(TableroGlobal tab) {
        this.cromosoma = new Cromosoma(0, tab);
    }
    */
    public void tutorial(){
        Graph graph = new SingleGraph("Tutorial 1");

		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addEdge("AB", "A", "B");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "C", "A");

		graph.display();
    }

    public Graph createTree(){
        Graph graph = new SingleGraph("Tutorial 1");
        arbol = cromosoma.getArbol();
        graph = arbol.createGraphFromArbol();
        
        return graph;
    }
}
