package src.arbol;

import java.util.Random;

import src.cromosoma.Cromosoma;
import src.utils.Pair;

public class Arbol {
	public static int max_prof;
	
	private Nodo raiz;
    private int profundidad;
    Random rand = new Random();

    public Arbol(int tipoCreacion) {
    	switch (tipoCreacion) {
		case 0:
			inicializacionCreciente(0, null);
			break;
		case 1:
			inicializacionCompleta(0, null);
			break;
		case 2:
			if (rand.nextDouble() >= 0.5)
				inicializacionCreciente(0, null);
			else
				inicializacionCompleta(0, null);
			break;
	}
    }
    
    public Arbol(int tipoCreacion, Nodo act) {
    	switch (tipoCreacion) {
		case 0:
			inicializacionCreciente(0, act);
			break;
		case 1:
			inicializacionCompleta(0, act);
			break;
		case 2:
			if (rand.nextDouble() >= 0.5)
				inicializacionCreciente(0, act);
			else
				inicializacionCompleta(0, act);
			break;
	}
    }
    public void calcProf(Nodo act, int prof) {
    	if(act.esHoja()) {
    		if(prof > profundidad) {
    			setProfundidad(prof);
    		}
    	}
    	else {
    		if(prof > profundidad) {
    			setProfundidad(prof);
    		}
    		if(act.getNumhijos() == 1) {
    			calcProf(act.getIzq(), prof+1);
    		}
    		else {
    			calcProf(act.getIzq(), prof+1);
    			calcProf(act.getDer(), prof+1);
    		}
    	}
    }
    public Nodo getHojaRand(Nodo act) {
    	if(act.esHoja()) {
    		return act;
    	}
    	else {
    		if(act.getNumhijos() == 1) {
				return getHojaRand(act.getIzq());
			}
			else {
				if(rand.nextDouble() > 0.5) {
    				return getHojaRand(act.getIzq());
    			}
    			else {
    				return getHojaRand(act.getDer());
    			}
			}
    	}
    }
    
    public Nodo getNodoRand(Nodo act, int prof) {
    	if(prof == profundidad - 1) {
    		return act;
    	}
    	else {
    		if(prof == 0) {
    			if(act.getNumhijos() == 1) {
    				return getNodoRand(act.getIzq(), prof + 1);
    			}
    			else {
    				if(rand.nextDouble() > 0.5) {
        				return getNodoRand(act.getIzq(), prof + 1);
        			}
        			else {
        				return getNodoRand(act.getDer(), prof + 1);
        			}
    			}
    		}
    		else {
    			if(rand.nextDouble() > 0.5) {
        			return act;
        		}
    			else {
    				if(act.getNumhijos() == 1) {
        				return getNodoRand(act.getIzq(), prof + 1);
        			}
        			else {
        				if(rand.nextDouble() > 0.5) {
            				return getNodoRand(act.getIzq(), prof + 1);
            			}
            			else {
            				return getNodoRand(act.getDer(), prof + 1);
            			}
        			}
    			}
    		}
    	}
    }
    
    public String toString(Nodo act) {
    	if(act.esHoja()) {
    		if(act.getValor() == "CONSTANTE") {
    			return act.getNumval().toString();
    		}
    		else {
    			return "(" + act.getValor() + ")";
    		}
    	}
    	else {
    		if(act.getNumhijos() == 1) {
    			return "(" + act.getValor() + toString(act.getIzq()) + ")";
    		}
    		else {
    			String hijoIzq, hijoDer;
    			hijoIzq = toString(act.getIzq());
    			hijoDer = toString(act.getDer());
    			return "(" + act.getValor() + hijoIzq + hijoDer + ")";
    		}
    	}
    }
    
    private void iniFuncCr(int numHijos, int aleat, int profundidad, String valor, Nodo aux, Nodo ant) {
    	setProfundidad(profundidad);
        numHijos = 0; aleat = rand.nextInt(Cromosoma.funciones.length);
        valor = Cromosoma.funciones[aleat];
        if(valor == "SUMA" || valor == "PROGN")
        	numHijos = 2;
        else
        	numHijos = 1;
        aux = new Nodo(valor, ant, null, null, numHijos);
        if(numHijos == 2) {
        	aux.setIzq(inicializacionCreciente(profundidad+1, aux));
        	aux.setDer(inicializacionCreciente(profundidad+1, aux));
        }
        else {
        	aux.setIzq(inicializacionCreciente(profundidad+1, aux));
        }
        if(aux.esRaiz()) {
        	this.raiz = aux;
        }
    }
    private void iniFuncC(int numHijos, int aleat, int profundidad, String valor, Nodo aux, Nodo ant) {
    	setProfundidad(profundidad);
        numHijos = 0; aleat = rand.nextInt(Cromosoma.funciones.length);
        valor = Cromosoma.funciones[aleat];
        if(valor == "SUMA" || valor == "PROGN")
        	numHijos = 2;
        else
        	numHijos = 1;
        aux = new Nodo(valor, ant, null, null, numHijos);
        if(numHijos == 2) {
        	aux.setIzq(inicializacionCompleta(profundidad+1, aux));
        	aux.setDer(inicializacionCompleta(profundidad+1, aux));
        }
        else {
        	aux.setIzq(inicializacionCompleta(profundidad+1, aux));
        }
        if(aux.esRaiz()) {
        	this.raiz = aux;
        }
    }
    
    private void iniTerm(int numHijos, int aleat, int profundidad, String valor, Nodo aux, Nodo ant) {
    	setProfundidad(profundidad);
        numHijos = 0; aleat = rand.nextInt(Cromosoma.terminales.length);
        valor = Cromosoma.terminales[aleat];
        Pair numVal = new Pair();
        aux = new Nodo(valor, ant, null, null, numHijos);
        if(valor == "CONSTANTE") {
        	numVal = new Pair(rand.nextInt(8), rand.nextInt(Cromosoma.dimension));
        }
        aux.setNumval(numVal);
    }

    public Nodo inicializacionCompleta(int profundidad, Nodo ant){
    	int numHijos = 0, aleat = -1;
    	String valor = null; Nodo aux = new Nodo();
        if(profundidad < max_prof){
        	iniFuncC(numHijos, aleat, profundidad, valor, aux, ant);
        }
        else if(profundidad == (max_prof - 1)) {
        	iniTerm(numHijos, aleat, profundidad, valor, aux, ant);
        }
        return aux;
    }
  
    public Nodo inicializacionCreciente(int profundidad, Nodo ant){
    	int numHijos = 0, aleat = -1;
    	String valor = null; Nodo aux = new Nodo();
        if(profundidad < max_prof){
            if(profundidad == 0) {
            	iniFuncCr(numHijos, aleat, profundidad, valor, aux, ant);
            }
            else {
            	if(rand.nextDouble() >= 0.5) {
            		iniFuncCr(numHijos, aleat, profundidad, valor, aux, ant);
            	}
            	else {
            		iniTerm(numHijos, aleat, profundidad, valor, aux, ant);
            	}
            }
        }
        else {
        	iniTerm(numHijos, aleat, profundidad, valor, aux, ant);
        }
      return aux;
    }

	public void swapSubtrees(Nodo nodo1, Nodo nodo2) {
		Nodo antN2 = nodo2.getAnt();
		nodo2.setAnt(nodo1.getAnt());
		if(nodo1.getAnt().getIzq() == nodo1) {
			nodo1.getAnt().setIzq(nodo2);
		}
		else {
			nodo1.getAnt().setDer(nodo2);
		}

		nodo1.setAnt(antN2);
		if(antN2.getIzq() == nodo2) {
			antN2.setIzq(nodo1);
		}
		else {
			antN2.setDer(nodo1);
		}
	}

    /// Getters & Setters ---------------------------------------------------------
    
    public Nodo getRaiz() {
    	return raiz;
    }

    public int getMax_prof() {
        return max_prof;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }
}

