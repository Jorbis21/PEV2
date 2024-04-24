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
    
    private void iniFuncCr(int profundidad, Nodo aux, Nodo ant) {
    	setProfundidad(profundidad);
    	aux.setAnt(ant);
        aux.setValor(Cromosoma.funciones[rand.nextInt(Cromosoma.funciones.length)]);
        if(aux.getValor() == "SUMA" || aux.getValor() == "PROGN")
        	aux.setNumhijos(2);
        else
        	aux.setNumhijos(1);
        if(aux.getNumhijos() == 2) {
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
    private void iniFuncC(int profundidad, Nodo aux, Nodo ant) {
    	setProfundidad(profundidad);
    	aux.setAnt(ant);
        aux.setValor(Cromosoma.funciones[rand.nextInt(Cromosoma.funciones.length)]);
        if(aux.getValor() == "SUMA" || aux.getValor() == "PROGN")
        	aux.setNumhijos(2);
        else
        	aux.setNumhijos(1);
        if(aux.getNumhijos() == 2) {
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
    
    private void iniTerm(int profundidad, Nodo aux, Nodo ant) {
    	setProfundidad(profundidad);
        aux.setValor(Cromosoma.terminales[rand.nextInt(Cromosoma.terminales.length)]);
        if(aux.getValor() == "CONSTANTE") {
        	aux.setNumval(new Pair(rand.nextInt(8), rand.nextInt(Cromosoma.dimension)));
        }
        aux.setAnt(ant);
    }

    public Nodo inicializacionCompleta(int profundidad, Nodo ant){
    	Nodo aux = new Nodo();
        if(profundidad < max_prof){
        	iniFuncC(profundidad, aux, ant);
        }
        else if(profundidad == (max_prof - 1)) {
        	iniTerm(profundidad, aux, ant);
        }
        return aux;
    }
  
    public Nodo inicializacionCreciente(int profundidad, Nodo ant){
    	int numHijos = 0, aleat = -1;
    	String valor = null; Nodo aux = new Nodo();
        if(profundidad < max_prof){
            if(profundidad == 0) {
            	iniFuncCr(profundidad, aux, ant);
            }
            else {
            	if(rand.nextDouble() >= 0.5) {
            		iniFuncCr(profundidad, aux, ant);
            	}
            	else {
            		iniTerm(profundidad, aux, ant);
            	}
            }
        }
        else {
        	iniTerm(profundidad, aux, ant);
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

