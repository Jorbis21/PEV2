package src.utils;

import java.util.ArrayList;
import java.util.Collections;

import src.cromosoma.Cromosoma;

public class Utils {

  	/*
	 * Algoritmo de busqueda binaria para encontrar el intervalo en el que se encuentra el valor value
	 * 
	 * qi-1 < value < qi
	 * 
	 * @param value
	 * @param array
	 * @param start
	 * @param end
	 * 
	 * @return int
	 */
	static int findIntervalAux(ArrayList<Integer> array, int low, int high, Double value)
	{
		if (low == high - 1)
			return low;

		// Find the middle index
		int mid = (high + low) / 2;

		if((double)(array.get(mid)) == value)
			return mid;
		else if(value < array.get(mid))
			return findIntervalAux(array, low, mid, value);
		else
			return findIntervalAux(array, mid, high, value);
	}
	
	/*
	 * Algoritmo de busqueda binaria para encontrar el intervalo en el que se encuentra el valor value
	 * 
	 * qi-1 < value < qi
	 * 
	 * @param value
	 * @param array
	 * 
	 * @return int
	 */
	public static int findInterval(Double value, ArrayList<Integer> array) {
		int low = 0, high = array.size();

		return findIntervalAux(array, low, high, value);
	}
  

	public static ArrayList<Cromosoma> sortSample(ArrayList<Cromosoma> array){
		// order from largest to smallest
    Collections.sort(array, (a, b) -> Integer.compare(b.getFitness(), a.getFitness()));
		return array;
	}
}